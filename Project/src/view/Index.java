package view;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. 회원가입\n2. 로그인\n3. 영화 \n4. 나가기");
			int choice = sc.nextInt();
			// 컨트롤러
			if (choice == 4) {
				System.out.println("이용해주셔서 감사합니다.\n안녕히 가세요.");
				break;
			}
			switch (choice) {
			case 1:
				// 회원가입
				new JoinView();
				break;
			case 2:
				// 로그인
				new LoginView();
				break;
			case 3:
				// 영화
				new MovieView();
				break;
			}
		}
	}
}
