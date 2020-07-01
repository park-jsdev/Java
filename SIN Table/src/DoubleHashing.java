/**
 * DoubleHashing.java
 * 
 * This class contains the constructor for a dynamically resizable hash table with an initial size of 31
 * and a load factor of 0.80.
 * It contains a hashing function which uses double hashing, which uses the extraction method with division using the
 * last 4 digits of the SIN for the first hash and the extraction method with division on the first 3 digits
 * of the SIN for the second hash function.
 * 
 */


public class DoubleHashing {
	private final int TABLE_SIZE = 31;
	private final double LOAD_FACTOR = 0.80;

	// Hash table to store object Person
	private Person[] hash_table;
	private int people_count;
	private int array_size;
	private double load_factor;
	private int hash_count = 1;
	private int hash_code;

	// Default constructor for the first_hash table created with default size 31 and load factor 0.80
	public DoubleHashing() {
	hash_table = new Person[TABLE_SIZE];
	load_factor = LOAD_FACTOR;
	array_size = TABLE_SIZE;
	people_count = 0;
	}

	// Parameterized constructor, takes a new size, used for dynamically resizing the table
	public DoubleHashing(int new_size) {
		if (new_size > 0) {
			hash_table = new Person[new_size];
			array_size = new_size;
		} else {
			hash_table = new Person[TABLE_SIZE];
			array_size = TABLE_SIZE;
		}
		load_factor = LOAD_FACTOR;
		people_count = 0;
	}
	

	public void put(Integer key, Person people) {
		// verifies if key is already in the table 
		if (key == null || containsKey(key)) { // error
			System.out.println("SIN already exists.");
			return;
		}
		if (people_count >= (load_factor * array_size)) {
			rehash();
		}
		// Get the internal index from the first hash function
		hash_code = hashCode(key);
		hash_count = 1;
		// if the cell is filled, use double hashing to solve collision
		while (collisionExistsIn(hash_code)) {
			// the double hashing formula is (hash1(key) + i*hash2(key)) % n
			hash_code = doubleHashCode(key);
		}
		hash_table[hash_code] = people;
		people_count++;
	}


	// Remove method which removes and return the value of the specified key
	public Person remove(Integer key) {
		// Use double hashing to obtain internal index
		hash_code = hashCode(key);
		hash_count = 1;
		// loop the hash table
		while (collisionExistsIn(hash_code)) {
			if (keyAtHashCodeMatches(key)) {
			// Stores the value in a temp pointer
			Person temp = hash_table[hash_code];
			hash_table[hash_code] = null;
			people_count--;
			return temp;
			}
		hash_code = doubleHashCode(key);

		}
		return null;
	}

//	 Method that returns true if the key exists in the table
	public boolean containsKey(Integer key) {
		// double hashing functions to obtain index
		hash_code = hashCode(key);
		hash_count = 1;

		while (collisionExistsIn(hash_code)) {
			if (keyAtHashCodeMatches(key)) {
				return true;
			} else {
			// this is the hash loop
		    hash_code = doubleHashCode(key);
			}
		}
		return false;
	}
	
	public boolean keyAtHashCodeMatches(Integer key) {
		return hash_table[hash_code].getKey() == key;
	}

	// Method to get the value of Person object corresponding to the key
	public Person getValue(Integer key) {
		// double hashing to get index
		hash_code = hashCode(key);
		hash_count = 1;
		// hash loop
		while (collisionExistsIn(hash_code)) {
			if (keyAtHashCodeMatches(key)){
				return hash_table[hash_code];
			}
		    hash_code = doubleHashCode(key);
			}
		return null;
	}	
	
	// method to return size
	public int size() {
		return people_count;
	}

	// method to check if hash table is empty
	public boolean isEmpty() {
		return (people_count == 0);
	}

	// First hash function method that returns the initial index or hashcode
	// Uses extraction method with division using the last four digits of the SSN using modulo
	private int firstHashFunction(Integer key) {
		return key % 10000;
	}
	
	// Second hash function method that returns the overflow hashcode in case of collisions
	// Uses extraction method on the first three digits of the SSN using division
	private int secondHashFunction(Integer key) {
		return key / 1000000;
	}
	
	private int hashCode(Integer key) {
		int hash = firstHashFunction(key);
		return Math.abs(hash) % array_size; 
	}
	
	// Method to return the hash code in case of double hashing
	// double hashing formula is (hash1(key) + i*hash2(key)) % n
	private int doubleHashCode(Integer key) {
		int double_hash = firstHashFunction(key) + hash_count*secondHashFunction(key);
		hash_count++; // iterate the hash counter for the double hash loop formula
		return Math.abs(double_hash) % array_size; 
	}
	
	// Method to determine if there is a collision at the key
	private boolean collisionExistsIn(Integer key) {
		return hash_table[key] != null;
	}
	
	// Method creates a new hash table and redistributes elements
	private void rehash() {
		// Create a new hash table with double the size of the previous table size
		Person[] new_table = new Person[array_size*2];
		int old_size = array_size;
		array_size = 2 * array_size;
		// redistribute elements in the new table
		for (int i=0;i<old_size;i++) {
			Person people = hash_table[i];
		// Check if the current value is null in the table
			if (people != null) {
			// get initial index by double hashing
			hash_code = hashCode(hash_table[i].getKey());
			hash_count = 1;
			// find empty cell using double hashing technique
			while(new_table[hash_code] != null) {
			    // the double hashing formula is (hash1(key) + i*hash2(key)) % n
				hash_code = doubleHashCode(hash_table[i].getKey());
			}
			new_table[hash_code] = people;
			}
		}
		// replace the old table with the new table
		hash_table = new_table;
	}

	// Method to return a string representation of the hash table
	public String toString() {
		String result="";
		for (int i=0;i<hash_table.length;i++) {
			if (hash_table[i] != null) {
				result += "# " + hash_table[i].toString() + "\n";
			} 
		}
	return result;
	}
}
