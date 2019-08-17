package dynamic_beat_17.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dynamic_beat_17.Main;
import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.User;
import dynamic_beat_17.service.impl.UserDAO;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SignUp extends JPanel {
	private JTextField IDField;
	private JPasswordField PWField;
	private JPasswordField CPWField;
	private Main win;
	JButton btnLogin;
	JButton btnSignUp;
	JButton btnCF;

	char[] cpw;
	private JLabel label_3;

	/**
	 * Create the panel.
	 */
	public SignUp(Main win) {
		setLayout(null);
		this.win = win;

		JLabel label = new JLabel("ID: ");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label.setBounds(530, 300, 86, 30);
		add(label);

		JLabel label_1 = new JLabel("PW: ");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label_1.setBounds(530, 350, 75, 30);
		add(label_1);

		JLabel label_2 = new JLabel("CPW: ");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		label_2.setBounds(530, 400, 86, 30);
		add(label_2);

		IDField = new JTextField();
		IDField.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		IDField.setColumns(10);
		IDField.setBounds(630, 300, 116, 30);
		add(IDField);

		PWField = new JPasswordField();
		PWField.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		PWField.setBounds(630, 350, 116, 30);
		add(PWField);

		CPWField = new JPasswordField();
		CPWField.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		CPWField.setBounds(630, 400, 116, 30);
		add(CPWField);

		btnSignUp = new JButton("Sign Up");

		btnSignUp.setBounds(530, 465, 97, 23);
		add(btnSignUp);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(649, 465, 97, 23);
		add(btnLogin);

		btnCF = new JButton("CF");
		btnCF.setBounds(814, 304, 57, 23);
		add(btnCF);
		
		
		//배경부분
		JLabel lblNewLabel = new JLabel("");
		Image backgound = new ImageIcon(Main.class.getResource("../images/introBackGround(Title).jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(backgound));	
		lblNewLabel.setBounds(0, 0, 1280, 720);
		add(lblNewLabel);

		btnLogin.addActionListener(new MyActionListener());
		btnSignUp.addActionListener(new MyActionListener());
//		btnCF.addActionListener(new MyActionListener());

	}

	class MyActionListener implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnSignUp) {
				try {
					User user = new User();
					user.setUserid(IDField.getText());
					user.setPasswd(PWField.getPassword());
					cpw = CPWField.getPassword();
					
					//char -> string
					String passwd = new String(PWField.getPassword());
					String passcpw = new String(cpw);


					if (passwd.equals(passcpw)) {
						UserDAO.getInstance().insert(user);
						JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!!");
						
					} else {
						System.out.println("PW: " + passwd);
						System.out.println("CPW: " + passcpw);
						JOptionPane.showMessageDialog(null, "잘못 입력하셨습니다.");
					
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnLogin)
				win.change("login");
			
			//아이디 체크부분
			if (e.getSource() == btnCF) {
				try {
					User user = new User();
					user.setUserid(IDField.getText());


					if (user.getPasswd().equals(cpw)) {
						UserDAO.getInstance().insert(user);
						JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!!");
						
					} else {
						System.out.println("PW: " + user.getPasswd());
						System.out.println("CPW: " + cpw);
						JOptionPane.showMessageDialog(null, "잘못 입력하셨습니다.");
					
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource() == btnLogin)
				win.change("login");
			
		}
	
	}

}
