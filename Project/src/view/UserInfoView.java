package view;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;

public class UserInfoView {
	public UserInfoView(){
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		while(true) {
			System.out.println("1. 개인정보 수정\n2. 충전하기\n3. 쿠폰함\n4. 예매한 티켓 정보\n5. 찜목록 보기\n6. 메인뷰 가기\n7. 로그아웃");
			choice = sc.nextInt();
			if (choice==6) {new MainView();
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
				String userid = Session.get("session_id");
				mdao.bringCoupon(userid);
				String[] couponList = mdao.bringCoupon(userid).split(";");
	    		for (int i = 0; i < couponList.length; i++) {
					System.out.println((i+1)+". "+couponList[i]);
				}
				break;
			case 4:
				//예매한 티켓 정보
				new MyTicketView();
				break;
			case 5:
				//찜목록 보기
				String[] favList = mdao.watchFavList(Session.get("session_id")).split(";");
				for (int i = 0; i < favList.length; i++) {
					System.out.println((i+1)+". "+favList[i]);
				}
				System.out.println("찜하신 영화를 삭제하시겠습니까?\t1. 예\t2. 아니요");
				choice = sc.nextInt();
				if (choice==1) {
					new Fav_user_del();
				}
				break;
			}
		}
	}
}
