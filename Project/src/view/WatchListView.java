package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class WatchListView {
	public WatchListView() {
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("1. 찜하기\n2. 뒤로가기");
			int choice = sc.nextInt();
			
			if (choice==1) {
				String id =Session.get("session_id");
				if(id==null) {
					new LoginView(-1);
				}else {
					System.out.println("찜하기에 추가할 영화제목을 입력해주세요.");
					sc.nextLine();
					String title = sc.nextLine();
					mdao.create(id,title);	
					//**찜하기 완료! 뜬 후에 다른 페이지 어디로 갈꺼야?
					new UserInfoView();
				}
				break;
			}else if (choice==2) {
				//MovieView에 돌아가기
				break;
			}else {
				System.out.println("다시 입력해 주세요.\n");
			}
		}
	}
}
