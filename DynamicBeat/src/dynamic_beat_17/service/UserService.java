package dynamic_beat_17.service;

import java.util.List;

import dynamic_beat_17.model.User;

public interface UserService {

	// 등록
	void insert(User user);

	// department_name 수정
	void update(User user);

	// 삭제
	void delete(String userId);

	// 단건조회
	User selectOne(String userId);

	
	// 전체조회
	List<User> selectAll();

}