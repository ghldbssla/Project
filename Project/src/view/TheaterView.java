package view;

import java.util.Scanner;

import dao.MovieDAO;
import dto.MovieDTO;
import dto.UserDTO;

public class TheaterView {
	public TheaterView() {
		//dao�� ���忡 �´� �� ��ȭ�� ��¥ �ð����� �����ش�.
		MovieDAO mdao = new MovieDAO();
		Scanner sc=new Scanner(System.in);

		while(true) {
			System.out.println("������ġ�� �Է����ּ���.");
			String Theaterlo=sc.next();

			if(Theaterlo==mdao.Movietimedate(Theaterlo)) {

				mdao.Movietimedate(Theaterlo);
				
				new TicketingView();
			}else {
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
			}
		}
	}


}

