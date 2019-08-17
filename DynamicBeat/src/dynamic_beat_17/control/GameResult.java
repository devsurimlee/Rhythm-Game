package dynamic_beat_17.control;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import dynamic_beat_17.Main;

public class GameResult extends Thread{

	private Image screenImage;
	private Graphics screenGraphic;
	
//	private Image background = new ImageIcon(Main.class.getResource("../images/rankBackground.jpg")).getImage();

	private Image rank1 = new ImageIcon(Main.class.getResource("../images/rank/1st.png")).getImage();
	private Image rank2 = new ImageIcon(Main.class.getResource("../images/rank/2rd.png")).getImage();
	private Image rank3 = new ImageIcon(Main.class.getResource("../images/rank/3nd.png")).getImage();

	public void screenDraw(Graphics2D g) {
		g.drawImage(rank1, 200, 200, null);
		g.drawImage(rank2, 200, 350, null);
		g.drawImage(rank3, 200, 500, null);
//		g.drawImage(background, 0, 0, null);

	}
	
	@Override
	public void run() {
	}

	public void start() {
		System.out.println("test");
		
	}

}
