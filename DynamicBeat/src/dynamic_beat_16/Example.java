package dynamic_beat_16;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Example {

	private JFrame frame;
	private JTextField userName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Example window = new Example();
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
	public Example() {
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
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(37, 59, 136, 29);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setBounds(37, 115, 136, 29);
		frame.getContentPane().add(lblPw);
		
		userName = new JTextField();
		userName.setBounds(190, 63, 116, 21);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(190, 119, 116, 21);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uname = userName.getText();
				String psd = passwordField.getText();
				
				if(uname.equals("name") && psd.equals("password"))
				{
					JOptionPane.showMessageDialog(frame, "You are successfully logined");
				}else
				{
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}
				
			}
		});
		btnNewButton.setBounds(62, 194, 97, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
