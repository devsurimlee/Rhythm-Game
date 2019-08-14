package dynamic_beat_17;

import javax.swing.JFrame;

import dynamic_beat_17.control.DynamicBeat;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;  //final은 한번 선언하면 절대 안바뀜, 상수는 전부 대문자
	public static final int SCREEN_HEIGHT = 750;
	public static final int NOTE_SPEED = 3;  //노트의 속도는 7
	public static final int SLEEP_TIME = 5; //노트가 10 주기로 떨어짐
	public static final int REACH_TIME = 4; //노트가 생성된 후 판정바에 도달하기 까지의 시간
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Dynamic Beat");
		frame.setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		frame.setResizable(false); // 한번 만들어진 창은 사용자가 임의로 줄이거나 늘릴 수 없다.
		frame.setLocationRelativeTo(null); // 실행시 게임 화면이 정 중앙에 뜨게 설정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 창을 닫을때 프로그램 전체가 종료되는것
		frame.add(new DynamicBeat());
		frame.setVisible(true);
	}
}
