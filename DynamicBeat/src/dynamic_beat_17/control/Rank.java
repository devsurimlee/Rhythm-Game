package dynamic_beat_17.control;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dynamic_beat_17.Main;

public class Rank extends Thread {

	private Image screenImage;
	private Graphics screenGraphic;

	private Image rank1 = new ImageIcon(Main.class.getResource("../images/rank/1st.png")).getImage();
	private Image rank2 = new ImageIcon(Main.class.getResource("../images/rank/2rd.png")).getImage();
	private Image rank3 = new ImageIcon(Main.class.getResource("../images/rank/3nd.png")).getImage();


	String userId;
	String UserPw;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return UserPw;
	}

	public void setUserPw(String userPw) {
		UserPw = userPw;
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(rank1, 200, 200, null);	
		g.drawImage(rank2, 200, 350, null);
		g.drawImage(rank3, 200, 500, null);


		
		
//		String suffix = String.format("%06d", "11");
//		g.drawString(suffix, 565, 702); // 점수 출력

	}
	
	@Override
	public void run() {
	}

	public void close() {
		this.interrupt();
	}
	
	
	

}
