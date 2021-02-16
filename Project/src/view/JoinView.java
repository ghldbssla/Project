package view;

import java.util.Scanner;

import dao.MovieDAO;
import dao.UserDAO;
import dto.UserDTO;


public class JoinView {
	public JoinView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		MovieDAO mdao = new MovieDAO();
		String userid;
		String userpw;
		String useremail;
		String bday;
		String checkpassView = "사용가능한 비밀번호입니다.";
		String checkemailView = "사용가능한 이메일 입니다.";
			
		//아이디
		while(true) {
			System.out.print("아이디 : ");
			userid = sc.next();
			if (udao.checkID(userid)) {
				System.out.println("사용가능한 아이디입니다.");
				break;
			}else {
				System.out.println("중복된 아이디가 있습니다.");
			}
		}
		//비밀번호
		while(true) {
			System.out.print("비밀번호 : ");
			userpw = sc.next();
			String checkpass = udao.checkPass(userpw);
			System.out.println(checkpass);
			if (checkpass.equals(checkpassView)) {
				break;
			}
		}

		//이름
		System.out.print("이름 : ");
		String username = sc.next();
		//이메일
		while (true) {
			
			System.out.print( "이메일 : ");
			useremail = sc.next();
			if (!udao.dupEmail(useremail).equals("0")) {
				System.out.println("존재하는 이메일입니다.");
			}else {
				String checkemail = udao.checkEmail(useremail);
				System.out.println(checkemail);
				if (checkemail.equals(checkemailView)) {
					break;
				}
				
			}
			
		}
		//핸드폰 번호
		System.out.print("핸드폰 번호 : ");
		String userphone = sc.next();
		//주소
		System.out.print("주소 : ");
		sc.nextLine();
		String useraddr = sc.nextLine();
		//생일
		while(true) {
			System.out.print("생일 : (예 19900502) ");
			bday = sc.next();
			if (bday.length()==8) {
				break;
			}else {
				System.out.println("다시 입력해주십시오.");
			}
		}
		//쿠폰
		int usercoupon = 0;
		//계좌
		int usermoney = 0;
		
		UserDTO newUser = new UserDTO(userid, userpw, username, useremail, userphone, useraddr, bday, usermoney);
		udao.join(newUser);
		System.out.println("회원가입 성공!");
		System.out.println(userid + "님 가입을 환영합니다.");
		String coupon_name = mdao.coupon();
		mdao.insertCoupon(userid, coupon_name);
		System.out.println("회원가입 선물로 "+coupon_name+"쿠폰이 지급되었습니다.");
	}
}


