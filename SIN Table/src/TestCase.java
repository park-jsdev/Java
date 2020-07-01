
public class TestCase {

	public static void main(String[] args) {
		
		// Create a test case for n > 31
		DoubleHashing test = new DoubleHashing(); // testing random keys
		DoubleHashing test2 = new DoubleHashing(); // testing constraints, dont test constraints because they are caught in the driver
		DoubleHashing test3 = new DoubleHashing(); // testing dynamic resizing
		
		// Test Case 1
		int test_number;
		String test_name = "A";
		int random;
		
		random = 100000000 + (int)(Math.random() * ((999999999 - 100000000) + 1));
		
		for (int i=0;i<30;i++) {
			random = 100000000 + (int)(Math.random() * ((999999999 - 100000000) + 1));
			test_number = random;
			test_name += "a";
			
			Person test_person = new Person(test_number, test_name);
			test.put(test_person.getKey(), test_person);
		}
		
		System.out.println("Displaying hash table:");
		System.out.println("SSN:         Name:");
		System.out.println("----------------------------");
		System.out.println(test.toString());

		
		// Test case 3
		int test_number2;
		String test_name2 = "C";
		int random2;
		
		random = 100000000 + (int)(Math.random() * ((999999999 - 100000000) + 1));
		
		for (int i=0;i<1000;i++) {
			random2 = 100000000 + (int)(Math.random() * ((999999999 - 100000000) + 1));
			test_number2 = random2;
			test_name2 += "a";
			
			Person test_person = new Person(test_number2, test_name2);
			test3.put(test_person.getKey(), test_person);
		}
		
		System.out.println("Testing dynamic resizing:");
		System.out.println("SSN:         Name:");
		System.out.println("----------------------------");
		System.out.println(test3.toString());
		
	}

}
