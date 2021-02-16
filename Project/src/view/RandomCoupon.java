package view;

import java.util.Random;

import dao.MovieDAO;
import dao.Session;

public class RandomCoupon {
	public static int cnt = 0;
	
	public RandomCoupon(){
		MovieDAO mdao = new MovieDAO();
		if (cnt<3) {
			String userid = Session.get("session_id");
			Random rand = new Random();
			int r = rand.nextInt(20);
			
			if (r==7) {
				String coupon_name = mdao.coupon();
				System.out.println("�����մϴ�!"+coupon_name+" ������ ��÷ �Ǽ̽��ϴ�.");
				//��÷���� ���� COUPON DB�� �־��ֱ�
				mdao.insertCoupon(userid, coupon_name);
				cnt=3;
				new MainView();
			}else {
				System.out.println("��~! ���� ��ȸ��~");
				cnt++;
				if (cnt==3) {
					System.out.println("���̻� ������ �� �����ϴ�.");
				}else {
					System.out.println((3-cnt)+"���� ��ȸ�� ���ҽ��ϴ�.");
				}
				new MainView();
			}
		}else {
			System.out.println("������ �� �ִ� ��ȸ�� �� �̻� �����ϴ�.");
			new MainView();
		}
	}
}
