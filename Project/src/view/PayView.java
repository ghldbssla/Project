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
	    System.out.println("����â�Դϴ�.");
        
	    while (true) {
	    	String coupon_name = "";
	    	int couponPrice = 0;
	    	String userAge = "";
		    int price = 0;
		    boolean payment = false;
	        String userid = Session.get("session_id");
	        
	        System.out.println("====��ȭǥ ����====");
	        System.out.println(mdao.payCheck(userid, payment));
	        System.out.println("���� : 10000��\nû�ҳ� : 8000��");
	        System.out.println("================");
	        
	        String ticketAge = udao.findAge(userid);		          
	        int ticketNum = mdao.bringTicketNum(userid,payment);
	        for (int i = 0; i < 4; i++) {
	       	//�⵵ 4�ڸ��� ����
			userAge+=ticketAge.charAt(i);
	        }
	        int birthYear = Integer.parseInt(userAge);
	        if (birthYear <=2002) {
			price = 10000;
	        }else {
			price = 8000;
	        }
	        System.out.println("�� ������ "+(ticketNum*price)+"�� �Դϴ�.");
	        
	    	System.out.println("���� �Ͻðڽ��ϱ�?\n1. ��\t2. �ƴϿ�");
	    	int choice = sc.nextInt();
	    	int subchoice = 0;
	    	switch (choice) {
	    	case 1:
	            //�����ϱ�
	    		//���� ��� ����
		        System.out.println("������ ����Ͻðڽ��ϱ�?\n1. ��\t2. �ƴϿ�");
		        choice = sc.nextInt();
		        if (choice==1) {
		        	String[] couponList = mdao.bringCoupon(userid).split(";");
		    		for (int i = 0; i < couponList.length; i++) {
						System.out.println((i+1)+". "+couponList[i]);
					}
		    		System.out.println("���� ���� : ");
		    		subchoice = sc.nextInt();
		    		
		    		coupon_name = couponList[subchoice-1];
		    	
		    		if(coupon_name!=null) {
		    			couponPrice = mdao.useCoupon(coupon_name);
		    			System.out.println("������ ���� �Ǿ����ϴ�.");
		    		}
		        }
		        
	    		int usermoney = udao.bringMoney(userid);
	    		int change = usermoney-(ticketNum*price-couponPrice);
	    		if(change>=0) {
	    			cnt++;
	    			System.out.println((ticketNum*price-couponPrice)+"�� �����Ϸ�!\n���� �ܾ��� "+change+"�Դϴ�.");
	    			udao.updateMoney(userid, change);
	    			payment=true;
	    			//�����Ϸ��� DB SCREEN_TABLE�� PAYMENT true�� update�����ֱ� 
	    			mdao.updatePayment(userid, payment);
	    			//����� ������ ���̺��� ���������ֱ�
	    			mdao.deleteCoupon(userid, coupon_name);
	    			//5ȸ �̻� ���Ž� ���� �߱�
	    			if (cnt>=5) {
	    				coupon_name = mdao.coupon();
	    				mdao.insertCoupon(userid, coupon_name);
	    				System.out.println(coupon_name+"������ �߱� �޾ҽ��ϴ�.\n");
	    				cnt=0;
	    			}
	    			new UserInfoView();
	    		}else {
	    			System.out.println("�ܾ��� �����մϴ�.");
	    			System.out.println("�����Ͻðڽ��ϱ�?\t1.��\t2.�ƴϿ�");
	    			subchoice = sc.nextInt();
	    			if(subchoice==1) {
	    				System.out.println("�����Ͻ� �ݾ��� �Է����ּ���.");
	    				int addMoney = sc.nextInt();
	    				int currentM =udao.charge(addMoney,userid);
	    				if (currentM<0) {
	    					System.out.println("�������� �ٽ� �õ����ּ���.");
	    				}else {
	    					System.out.println(addMoney+"�� �����Ǿ����ϴ�.");
	    					System.out.println("���� �ܾ� : "+currentM+"��");
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
