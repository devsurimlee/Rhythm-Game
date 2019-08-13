package dynamic_beat_17;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class SignUp {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setBounds(82, 65, 57, 15);
		frame.getContentPane().add(lblId);
		
		JLabel lblPw = new JLabel("PW: ");
		lblPw.setBounds(82, 108, 57, 15);
		frame.getContentPane().add(lblPw);
		
		JLabel lblCpw = new JLabel("CPW: ");
		lblCpw.setBounds(82, 150, 57, 15);
		frame.getContentPane().add(lblCpw);
		
		textField = new JTextField();
		textField.setBounds(162, 62, 116, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 108, 116, 21);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(162, 147, 116, 21);
		frame.getContentPane().add(passwordField_1);
	}
}
