#include <HX711_ADC.h>
#include <SPI.h>
#include <Wire.h>
#include <Adafruit_ADS1X15.h>
//#include <Adafruit_ADS1015.h>

Adafruit_ADS1115 ads;

#include <OneWire.h>
#include <DallasTemperature.h>

#define ONE_WIRE_BUS 2  // Dallas temprature sensor=
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);

float current = 0;
int16_t adc0, adc1;
float volts0, volts1;
float currentraw=0;
float average = 0;

/////////////////////////////
//pins:
const int HX711_dout = 8; //mcu > HX711 dout pin
const int HX711_sck = 9; //mcu > HX711 sck pin
const int curr1 = 2;
//const int analogInPin = A2;

const int analogInPin1 = A1;
const int analogInPin2 = A0;

//yellow cable to A0 that is Current
//white cable to A1 that is voltage
int sensorValue = 0;
int sensorValue1 = 0;
int sensorValue2 = 0;
float c = 0;
float v = 0;
float i=0;
float temprature=0;

//  *******  RPM ********
// IR sensor on port D3
float REV = 0;
int RPM_VALUE;
int PREVIOUS = 0;
int TIME;
void INTERRUPT()
{
  REV++;
}


///////////////////  Throttle Percentage   //////////////////////
#define CH3 0
int readChannel(int channelInput, int minLimit, int maxLimit, int defaultValue){
  int ch = pulseIn(channelInput, HIGH, 30000);
  if (ch < 100) return defaultValue;
  return map(ch, 1000, 2000, minLimit, maxLimit);
}
int ch3Value;

////////////////////////////////////////////////////////////////
HX711_ADC LoadCell(HX711_dout, HX711_sck);

const int calVal_calVal_eepromAdress = 0;
unsigned long t = 0;

void setup() {

  Serial.begin(9600); delay(10);
  pinMode(CH3, INPUT);  //   Throttle percentage on channel 3
  sensors.begin();   //  Dallas temprature sensor
  ads.begin();
//  ** RPM Code below *******
attachInterrupt(1, INTERRUPT, RISING);

  float calibrationValue; // calibration value
  //calibrationValue = 100; // 415  **Sensotech   395 increased here 150g more here
  calibrationValue = 108; // 415  **Sensotech   395 increased here low here 103 (101 means high here)( 102 means high here)(102.5 means little more here)
// 103 also high 

  LoadCell.begin();
  //LoadCell.setReverseOutput();
  unsigned long stabilizingtime = 100; // tare preciscion can be improved by adding a few seconds of stabilizing time
  boolean _tare = true; //set this to false if you don't want tare to be performed in the next step
  LoadCell.start(stabilizingtime, _tare);
  if (LoadCell.getTareTimeoutFlag()) {
    Serial.println("Timeout, check MCU>HX711 wiring and pin designations");
  }
  else {
    LoadCell.setCalFactor(calibrationValue); // set calibration factor (float)

  }

}

void loop() {
  static boolean newDataReady = 0;
  const int serialPrintInterval = 500; //increase value to slow down serial print activity

sensorValue1= analogRead(analogInPin1);
sensorValue2= analogRead(analogInPin2);


adc0 = ads.readADC_SingleEnded(0);
adc1 = ads.readADC_SingleEnded(1);

volts0 = ads.computeVolts(adc0);
volts1 = ads.computeVolts(adc1);

//Serial.println((volts0 - 0.019) * 18.182);
//Serial.println((volts1 - 0.015) * 36.364);
v = ((volts0 - 0.015) * 18.182);
c = ((volts1 - 0.033) * 36.364);
 // currentVal = 0;
 // for(int i = 0; i <= 10; i++){
   average = average + (analogRead(analogInPin1) * (5.0 / 1024)) * 36.364;
//}


///////////////////////////////////////////////////   dallas temprature    ////////////////////

  sensors.requestTemperatures(); // Send the command to get temperatures
  float tempC = sensors.getTempCByIndex(0);

  ////////////////////////////////////////////////////////////////////////////////////////////

  // check for new data/start next conversion:
  if (LoadCell.update()) newDataReady = true;

  // get smoothed value from the dataset:
  if (newDataReady) {
    if (millis() > t + serialPrintInterval) {
      double i = LoadCell.getData() * 10;
//      Serial.println(i);
      newDataReady = 0;
      t = millis();


//**** Below code for RPM  *******
 
 //delay(100);
  detachInterrupt(0);                   
  TIME = millis() - PREVIOUS;          
  RPM_VALUE = (REV/TIME) * 60000;       
  PREVIOUS = millis();                  
  REV = 0;
  attachInterrupt(1, INTERRUPT, RISING);
  // *****************
//Serial.println(String(v)+","+String(c)+","+int(RPM_VALUE)+","+int(i/10)+","+String(tempC) +","+String(tempC));
Serial.println(String(v)+","+String(c)+","+int(RPM_VALUE)+","+int(i/10)+","+String(tempC) +","+String(readChannel(CH3, 0, 100, 100)));
}

  }
  
  }