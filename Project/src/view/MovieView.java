package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class MovieView {
	public MovieView() {
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("MovieView입니다.");
		while (true) {
			System.out.print("1. 현재 상영작 정보\n2. 개봉 예정작 정보\n3. 예매하기\n4. 뒤로가기\n");
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
				new WatchListView();
				break;
			case 2:
				// 개봉 예정작 정보
				System.out.println("개봉 상영작 정보");
				System.out.println(mdao.SoonList());
				new WatchListView();
				break;
			case 3:
				//예매하기
				if(Session.get("session_id")==null) {
					//비로그인상태
					new LoginView(1);					
				}else {
					//로그인상태
					new TicketView();
				}
				break;
			default:
				System.out.println("다시 입력해 주세요.\n");
			}
		}
	}
}

