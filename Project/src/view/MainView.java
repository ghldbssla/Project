package view;

import java.util.Scanner;

public class MainView {
	public MainView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. ����������\n2. ��ȭ\n3. ���� �̺�Ʈ\n4. �ڷΰ���");
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			new UserInfoView();
			break;
		case 2:
			new MovieView();
			break;
		case 3:
			//�����ϰ� �������� 
			new RandomCoupon();
			break;
		case 4:
			new LoginView(0);
			break;
		}
	}
}
