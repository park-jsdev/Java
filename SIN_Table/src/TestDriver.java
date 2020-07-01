import java.util.Scanner;

/**
 * TestDriver.java
 * 
 * @author Junsoo Park
 * This is the test driver for the DoubleHashing function.
 * Uses the extraction method with division using the last four digits of the SIN.
 * Uses open addressing with double hashing using an extraction method on the first three digits of the SSN. 
 * 
 */

public class TestDriver {
	
	public static void main(String[] args) {
		
		int number;
		String name;
		String choice = "Y";
		
		// create object for doubleHashing
		DoubleHashing doubleHashing = new DoubleHashing();
		Scanner input = new Scanner(System.in);
		boolean inputIsValid = true;
		boolean innerValid = false;
		
		// This is input validation to prevent numbers outside range
		while (inputIsValid) {
			innerValid = false;
			System.out.println("Enter a 9 digit social security number: ");
			
			// Input validation for integer
			while (!input.hasNextInt()) {
			System.out.println("Input is not an integer in range.");
			System.out.println("Enter a 9 digit social security number: ");
			input.nextLine();
			}
			
			// Input validation for integer in range
			number = input.nextInt();
			 if (number >= 100000000 && number <= 999999999) {
				inputIsValid = true;
				innerValid = true;
			 } else {
				 input.nextLine();
			 }
				 while (innerValid) {
					 innerValid = false;
				System.out.println("Enter a name: ");
				name = input.next();
				Person people = new Person(number, name);
				doubleHashing.put(people.getKey(), people);
				System.out.println("Repeat? (Y/N)");
				choice = input.next();
				System.out.println();
				
				// Checks if user repeating the data entry
				if (choice.equalsIgnoreCase("Y")) {
					inputIsValid = true;
				} else {
					inputIsValid = false;
				}
			}
		}
		System.out.println("Displaying hash table:");
		System.out.println("SSN:         Name:");
		System.out.println("----------------------------");
		System.out.println(doubleHashing.toString());
	}
}

