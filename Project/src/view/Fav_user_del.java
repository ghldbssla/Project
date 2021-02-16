package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class Fav_user_del {
	public Fav_user_del() {
		MovieDAO mdao = new MovieDAO();
		Session session = new Session();
		String id = session.get("session_id");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. 전체 취소\n2. 선택 취소\n3. 찜 목록보기 \n4. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}
			
			switch (choice) {
				
			case 1:
				if (mdao.getlist(id).equals("")) {
					System.out.println("찜 하신 영화가 없습니다.");
				} else {
					System.out.println(mdao.getlist(id));
				}
				System.out.println("정말 취소 하시겠습니까? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_a(id);
				}
				break;
			case 2:
				if (mdao.getlist(id).equals("")) {
					System.out.println("찜 하신 영화가 없습니다.");
				} else {
					System.out.println(mdao.getlist(id));
				}
				System.out.println("취소하실 영화 제목을 입력해 주세요.");
				sc.nextLine();
				String title = sc.nextLine();
				if (!title.equals("")) {
					System.out.println("정말 취소 하시겠습니까? (Y/N)");
					if (sc.next().equals("y") || sc.next().equals("Y")) {
						mdao.delete_c(id,title);
					}
				}
				break;
			case 3:
				if (mdao.getlist(id).equals("")) {
					System.out.println("찜 하신 영화가 없습니다.");
				} else {
					System.out.println(mdao.getlist(id));
				}
				break;
			default:
				System.out.println("다시 입력해 주세요.\n");
			}
		}
	}
}

