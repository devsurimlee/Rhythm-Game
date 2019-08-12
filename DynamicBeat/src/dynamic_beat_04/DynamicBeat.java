package dynamic_beat_04;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage; // 더블버퍼링을 위해 전체 화면에 대해서 이미지를 담는 두 인스턴스
	private Graphics screenGraphic;

	// Main클래스의 위치를 기반으로 해서 인트로이미지 파일을 얻어온 후 그것의 이미지 인스턴스를 introBackground 라는 이미지
	// 변수에 초기화 해주는것.
	// introBackground를 위쪽에서 바로 초기화 하도록 설정

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround(Title).jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	/* 아직 메뉴바 png 안넣음!!!! */

	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY; // 프로그램 상에서 마우스 x좌표와 y좌표를 의미한다.

	public DynamicBeat() /* 생성자 */ {
		setUndecorated(true); // 실행 시 기본적으로 보이는 메뉴바가 보이지 않게됨
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // 한번 만들어진 창은 사용자가 임의로 줄이거나 늘릴 수 없다.
		setLocationRelativeTo(null); // 실행시 게임 화면이 정 중앙에 뜨게 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창을 닫을때 프로그램 전체가 종료되는것
		setVisible(true); // 게임 화면이 정상적으로 출력되도록(보이게 하도록)
		setBackground(new Color(0, 0, 0, 0)); // 페인트 컬러시 배경을 회색이 아니라 전부 흰색으로 변환
		setLayout(null); // 버튼이나 다른것들을 넣었을때 그 위치에 그대로 꽂히도록 설정

		exitButton.setBounds(1245, 0, 30, 30); // 메뉴바의 가장 오른쪽에 위치
		exitButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); //마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); //마우스가 클릭되었을때 효과음
				try {
					Thread.sleep(1000); //효과음을 못 듣고 꺼질 경우를 대비해 소리가 나온 후 1초 후에 종료시키는 것
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // 클릭 했을때 해당 자체 게임이 종료가 된다.
			}
		});
		add(exitButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY(); // 이벤트 발생시 x좌표와 y좌표를 얻어오겠다.
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); // JFrame의 위치 자체를 변경,
				// 드래그 할때 자동으로 x좌표, y좌표를 얻어와서 자동으로 게임창의 위치를 바꿔줌 -> 메뉴바를 가지고 이동 가능
			}
		});
		add(menuBar); // JFrame에 메뉴바가 추가되는것

		Music introMusic = new Music("introMusic.mp3", true/* 직접 종료 전에는 계속해서 반복 재생 */);
		// 인트로 뮤직이 무한정으로 재생되도록 해주는것
		introMusic.start(); // 게임을 실행하면서 동시에 음악이 시작됨.
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 크기만큼 스크린 이미지
		screenGraphic = screenImage.getGraphics(); // 스크린 그래픽은 스크린 이미지를 이용해서 그래픽 객체를 얻어옴
		screenDraw(screenGraphic); // 그림을 그려줌
		g.drawImage(screenImage, 0, 0, null); // 스크린 이미지를 0,0위치에 그려줌

	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		paintComponents(g); // 메뉴바, JLabel등을 JFrame안에 추가하면 그것을 그려주는 것
		// draw로 그리거나 paint로 그리는데 메뉴바의 경우 항상 존재, 역동적으로 움직이지 않으므로 항상 고정이므로 페인트를 사용(정적)
		// 백그라운드 같이 단순 이미지는 draw로 그림
		this.repaint(); // paint는 JFrame을 상속받은 GUI게임에서 가장 먼저 그려주는 첫번째 함수

	}
}