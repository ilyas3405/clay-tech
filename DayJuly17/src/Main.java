import java.util.ArrayList;
import java.util.List;

 

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = new String("Sunday");
		String s2= new String("Monday");
		String s3= new String("Tuesday");
		String s4= new String("Wednesday");
		
		ArrayList<String> listofstrings = new ArrayList<String>();
		
		listofstrings.add(s1);
		
	
		Employee emp1 = new Employee(1, "John Doe", 20, Employee.Gender.MALE);
		Employee emp2 = new Employee(2, "Abdul Aziz", 35, Employee.Gender.MALE);
		Employee emp3 = new Employee(3, "Alex Johnson", 25, Employee.Gender.MALE);
		Employee emp4 = new Employee(4, "Peter John", 50, Employee.Gender.MALE);
     	Employee emp5 = new Employee(5, "Lesley Brits", 55, Employee.Gender.FEMALE);
		
		ArrayList<Employee> employees = new ArrayList<>();
		
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		employees.add(emp4);
		employees.add(new Employee(5, "Lesley Brits", 55, Employee.Gender.FEMALE) );
		
		
		employees.addAll(List.of(emp1, emp2, emp3,emp4, emp5));
		
		System.out.println("There are " + employees.size() +" people in the object");
		
		 //Using simple for loop
		int sizeoflist = employees.size();
		
		for(int i =0; i < sizeoflist; i++) {
			System.out.println(employees.get(i).toString());	
		}
		
		System.out.println("----------- Below printing using a enhanced for loop");
		
		//Using enhanced for loop
		for (Employee emp : employees ) {
			System.out.println(emp.toString());	
		}
		
		System.out.println("----------- Below printing using a enhanced for loop");
		
		
		//lambda expression 
	//	employees.forEach((e) -> System.out.println("Value in e object is " + e));
		
		employees.stream()
			//	.filter(e -> e.getAge() == 20)
				.map(i -> i.getAge() + 10)
				.forEach(System.out::println);  //Method Reference
		 
		
		// Return only one employee by name Peter John
		Employee.Gender searchField = Employee.Gender.MALE;
		
		for (Employee emp : employees ) {
		 
			
		//	System.out.println(emp.getName());
			if (emp.getGender().equals(searchField)) {
				System.out.println("Found: " + emp.toString());	
			}
			
		}
		
	}

}
