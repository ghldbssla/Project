package view;

import java.util.Scanner;

public class Index {
	public Index() {
		Scanner sc= new Scanner(System.in);
		
		while(true) {
			System.out.println("1. 회원가입\n2. 로그인\n3. 영화\n4. 새로고침\n5. 종료");
			int choice=sc.nextInt();
			
			//컨트롤러
			if(choice==5) {
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
			case 4:
				System.out.println("새로고침하시겠습니까?\n1. 네\t2. 아니오");
				int subchoice = sc.nextInt();
				if (subchoice == 1) {
					String start="";
					new CrawLing(start);
				}else if(subchoice==2) {
					continue;
				}else {
					System.out.println("잘못입력하셨습니다.");
				}
			}
		}
	}
}
