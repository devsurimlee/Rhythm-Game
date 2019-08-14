package dynamic_beat_17.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserFrame {
	Scanner sc = new Scanner(System.in);

	
	public UserFrame() {
	
		try {
			while (true) {
				menuPrint();
				int menuNo = menuSelect();
				if (menuNo == 1) {
					insertUser();
				} else if (menuNo == 2) {
					updateUser();
				} else if (menuNo == 3) {
					deleteUser();
				} else if (menuNo == 4) {
					selectOn();
				} else if (menuNo == 5) {
					selectAll();
				} else if (menuNo == 9) {
					end();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void menuPrint() {
		System.out.println("================================================");
		System.out.println("1.회원등록  2.수정  3.삭제  4.회원조회  5.회원전체조회  9.종료");
		System.out.println("================================================");

	}

	private int menuSelect() {
		int menuNO = sc.nextInt();
		sc.nextLine();
		System.out.println("선택 >> " + menuNO);
		return menuNO;
	}

	private void insertUser() {

		try {
			System.out.println("아이디>");
			String userId = sc.nextLine();
			System.out.println("비밀번호>");
			String userPw = sc.nextLine();
			System.out.println("User / Admin>");
			String role = sc.nextLine();
			System.out.println("생일>");
			String birth = sc.nextLine();

			Users user = new Users();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setRole(role);
			user.setBirth(birth);

			UserDAOImpl.getInscance().insert(user);
			System.out.println("1건 등록 완료");
		} catch (Exception e) {
			sc = new Scanner(System.in);
			System.out.println("잘못 입력하셨습니다");
		}
	}

	private void updateUser() {

		try {
			System.out.println("아이디>");
			String userId = sc.nextLine();
			System.out.println("비밀번호>");
			String userPw = sc.nextLine();
			System.out.println("User / Admin>");
			String role = sc.nextLine();
			System.out.println("생일>");
			String birth = sc.nextLine();

			Users user = new Users();
			user.setUserId(userId);
			user.setUserPw(userPw);
			user.setRole(role);
			user.setBirth(birth);

			UserDAOImpl.getInscance().update(user);

		} catch (Exception e) {
			sc = new Scanner(System.in);
			System.out.println("잘못 입력하셨습니다");
		}

	}

	void deleteUser() {
		System.out.println("아이디>");
		String userId = sc.nextLine();
		UserDAOImpl.getInscance().delete(userId);

	}

	void selectOn() {
		System.out.println("아이디>");
		
		String userId = sc.nextLine();
		Users user = UserDAOImpl.getInscance().selectOne(userId);

		System.out.printf("검색결과는 아이디:%s  비밀번호:%s  회원분류:%s, 가입일:%s \n", user.getUserId(), user.getUserPw(), 
				user.getRole(), user.getBirth());
	}

	void selectAll() {
		List<Users> list = UserDAOImpl.getInscance().selectAll();
		for (Users temp : list) {
			System.out.printf("%s:  %s  %s  %s \n", temp.getUserId(), temp.getUserPw(),
					temp.getRole(), temp.getBirth());
		}

	}

	void end() {
		System.out.println("프로그램을 종료합니다");
		System.exit(0);
	}

}
