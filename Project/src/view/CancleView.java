package view;

import java.util.Scanner;

import dao.MovieDAO;

public class CancleView {
	public CancleView() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. �� ��� ����\n2. ������ ���� ����\n3. ���Ÿ�� ����\n4. �ڷΰ���");
			int choice = sc.nextInt();

			// ��Ʈ�ѷ�
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				// �� ��� ����
				new Fav_user_del();
				break;
			case 2:
				// ������ ���� ����
				new Book_del();
				break;
			case 3:
				// ���Ÿ�� ���� ����
				new Pay_del();
				break;
			default:
				System.out.println("�ٽ� �Է��� �ּ���.");
			}
		}
	}
}