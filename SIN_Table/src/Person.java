/**
 * Person.java
 * 
 * This Object class represents the person to be assigned to the map.
 * Contains SIN, name, and key, which is just a copy of the SIN. Provides getter and setter methods
 * 
 */

public class Person {
	private int SIN;
	private String name;
	private int key;
	
	public Person(int new_SIN, String new_name) {
		SIN = new_SIN;
		name = new_name;
		key = new_SIN;
	}
	
	// setter and getter for SIN
	public void setSIN(int new_SIN) {
		SIN = new_SIN;
		key = new_SIN;
	}
	public int getSIN() {
		return SIN;
	}
	
	// setter and getter for name
	public void setName(String new_name) {
		name = new_name;
	}
	public String getName() {
		return name;
	}
	
	// getter for key
	public int getKey() {
		return key;
	}
	
	public String toString() {
		return SIN + ": " + name;
	}

}
