package dynamic_beat_17;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Main win;

	/**
	 * Create the panel.
	 */
	public SignUp(Main win) {
		setLayout(null);
		this.win = win;
		
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
		
		JButton btnSignUp = new JButton("Sign Up");
		
		btnSignUp.setBounds(119, 230, 97, 23);
		add(btnSignUp);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(230, 230, 97, 23);
		add(btnLogin);
		
		btnLogin.addActionListener(new MyActionListener());
			
		
		JButton btnNewButton = new JButton("CF");
		btnNewButton.setBounds(339, 92, 57, 23);
		add(btnNewButton);

	}
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("login");
		}
	}
}
