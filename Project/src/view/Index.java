package view;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. ȸ������\n2. �α���\n3. ��ȭ \n4. ������");
			int choice = sc.nextInt();
			// ��Ʈ�ѷ�
			if (choice == 4) {
				System.out.println("�̿����ּż� �����մϴ�.\n�ȳ��� ������.");
				break;
			}
			switch (choice) {
			case 1:
				// ȸ������
				new JoinView();
				break;
			case 2:
				// �α���
				new LoginView();
				break;
			case 3:
				// ��ȭ
				new MovieView();
				break;
			}
		}
	}
}
