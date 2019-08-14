package dynamic_beat_17.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dynamic_beat_17.Main;
import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.User;
import dynamic_beat_17.service.impl.UserDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class SignUp extends JPanel {
	private JTextField IDField;
	private JPasswordField PWField;
	private JPasswordField CPWField;
	private Main win;
	JButton btnLogin;
	JButton btnSignUp;
	
	String cpw;
	
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
		
		IDField = new JTextField();
		IDField.setColumns(10);
		IDField.setBounds(211, 93, 116, 21);
		add(IDField);
		
		PWField = new JPasswordField();
		PWField.setBounds(211, 139, 116, 21);
		add(PWField);
		
		CPWField = new JPasswordField();
		CPWField.setBounds(211, 178, 116, 21);
		add(CPWField);
		
		btnSignUp =  new JButton("Sign Up");
		
		btnSignUp.setBounds(119, 230, 97, 23);
		add(btnSignUp);
		
		btnLogin  = new JButton("Login");
		btnLogin.setBounds(230, 230, 97, 23);
		add(btnLogin);
		
		btnLogin.addActionListener(new MyActionListener());
		btnSignUp.addActionListener(new MyActionListener());	
		
		JButton btnNewButton = new JButton("CF");
		btnNewButton.setBounds(339, 92, 57, 23);
		add(btnNewButton);

	}
	class MyActionListener implements ActionListener{
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnSignUp) {
				try {
					User user = new User();
//					System.out.println("아이디>");
					user.setUserid(IDField.getText());
//					System.out.println("비밀번호>");
					user.setPasswd(PWField.getText());
//					if(PWField.getText() != CPWField.getText()) {
//						System.out.println();
//					}
					cpw = CPWField.getText();
					
					if (user.getPasswd().equals(cpw)) {
						UserDAO.getInstance().insert(user);
					}
					else {System.out.println(cpw);
					System.out.println(user.getPasswd());
					}
					
					

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else if(e.getSource() == btnLogin)
				win.change("login");
		}
	}
	
	
}
