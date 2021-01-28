package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		//아이디, 비밀번호 입력
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		
		//session 객체에 user 넘겨주기
		UserDTO session = udao.login(userid,userpw);
		
		//로그인 실패
		if(session==null) {
			//입력한 아이디와 비밀번호를 inputdata에 저장
			HashMap<String, String> inputdata = new HashMap<String, String>();
			inputdata.put(userid, userpw);
			Collection<String> inputvalue = inputdata.values();

			//logincheck에서 찾은 존재하는 아이디와 비밀번호가 담긴 set
			HashMap<String, String> existdata = udao.logincheck(userid, userpw);
			Set<String> key = existdata.keySet();
			Collection<String> value = existdata.values();
			
			if (!key.equals(inputdata.keySet())) {
				System.out.println("존재하지 않는 아이디입니다.\n");
				new LoginFailView();
			}
			else if (!value.equals(inputvalue)) {
				System.out.println("비밀번호를 잘못입력하셨습니다.\n");
				new LoginFailView();
			}
			
//			if (udao.idfail(userid, userpw)) {
//				System.out.println("존재하지 않는 아이디입니다.");
//				new LoginFailView();
//			}
//			if(udao.pwfail(userid, userpw)) {
//				System.out.println("비밀번호를 잘못입력하셨습니다.");
//				new LoginFailView();
//			}
//			if(!udao.idfail(userid, userpw)&!udao.pwfail(userid, userpw)) {
//				System.out.println("존재하지 않는 아이디와 비밀번호입니다.");
//				new LoginFailView();
//			}
		}
		
		//로그인 성공
		else {
			Session.put("session_id", session.getUserid());
			System.out.println(userid+"님 로그인 성공!\n");
			new MainView();
		}
	}
}
