package view;

import java.util.Scanner;

import dao.MovieDAO;

public class NowListView {
	public NowListView() {
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. 찜하기\n2. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 2) {
				break;
			} else if (choice == 1) {
				new LoginView();
				System.out.println("찜하기에 추가할 영화제목을 입력해주세요.");
				sc.nextLine();
				String title=sc.nextLine();
				if (true) {
					mdao.create(title);
				} else {
					System.out.println("상영 중인 작품 찜하기 로그인 실패");
				}
				break;
			} else {
				System.out.println("다시 입력해 주세요.\n");
			}

		}
	}
}
