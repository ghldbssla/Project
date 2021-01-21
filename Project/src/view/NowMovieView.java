package view;

import java.util.Scanner;

import dao.MovieDAO;

public class NowMovieView {
	public NowMovieView() {
		String result="";
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);

		System.out.println("���� ������ ��ȭ ��� ������ �Դϴ�.");
		while (true) {
			System.out.println("1. ������ ��ȭ ���\n2. �帣�� ��ȭ ���\n3. ������ ��ȭ��� \n4. �ڷΰ���");
			int choice = sc.nextInt();
			if (choice == 4) {
				System.out.println("���� �������� �̵��մϴ�.");
				break;
			}
			switch (choice) {
			case 1:
				// ������ ��ȭ ���
				result = mdao.NowList();
				if (result == "") {
					System.out.println("===���� ������ ��ȭ�� �����ϴ�.===");
				} else {
					System.out.println("===���� ������ ��ȭ ���===");
					System.out.println(result);
				}
				break;
			case 2:
				// �帣�� ��ȭ ���
				result = mdao.NowList_genre();
				if (result == "") {
					System.out.println("===���� ������ ��ȭ�� �����ϴ�.===");
				} else {
					System.out.println("===���� ������ ��ȭ ���===");
					System.out.println(result);
				}
				break;
			case 3:
				// ������ ��ȭ���
				result = mdao.NowList_rate();
				if (result == "") {
					System.out.println("===���� ������ ��ȭ�� �����ϴ�.===");
				} else {
					System.out.println("===���� ������ ��ȭ ���===");
					System.out.println(result);
				}
				break;
			}
		}
	}
}
