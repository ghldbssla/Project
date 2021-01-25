package view;

import java.util.Scanner;

import dao.MovieDAO;

public class CancleView {
	public CancleView() {
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. 전체 취소\n2. 선택 취소\n3. 찜 목록보기 \n4. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				System.out.println("정말 취소 하시겠습니까? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_a();
				}
				break;
			case 2:
				System.out.println("취소하실 영화 제목을 입력해 주세요.");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.println("정말 취소 하시겠습니까? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_c(title);
				}
				break;
			case 3:
				String bookList = mdao.getlist();
				if (bookList.equals("")) {
					System.out.println("찜 하신 영화가 없습니다.");
				} else {
					System.out.println(mdao.getlist());
				}
				break;
			default:
				System.out.println("다시 입력해 주세요.\n");
			}
		}
	}
}