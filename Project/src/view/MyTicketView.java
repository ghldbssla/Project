package view;

import dao.MovieDAO;
import dao.Session;

public class MyTicketView {
	MovieDAO mdao = new  MovieDAO();
	public MyTicketView() {
		System.out.println("\"예매된 영화 정보\"입니다.");
		mdao.myticket(Session.get("session_id"));
	}
}
