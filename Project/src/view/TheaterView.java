package view;

import java.util.Scanner;

import dao.MovieDAO;
import dto.MovieDTO;
import dto.UserDTO;

public class TheaterView {
	public TheaterView() {

		// dao로 극장에 맞는 상영 영화와 날짜 시간등을 보여준다.
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		CrawLing crawl = new CrawLing();

		while (true) {
			System.out.print("극장위치를 입력해주세요.\nex)경기\t서울\t인천\t강원\t대전/충청\t대구\t부산/울산\t경상\t광주/전라/제주\n");
			String Theaterlo = sc.next();
			mdao.findlo(Theaterlo);
			System.out.print("극장을 입력해주세요.\n");
			String cgvCode = sc.next();
			// 맞는지 확인하는 dao필요
			// 해당 cgv의 당일 상영시간표 보여주기
			crawl.find(cgvCode, Theaterlo);

			// 영화제목, 시간 입력 받기
			System.out.print("원하시는 영화제목을 입력해주세요.\n");
			sc.nextLine();
			String m_name= sc.nextLine();
			if(mdao.check_n(cgvCode,m_name)) {
				//영화제목, cgv가 일치하는 정보를 찾았을때
				System.out.print("원하시는 영화시간을 입력해주세요. ex)14:00\n");
				String m_time= sc.next();
				if(mdao.check_t(cgvCode,m_name,m_time)) {
					System.out.println("\n===============좌석 정보===============");
				String[][] seat = new String[9][9];
				String[] raw = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
				String[] col = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

				for (int i = 0; i < raw.length; i++) {
					// 좌석 중복 검사 -> mdao
					for (int j = 0; j < col.length; j++) {
						seat[i][j] = raw[i] + col[j];
						
						if(mdao.check_S(cgvCode,m_name,m_time,seat[i][j])) {
							//중복된 좌석이 있는 경우
							seat[i][j]="  ";
						}
						System.out.print(seat[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("=====================================");
				// A8
				System.out.print("원하시는 좌석을 입력해주세요\n");
				String sit = sc.next();

				
				// 테이블에 예매 정보 insert -> mdao
				mdao.sit_insert(cgvCode,m_name,m_time,sit);
				// 티케팅뷰에서 delete

				// 티케팅뷰()
				new TicketingView();
				}else {
					System.out.println("일치하는 정보가 없습니다.");
				}
			}else {
				System.out.println("일치하는 정보가 없습니다.");
			}
			// 비교하는 메소드 만들어야함

			
		}

	}

}
