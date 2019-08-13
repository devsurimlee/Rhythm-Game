package dynamic_beat_17;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SignUp2 extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Create the panel.
	 */
	public SignUp2() {
		setLayout(null);
		
		JLabel label = new JLabel("ID: ");
		label.setBounds(131, 96, 57, 15);
		add(label);
		
		JLabel label_1 = new JLabel("PW: ");
		label_1.setBounds(131, 139, 57, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("CPW: ");
		label_2.setBounds(131, 181, 57, 15);
		add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(211, 93, 116, 21);
		add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(211, 139, 116, 21);
		add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(211, 178, 116, 21);
		add(passwordField_1);

	}

}
