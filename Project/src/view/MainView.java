package view;

import java.util.Scanner;

public class MainView {
	public MainView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. 마이페이지\n2. 영화\n3. 쿠폰 이벤트\n4. 뒤로가기");
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			new UserInfoView();
			break;
		case 2:
			new MovieView();
			break;
		case 3:
			//랜덤하게 들어왔을때 
			new RandomCoupon();
			break;
		case 4:
			new LoginView(0);
			break;
		}
	}
}
