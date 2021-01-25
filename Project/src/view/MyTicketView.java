package view;

import dao.MovieDAO;
import dao.Session;

public class MyTicketView {
	MovieDAO mdao = new  MovieDAO();
	public MyTicketView() {
		mdao.myticket(Session.get("session_id"));
	}
}
