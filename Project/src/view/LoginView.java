package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView() {
		boolean loginflag = false;
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		if(false) {
			loginflag = false;
			//아이디 비밀번호 입력시 로그인 실패시 1)존재하지 않는 아이디입니다 2)잘못된 비밀번호입니다 라고 뜨게 하고싶음
			//문제는 login메소드를 boolean 값으로 바꿔주면 session을 사용못함.
			System.out.println("로그인 실패 / 다시 시도해주세요.");
			while(true) {
				System.out.println("1. 다시 시도하기/n2. 아이디 찾기/n3. 비밀번호 재설정/n4. 나가기");
				int choice = sc.nextInt();
				if (choice==4) {break;}
				switch(choice) {
				case 1 :
					//아이디어??? 몇번시도실패하면 계정라크??
					//cnt 이용해서?
					new LoginView();
					break;
				case 2 :
					//아이디 찾기
					System.out.println("1. 이메일로 찾기/n2. 핸드폰번호로 찾기");
					choice=sc.nextInt();
					if (choice==1) {
						System.out.print("이메일을 입력하세요");
						String userinfo = sc.next();
						udao.find_id(choice, userinfo);	
					}
					else if (choice==2) {
						System.out.print("핸드폰번호를 입력하세요 (숫자만 입력하세요)");
						String userinfo = sc.next();
						udao.find_id(choice, userinfo);
					}
					break;
				case 3 :
					//비밀번호 재설정
					System.out.println("아이디  : ");
					userid = sc.next();
					if(udao.checkid(userid)) {
						System.out.println("재설정할 비밀번호를 입력하세요 : ");	
						userpw=sc.next();
					}
					else {
						System.out.println("존재하지 않는 아이디입니다.");
					}
					break;
				default: 
					System.out.println("잘못 입력하셨습니다.");
				}
			}			
		}else {
			loginflag = true;
			System.out.println("로그인 성공!");
		}
	}
}
