package view;

import java.util.Scanner;

public class Index {
	public Index() {
		Scanner sc= new Scanner(System.in);
		
		while(true) {
			System.out.println("1. ȸ������\n2. �α���\n3. ��ȭ\n4. ���ΰ�ħ\n5. ����");
			int choice=sc.nextInt();
			
			//��Ʈ�ѷ�
			if(choice==5) {
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
			case 4:
				System.out.println("���ΰ�ħ�Ͻðڽ��ϱ�?\n1. ��\t2. �ƴϿ�");
				int subchoice = sc.nextInt();
				if (subchoice == 1) {
					String start="";
					new CrawLing(start);
				}else if(subchoice==2) {
					continue;
				}else {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
				}
			}
		}
	}
}
