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
				System.out.println("축하합니다!"+coupon_name+" 쿠폰에 당첨 되셨습니다.");
				//당첨받은 쿠폰 COUPON DB에 넣어주기
				mdao.insertCoupon(userid, coupon_name);
				cnt=3;
				new MainView();
			}else {
				System.out.println("꽝~! 다음 기회에~");
				cnt++;
				if (cnt==3) {
					System.out.println("더이상 참여할 수 없습니다.");
				}else {
					System.out.println((3-cnt)+"번의 기회가 남았습니다.");
				}
				new MainView();
			}
		}else {
			System.out.println("참여할 수 있는 기회가 더 이상 없습니다.");
			new MainView();
		}
	}
}
