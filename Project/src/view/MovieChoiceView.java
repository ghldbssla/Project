package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Seat;

public class MovieChoiceView {
	public MovieChoiceView() {
		//영화 시간대
		//영화 좌석 선택
		Seat seat = new Seat();
		MovieDAO mdao = new MovieDAO();
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println(mdao.NowList());
			
			System.out.print("영화를 선택해주세요.");
			String MovieChoice =sc.next();
			
			//moviecho에 있는게 이름이 같다면 moivesit을 보여주고 자리를 db에 저장해야함
			if(MovieChoice.equals(mdao.MovieChoice(MovieChoice))) {
				
				System.out.println("좌석 선택");
				//에러가 남 -- 가져오는 값이 null임
				System.out.println(seat.Movietimesit(MovieChoice));
				String choice = sc.next();
				
				//db에 저장
				seat.Sit(choice);
				
				new TicketingView();
			}else {
				System.out.println("잘못입력하셨습니다.");
			}
		}
	

	}
}