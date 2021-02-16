package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;

public class PayView {
	public static int cnt = 0;
	public PayView() {
		MovieDAO mdao = new MovieDAO();
	    UserDAO udao = new UserDAO();
	    Scanner sc = new Scanner(System.in);
	    System.out.println("결제창입니다.");
        
	    while (true) {
	    	String coupon_name = "";
	    	int couponPrice = 0;
	    	String userAge = "";
		    int price = 0;
		    boolean payment = false;
	        String userid = Session.get("session_id");
	        
	        System.out.println("====영화표 정보====");
	        System.out.println(mdao.payCheck(userid, payment));
	        System.out.println("성인 : 10000원\n청소년 : 8000원");
	        System.out.println("================");
	        
	        String ticketAge = udao.findAge(userid);		          
	        int ticketNum = mdao.bringTicketNum(userid,payment);
	        for (int i = 0; i < 4; i++) {
	       	//년도 4자리수 저장
			userAge+=ticketAge.charAt(i);
	        }
	        int birthYear = Integer.parseInt(userAge);
	        if (birthYear <=2002) {
			price = 10000;
	        }else {
			price = 8000;
	        }
	        System.out.println("총 가격은 "+(ticketNum*price)+"원 입니다.");
	        
	    	System.out.println("결제 하시겠습니까?\n1. 네\t2. 아니오");
	    	int choice = sc.nextInt();
	    	int subchoice = 0;
	    	switch (choice) {
	    	case 1:
	            //결제하기
	    		//쿠폰 사용 여부
		        System.out.println("쿠폰을 사용하시겠습니까?\n1. 예\t2. 아니요");
		        choice = sc.nextInt();
		        if (choice==1) {
		        	String[] couponList = mdao.bringCoupon(userid).split(";");
		    		for (int i = 0; i < couponList.length; i++) {
						System.out.println((i+1)+". "+couponList[i]);
					}
		    		System.out.println("쿠폰 선택 : ");
		    		subchoice = sc.nextInt();
		    		
		    		coupon_name = couponList[subchoice-1];
		    	
		    		if(coupon_name!=null) {
		    			couponPrice = mdao.useCoupon(coupon_name);
		    			System.out.println("쿠폰이 적용 되었습니다.");
		    		}
		        }
		        
	    		int usermoney = udao.bringMoney(userid);
	    		int change = usermoney-(ticketNum*price-couponPrice);
	    		if(change>=0) {
	    			cnt++;
	    			System.out.println((ticketNum*price-couponPrice)+"원 결제완료!\n남은 잔액은 "+change+"입니다.");
	    			udao.updateMoney(userid, change);
	    			payment=true;
	    			//결제완료후 DB SCREEN_TABLE의 PAYMENT true로 update시켜주기 
	    			mdao.updatePayment(userid, payment);
	    			//사용한 쿠폰은 테이블에서 삭제시켜주기
	    			mdao.deleteCoupon(userid, coupon_name);
	    			//5회 이상 예매시 쿠폰 발급
	    			if (cnt>=5) {
	    				coupon_name = mdao.coupon();
	    				mdao.insertCoupon(userid, coupon_name);
	    				System.out.println(coupon_name+"쿠폰을 발급 받았습니다.\n");
	    				cnt=0;
	    			}
	    			new UserInfoView();
	    		}else {
	    			System.out.println("잔액이 부족합니다.");
	    			System.out.println("충전하시겠습니까?\t1.예\t2.아니요");
	    			subchoice = sc.nextInt();
	    			if(subchoice==1) {
	    				System.out.println("충전하실 금액을 입력해주세요.");
	    				int addMoney = sc.nextInt();
	    				int currentM =udao.charge(addMoney,userid);
	    				if (currentM<0) {
	    					System.out.println("충전오류 다시 시도해주세요.");
	    				}else {
	    					System.out.println(addMoney+"원 충전되었습니다.");
	    					System.out.println("현재 잔액 : "+currentM+"원");
	    				}
	    			}
	    		}
	      		break;
	    	case 2:
	    		new CancleView();
	            break;
	       }
	    }
	}
}
