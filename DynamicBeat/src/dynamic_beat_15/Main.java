package dynamic_beat_15;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;  //final은 한번 선언하면 절대 안바뀜, 상수는 전부 대문자
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3;  //노트의 속도는 7
	public static final int SLEEP_TIME = 10; //노트가 10 주기로 떨어짐
	public static final int REACH_TIME = 2; //노트가 생성된 후 판정바에 도달하기 까지의 시간
	
	public static void main(String[] args) {
		
		new DynamicBeat();
		
	}
}
