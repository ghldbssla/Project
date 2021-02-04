package view;

import java.util.Scanner;

public class Index {
	public Index() {
		String start="";
		new CrawLing(start);
		Scanner sc= new Scanner(System.in);
		
		while(true) {
			System.out.println("1. 회원가입\n2. 로그인\n3. 영화\n4. 종료");
			int choice=sc.nextInt();
			
			//컨트롤러
			if(choice==4) {
				break;
			}
			switch(choice) {
			case 1:
				//회원가입
				new JoinView();
				break;
			case 2:
				//로그인
				new LoginView(0);
				break;
			case 3:
				new MovieView();
				break;
			}
		}
	}
}
