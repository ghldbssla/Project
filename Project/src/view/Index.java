package view;

import java.util.Scanner;

public class Index {
	public Index() {
		String start="";
		new CrawLing(start);
		Scanner sc= new Scanner(System.in);
		
		while(true) {
			System.out.println("1. ȸ������\n2. �α���\n3. ��ȭ\n4. ����");
			int choice=sc.nextInt();
			
			//��Ʈ�ѷ�
			if(choice==4) {
				break;
			}
			switch(choice) {
			case 1:
				//ȸ������
				new JoinView();
				break;
			case 2:
				//�α���
				new LoginView(0);
				break;
			case 3:
				new MovieView();
				break;
			}
		}
	}
}
