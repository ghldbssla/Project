package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class MovieView {
	public MovieView() {
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("MovieView�Դϴ�.");
		while (true) {
			System.out.print("1. ���� ���� ����\n2. ���� ������ ����\n3. �����ϱ�\n4. �ڷΰ���\n");
			int choice = sc.nextInt();

			if (choice == 4) {
				System.out.println("���� ȭ������ ���ư��ϴ�.");
				break;
			}
			switch (choice) {
			case 1:
				// ���� ���� ����
				System.out.println("���� ���� ����");
				System.out.println(mdao.NowList());
				new WatchListView();
				break;
			case 2:
				// ���� ������ ����
				System.out.println("���� ���� ����");
				System.out.println(mdao.SoonList());
				new WatchListView();
				break;
			case 3:
				//�����ϱ�
				if(Session.get("session_id")==null) {
					//��α��λ���
					new LoginView(1);					
				}else {
					//�α��λ���
					new TicketView();
				}
				break;
			default:
				System.out.println("�ٽ� �Է��� �ּ���.\n");
			}
		}
	}
}

