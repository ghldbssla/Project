package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class Fav_user_del {
	public Fav_user_del() {
		MovieDAO mdao = new MovieDAO();
		Session session = new Session();
		String id = session.get("session_id");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("1. ��ü ���\n2. ���� ���\n3. �� ��Ϻ��� \n4. �ڷΰ���");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}
			
			switch (choice) {
				
			case 1:
				if (mdao.getlist(id).equals("")) {
					System.out.println("�� �Ͻ� ��ȭ�� �����ϴ�.");
				} else {
					System.out.println(mdao.getlist(id));
				}
				System.out.println("���� ��� �Ͻðڽ��ϱ�? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_a(id);
				}
				break;
			case 2:
				if (mdao.getlist(id).equals("")) {
					System.out.println("�� �Ͻ� ��ȭ�� �����ϴ�.");
				} else {
					System.out.println(mdao.getlist(id));
				}
				System.out.println("����Ͻ� ��ȭ ������ �Է��� �ּ���.");
				sc.nextLine();
				String title = sc.nextLine();
				if (!title.equals("")) {
					System.out.println("���� ��� �Ͻðڽ��ϱ�? (Y/N)");
					if (sc.next().equals("y") || sc.next().equals("Y")) {
						mdao.delete_c(id,title);
					}
				}
				break;
			case 3:
				if (mdao.getlist(id).equals("")) {
					System.out.println("�� �Ͻ� ��ȭ�� �����ϴ�.");
				} else {
					System.out.println(mdao.getlist(id));
				}
				break;
			default:
				System.out.println("�ٽ� �Է��� �ּ���.\n");
			}
		}
	}
}

