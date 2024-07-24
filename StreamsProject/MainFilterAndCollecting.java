import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//below class to encapsulate the internal features of object.
record Employee(String name, Integer age) {}

//animal -> 
// - cat and dog.

//class Employee {
//	
//	private String name;
//	private int age;
//	
//	
//	public Employee(String name, int age) {
//		super();
//		this.name = name;
//		this.age = age;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getAge() {
//		return age;
//	}
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	@Override
//	public String toString() {
//		return "Employee [name=" + name + ", age=" + age + "]";
//	}
//	
//	
//	 
//}

public class MainFilterAndCollecting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

 	
	//	List<String> names = Arrays.asList("Alice","Bob","Ann","Mike","Amy");
		
		List<Employee> namesEmployee = Arrays.asList(new Employee("Alice",25),
											new Employee("Bob",23),
											new Employee("Ann",55),
											new Employee("Alice",65),
											new Employee("Amy",85));
									
		
		 //Filtering and Collecting
		//but breaking the process and create a Stream object using the Stream interface reference 	
		Stream<Employee> mystream = namesEmployee.stream();
		
		List<Employee> result1 = mystream
				.filter(singleelement -> singleelement.name().startsWith("a") || singleelement.name().startsWith("A"))
				.collect(Collectors.toList());;
	
		System.out.println(result1);
 

		List<String> names = Arrays.asList("Alice","Bob","Ann","Mike","Amy");
		//Mapping and Collecting
		List<String> result2 = names.stream()
						//	.map(String::toLowerCase)
							.map(e -> e.toUpperCase())  //using lambda expression (arrow)
							.map(String::toUpperCase)  //Method reference 
							.collect(Collectors.toList());
		System.out.println(result2);

		
		//Sorting and Collecting
		List<String> result$ = names.stream()
								.sorted((a,b) -> a.compareTo(b))
								//.sorted()
								.collect(Collectors.toList());
		System.out.println("Perform Sort and Collecting Operation on the list :: " + result$);
		
		
		// Reducing Operation . 
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
		
		int sum = numbers.stream()
					.reduce(0, Integer::sum);
		System.out.println("Performing reeducing operation :: " +  sum);
		
		
		//5. Grouping
		Map<Integer,List<String>> result4 = names.stream()
											.collect(Collectors.groupingBy(String::length));
		
		System.out.println("Group by Operation :: " + result4);	
		
		List<List<String>> listofLists = Arrays.asList(
				
							Arrays.asList("hyderabad","delhi","mumbai"),
							Arrays.asList("chicago","houston","college station"),
							Arrays.asList("dubai","abu dhabi","ajman")
									);
		
		List<String> singlelistofcity = listofLists.stream()
								.flatMap(List::stream)
								.collect(Collectors.toList());
	
		System.out.println(singlelistofcity);	
		
	
	}

}
