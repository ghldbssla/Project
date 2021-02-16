package view;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class TicketingView {
	public TicketingView() {
		System.out.println("티케팅뷰 입니다.");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. 결제하기\n2. 취소하기\n3. 예매된 영화 정보 \n4. 나가기");
			int choice = sc.nextInt();
			if (choice == 4) {
				System.out.println("이전 화면으로 되돌아갑니다.");
				new TicketView();
			}
			switch (choice) {
			case 1:
				// 결제하기
				new PayView();
				break;
			case 2:
				// 결제된 티켓 취소하기
				new CancleView();
				break;
			case 3:
				// 예매된 영화 정보
				new MyTicketView();
				break;
			}
		}
	}
}
