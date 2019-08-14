package dynamic_beat_17.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dynamic_beat_17.common.DAO;


public class UserDAOImpl extends DAO implements UserDAO {
	
//    java.util.Date utilDate = new java.util.Date();
//    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	// 싱글톤
	private static UserDAOImpl instance = new UserDAOImpl();

	public static UserDAOImpl getInscance() {
		return instance;
	}

	// 등록
	@Override
	public void insert(Users user) {
//		Date date = null;

		try {
			// 1 디비연결
			this.getConnect();

			// 2 구문 셀이름 안햇갈리게 적어줌
			String sql = "insert into users(userid, userpw, role, birth) "
					+ "values (?, ?, ?, ?)";
			
//			  DateFormat formatter ; 
//			  formatter = new SimpleDateFormat("yyyy-MM-dd");
//			  date = (Date)formatter.parse(user.getBirth());
			
			// 이거쓰면 try/catch 선택버튼생김
			pstmt = conn.prepareStatement(sql);
			// 3 파라미터셋팅
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getRole());
			pstmt.setString(4, user.getBirth());

			// 4 실행
			int r = pstmt.executeUpdate();
			System.out.println(r + "등록완료");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			// 5 연결해제
			this.close(conn);
		}

	}

	// 업데이트
	@Override
	public void update(Users user) {

		try {
			this.getConnect();
			String sql = "update users set userid = ?, userpw = ?, role = ?, birth = ? where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getRole());
			pstmt.setString(4, user.getBirth());
			pstmt.setString(5, user.getUserId());


			int r = pstmt.executeUpdate();
			System.out.println(r + "건 수정완료");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5 연결해제
			this.close(conn);
		}

	}

	// 삭제
	@Override
	public void delete(String userId) {

		try {
			this.getConnect();
			String sql = "delete users where userid = ? ";
			pstmt = conn.prepareStatement(sql);
			
			//! sql 받아온 값을 저장을 안함
			pstmt.setString(1, userId);
			int r = pstmt.executeUpdate();
			System.out.println(r + "건 삭제완료");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5 연결해제
			this.close(conn);
		}

	}

	// 단건조회(1명)
	@Override
	public Users selectOne(String userId) {

		Users user = null;

		try {
			this.getConnect();
			String sql = "select userid, " 
					+ "          userpw," 
					+ "          role,"
					+ "          birth" 
					+ "   from users" 
					+ "   where userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				user = new Users();
				user.setUserId(rs.getString("userid"));
				user.setUserPw(rs.getString("userpw"));
				user.setRole(rs.getString("role"));
				user.setBirth(rs.getString("birth"));
				
				return user;

			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("해당 회원이 없습니다");
		} finally {
			close(conn);
		}

		return user;
	}

	// 전체선택
	@Override
	public List<Users> selectAll() {
		Users user = null;
		List<Users> list = new ArrayList<Users>(); //리턴값을 넣을 배열

		try {
			this.getConnect();
			String sql = "select userid, " 
					+ "          userpw, " 
					+ "          role, "
					+ "          birth " 
					+ "   from users" 
					+ " order by 1";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new Users();
				user.setUserId(rs.getString("userid"));
				user.setUserPw(rs.getString("userpw"));
				user.setRole(rs.getString("role"));
				user.setBirth(rs.getString("birth"));
	
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return list;
	}

}
