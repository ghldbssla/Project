package view;

import java.util.Scanner;

import dao.MovieDAO;

public class NowListView {
	public NowListView() {
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. ���ϱ�\n2. �ڷΰ���");
			int choice = sc.nextInt();
			if (choice == 2) {
				break;
			} else if (choice == 1) {
				new LoginView();
				System.out.println("���ϱ⿡ �߰��� ��ȭ������ �Է����ּ���.");
				sc.nextLine();
				String title=sc.nextLine();
				if (true) {
					mdao.create(title);
				} else {
					System.out.println("�� ���� ��ǰ ���ϱ� �α��� ����");
				}
				break;
			} else {
				System.out.println("�ٽ� �Է��� �ּ���.\n");
			}

		}
	}
}
