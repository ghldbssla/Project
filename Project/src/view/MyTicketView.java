package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.Session;

public class MyTicketView {
	MovieDAO mdao = new  MovieDAO();
	public MyTicketView() {
	      MovieDAO mdao = new MovieDAO();
	      
	      Scanner sc = new Scanner(System.in);
	      
	      
	      System.out.println("���ŵ� ��ȭ ���� �Դϴ�.");
	      
	       String userid = Session.get("session_id");
	           
	           System.out.println("====��ȭǥ ����====");
	           System.out.println(mdao.myticket2(userid));
	           System.out.println("================");
	   }
}
