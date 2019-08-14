package dynamic_beat_17.service.impl;

import java.util.List;

public interface UserDAO {

	// 등록
	void insert(Users user);

	// department_name 수정
	void update(Users user);

	// 삭제
	void delete(String userId);

	// 단건조회
	Users selectOne(String userId);

	// 전체조회
	List<Users> selectAll();

}