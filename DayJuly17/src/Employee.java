
public class Employee extends Object{

	public enum Gender {
		MALE, FEMALE, OTHER
	}

	private int id;
	private String name;
	private int age;
	private Gender gender;
	
 
	
	//create a default constructor 
	public Employee(int id, String name, int age, Employee.Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public Gender getGender() {
		return gender;
	}



	public void setGender(Gender gender) {
		this.gender = gender;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", "
				+ "name=" + name 
				+ ", age=" + age 
				+ ", gender=" 
				+ gender + "]";
	
	}
	
	
	
}
