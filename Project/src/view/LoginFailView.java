package view;

import java.util.Scanner;

import dao.UserDAO;

public class LoginFailView {
	public static int cnt=0;
	public LoginFailView(int page) {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		while(true) {
			System.out.println("1. 다시 시도하기\n2. 아이디 찾기\n3. 비밀번호 재설정\n4. 나가기");
			int choice = sc.nextInt();
			
			if (choice==4) {break;}
			
			switch(choice) {
			case 1 :
				while(true) {
					cnt++;
					if(cnt>3) {
						//만약 비밀번호 4번이상 틀리면 회원가입하겠냐고 권유
						System.out.println("회원가입하시겠습니까?\n1. 예\n2. 아니요");
						int subchoice = sc.nextInt();
						if(subchoice==1) {
							System.out.println("회원가입창입니다.");
							new JoinView();
						}else {
							//아니요 누르면 cnt초기화 시켜주고 로그인 다시 시도하기
							cnt=0;
							new LoginView(page);
						}
					}else {
						new LoginView(page);
					}
				}
			case 2 :
				//아이디 찾기
				System.out.println("1. 이메일로 찾기\n2. 핸드폰번호로 찾기");
				choice=sc.nextInt();
				if (choice==1) {
					System.out.println("이메일을 입력하세요.");
					String userinfo = sc.next();
					//sql문에 들어갈 idx미리 설정
					udao.find_id(0, userinfo);	
				}
				else if (choice==2) {
					System.out.println("핸드폰번호를 입력하세요 (숫자만 입력하세요).");
					String userinfo = sc.next();
					udao.find_id(1, userinfo);
				}
				break;
			case 3 :
				//비밀번호 재설정
				System.out.print("아이디  : ");
				String userid = sc.next();
				udao.moveTopw(userid);
				break;
			default: 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}