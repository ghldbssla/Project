package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class JoinView {
	public JoinView() {
		// 아이디 비밀번호 이름 나이 핸드폰번호 주소 추천수 거래수
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.print("아이디 : ");
		String userid = sc.next();
		//아이디 체크라는것은 기능 이므로 dao에다가 구현을 해놓고
		//View단에서는 단순하게 호출하여 사용하기만 한다.
/*	private String userid;
	private String userpw;
	private String username;
	private String email;
	private String userphone;
	private String useraddr;
	private String bday;
	private int coupon;
	private int money;*/
		if (!udao.checkDup(userid)) {
			System.out.println("중복된 아이디가 있습니다. 다시 시도해 주세요.");
		} else {
			System.out.print("비밀번호 : ");
			String userpw = sc.next();
			System.out.print("이름 : ");
			String username = sc.next();
			System.out.print("나이 : ");
			String email = sc.next();
			System.out.print("핸드폰 번호 : ");
			String userphone = sc.next();
			System.out.print("주소 : ");
			sc.nextLine();
			String useraddr = sc.nextLine();
			System.out.print("핸드폰 번호 : ");
			String bday = sc.next();
			//정보가 6개나 되기 때문에 join이라는 메소드에 넘겨주려면 매개변수가
			//6개가 필요하다. 따라서 너무 데이터를 넘기기 힘들기 때문에
			//UserDTO로 포장해준다.
			UserDTO newUser = new UserDTO(userid, userpw, username, email, userphone, useraddr,bday);
			
		}

	}
}
