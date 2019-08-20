package dynamic_beat_17.control;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import dynamic_beat_17.view.Login;
import dynamic_beat_17.Main;
import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.Score;
import dynamic_beat_17.service.impl.ScoreDAO;
import dynamic_beat_17.service.impl.UserDAO;

public class Game extends Thread /* 하나의 프로그램 안에서 작게 돌아가는 프로그램 */ {
	Connection conn = DAO.getConnect();
	String id = (dynamic_beat_17.view.Login.userId); // 로그인에서 가져온 아이디
	public static String musicName; // 곡 이름
	public static int score = 0;
	public static GameResult gameresult;
	int highScore;
	int stage = 3;

	// 겜 스타트때 0 초기화
	private Image background = new ImageIcon(Main.class.getResource("../images/result/resultBackground.jpg"))
			.getImage();

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image resultPopup = new ImageIcon(Main.class.getResource("../images/resultPopup.png")).getImage();

//	private Image blueFlareImage;
	private Image judgeImage;

	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	// 노트 판정이펙트추가
	private Image keyPadSEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png")).getImage();
	private Image keyPadDEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png")).getImage();
	private Image keyPadFEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png")).getImage();
	private Image keyPadSpaceLEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png"))
			.getImage();
	private Image keyPadJEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png")).getImage();
	private Image keyPadKEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png")).getImage();
	private Image keyPadLEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect.png")).getImage();

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) throws SQLException { // 생성자 생성
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
//		getHighScore();
//		if (highScore >= score) {
//			// 최고 점수 유지
//		} else if (highScore < score) {
//			// 최고 점수 수정
//			highScore = score;
//		}

	}

	public void screenDraw(Graphics2D g) throws SQLException {

		if (stage == 3) {
			g.drawImage(noteRouteSImage, 228, 30, null);
			g.drawImage(noteRouteDImage, 332, 30, null);
			g.drawImage(noteRouteFImage, 436, 30, null);
			g.drawImage(noteRouteSpace1Image, 540, 30, null);
			g.drawImage(noteRouteSpace2Image, 640, 30, null);
			g.drawImage(noteRouteJImage, 744, 30, null);
			g.drawImage(noteRouteKImage, 848, 30, null);
			g.drawImage(noteRouteLImage, 952, 30, null);
			g.drawImage(noteRouteLineImage, 224, 30, null);
			g.drawImage(noteRouteLineImage, 328, 30, null);
			g.drawImage(noteRouteLineImage, 432, 30, null);
			g.drawImage(noteRouteLineImage, 536, 30, null);
			g.drawImage(noteRouteLineImage, 740, 30, null);
			g.drawImage(noteRouteLineImage, 844, 30, null);
			g.drawImage(noteRouteLineImage, 948, 30, null);
			g.drawImage(noteRouteLineImage, 1052, 30, null);

			// 공통부분 파란선
			g.drawImage(gameInfoImage, 0, 660, null);
			// 붉은선(판정라인)
			g.drawImage(judgementLineImage, 0, 580, null);
			for (int i = 0; i < noteList.size(); i++) {
				Note note = noteList.get(i);
				if (note.getY() > 620) {
					judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage(); // Miss 출력
				}
				if (!note.isProceeded()) {
					noteList.remove(i);
					i--;
				} else {
					note.screenDraw(g);
				}
			}
			//테두리 부드럽게
			g.setColor(Color.WHITE);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 결론적으로
																												// 깨짐 없이
																												// 매끄럽게
																												// 출력됨.
			g.setFont(new Font("Arial", Font.BOLD, 30)); // 글씨를 그릴 수 있도록 세팅
			g.drawString(titleName, 20, 702); // 실행중인 곡에 대한 정보
			g.drawString(difficulty, 1190, 702);
			g.setFont(new Font("Arial", Font.PLAIN, 26));
			g.setColor(Color.DARK_GRAY);
			g.drawString("S", 270, 609);
			g.drawString("D", 374, 609);
			g.drawString("F", 478, 609);
			g.drawString("Space Bar", 580, 609); // 해당 키패드 각각 출력
			g.drawString("J", 784, 609);
			g.drawString("K", 889, 609);
			g.drawString("L", 993, 609);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Elephant", Font.BOLD, 30));

			// 점수 출력
			String suffix = String.format("%06d", score);
//			String stringScore = String.valueOf(this.score);
//			String temp = leftPad(stringScore, 6, '0');
			g.drawString(suffix, 565, 702); // 점수 출력
//			g.drawImage(blueFlareImage, 320, 430, null);
			g.drawImage(judgeImage, 460, 420, null);
			g.drawImage(keyPadSImage, 228, 580, null);
			g.drawImage(keyPadDImage, 332, 580, null);
			g.drawImage(keyPadFImage, 436, 580, null);
			g.drawImage(keyPadSpace1Image, 540, 580, null);
			g.drawImage(keyPadSpace2Image, 640, 580, null);
			g.drawImage(keyPadJImage, 744, 580, null);
			g.drawImage(keyPadKImage, 848, 580, null);
			g.drawImage(keyPadLImage, 952, 580, null);

			g.drawImage(keyPadSEffectImage, 180, 500, null);
			g.drawImage(keyPadDEffectImage, 280, 500, null);
			g.drawImage(keyPadFEffectImage, 380, 500, null);

//			g.drawImage(keyPadSpaceEffectImage, 180, 500, null);
			g.drawImage(keyPadJEffectImage, 680, 500, null);
			g.drawImage(keyPadKEffectImage, 780, 500, null);
			g.drawImage(keyPadLEffectImage, 880, 500, null);
		}

		if (stage == 4) {
			// 최고 점수 조회

			g.drawImage(background, 0, 0, null);

			// 공통부분 파란선
			g.drawImage(gameInfoImage, 0, 660, null);

			g.setColor(Color.WHITE);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 결론적으로
																												// 깨짐 없이
																												// 매끄럽게
																												// 출력됨.
			g.setFont(new Font("Arial", Font.BOLD, 30)); // 글씨를 그릴 수 있도록 세팅
			g.drawString(titleName, 20, 702); // 실행중인 곡에 대한 정보
			g.drawString(difficulty, 1190, 702);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Elephant", Font.BOLD, 30));

			// 점수 출력
			String suffix = String.format("%06d", score);
			g.drawString(suffix, 565, 702); // 점수 출력

			// 결과창 레이어
			g.drawImage(resultPopup, 200, 120, null);

			// 결과창에 그려지는 것들
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.setColor(Color.BLUE);
			g.drawString("Score  : ", 475, 425);
//			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.setColor(Color.WHITE);
			g.drawString(suffix, 700, 425); // 점수 출력

//			if (highScore >= score) {
			// 최고 점수 유지
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.setColor(Color.GREEN);
			g.drawString("High_Score  : ", 337, 500);
			String hiScore = String.format("%06d", highScore);
			g.drawString(hiScore, 700, 500); // 최고 점수 출력

//			} else if (highScore < score) {
//				// 최고 점수 수정
//				g.setColor(Color.GREEN);
//				g.drawString("High_Score  : ", 337, 500);
//				g.drawString(suffix, 700, 500); // 최고 점수 출력
//			}

		}

	}

	public Score getHighScore() throws SQLException { // highScore 가져옴
		Score daoScore = new Score();
		daoScore.setUserid(id);
		daoScore.setMusic(musicName);
		ScoreDAO.getInscance().selectOne(daoScore);
		highScore = daoScore.getHighScore();
		return daoScore;
	}

	public void updateHighScore() throws SQLException {
//		highScore = score;
		Score daoScore = new Score();
		daoScore.setHighScore(score);
		daoScore.setUserid(id);
		daoScore.setMusic(musicName);
		ScoreDAO.getInscance().update(daoScore);
	}

	public void insertHighScore() throws SQLException {
//		highScore = score;
		Score daoScore = new Score();
		daoScore.setHighScore(score);
		daoScore.setUserid(id);
		daoScore.setMusic(musicName);
		ScoreDAO.getInscance().insert(daoScore);
	}

	public void pressS() { // S를 눌렀을때 이벤트 처리를 해주는 함수
		judgeKey("S");
		// 눌렀을때만 이펙트 뜸

//		if (judge.equals("Good") || judge.equals("Great") || judge.equals("Perfect")) {
		keyPadSEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect_on.png")).getImage();
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumHihat1.mp3", false).start();

	}

	public void releaseS() { // S를 눌렀을때 이벤트 처리를 해주는 함수
		keyPadSEffectImage = null;
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressD() {
		judgeKey("D");
		keyPadDEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect_on.png")).getImage();
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumHihat1.mp3", false).start();
	}

	public void releaseD() {
		keyPadDEffectImage = null;
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		judgeKey("F");
		keyPadFEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect_on.png")).getImage();
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumHihat1.mp3", false).start();
	}

	public void releaseF() {
		keyPadFEffectImage = null;
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressSpace() {
		judgeKey("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumBass1.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressJ() {
		judgeKey("J");
		keyPadJEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect_on.png")).getImage();
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumHihat1.mp3", false).start();
	}

	public void releaseJ() {
		keyPadJEffectImage = null;
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressK() {
		judgeKey("K");
		keyPadKEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect_on.png")).getImage();
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumHihat1.mp3", false).start();
	}

	public void releaseK() {
		keyPadKEffectImage = null;
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressL() {
		judgeKey("L");
		keyPadLEffectImage = new ImageIcon(Main.class.getResource("../images/noteEffect_on.png")).getImage();
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("drumHihat1.mp3", false).start();
	}

	public void releaseL() {
		keyPadLEffectImage = null;
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();

	}

	@Override
	public void run() {
		try {
			dropNotes(this.titleName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		gameMusic.close();
		this.interrupt(); // 지금 실행되고 있는 하나의 게임 쓰레드를 종료
	}

	// 노트찍는부분
	public void dropNotes(String titleName) throws SQLException {
		Beat[] beats = null;
		score = 0;
		if (titleName.equals("Joakim Karud - Mighty Love") && difficulty.equals("Easy")) {
			musicName = "JK - ML , E"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 4460 - Main.REACH_TIME * 1000; // 항상 똑같은 첫번째 노트가 판정바에 적중하는 박자 타이밍
			int gap = 125; // 박자 계산
			beats = new Beat[] {
					// 수동으로 찍어줘야함.
					new Beat(startTime + gap * 1, "S"), new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 5, "S"), new Beat(startTime + gap * 7, "D"),
					new Beat(startTime + gap * 9, "S"), new Beat(startTime + gap * 11, "D"),
					new Beat(startTime + gap * 13, "S"), new Beat(startTime + gap * 15, "D"),
					new Beat(startTime + gap * 18, "J"), new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 22, "J"), new Beat(startTime + gap * 24, "K"),
					new Beat(startTime + gap * 26, "J"), new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"), new Beat(startTime + gap * 32, "K"),
					new Beat(startTime + gap * 35, "S"), new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 39, "S"), new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 43, "S"), new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 48, "J"), new Beat(startTime + gap * 49, "K"),
					new Beat(startTime + gap * 50, "L"), new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 52, "Space"), new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 54, "S"), new Beat(startTime + gap * 56, "D"),
					new Beat(startTime + gap * 59, "F"), new Beat(startTime + gap * 59, "Space"),
					new Beat(startTime + gap * 59, "J"), new Beat(startTime + gap * 61, "K"),
					new Beat(startTime + gap * 63, "D"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 65, "Space"), new Beat(startTime + gap * 65, "J"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 72, "S"),
					new Beat(startTime + gap * 74, "S"), new Beat(startTime + gap * 78, "J"),
					new Beat(startTime + gap * 79, "K"), new Beat(startTime + gap * 80, "L"),
					new Beat(startTime + gap * 83, "Space"), new Beat(startTime + gap * 85, "S"),
					new Beat(startTime + gap * 87, "D"), new Beat(startTime + gap * 89, "S"),
					new Beat(startTime + gap * 91, "D"), new Beat(startTime + gap * 93, "F"),
					new Beat(startTime + gap * 96, "Space"), new Beat(startTime + gap * 98, "L"),
					new Beat(startTime + gap * 100, "Space"), new Beat(startTime + gap * 102, "S"),
					new Beat(startTime + gap * 103, "D"), new Beat(startTime + gap * 109, "Space"),
					new Beat(startTime + gap * 111, "Space"), new Beat(startTime + gap * 116, "Space"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 119, "D"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 123, "S"),
					new Beat(startTime + gap * 124, "D"), new Beat(startTime + gap * 125, "F"),
					new Beat(startTime + gap * 126, "J"), new Beat(startTime + gap * 127, "K"),
					new Beat(startTime + gap * 130, "J"), new Beat(startTime + gap * 133, "K"),
					new Beat(startTime + gap * 136, "L"), new Beat(startTime + gap * 138, "S"),
					new Beat(startTime + gap * 140, "Space"), new Beat(startTime + gap * 142, "S"),
					new Beat(startTime + gap * 144, "Space"), new Beat(startTime + gap * 146, "Space"),
					new Beat(startTime + gap * 150, "Space"), new Beat(startTime + gap * 152, "Space"),
					new Beat(startTime + gap * 157, "J"), new Beat(startTime + gap * 161, "K"),
					new Beat(startTime + gap * 165, "L"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 169, "D"), new Beat(startTime + gap * 171, "F"),
					new Beat(startTime + gap * 174, "S"), new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 178, "F"), new Beat(startTime + gap * 180, "Space"),
					new Beat(startTime + gap * 181, "L"), new Beat(startTime + gap * 184, "Space"),
					new Beat(startTime + gap * 187, "L"), new Beat(startTime + gap * 188, "K"),
					new Beat(startTime + gap * 189, "J"), new Beat(startTime + gap * 192, "S"),
					new Beat(startTime + gap * 192, "Space"), new Beat(startTime + gap * 196, "D"),
					new Beat(startTime + gap * 196, "Space"), new Beat(startTime + gap * 200, "S"),
					new Beat(startTime + gap * 200, "Space"), new Beat(startTime + gap * 207, "J"),
					new Beat(startTime + gap * 216, "L"), new Beat(startTime + gap * 216, "Space"),
					new Beat(startTime + gap * 218, "Space"), new Beat(startTime + gap * 221, "J"),
					new Beat(startTime + gap * 223, "K"), new Beat(startTime + gap * 225, "L"),
					new Beat(startTime + gap * 227, "Space"), new Beat(startTime + gap * 231, "D"),
					new Beat(startTime + gap * 231, "Space"), new Beat(startTime + gap * 235, "S"),
					new Beat(startTime + gap * 235, "Space"), new Beat(startTime + gap * 242, "S"),
					new Beat(startTime + gap * 242, "Space"), new Beat(startTime + gap * 242, "L"),
					new Beat(startTime + gap * 246, "D"), new Beat(startTime + gap * 246, "Space"),
					new Beat(startTime + gap * 246, "K"), new Beat(startTime + gap * 250, "F"),
					new Beat(startTime + gap * 250, "Space"), new Beat(startTime + gap * 250, "J"),
					new Beat(startTime + gap * 255, "J"), new Beat(startTime + gap * 257, "K"),
					new Beat(startTime + gap * 259, "K"), new Beat(startTime + gap * 262, "S"),
					new Beat(startTime + gap * 262, "Space"), new Beat(startTime + gap * 266, "D"),
					new Beat(startTime + gap * 266, "S"), new Beat(startTime + gap * 266, "Space"),
					new Beat(startTime + gap * 270, "S"), new Beat(startTime + gap * 270, "Space"),
					new Beat(startTime + gap * 275, "J"), new Beat(startTime + gap * 277, "K"),
					new Beat(startTime + gap * 279, "L"), new Beat(startTime + gap * 282, "J"),
					new Beat(startTime + gap * 284, "K"), new Beat(startTime + gap * 286, "L"),
					new Beat(startTime + gap * 289, "J"), new Beat(startTime + gap * 291, "K"),
					new Beat(startTime + gap * 293, "L"), new Beat(startTime + gap * 295, "J"),
					new Beat(startTime + gap * 297, "L"), new Beat(startTime + gap * 299, "D"),
					new Beat(startTime + gap * 301, "S"), new Beat(startTime + gap * 304, "F"),
					new Beat(startTime + gap * 306, "S"), new Beat(startTime + gap * 308, "S"),
					new Beat(startTime + gap * 310, "F"), new Beat(startTime + gap * 312, "D"),
					new Beat(startTime + gap * 314, "S"), new Beat(startTime + gap * 317, "F"),
					new Beat(startTime + gap * 319, "D"), new Beat(startTime + gap * 321, "S"),
					new Beat(startTime + gap * 323, "F"), new Beat(startTime + gap * 325, "D"),
					new Beat(startTime + gap * 327, "S"), new Beat(startTime + gap * 330, "F"),
					new Beat(startTime + gap * 332, "S"), new Beat(startTime + gap * 332, "Space"),
					new Beat(startTime + gap * 336, "D"), new Beat(startTime + gap * 336, "Space"),
					new Beat(startTime + gap * 340, "S"), new Beat(startTime + gap * 340, "Space"),

			};
		} else if (titleName.equals("Joakim Karud - Mighty Love") && difficulty.equals("Hard")) {
			musicName = "JK - ML , H"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 1000;
			int gap = 125; // 박자 계산

			beats = new Beat[] { new Beat(startTime + gap * 1, "S"), };
			

		} else if (titleName.equals("Joakim Karud - Wild Flower") && difficulty.equals("Easy")) {
			musicName = "JK - WF , E"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 1000;
			int gap = 125; // 박자 계산

			beats = new Beat[] { new Beat(startTime + gap * 1, "S"), };

		} else if (titleName.equals("Joakim Karud - Wild Flower") && difficulty.equals("Hard")) {
			musicName = "JK - WF , H"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 1000;
			int gap = 125; // 박자 계산

			beats = new Beat[] { new Beat(startTime + gap * 1, "S"), };

		} else if (titleName.equals("Bensound - Energy") && difficulty.equals("Easy")) {
			musicName = "B - E , E"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 1000;
			int gap = 125; // 박자 계산

			beats = new Beat[] { new Beat(startTime + gap * 1, "S"), };

		} else if (titleName.equals("Bensound - Energy") && difficulty.equals("Hard")) {
			musicName = "B - E , H"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 1000;
			int gap = 125; // 박자 계산

			beats = new Beat[] { new Beat(startTime + gap * 1, "S"), };

		}

		else if (titleName.equals("Miya - Ask The Wind") && difficulty.equals("Easy")) {
			musicName = "M - ATW , E"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 1000;
			int gap = 125; // 박자 계산

			beats = new Beat[] { new Beat(startTime + gap * 1, "S"), };
		}

		else if (titleName.equals("Miya - Ask The Wind") && difficulty.equals("Hard")) {
			musicName = "M - ATW , H"; // 글자 수 때문에.. 어쩔 수 없음
			int startTime = 4460 - Main.REACH_TIME * 1000; // 항상 똑같은 첫번째 노트가 판정바에 적중하는 박자 타이밍
			int gap = 125; // 박자 계산
			beats = new Beat[] {
					// 수동으로 찍어줘야함.
					new Beat(startTime + gap * 1, "S"), new Beat(startTime + gap * 3, "D"),
					new Beat(startTime + gap * 5, "S"), new Beat(startTime + gap * 7, "D"),
					new Beat(startTime + gap * 9, "S"), new Beat(startTime + gap * 11, "D"),
					new Beat(startTime + gap * 13, "S"), new Beat(startTime + gap * 15, "D"),
					new Beat(startTime + gap * 18, "J"), new Beat(startTime + gap * 20, "K"),
					new Beat(startTime + gap * 22, "J"), new Beat(startTime + gap * 24, "K"),
					new Beat(startTime + gap * 26, "J"), new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"), new Beat(startTime + gap * 32, "K"),
					new Beat(startTime + gap * 35, "S"), new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 39, "S"), new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 43, "S"), new Beat(startTime + gap * 45, "D"),
					new Beat(startTime + gap * 48, "J"), new Beat(startTime + gap * 49, "K"),
					new Beat(startTime + gap * 50, "L"), new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 52, "Space"), new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 54, "S"), new Beat(startTime + gap * 56, "D"),
					new Beat(startTime + gap * 59, "F"), new Beat(startTime + gap * 59, "Space"),
					new Beat(startTime + gap * 59, "J"), new Beat(startTime + gap * 61, "K"),
					new Beat(startTime + gap * 63, "D"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 65, "Space"), new Beat(startTime + gap * 65, "J"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 72, "S"),
					new Beat(startTime + gap * 74, "S"), new Beat(startTime + gap * 78, "J"),
					new Beat(startTime + gap * 79, "K"), new Beat(startTime + gap * 80, "L"),
					new Beat(startTime + gap * 83, "Space"), new Beat(startTime + gap * 85, "S"),
					new Beat(startTime + gap * 87, "D"), new Beat(startTime + gap * 89, "S"),
					new Beat(startTime + gap * 91, "D"), new Beat(startTime + gap * 93, "F"),
					new Beat(startTime + gap * 96, "Space"), new Beat(startTime + gap * 98, "L"),
					new Beat(startTime + gap * 100, "Space"), new Beat(startTime + gap * 102, "S"),
					new Beat(startTime + gap * 103, "D"), new Beat(startTime + gap * 109, "Space"),
					new Beat(startTime + gap * 111, "Space"), new Beat(startTime + gap * 116, "Space"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 119, "D"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 123, "S"),
					new Beat(startTime + gap * 124, "D"), new Beat(startTime + gap * 125, "F"),
					new Beat(startTime + gap * 126, "J"), new Beat(startTime + gap * 127, "K"),
					new Beat(startTime + gap * 130, "J"), new Beat(startTime + gap * 133, "K"),
					new Beat(startTime + gap * 136, "L"), new Beat(startTime + gap * 138, "S"),
					new Beat(startTime + gap * 140, "Space"), new Beat(startTime + gap * 142, "S"),
					new Beat(startTime + gap * 144, "Space"), new Beat(startTime + gap * 146, "Space"),
					new Beat(startTime + gap * 150, "Space"), new Beat(startTime + gap * 152, "Space"),
					new Beat(startTime + gap * 157, "J"), new Beat(startTime + gap * 161, "K"),
					new Beat(startTime + gap * 165, "L"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 169, "D"), new Beat(startTime + gap * 171, "F"),
					new Beat(startTime + gap * 174, "S"), new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 178, "F"), new Beat(startTime + gap * 180, "Space"),
					new Beat(startTime + gap * 181, "L"), new Beat(startTime + gap * 184, "Space"),
					new Beat(startTime + gap * 187, "L"), new Beat(startTime + gap * 188, "K"),
					new Beat(startTime + gap * 189, "J"), new Beat(startTime + gap * 192, "S"),
					new Beat(startTime + gap * 192, "Space"), new Beat(startTime + gap * 196, "D"),
					new Beat(startTime + gap * 196, "Space"), new Beat(startTime + gap * 200, "S"),
					new Beat(startTime + gap * 200, "Space"), new Beat(startTime + gap * 207, "J"),
					new Beat(startTime + gap * 216, "L"), new Beat(startTime + gap * 216, "Space"),
					new Beat(startTime + gap * 218, "Space"), new Beat(startTime + gap * 221, "J"),
					new Beat(startTime + gap * 223, "K"), new Beat(startTime + gap * 225, "L"),
					new Beat(startTime + gap * 227, "Space"), new Beat(startTime + gap * 231, "D"),
					new Beat(startTime + gap * 231, "Space"), new Beat(startTime + gap * 235, "S"),
					new Beat(startTime + gap * 235, "Space"), new Beat(startTime + gap * 242, "S"),
					new Beat(startTime + gap * 242, "Space"), new Beat(startTime + gap * 242, "L"),
					new Beat(startTime + gap * 246, "D"), new Beat(startTime + gap * 246, "Space"),
					new Beat(startTime + gap * 246, "K"), new Beat(startTime + gap * 250, "F"),
					new Beat(startTime + gap * 250, "Space"), new Beat(startTime + gap * 250, "J"),
					new Beat(startTime + gap * 255, "J"), new Beat(startTime + gap * 257, "K"),
					new Beat(startTime + gap * 259, "K"), new Beat(startTime + gap * 262, "S"),
					new Beat(startTime + gap * 262, "Space"), new Beat(startTime + gap * 266, "D"),
					new Beat(startTime + gap * 266, "S"), new Beat(startTime + gap * 266, "Space"),
					new Beat(startTime + gap * 270, "S"), new Beat(startTime + gap * 270, "Space"),
					new Beat(startTime + gap * 275, "J"), new Beat(startTime + gap * 277, "K"),
					new Beat(startTime + gap * 279, "L"), new Beat(startTime + gap * 282, "J"),
					new Beat(startTime + gap * 284, "K"), new Beat(startTime + gap * 286, "L"),
					new Beat(startTime + gap * 289, "J"), new Beat(startTime + gap * 291, "K"),
					new Beat(startTime + gap * 293, "L"), new Beat(startTime + gap * 295, "J"),
					new Beat(startTime + gap * 297, "L"), new Beat(startTime + gap * 299, "D"),
					new Beat(startTime + gap * 301, "S"), new Beat(startTime + gap * 304, "F"),
					new Beat(startTime + gap * 306, "S"), new Beat(startTime + gap * 308, "S"),
					new Beat(startTime + gap * 310, "F"), new Beat(startTime + gap * 312, "D"),
					new Beat(startTime + gap * 314, "S"), new Beat(startTime + gap * 317, "F"),
					new Beat(startTime + gap * 319, "D"), new Beat(startTime + gap * 321, "S"),
					new Beat(startTime + gap * 323, "F"), new Beat(startTime + gap * 325, "D"),
					new Beat(startTime + gap * 327, "S"), new Beat(startTime + gap * 330, "F"),
					new Beat(startTime + gap * 332, "S"), new Beat(startTime + gap * 332, "Space"),
					new Beat(startTime + gap * 336, "D"), new Beat(startTime + gap * 336, "Space"),
					new Beat(startTime + gap * 340, "S"), new Beat(startTime + gap * 340, "Space"), 
			};
		}

		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);

					// 음악재생 gettime확인부분
