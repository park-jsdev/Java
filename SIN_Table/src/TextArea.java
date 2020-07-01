import javax.swing.*;
import java.awt.*;

public class TextArea extends JPanel {
	static JTextArea textArea = new JTextArea(5, 50);
	
	public TextArea() {
		initializeUI();
	}
	
	public static void addEntry(String s) {
		textArea.setText(s);
	}
	
	private void initializeUI() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500, 200));
		
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	public static void showFrame() {
		JPanel panel = new TextArea();
		panel.setOpaque(true);
		
		JFrame frame = new JFrame("SIN Entries");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.requestFocusInWindow();
		

		
	}
	
	
}
