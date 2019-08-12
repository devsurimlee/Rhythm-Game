package dynamic_beat_02;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	
	private Image screenImage;		//더블버퍼링을 위해 전체 화면에 대해서 이미지를 담는 두 인스턴스
	private Graphics screenGraphic;
	
	private Image introBackground;

	public DynamicBeat() /*생성자*/{
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //한번 만들어진 창은 사용자가 임의로 줄이거나 늘릴 수 없다.
		setLocationRelativeTo(null); //실행시 게임 화면이 정 중앙에 뜨게 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임 창을 닫을때 프로그램 전체가 종료되는것
		setVisible(true); //게임 화면이 정상적으로 출력되도록(보이게 하도록)
		
		
		//Main클래스의 위치를 기반으로 해서 인트로이미지 파일을 얻어온 후 그것의 이미지 인스턴스를 introBackground 라는 이미지 변수에 초기화 해주는것.
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround(Title).jpg")).getImage();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //크기만큼 스크린 이미지
		screenGraphic = screenImage.getGraphics(); //스크린 그래픽은 스크린 이미지를 이용해서 그래픽 객체를 얻어옴
		screenDraw(screenGraphic); //그림을 그려줌
		g.drawImage(screenImage, 0, 0, null); //스크린 이미지를 0,0위치에 그려줌
		
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint(); //paint는 JFrame을 상속받은 GUI게임에서 가장 먼저 그려주는 첫번째 함수 
		
	}
}