//					System.out.println("gameMusic: " + gameMusic.getTime());
//					System.out.println("beats: " + beats[i].getTime());

				} catch (Exception e) {
//					e.printStackTrace();
				}
			}

			// 결과창
			if (beats[beats.length - 1].getTime() <= gameMusic.getTime()) {
				close();
				stage = 4;
//				gameresult = new GameResult();
//				gameresult.start();
				Score score1 = getHighScore();
				System.out.println("high:====="+score1);
				if (score1.getHighScore() >= score) {
					highScore = score1.getHighScore();
					// 최고 점수 유지
				} else if (score1.getHighScore() < score) {
					// 최고 점수 수정
					highScore = score;
					if (score1.isStart() == true) { // true 했을때 밑으로
						insertHighScore();
					} else {
						System.out.println("안됨");
						updateHighScore();
					}
				}
			}
		}
	}

	public void judgeKey(String input) {
		for (int i = 0; i < noteList.size(); i++) { // 먼저 입력된 것부터 찾음. 큐처럼 사용
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge()); // 판정을 불러옴.
				break;
			}
		}
	}

//	g.drawString("000000", 565, 702); // 점수 출력

	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
//			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();

		} else if (judge.equals("Late")) {
			score += 100;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeLate.png")).getImage();
		} else if (judge.equals("Good")) {
			score += 300;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();

		} else if (judge.equals("Great")) {
			score += 400;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();

		} else if (judge.equals("Perfect")) {
			score += 500;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();

		} else if (judge.equals("Early")) {
			score += 100;
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
		}

	}

}