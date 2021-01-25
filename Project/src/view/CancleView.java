package view;

import java.util.Scanner;

import dao.MovieDAO;

public class CancleView {
	public CancleView() {
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. ��ü ���\n2. ���� ���\n3. �� ��Ϻ��� \n4. �ڷΰ���");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}
			switch (choice) {
			case 1:
				System.out.println("���� ��� �Ͻðڽ��ϱ�? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_a();
				}
				break;
			case 2:
				System.out.println("����Ͻ� ��ȭ ������ �Է��� �ּ���.");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.println("���� ��� �Ͻðڽ��ϱ�? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_c(title);
				}
				break;
			case 3:
				String bookList = mdao.getlist();
				if (bookList.equals("")) {
					System.out.println("�� �Ͻ� ��ȭ�� �����ϴ�.");
				} else {
					System.out.println(mdao.getlist());
				}
				break;
			default:
				System.out.println("�ٽ� �Է��� �ּ���.\n");
			}
		}
	}
}