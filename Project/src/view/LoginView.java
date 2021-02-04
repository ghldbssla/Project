package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import dao.MovieDAO;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView(int page) {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		
		//아이디, 비밀번호 입력
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		
		//session 객체에 user 넘겨주기
		UserDTO session = udao.login(userid, userpw);
		
		//로그인 실패
		if(session==null) {
			String existid = udao.logincheck(userid);
			//아이디가 x
			if(!existid.equals(userid)) {				
				System.out.println("존재하지 않는 계정입니다.");
				new LoginFailView(page);
			}//아이디 o
			else if (existid.equals(userid)) {
				System.out.println("비밀번호를 잘못입력하셨습니다.");
				new LoginFailView(page);
			}
		}
		
		//로그인 성공
		else {
			Session.put("session_id", session.getUserid());
			System.out.println(userid+"님 로그인 성공!\n");
			//받아온 숫자값에 따라 경로 나눠주기
			if(page==1) {
				//영화예매
				new TicketView();
			}
			else if(page==0) {
				//메인뷰
				new MainView();
			}
			else if(page==-1) {
				//WatchListView()에서 비로그인상태로 찜하기 선택하면 로그인 성공후 찜할수 있게 하는것
				System.out.println("찜하기에 추가할 영화제목을 입력해주세요.");
				sc.nextLine();
				String title = sc.nextLine();
				mdao.create(title);	
				//**찜하기 완료! 뜬 후에 다른 페이지로 연결해줘야하나?
			}
		}
	}

}
