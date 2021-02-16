package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.MovieDAO;
import dao.Seat;
import dao.Session;

public class MovieChoiceView {
	public MovieChoiceView() {
		//영화 시간대
		//영화 좌석 선택
		Seat seat = new Seat();
		MovieDAO mdao = new MovieDAO();
		Scanner sc=new Scanner(System.in);
		CrawLing crawl = new CrawLing();
					
		while(true) {	
			//영화 제목 리스트를 MOVIE_LIST 테이블에서 뽑아온다.
			//영화 제목 리스트를 MOVIE_LIST 테이블에서 뽑아온다. 
			String movieList = mdao.NowListM_Name();
			System.out.println("======영화 리스트======");
			String[] moviename = movieList.split(";");
			for (int i = 0; i < moviename.length; i++) {
				System.out.print((i+1)+". "+moviename[i]+"\n");
			}
			System.out.println("====================\n");
				
			//위의 리스트에서 내가 보고싶은 영화 선택
			//위의 리스트에서 내가 보고싶은 영화 선택
			try {
				System.out.print("영화를 선택해주세요 : ");
				String MovieChoice =sc.next();
				if(MovieChoice.equals("불사조")) {
					String coupon = mdao.coupon();
					System.out.println(coupon+" 쿠폰 당첨");
					mdao.insertCoupon(Session.get("session_id"), coupon);
					new MovieChoiceView();
				}
				//영화 선택 > 지역선택 > 상영관 선택 > 시간 선택 > 좌석선택
				
				//지역선택
				System.out.println("서울\t경기\t인천\t강원\t대전/충청\t대구\t부산/울산\t경상\t광주/전라/제주");
				System.out.print("지역를 선택해주세요 : ");
				String city_view =sc.next();
				
				//상영관 선택
				System.out.println("\n"+mdao.choiceTheater(city_view));
				System.out.print(city_view+"내에 있는 상영관을 선택해주세요 : ");
				String cgvCode =sc.next();
				
				//좌석 &시간 출력
				crawl.find(cgvCode, city_view, MovieChoice);
				System.out.print("원하시는 영화시간을 입력해주세요. ex)14:00\n시간 입력  : ");
				String m_time= sc.next();
				//if(mdao.check_t(cgvCode,MovieChoice,m_time)) {
				int ticketNum = 0;
				System.out.print("인원 수 : ");
				ticketNum = sc.nextInt();
					
				System.out.println("\n===============좌석 정보===============");
				String[][] seat1 = new String[9][9];
				String[] raw = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
				String[] col = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

				for (int i = 0; i < raw.length; i++) {
					// 좌석 중복 검사 -> mdao
					for (int j = 0; j < col.length; j++) {
						seat1[i][j] = raw[i] + col[j];
						
						if(mdao.check_S(cgvCode,MovieChoice,m_time,seat1[i][j])) {
							//중복된 좌석이 있는 경우
							seat1[i][j]="  ";
						}
						System.out.print(seat1[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("=====================================");
				
				System.out.print("원하시는 좌석"+ticketNum+"개를 입력해주세요\n");
				for (int i = 1; i <= ticketNum; i++) {
					System.out.print(i+"번째 좌석선택 : ");
					String sit = sc.next();
					mdao.sit_insert(m_time,ticketNum,MovieChoice,cgvCode,sit);
				}
				new TicketingView();
				
			} catch (InputMismatchException ime) {
				System.out.println("번호만 입력해주세요.");
			} catch (NumberFormatException nfe) {
				System.out.println("이 상영관에서는 선택하신 영화를 상영하지 않습니다.");
			}	
		}
	}
}