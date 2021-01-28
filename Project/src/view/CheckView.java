package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class CheckView {

	public CheckView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		UserDTO session = udao.login(userid, userpw);
		if (session == null) {
			System.out.println("로그인 실패 / 다시 시도해주세요.");
		} else {
			new TicketingView();
		}
	}
}
