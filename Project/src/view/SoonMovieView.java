package view;

import dao.MovieDAO;

public class SoonMovieView {
	public SoonMovieView() {
		MovieDAO mdao = new MovieDAO();
		String result = mdao.SoonList();
		

		
		
		
		if (result == "") {
			System.out.println("===현재 상영중인 영화가 없습니다.===");
		} else {
			System.out.println("===현재 상영중인 영화 목록===");
			System.out.println(result);
		}
		while(true) {
			
			
			
		}
	}
}
