package dynamic_beat_17;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login1 extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public Login1() {
		setLayout(null);
		
		JLabel label = new JLabel("ID :");
		label.setBounds(128, 66, 74, 29);
		add(label);
		
		JLabel label_1 = new JLabel("PW :");
		label_1.setBounds(128, 122, 74, 29);
		add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(219, 70, 116, 21);
		add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(219, 126, 116, 21);
		add(passwordField);
		
		JButton button = new JButton("Login");
		button.setBounds(176, 175, 97, 23);
		add(button);
		
		JButton button_1 = new JButton("Sign up");
		button_1.setBounds(176, 218, 97, 23);
		add(button_1);

	}

}
