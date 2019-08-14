package dynamic_beat_17.service.impl;

import java.sql.Date;

public class Users {
	
	private String userId;
	private String userPw;
	private String role;
	private String birth;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String date) {
		this.birth = date;
	} 

	

}
