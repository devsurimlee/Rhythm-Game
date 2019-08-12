package dynamic_beat_1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame {

	public DynamicBeat() /*생성자*/{
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); //한번 만들어진 창은 사용자가 임의로 줄이거나 늘릴 수 없다.
		setLocationRelativeTo(null); //실행시 게임 화면이 정 중앙에 뜨게 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임 창을 닫을때 프로그램 전체가 종료되는것
		setVisible(true); //게임 화면이 정상적으로 출력되도록(보이게 하도록)
	}
}
