package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.UserDAO;
import dto.UserDTO;

public class MovieView {
	public MovieView() {
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		System.out.println("MovieView입니다.");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("1. 현재 상영작 정보\n2. 개봉 예정작 정보\n3. 예매하기\n4. 뒤로가기");
			int choice = sc.nextInt();

			if (choice == 4) {
				System.out.println("이전 화면으로 돌아갑니다.");
				break;
			}
			switch (choice) {
			case 1:
				// 현재 상영작 정보
				System.out.println("현재 상영작 정보");
				System.out.println(mdao.NowList());
				break;
			case 2:
				// 개봉 예정작 정보
				System.out.println("개봉 상영작 정보");
				System.out.println(mdao.SoonList());
				break;
			case 3:
				//예매하기(로그인 -> 예매화면)
				new LoginView();
				if(true) {
					new TicketingView();
				}else {
					System.out.println("MovieView 로그인 실패");
				}
			}
		}
	}
}
