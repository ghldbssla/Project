package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class MyTicketView {
	MovieDAO mdao = new  MovieDAO();
	public MyTicketView() {
	      MovieDAO mdao = new MovieDAO();
	      
	      Scanner sc = new Scanner(System.in);
	      
	      
	      System.out.println("예매된 영화 정보 입니다.");
	      
	       String userid = Session.get("session_id");
	           
	           System.out.println("====영화표 정보====");
	           System.out.println(mdao.myticket2(userid));
	           System.out.println("================");
	   }
}
