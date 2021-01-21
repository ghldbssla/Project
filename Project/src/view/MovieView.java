package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class MovieView {
	public MovieView() {
		UserDAO udao = new UserDAO();
		System.out.println("MovieView�Դϴ�.");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("1. ���� ���� ����\n2. ���� ������ ����\n3. �����ϱ�\n4. �ڷΰ���");
			int choice = sc.nextInt();

			if (choice == 4) {
				System.out.println("���� ȭ������ ���ư��ϴ�.");
				break;
			}
			switch (choice) {
			case 1:
				// ���� ���� ����
				System.out.println("���� ���� ����");
				new NowMovieView();
				break;
			case 2:
				// ���� ������ ����
				System.out.println("���� ���� ����");
				new SoonMovieView();
				break;
			case 3:
				//�����ϱ�(�α��� -> ����ȭ��)
				new LoginView();
				if(true) {
					new TicketingView();
				}else {
					System.out.println("MovieView �α��� ����");
				}
			}
		}
	}
}
