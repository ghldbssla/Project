package view;

import java.util.Scanner;

public class TicketingView {
	public TicketingView() {
		System.out.println("티케팅뷰 입니다.");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. 결제하기\n2. 취소하기\n3. 예매된 영화 정보 \n4. 나가기");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				// 결제하기
				new PayView();
				break;
			case 2:
				// 취소하기
				new CancleView();
				break;
			case 3:
				// 에매된 영화 정보
				new MyTicketView();
				break;
			}
		}
	}
}
