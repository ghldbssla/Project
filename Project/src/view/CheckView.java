package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class CheckView {

	public CheckView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("���̵� : ");
		String userid = sc.next();
		System.out.print("��й�ȣ : ");
		String userpw = sc.next();
		UserDTO session = udao.login(userid, userpw);
		if (session == null) {
			System.out.println("�α��� ���� / �ٽ� �õ����ּ���.");
		} else {
			new TicketingView();
		}
	}
}
