package view;

import java.util.Scanner;

import dao.UserDAO;

public class LoginFailView {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		while(true) {
			System.out.println("1. 다시 시도하기\n2. 아이디 찾기\n3. 비밀번호 재설정\n4. 나가기");
			int choice = sc.nextInt();
			
			if (choice==4) {break;}
			
			switch(choice) {
			case 1 :
				new LoginView();
				break;
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
				if(udao.checkid(userid)) {
					String newuserpw="";
					while(true) {
						//비밀번호 사용가능한지 체크
						String checkpassView = "사용가능한 비밀번호입니다.";
						System.out.print("재설정할 비밀번호를 입력하세요 : ");	
						newuserpw=sc.next();
						String checkpass = udao.checkPass(newuserpw);
						System.out.println(checkpass);
						if (checkpass.equals(checkpassView)) {
							break;
						}
					}
					udao.updatepw(userid, newuserpw);
				}
				else {
					System.out.println("존재하지 않는 아이디입니다.");
				}
				break;
			default: 
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
}
