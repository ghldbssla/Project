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
			System.out.println("1. �������� ����\n2. �����ϱ�\n3. ������\n4. ������ Ƽ�� ����\n5. ���� ����\n6. ���κ� ����\n7. �α׾ƿ�");
			choice = sc.nextInt();
			if (choice==6) {new MainView();
			}else if(choice==7) {
				//�α׾ƿ�
					System.out.println("�α׾ƿ� �մϴ�.");
					Session.put("session_id", null);
					new Index();
				}
			
			switch(choice) {
			case 1:
				//�������� ����
				new UserEditView();
				break;
			case 2:
				//�����ϱ�
				System.out.println("�����Ͻ� �ݾ��� �Է����ּ���.");
				int addMoney = sc.nextInt();
				String userId = Session.get("session_id");
				int currentM =udao.charge(addMoney,userId);
				if (currentM<0) {
					System.out.println("�������� �ٽ� �õ����ּ���.");
				}else {
					System.out.println(addMoney+"�� �����Ǿ����ϴ�.");
					System.out.println("���� �ܾ� : "+currentM+"��");
				}
				
				break;
			case 3:
				//������
				String userid = Session.get("session_id");
				mdao.bringCoupon(userid);
				String[] couponList = mdao.bringCoupon(userid).split(";");
	    		for (int i = 0; i < couponList.length; i++) {
					System.out.println((i+1)+". "+couponList[i]);
				}
				break;
			case 4:
				//������ Ƽ�� ����
				new MyTicketView();
				break;
			case 5:
				//���� ����
				String[] favList = mdao.watchFavList(Session.get("session_id")).split(";");
				for (int i = 0; i < favList.length; i++) {
					System.out.println((i+1)+". "+favList[i]);
				}
				System.out.println("���Ͻ� ��ȭ�� �����Ͻðڽ��ϱ�?\t1. ��\t2. �ƴϿ�");
				choice = sc.nextInt();
				if (choice==1) {
					new Fav_user_del();
				}
				break;
			}
		}
	}
}
