package view;

import java.util.Scanner;

import dao.MovieDAO;

public class MovieChoiceView {
	public MovieChoiceView() {
		//영화 시간대
		//영화 좌석 선택
		MovieDAO mdao = new MovieDAO();
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			mdao.MovieChoice();
			
			System.out.println("영화를 선택해주세요.");
			String MovieChoice =sc.next();
			
			//moviecho에 있는게 이름이 같다면 moivesit을 보여주고 자리를 db에 저장해야함
			if(MovieChoice==mdao.MovieChoice()) {

				System.out.println(mdao.Movietimesit());
				String choice = sc.next();
				
				//db에 저장
				mdao.Sit(choice);
				
				new TicketingView();
			}else {
				System.out.println("잘못입력하셨습니다.");
			}
		}
	

	}
}