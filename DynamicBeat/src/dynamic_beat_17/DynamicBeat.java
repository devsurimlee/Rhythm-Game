package dynamic_beat_17;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DynamicBeat extends JPanel /* JFrame */ {

	private Image screenImage; // 더블버퍼링을 위해 전체 화면에 대해서 이미지를 담는 두 인스턴스
	private Graphics screenGraphic;

	// Main클래스의 위치를 기반으로 해서 인트로이미지 파일을 얻어온 후 그것의 이미지 인스턴스를 background 라는 이미지
	// 변수에 초기화 해주는것.
	// background를 위쪽에서 바로 초기화 하도록 설정
	
	//화면전환용 변수 0:인트로메인 1: 엔터메인 2:백메인 3:게임스타트
	int stage = 0;
	
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));

	// introBackground => background 로 한 이유는 시작화면에서 메인화면으로 전환되었을 경우 단순히 변수에 이미지만
	// 변경하기 위해서
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackGround(Title).jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

//	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY; // 프로그램 상에서 마우스 x좌표와 y좌표를 의미한다.

//	private boolean isMainScreen = false; // 시작화면일때는 false, 메인 화면일때는 true값으로 변경
//	private boolean isGameScreen = false; // 게임 화면으로 넘어온 변수 인지 아닌지

	ArrayList<Track> trackList = new ArrayList<Track>(); // ArrayList 사용, 인덱스 0부터 사용.

	// 처음에는 선언만 할 수 있도록 ,초기화 시킬 필요 없도록
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic; // 변수 설정
	// 인트로 뮤직이 무한정으로 재생되도록 해주는것
	private Music introMusic = new Music("introMusic.mp3", true/* 직접 종료 전에는 계속해서 반복 재생 */);
	private int nowSelected = 0; // 현재 선택된 곡을 의미, 맨 처음에는 0으로 첫번째 곡을 의미

	// 하나의 게임이라는 것은 하나의 프로그램이 실행되었을때 단 하나의 게임만 진행 가능, 즉 동시에 여러 게임을 실행 시킬 수 없기때문에,
	// 프로그램 전체에서 통용이 가능하다.
	public static Game game; // 따라서, public static으로 선언해준다. 이제 게임이라는 변수는 프로젝트 전체에서 사용 가능한 변수가 된다.

	public DynamicBeat() /* 생성자 */ {
		// 순서에 맞게 넣어줌으로써 변수를 순식간에 초기화, 초기화 된 변수를 트랙리스트에 넣어줌으로써 곡들의 리스트를 관리할 수 있게 됨
		trackList.add(
				new Track("Mighty Love Title Image.png", "Mighty Love Start Image.jpg", "Mighty Love Game Image.jpg",
						"Mighty Love Selected.mp3", "Joakim Karud - Mighty Love.mp3", "Joakim Karud - Mighty Love"));
		trackList.add(
				new Track("Wild Flower Title Image.png", "Wild Flower Start Image.jpg", "Wild Flower Game Image.jpg",
						"Wild Flower Selected.mp3", "Joakim Karud - Wild Flower.mp3", "Joakim Karud - Wild Flower"));
		trackList.add(new Track("Energy Title Image.png", "Energy Start Image.jpg", "Energy Game Image.jpg",
				"Energy Selected.mp3", "Bensound - Energy.mp3", "Bensound - Energy"));
		trackList.add(new Track("Energy Title Image.png", "Ask The Wind Start.jpg", "Ask The Wind Game.jpg",
				"Miya - Ask the wind.mp3", "Miya - Ask the wind.mp3", "Miya - Ask The Wind"));

//		setUndecorated(true); // 실행 시 기본적으로 보이는 메뉴바가 보이지 않게됨
//		setTitle("Dynamic Beat");
//		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
//		setResizable(false); // 한번 만들어진 창은 사용자가 임의로 줄이거나 늘릴 수 없다.
//		setLocationRelativeTo(null); // 실행시 게임 화면이 정 중앙에 뜨게 설정
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창을 닫을때 프로그램 전체가 종료되는것
//		setVisible(true); // 게임 화면이 정상적으로 출력되도록(보이게 하도록)
		setBackground(new Color(0, 0, 0, 0)); // 페인트 컬러시 배경을 회색이 아니라 전부 흰색으로 변환
		setLayout(null); // 버튼이나 다른것들을 넣었을때 그 위치에 그대로 꽂히도록 설정

		addKeyListener(new KeyListener()); // 키리스너를 사용

		introMusic.start(); // 게임을 실행하면서 동시에 음악이 시작됨.

		// exit버튼 삭제 ->16참고
		

		startButton.setBounds(40, 200, 400, 100); // 메뉴바의 가장 오른쪽에 위치 (x, y, 가로크기, 세로크기)
		startButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 클릭되었을때 효과음
				enterMain();
			}
		});
		add(startButton);

		quitButton.setBounds(40, 330, 400, 100); // 메뉴바의 가장 오른쪽에 위치 (x, y, 가로크기, 세로크기)
		quitButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 클릭되었을때 효과음
				try {
					Thread.sleep(1000); // 효과음을 못 듣고 꺼질 경우를 대비해 소리가 나온 후 1초 후에 종료시키는 것
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // 클릭 했을때 해당 자체 게임이 종료가 된다.
			}
		});
		add(quitButton);

		leftButton.setVisible(false); // 맨 처음은 보이지 않도록
		leftButton.setBounds(140, 310, 60, 60); // 메뉴바의 가장 오른쪽에 위치 (x, y, 가로크기, 세로크기)
		leftButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 클릭되었을때 효과음
				selectLeft(); // 왼쪽 버튼 이벤트
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60); // 메뉴바의 가장 오른쪽에 위치 (x, y, 가로크기, 세로크기)
		rightButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 클릭되었을때 효과음
				selectRight(); // 오른쪽 버튼 이벤트
			}
		});
		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67); // 메뉴바의 가장 오른쪽에 위치 (x, y, 가로크기, 세로크기)
		easyButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 클릭되었을때 효과음
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67); // 메뉴바의 가장 오른쪽에 위치 (x, y, 가로크기, 세로크기)
		hardButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 클릭되었을때 효과음
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60); // 메뉴바의 가장 오른쪽에 위치 (x, y, 가로크기, 세로크기)
		backButton.setBorderPainted(false);// 제공하는 모습은 우리가 원하는 모습이 아니므로, 수정해준다.
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage); // 마우스가 올라갔을때 엔터이미지로 변경
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스가 올라갔을때 커서 변경
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 올라갔을때 효과음
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage); // 마우스가 벗어났을때 베이직으로 변경
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스가 벗어났을때 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start(); // 마우스가 클릭되었을때 효과음
				
				if (stage == 3) {
					backMain(); // 메인 화면으로 돌아가는 이벤트
				}
				else if (stage == 2 || stage == 1){
					introMain();
					
				}
			}
		});
		add(backButton);

