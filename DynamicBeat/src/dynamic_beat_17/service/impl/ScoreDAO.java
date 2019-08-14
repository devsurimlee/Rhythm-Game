package dynamic_beat_17.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.Score;

public class ScoreDAO extends DAO {
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// singletone
	private static ScoreDAO instance = new ScoreDAO();

	public static ScoreDAO getInscance() {
		return instance;
	}

	// 점수 등록

	public void insert(Connection conn, Score score) throws SQLException {
		String sql = "INSERT INTO Music (high_score, ID, Music) values(? , ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, score.getHighScore());
		pstmt.setString(2, score.getUserid());
		pstmt.setString(3, score.getMusic());
		int r = pstmt.executeUpdate();
		System.out.println(r + "건 등록완료. ");
	}

	// 최고 점수 수정

	public void update(Connection conn, Score score) throws SQLException {
		String sql = "UPDATE MUSIC SET high_score = ? WHERE ID = ? and music = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, score.getHighScore());
		pstmt.setString(2, score.getUserid());
		pstmt.setString(3, score.getMusic());
		int r = pstmt.executeUpdate();
		System.out.println(r + "건 수정완료");

	}
	
	//곡에 따른 최고 점수 조회
	public Score selectOne(Connection conn, String userid) throws SQLException {
		Score score = null;
		String sql = "SELECT high_score FROM music WHERE id = ? and music = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, score.getUserid());
		pstmt.setString(2, score.getMusic());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			score = new Score();
			score.setHighScore(rs.getInt("high_score"));
			score.setUserid(rs.getString("user_id"));
			score.setMusic(rs.getString("music"));
		}
		return score;
	}
	
	//전체 총점
	public List<Score>/*util로 import*/ selectTotal(Connection conn) throws SQLException {
		List<Score> list = new ArrayList<>();
		Score score = null;
		String sql = "SELECT Id, SUM(HIGH_SCORE) total_score FROM music GROUP BY Id";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
		score = new Score();
		score.setHighScore(rs.getInt("high_score"));
//		score.setUserid(rs.getString("id"));
//		score.setMusic(rs.getString("music"));
		score.setTotalScore(rs.getInt("Total_score"));
		list.add(score);
		}
		return list;
	}

	//전체 총점에 대한 랭킹
	public List<Score> rankList(Connection conn) throws SQLException{
		List<Score> list = new ArrayList<>();
		Score score = null;
		String sql = "SELECT ID, SUM(HIGH_SCORE) score,  RANK() OVER (ORDER BY SUM(HIGH_SCORE) DESC) as rank FROM MUSIC GROUP BY ID";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			score = new Score();
			score.setHighScore(rs.getInt("high_score"));
//			score.setMusic(rs.getString("music"));
			score.setUserid(rs.getString("id"));
			score.setRank(rs.getInt("rank"));
			list.add(score);
		}
		return list;
	}
}
