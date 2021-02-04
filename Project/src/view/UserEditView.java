package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;

public class UserEditView {
	public UserEditView() {
		UserDAO udao = new UserDAO();
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		while(true) {
			System.out.println("수정할 정보\n1. 비밀번호\n2. 이메일\n3. 핸드폰 번호\n4. 주소\n5. 회원 탈퇴\n6. 돌아가기");
			int choice = sc.nextInt();
			if(choice==6) {break;}
			switch(choice) {
			case 1:
				//비밀번호
				System.out.print("새로운 비밀번호 : ");
				String newData = sc.next();
				udao.modify(0,udao.encrypt(newData));
				break;
			case 2:
				//이메일
				System.out.print("새로운 이메일 : ");
				newData = sc.next();
				udao.modify(1,newData);
				break;
			case 3:
				//핸드폰 번호
				System.out.print("새로운 정보 : ");
				newData = sc.next();
				udao.modify(2,newData);
				break;
			case 4:
				//주소
				sc = new Scanner(System.in);
				System.out.print("새로운 정보 : ");
				newData = sc.nextLine();
				udao.modify(3,newData);
				break;
			case 5:
				//회원탈퇴
				int cnt=0;
				while(true) {
					System.out.println("1. 비밀번호 입력\n2. 돌아가기");
					choice = sc.nextInt();
					if(cnt==3) {cnt=0;}
					if(choice==2) {break;}
					switch(choice) {
					case 1:
						System.out.print("비밀번호 재입력 : ");
						String userpw = sc.next();
						if(udao.leaveId(userpw)) {
							System.out.println("이용해 주셔서 감사합니다.");
							Session.put("session_id", null);
							new Index();
						}else {
							if (cnt==0) {
								System.out.println("비밀번호가 잘못되었습니다. 다시 시도해 주세요.");
							}else if(cnt<2){
								System.out.println("비밀번호가 잘못되었습니다. 비밀번호를 재설정하시겠습니까?\n1. 예\t2. 아니요");
								choice=sc.nextInt();
								switch(choice) {
								case 1:
									System.out.print("아이디  : ");
									String userid = sc.next();
									udao.moveTopw(userid);
									break;
								case 2:
									break;
								default:
									System.out.println("잘못 입력하셨습니다.");
									break;
								}
							}else if(cnt==2) {
								System.out.println("비밀번호를 3번 틀리셨습니다. 비밀번호 재설정창으로 가겠습니다.");
								System.out.print("아이디  : ");
								String userid = sc.next();
								udao.moveTopw(userid);
								break;
							}
							cnt++;
						}
						break;
					}
				}
			}
		}
	}
}
