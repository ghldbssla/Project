package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class Pay_del {
	public Pay_del() {
		MovieDAO mdao = new MovieDAO();
		Session session = new Session();
		String id = session.get("session_id");
		Scanner sc = new Scanner(System.in);
		String bookList = mdao.payCheck(id, true);
		while (true) {
			System.out.println("1. ��ü ���\n2. ���� ���\n3. ������ ��Ϻ��� \n4. �ڷΰ���");
			int choice = sc.nextInt();
			if (choice == 4) {
				break;
			}

			switch (choice) {
			case 1:
				if (bookList.equals("")) {
					System.out.println("���� �Ͻ� ��ȭ�� �����ϴ�.");
				} else {
					System.out.println(mdao.payCheck(id, true));
				}
				System.out.println("���� ��� �Ͻðڽ��ϱ�? (Y/N)");
				if (sc.next().equals("y") || sc.next().equals("Y")) {
					mdao.delete_a_B(id,true);
				}
				break;
			case 2:
				if (bookList.equals("") || bookList == null) {
					System.out.println("���� �Ͻ� ��ȭ�� �����ϴ�.");
				} else {
					System.out.println(mdao.payCheck(id, true));

					System.out.print("�����Ͻ� ��ȭ�� ������ �Է����ּ���.\n");
					String cgvCode = sc.next();
					System.out.print("�����Ͻ� ��ȭ������ �Է����ּ���.\n");
					sc.nextLine();
					String m_name = sc.nextLine();
					System.out.print("�¼��� �Է����ּ���.\n");
					String sit = sc.next();
					if (mdao.check_n(cgvCode, m_name, id, sit, true)) {
						// ��ȭ����, cgv�� ��ġ�ϴ� ������ ã������
						System.out.print("�����Ͻ� ��ȭ�ð��� �Է����ּ���. ex)14:00\n");
						String m_time = sc.next();
						System.out.println("���� ��� �Ͻðڽ��ϱ�? (Y/N)");
						if (sc.next().equals("y") || sc.next().equals("Y")) {
							mdao.delete_c_B(id, cgvCode, m_name, m_time,true);
						}
					} else {
						System.out.println("ã���ô� ������ �����ϴ�.");
					}
				}
				break;
			case 3:
				if (bookList.equals("") || bookList == null || bookList.equals("\n")) {
					System.out.println("���� �Ͻ� ��ȭ�� �����ϴ�.");
				} else {
					System.out.println(mdao.payCheck(id, true));
				}
				break;
			default:
				System.out.println("�ٽ� �Է��� �ּ���.\n");
			}
		}
	}
}