//메뉴바부분 삭제 -> 16참고
		
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
	

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 크기만큼 스크린 이미지
		screenGraphic = screenImage.getGraphics(); // 스크린 그래픽은 스크린 이미지를 이용해서 그래픽 객체를 얻어옴
		screenDraw((Graphics2D) screenGraphic); // 그림을 그려줌 Grapics2D 라는 라이브러리는 글씨 깨짐 현상을 없애고 출력하게끔 형변환을 시켜주는 것이다.
		g.drawImage(screenImage, 0, 0, null); // 스크린 이미지를 0,0위치에 그려줌

	}

	public void screenDraw(Graphics2D g) { // 그래픽스2D 매개변수로 변환
		g.drawImage(background, 0, 0, null); // add 된 것들이 아닌 단순 이미지들을 화면에 출력해주는것
		if (stage == 1 || stage == 2) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (stage == 3) {
			game.screenDraw(g); // 원래 내용은 Game클래스로 이동한것.
		}
		paintComponents(g); // 메뉴바, JLabel등을 JFrame안에 추가하면 그것을 그려주는 것 (add로 추가된것들 )
		// draw로 그리거나 paint로 그리는데 메뉴바의 경우 항상 존재, 역동적으로 움직이지 않으므로 항상 고정이므로 페인트를 사용(정적)
		// 백그라운드 같이 단순 이미지는 draw로 그림
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint(); // paint는 JFrame을 상속받은 GUI게임에서 가장 먼저 그려주는 첫번째 함수

	}

	public void selectTrack(int nowSelected /* 현재 선택된 곡의 번호를 받는 인수 */) {
		if (selectedMusic != null)
			selectedMusic.close(); // 어떤 곡이 실행되고 있다면 바로 종료
		// 현재 선택된 곡이 가지고 있는 타이틀 이미지를 가지고 오겠다는 의미
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		// 현재 선택된 곡의 이미지를 곡의 이미지로 바꿔주는것.
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		// 음악파일 선택된것
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); // true로 무한 재생
		selectedMusic.start();
	}

	public void selectLeft() {
		if (nowSelected == 0) // 현재 선택된 곡이 첫번째 곡이라면
			nowSelected = trackList.size() - 1; // 0번째일때 왼쪽을 누르면 가장 오른쪽의 곡이 선택되어야 하기 때문
		else
			nowSelected--; // 0번째가 아닌경우, nowSelected에서 -1을 해주면 됨.
		selectTrack(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) // 현재 선택 곡이 마지막 곡이라면
			nowSelected = 0; // 마지막일때 오른쪽을 누르면 가장 왼쪽으로 곡이 선택되어야 하기 때문
		else
			nowSelected++; // 마지막이 아닌 경우, nowSelected에서 +1을 해주면 됨.
		selectTrack(nowSelected);
	}

	// 곡선택부분
	public void gameStart(int nowSelected, String difficulty) {
		stage = 3;
		if (selectedMusic != null)
			selectedMusic.close();
//		isMainScreen = false; // 메인 스크린이 아니란걸 변수로 표현, 따라서 screen draw에서 if 부분이 실행이 안됨
//		isGameScreen = true;
		leftButton.setVisible(false); // 메인화면이 아니므로, 곣 선택 버튼은 보여지면 안된다.
		rightButton.setVisible(false);
		easyButton.setVisible(false); // 메인화면이 아니므로, 난이도 버튼은 보여지면 안된다.
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start(); // 런 함수 자동 실행, 노트 생성됨.
		setFocusable(true); // 메인 프레임에 키보드 포커스가 맞춰짐.
	}

	public void backMain() {
		stage = 2;
		backButton.setVisible(true);
//		isMainScreen = true;
//		isGameScreen = false;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		selectTrack(nowSelected);
		introMusic.close();
		game.close(); // 현재 실행되고 있는 게임을 종료
	}

	public void enterMain() {
		stage = 1;
		backButton.setVisible(true);
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
//		isMainScreen = true;
		leftButton.setVisible(true);// 메인에서는 좌, 우 이동 가능한 버튼이 보여야 하므로
		rightButton.setVisible(true);
		easyButton.setVisible(true);// 메인에서는 난이도 버튼이 보여야 하므로
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0); // 맨 처음에는 첫번째 곡을 실행
	}

	public void introMain() {
		stage = 0;	
		backButton.setVisible(false);
		startButton.setVisible(true);
		quitButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
//		isMainScreen = true;
//		isGameScreen = false;
		leftButton.setVisible(false);// 메인에서는 좌, 우 이동 가능한 버튼이 보여야 하므로
		rightButton.setVisible(false);
		easyButton.setVisible(false);// 메인에서는 난이도 버튼이 보여야 하므로
		hardButton.setVisible(false);
		selectedMusic.close();
		
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		/// 클로즈로 저장하면 스레드가 지워져서 일드나 슬립으로 해야됨
	}
	
	
	
	
}
