package dynamic_beat_17.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dynamic_beat_17.Main;
import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.Score;
import dynamic_beat_17.service.impl.ScoreDAO;
import dynamic_beat_17.service.impl.UserDAO;

public class Rank extends Thread {
	List<Score> list = new ArrayList<>();
	Score myRank = new Score();
	Score score = null;
	
	public Rank() { // rank 생성자
		Connection conn = DAO.getConnect();
		try {
			list = ScoreDAO.getInscance().rankList(conn);

//			myRank = ScoreDAO.getInscance().myRank(score);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Image screenImage;
	private Graphics screenGraphic;

	private Image rank1 = new ImageIcon(Main.class.getResource("../images/rank/1st.png")).getImage();
	private Image rank2 = new ImageIcon(Main.class.getResource("../images/rank/2rd.png")).getImage();
	private Image rank3 = new ImageIcon(Main.class.getResource("../images/rank/3nd.png")).getImage();

//   String userId;
	int rank;
	String UserPw;

	public String getUserPw() {
		return UserPw;
	}

	public void setUserPw(String userPw) {
		UserPw = userPw;
	}

	public void screenDraw(Graphics2D g) throws SQLException {
		// 총랭킹출력
		for (int i = 0; i < list.size(); i++) {
			String userID = String.format("%20s", list.get(i).getUserid());
			String totalScore = String.format("%10d", list.get(i).getTotalScore());
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.white);
			g.drawString(userID, 450, 275 + (150 * i));
			g.drawString(totalScore, 900, 275 + (150 * i)); // 각각 출력

//			 //내랭킹출력
//         String myID = String.format("%20s", myRank.getUserid());
//         String myScore = String.format("%10d", myRank.getTotalScore());
//         g.setFont(new Font("Arial", Font.BOLD, 30));
//         g.setColor(Color.white);
//         g.drawString(myID, 450, 900);
//         g.drawString(myScore, 900, 900);
		}

		g.drawImage(rank1, 200, 200, null);
		g.drawImage(rank2, 200, 350, null);
		g.drawImage(rank3, 200, 500, null);

	}

	@Override
	public void run() {
	}

	public void close() {
		this.interrupt();
	}

}