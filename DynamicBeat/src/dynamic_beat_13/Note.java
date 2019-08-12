package dynamic_beat_13;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED; // 현재 노트 위치 확인, 노트 생성후, 정확히 1초 뒤에 판정라인에 도달한다.
	private String noteType;

	public Note(int x, String noteType) {
		this.x = x; // 각각의 변수를 초기화
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(noteType.equals("short"))
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else if (noteType.equals("long"))
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null); //가로의 길이가 다른 것보다 두 배 긴것. 기본적인 노트의 가로폭은 100픽셀 이므로.
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED; //노트 스피드 만큼 떨어진다. 즉 아래쪽으로 7만큼 떨어진다.
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				Thread.sleep(Main.SLEEP_TIME); //슬립은 0.001초 기준, 10으로 했으므로, 1초에 100실행됨. 즉 1초에 700픽셀만큼 y좌표가 내려간다고 생각하면 됨.
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
