package view;

import java.util.Scanner;

public class TicketView {
	Scanner sc = new Scanner(System.in);

	public TicketView() {

		while (true) {

			System.out.println("1. 상영관 선택/n2. 영화 선택/n3. 나가기");
			int choice = sc.nextInt();

			if (choice == 3) {
				System.out.println("이전 화면으로 되돌아갑니다.");
				break;
			}

			switch (choice) {
			case 1:
				// 상영관 선택
				new TheaterView();

				break;
			case 2:
				// 영화 선택
				new MovieChoiceView();
				break;
			default:
				System.out.println("다시 입력해 주세요.\n");
			}

		}

	}

}