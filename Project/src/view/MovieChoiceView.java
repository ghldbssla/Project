package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Seat;

public class MovieChoiceView {
	public MovieChoiceView() {
		//��ȭ �ð���
		//��ȭ �¼� ����
		Seat seat = new Seat();
		MovieDAO mdao = new MovieDAO();
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println(mdao.NowList());
			
			System.out.print("��ȭ�� �������ּ���.");
			String MovieChoice =sc.next();
			
			//moviecho�� �ִ°� �̸��� ���ٸ� moivesit�� �����ְ� �ڸ��� db�� �����ؾ���
			if(MovieChoice.equals(mdao.MovieChoice(MovieChoice))) {
				
				System.out.println("�¼� ����");
				//������ �� -- �������� ���� null��
				System.out.println(seat.Movietimesit(MovieChoice));
				String choice = sc.next();
				
				//db�� ����
				seat.Sit(choice);
				
				new TicketingView();
			}else {
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
			}
		}
	

	}
}