package view;

import java.util.Scanner;

import dao.MovieDAO;
import dto.MovieDTO;
import dto.UserDTO;

public class TheaterView {
	public TheaterView() {
		//dao로 극장에 맞는 상영 영화와 날짜 시간등을 보여준다.
		MovieDAO mdao = new MovieDAO();
		Scanner sc=new Scanner(System.in);

		while(true) {
			System.out.println("극장위치를 입력해주세요.");
			String Theaterlo=sc.next();

			if(Theaterlo==mdao.Movietimedate(Theaterlo)) {

				mdao.Movietimedate(Theaterlo);
				
				new TicketingView();
			}else {
				System.out.println("잘못입력하셨습니다.");
			}
		}
	}


}

