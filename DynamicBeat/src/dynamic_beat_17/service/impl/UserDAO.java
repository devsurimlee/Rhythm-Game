package dynamic_beat_17.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dynamic_beat_17.common.DAO;
import dynamic_beat_17.model.User;

public class UserDAO extends DAO{

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// singletone
	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}
	
	// 유저 등록
		public void insert(Connection conn, User user) throws Exception {

			// 1. connect

			// 2. 구문
			String sql = "INSERT INTO GAME_USER (user_id, user_pw) values(?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 3. 파라미터 셋팅
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getPasswd());
			
			// 4. 실행
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 처리되었습니다.");

			// 5. 연결 해제
		}

		
		//유저 정보 수정은 아직 안됨.
//		public void update(Connection conn, User user) throws Exception {
//
//			String sql = "UPDATE game SET  department_name = ? , location_id = ? , manager_id = ? WHERE department_id = ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, dept.getDepartment_name());
//			pstmt.setInt(2, dept.getLocation_id());
//			pstmt.setInt(3, dept.getManager_id());
//			pstmt.setInt(4, dept.getDepartment_id());
//			int r = pstmt.executeUpdate();
//			System.out.println(r + "건 수정완료");
//		}

		
		//유저 삭제도 아직 구현 안됨.
//		public void delete(Connection conn, int dept_id) throws Exception {
//			String sql = "DELETE FROM game_user WHERE id = ? ";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, dept_id);
//			int r = pstmt.executeUpdate();
//			System.out.println(r + "건 삭제완료 ");
//
//		}
	
	

}
