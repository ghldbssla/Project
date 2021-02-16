package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class Pay_del {
	public Pay_del() {
		MovieDAO mdao = new MovieDAO();
		Session session = new Session();
		String id = session.get("session_id");
		Scanner sc = new Scanner(System.in);
		String bookList = mdao.payCheck(id, true);
		while (true) {
			System.out.println("1. 전체 취소\n2. 선택 취소\n3. 예매한 목록보기 \n4. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}

			switch (choice) {
			case 1:
				if (bookList.equals("")) {
					System.out.println("예매 하신 영화가 없습니다.");
				} else {
					System.out.println(mdao.payCheck(id, true));
				}
				System.out.println("정말 취소 하시겠습니까? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_a_B(id,true);
				}
				break;
			case 2:
				if (bookList.equals("") || bookList == null) {
					System.out.println("예매 하신 영화가 없습니다.");
				} else {
					System.out.println(mdao.payCheck(id, true));

					System.out.print("삭제하실 영화의 극장을 입력해주세요.\n");
					String cgvCode = sc.next();
					System.out.print("삭제하실 영화제목을 입력해주세요.\n");
					sc.nextLine();
					String m_name = sc.nextLine();
					System.out.print("좌석을 입력해주세요.\n");
					String sit = sc.next();
					if (mdao.check_n(cgvCode, m_name, id, sit, true)) {
						// 영화제목, cgv가 일치하는 정보를 찾았을때
						System.out.print("삭제하실 영화시간을 입력해주세요. ex)14:00\n");
						String m_time = sc.next();
						System.out.println("정말 취소 하시겠습니까? (Y/N)");
						if (sc.next().equals("y") || sc.next().equals("Y")) {
							mdao.delete_c_B(id, cgvCode, m_name, m_time,true);
						}
					} else {
						System.out.println("찾으시는 정보가 없습니다.");
					}
				}
				break;
			case 3:
				if (bookList.equals("") || bookList == null || bookList.equals("\n")) {
					System.out.println("예약 하신 영화가 없습니다.");
				} else {
					System.out.println(mdao.payCheck(id, true));
				}
				break;
			default:
				System.out.println("다시 입력해 주세요.\n");
			}
		}
	}
}

