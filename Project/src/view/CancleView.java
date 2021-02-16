package view;

import java.util.Scanner;

import dao.MovieDAO;

public class CancleView {
	public CancleView() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. 찜 목록 삭제\n2. 예약목록 내역 삭제\n3. 예매목록 삭제\n4. 뒤로가기");
			int choice = sc.nextInt();

			// 컨트롤러
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				// 찜 목록 삭제
				new Fav_user_del();
				break;
			case 2:
				// 예약목록 내역 삭제
				new Book_del();
				break;
			case 3:
				// 예매목록 내역 삭제
				new Pay_del();
				break;
			default:
				System.out.println("다시 입력해 주세요.");
			}
		}
	}
}