package view;

import java.util.Scanner;

import dao.MovieDAO;
import dto.MovieDTO;
import dto.UserDTO;

public class TheaterView {
	public TheaterView() {

		// dao�� ���忡 �´� �� ��ȭ�� ��¥ �ð����� �����ش�.
		MovieDAO mdao = new MovieDAO();
		Scanner sc = new Scanner(System.in);
		CrawLing crawl = new CrawLing();

		while (true) {
			System.out.print("������ġ�� �Է����ּ���.\nex)���\t����\t��õ\t����\t����/��û\t�뱸\t�λ�/���\t���\t����/����/����\n");
			String Theaterlo = sc.next();
			mdao.findlo(Theaterlo);
			System.out.print("������ �Է����ּ���.\n");
			String cgvCode = sc.next();
			// �´��� Ȯ���ϴ� dao�ʿ�
			// �ش� cgv�� ���� �󿵽ð�ǥ �����ֱ�
			crawl.find(cgvCode, Theaterlo);

			// ��ȭ����, �ð� �Է� �ޱ�
			System.out.print("���Ͻô� ��ȭ������ �Է����ּ���.\n");
			sc.nextLine();
			String m_name= sc.nextLine();
			if(mdao.check_n(cgvCode,m_name)) {
				//��ȭ����, cgv�� ��ġ�ϴ� ������ ã������
				System.out.print("���Ͻô� ��ȭ�ð��� �Է����ּ���. ex)14:00\n");
				String m_time= sc.next();
				if(mdao.check_t(cgvCode,m_name,m_time)) {
					System.out.println("\n===============�¼� ����===============");
				String[][] seat = new String[9][9];
				String[] raw = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
				String[] col = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

				for (int i = 0; i < raw.length; i++) {
					// �¼� �ߺ� �˻� -> mdao
					for (int j = 0; j < col.length; j++) {
						seat[i][j] = raw[i] + col[j];
						
						if(mdao.check_S(cgvCode,m_name,m_time,seat[i][j])) {
							//�ߺ��� �¼��� �ִ� ���
							seat[i][j]="  ";
						}
						System.out.print(seat[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("=====================================");
				// A8
				System.out.print("���Ͻô� �¼��� �Է����ּ���\n");
				String sit = sc.next();

				
				// ���̺� ���� ���� insert -> mdao
				mdao.sit_insert(cgvCode,m_name,m_time,sit);
				// Ƽ���ú信�� delete

				// Ƽ���ú�()
				new TicketingView();
				}else {
					System.out.println("��ġ�ϴ� ������ �����ϴ�.");
				}
			}else {
				System.out.println("��ġ�ϴ� ������ �����ϴ�.");
			}
			// ���ϴ� �޼ҵ� ��������

			
		}

	}

}
