package dynamic_beat_17.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dynamic_beat_17.Main;
import dynamic_beat_17.model.User;
import dynamic_beat_17.service.impl.UserDAO;

import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class Login extends JPanel {
	private JTextField IDField;
	private JPasswordField PWField;
	private Main win;
	JButton loginButton;
	JButton signUpButton;
	public static String userId;
	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public Login(Main win) throws SQLException {
		this.win = win;
		setLayout(null);

		JLabel label = new JLabel("ID :");
		label.setBounds(128, 66, 74, 29);
		add(label);

		JLabel label_1 = new JLabel("PW :");
		label_1.setBounds(128, 122, 74, 29);
		add(label_1);

		IDField = new JTextField();
		IDField.setColumns(10);
		IDField.setBounds(219, 70, 116, 21);
		add(IDField);

		PWField = new JPasswordField();
		PWField.setBounds(219, 126, 116, 21);
		add(PWField);

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
		User user = new User();
		String id = (IDField.getText());
		String pw = (PWField.getText());
//		boolean possible;
//		possible = UserDAO.getInstance().login(user);
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == signUpButton) {
				win.change("signUp");
			} else if (e.getSource() == loginButton) {
				// DAO.로그인
				try {
					User user = new User();
					user.setUserid(IDField.getText());
					user.setPasswd(PWField.getText());
					UserDAO.getInstance().login(user);
					if (UserDAO.getInstance().login(user) == true) {
						System.out.println("Login Success.");
						JOptionPane.showMessageDialog(null, "로그인 성공!!");
						userId = id;        //  로그인 아이디 저장
						win.change("dynamicBeat");
					} else {
						System.out.println("Login Failed");
						JOptionPane.showMessageDialog(null, "로그인 에 실패하였습니다.");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		}
	}

}
