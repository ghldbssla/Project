package view;

import java.util.Scanner;

import dao.MovieDAO;

public class TicketView {
	Scanner sc = new Scanner(System.in);


	public TicketView() {

		while(true) {

			System.out.println("1. �󿵰� ����\n2. ��ȭ ����\n3. ������ ����");
			int choice = sc.nextInt();

			if(choice==3) {
				new MovieView();
			}

			switch (choice) {
			case 1:
				// �󿵰� ����
				new TheaterView();

				break;
			case 2:
				// ��ȭ ����
				new MovieChoiceView();
				break;

			}

		}


	}

}
