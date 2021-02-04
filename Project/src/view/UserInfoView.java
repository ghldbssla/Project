package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;

public class UserInfoView {
	public UserInfoView(){
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		UserDAO udao = new UserDAO();
		while(true) {
			System.out.println("1. 개인정보 수정\n2. 충전하기\n3. 쿠폰함\n4. 예매한 티켓 정보\n5. 찜목록 보기\n6. 뒤로가기\n7. 로그아웃");
			choice = sc.nextInt();
			if (choice==6) {break;
			}else if(choice==7) {
				//로그아웃
					System.out.println("로그아웃 합니다.");
					Session.put("session_id", null);
					new Index();
				}
			
			switch(choice) {
			case 1:
				//개인정보 수정
				new UserEditView();
				break;
			case 2:
				//충전하기
				System.out.println("충전하실 금액을 입력해주세요.");
				int addMoney = sc.nextInt();
				String userId = Session.get("session_id");
				int currentM =udao.charge(addMoney,userId);
				if (currentM<0) {
					System.out.println("충전오류 다시 시도해주세요.");
				}else {
					System.out.println(addMoney+"원 충전되었습니다.");
					System.out.println("현재 잔액 : "+currentM+"원");
				}
				
				break;
			case 3:
				//쿠폰함
				break;
			case 4:
				//예매한 티켓 정보
				break;
			case 5:
				//찜목록 보기
				break;
			}
		}
	}
}
