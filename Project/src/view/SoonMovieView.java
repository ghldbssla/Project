package view;

import dao.MovieDAO;

public class SoonMovieView {
	public SoonMovieView() {
		MovieDAO mdao = new MovieDAO();
		String result = mdao.SoonList();
		

		
		
		
		if (result == "") {
			System.out.println("===���� ������ ��ȭ�� �����ϴ�.===");
		} else {
			System.out.println("===���� ������ ��ȭ ���===");
			System.out.println(result);
		}
		while(true) {
			
			
			
		}
	}
}
