/**
 * HashTable.java
 * 
 *  @author Junsoo Park
 * 
 * This class contains the GUI interface. constructor for a dynamically resizable hash table with an initial size of 31
 * and a load factor of 0.80.
 * 
 * It contains a hashing function which uses double hashing, which uses the extraction method with division using the
 * last 4 digits of the SIN for the first hash and the extraction method with division on the first 3 digits
 * of the SIN for the second hash function.
 * 
 */

import javax.swing.*;

public class HashTable {

	static DoubleHashing table = new DoubleHashing();
	
	public static void main(String[] args){
		manage();
	}
	
	// Displays all key and value pairs in the table
	public static void init(){
		System.out.println("Pairs stored: ");
		System.out.println(table.toString());
	}
	
	private static void manage() {
		// Text to be put on the buttons
		String[] choices = {"View Table", "Add Person", "Remove Person", "Quit"};
		
		while (true) {
			int response = JOptionPane.showOptionDialog(
			null // Center in window
			, "Select an Operation to Perfrom" // Message
			, "People Hash Table" // Title in title bar
			, JOptionPane.YES_NO_OPTION // option type
			, JOptionPane.PLAIN_MESSAGE // messageType
			, null // Icon (none)
			, choices // Button text as above.
			, "Add Row" // Default button's label
			);
			switch (response) {
			
			// View Table
			case 0:
				try {
					init();
					TextArea.showFrame();
				} catch (Exception e1){
					System.out.println("Exception occured");
				}
				break;
				
			// Add Person 
			case 1:
				JTextField name_Field = new JTextField(5);
				JTextField SIN_Field = new JTextField(5);
				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Person Name:"));
				myPanel.add(name_Field);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				myPanel.add(new JLabel("Person SIN:"));
				myPanel.add(SIN_Field);
				int result = JOptionPane.showConfirmDialog(null
						,myPanel
						,"Please Enter the Value"
						,JOptionPane.OK_CANCEL_OPTION
				);
				if (result == JOptionPane.OK_OPTION) {
					String name = name_Field.getText();
					String regex = "^[0-9]+$";
					String SIN = SIN_Field.getText();
					
					if (!SIN.matches(regex) || Integer.parseInt(SIN) < 100000000 || Integer.parseInt(SIN) > 999999999) {
							JOptionPane.showMessageDialog(null, "Invalid SIN ");
					} else {
					int SIN_value = Integer.parseInt(SIN);
					Person p1 = new Person(SIN_value, name);
					table.put(p1.getSIN(), p1);
					
					TextArea.addEntry(p1.toString());
					JOptionPane.showMessageDialog(null, "Table details inserted ");
					}
				}
				break;
				
			// Remove Person
			case 2:
				JTextField delete_Field = new JTextField(5);
				JPanel Panelp = new JPanel();
				Panelp.add(new JLabel("Enter Person's SIN:"));
				Panelp.add(delete_Field);
				int result1 = JOptionPane.showConfirmDialog(null, Panelp
						,"Please Enter the Values",
						JOptionPane.OK_CANCEL_OPTION);
				if (result1 == JOptionPane.OK_OPTION) {
					String delete_entry = delete_Field.getText();
					int delete_value = Integer.parseInt(delete_entry);
					table.remove(delete_value);
					JOptionPane.showMessageDialog(null, "Table details Deleted ");
				}
				break;
				
			// Exit
			case 3:
				System.exit(0);
				break;
			case -1:
				System.exit(0);
			default:
				// If we get here, something is wrong. Defensive programming
				JOptionPane.showConfirmDialog(null, "Unexpected response ");
			}
					
		}
	}

}
