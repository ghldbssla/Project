package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.MovieDAO;
import dao.Seat;
import dao.Session;

public class MovieChoiceView {
	public MovieChoiceView() {
		//��ȭ �ð���
		//��ȭ �¼� ����
		Seat seat = new Seat();
		MovieDAO mdao = new MovieDAO();
		Scanner sc=new Scanner(System.in);
		CrawLing crawl = new CrawLing();
					
		while(true) {	
			//��ȭ ���� ����Ʈ�� MOVIE_LIST ���̺��� �̾ƿ´�.
			//��ȭ ���� ����Ʈ�� MOVIE_LIST ���̺��� �̾ƿ´�. 
			String movieList = mdao.NowListM_Name();
			System.out.println("======��ȭ ����Ʈ======");
			String[] moviename = movieList.split(";");
			for (int i = 0; i < moviename.length; i++) {
				System.out.print((i+1)+". "+moviename[i]+"\n");
			}
			System.out.println("====================\n");
				
			//���� ����Ʈ���� ���� ������� ��ȭ ����
			//���� ����Ʈ���� ���� ������� ��ȭ ����
			try {
				System.out.print("��ȭ�� �������ּ��� : ");
				String MovieChoice =sc.next();
				if(MovieChoice.equals("�һ���")) {
					String coupon = mdao.coupon();
					System.out.println(coupon+" ���� ��÷");
					mdao.insertCoupon(Session.get("session_id"), coupon);
					new MovieChoiceView();
				}
				//��ȭ ���� > �������� > �󿵰� ���� > �ð� ���� > �¼�����
				
				//��������
				System.out.println("����\t���\t��õ\t����\t����/��û\t�뱸\t�λ�/���\t���\t����/����/����");
				System.out.print("������ �������ּ��� : ");
				String city_view =sc.next();
				
				//�󿵰� ����
				System.out.println("\n"+mdao.choiceTheater(city_view));
				System.out.print(city_view+"���� �ִ� �󿵰��� �������ּ��� : ");
				String cgvCode =sc.next();
				
				//�¼� &�ð� ���
				crawl.find(cgvCode, city_view, MovieChoice);
				System.out.print("���Ͻô� ��ȭ�ð��� �Է����ּ���. ex)14:00\n�ð� �Է�  : ");
				String m_time= sc.next();
				//if(mdao.check_t(cgvCode,MovieChoice,m_time)) {
				int ticketNum = 0;
				System.out.print("�ο� �� : ");
				ticketNum = sc.nextInt();
					
				System.out.println("\n===============�¼� ����===============");
				String[][] seat1 = new String[9][9];
				String[] raw = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
				String[] col = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

				for (int i = 0; i < raw.length; i++) {
					// �¼� �ߺ� �˻� -> mdao
					for (int j = 0; j < col.length; j++) {
						seat1[i][j] = raw[i] + col[j];
						
						if(mdao.check_S(cgvCode,MovieChoice,m_time,seat1[i][j])) {
							//�ߺ��� �¼��� �ִ� ���
							seat1[i][j]="  ";
						}
						System.out.print(seat1[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("=====================================");
				
				System.out.print("���Ͻô� �¼�"+ticketNum+"���� �Է����ּ���\n");
				for (int i = 1; i <= ticketNum; i++) {
					System.out.print(i+"��° �¼����� : ");
					String sit = sc.next();
					mdao.sit_insert(m_time,ticketNum,MovieChoice,cgvCode,sit);
				}
				new TicketingView();
				
			} catch (InputMismatchException ime) {
				System.out.println("��ȣ�� �Է����ּ���.");
			} catch (NumberFormatException nfe) {
				System.out.println("�� �󿵰������� �����Ͻ� ��ȭ�� ������ �ʽ��ϴ�.");
			}	
		}
	}
}