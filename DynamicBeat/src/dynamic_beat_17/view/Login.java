package dynamic_beat_17.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dynamic_beat_17.Main;
import dynamic_beat_17.model.User;
import dynamic_beat_17.service.impl.UserDAO;

import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Login extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private Main win;
	JButton loginButton;
	JButton signUpButton;

	/**
	 * Create the panel.
	 */
	public Login(Main win) {
		this.win = win;
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

		loginButton = new JButton("Login");
		loginButton.setBounds(176, 175, 97, 23);
		add(loginButton);

		signUpButton = new JButton("Sign up");
		signUpButton.setBounds(176, 218, 97, 23);
		add(signUpButton);

		signUpButton.addActionListener(new MyActionListener());

		loginButton.addActionListener(new MyActionListener());
	}

	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == signUpButton) {
				win.change("signUp");
			} else if (e.getSource() == loginButton) {
				//DAO.로그인
				
				
				win.change("dynamicBeat");
			}
		}
	}

}
