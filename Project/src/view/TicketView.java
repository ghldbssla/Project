package view;

import java.util.Scanner;

import dao.MovieDAO;

public class TicketView {
	Scanner sc = new Scanner(System.in);


	public TicketView() {

		while(true) {

			System.out.println("1. 상영관 선택\n2. 영화 선택\n3. 무비뷰로 가기");
			int choice = sc.nextInt();

			if(choice==3) {
				new MovieView();
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

			}

		}


	}

}
