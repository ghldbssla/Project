package view;

import java.util.Scanner;

public class TicketingView {
	public TicketingView() {
		System.out.println("Ƽ���ú� �Դϴ�.");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. �����ϱ�\n2. ����ϱ�\n3. ���ŵ� ��ȭ ���� \n4. ������");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				// �����ϱ�
				new PayView();
				break;
			case 2:
				// ����ϱ�
				new CancleView();
				break;
			case 3:
				// ���ŵ� ��ȭ ����
				new MyTicketView();
				break;
			}
		}
	}
}