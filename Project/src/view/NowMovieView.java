package view;

import java.util.Scanner;

import dao.MovieDAO;

public class NowMovieView {
	public NowMovieView() {
		String result="";
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);

		System.out.println("현재 상영중인 영화 목록 페이지 입니다.");
		while (true) {
			System.out.println("1. 상영중인 영화 목록\n2. 장르별 영화 목록\n3. 평점순 영화목록 \n4. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 4) {
				System.out.println("이전 페이지로 이동합니다.");
				break;
			}
			switch (choice) {
			case 1:
				// 상영중인 영화 목록
				result = mdao.NowList();
				if (result == "") {
					System.out.println("===현재 상영중인 영화가 없습니다.===");
				} else {
					System.out.println("===현재 상영중인 영화 목록===");
					System.out.println(result);
				}
				break;
			case 2:
				// 장르별 영화 목록
				result = mdao.NowList_genre();
				if (result == "") {
					System.out.println("===현재 상영중인 영화가 없습니다.===");
				} else {
					System.out.println("===현재 상영중인 영화 목록===");
					System.out.println(result);
				}
				break;
			case 3:
				// 평점순 영화목록
				result = mdao.NowList_rate();
				if (result == "") {
					System.out.println("===현재 상영중인 영화가 없습니다.===");
				} else {
					System.out.println("===현재 상영중인 영화 목록===");
					System.out.println(result);
				}
				break;
			}
		}
	}
}
