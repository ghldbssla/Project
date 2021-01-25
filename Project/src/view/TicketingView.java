package view;

import java.util.Scanner;

public class TicketingView {
	public TicketingView() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1.결제하기\n2. 취소하기\n3. 예매정보 확인\n4. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 4) {
				new TicketView();
			}
			switch (choice) {
			case 1:
				//결제하기
				new PayView();
				break;
			case 2:
				//취소하기
				new CancleView();
				break;
			case 3:
				//예매정보 확인
				new MyTicketView();
				break;
			}
		}
	}
}
