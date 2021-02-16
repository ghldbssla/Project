package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.Session;
import dto.UserDTO;

public class UserDAO {
	//DB 연결하기
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	String checkpassView = "사용가능한 비밀번호입니다.";
	String checkemailView = "사용가능한 이메일 입니다.";
	Scanner sc = new Scanner(System.in);
	
	private static int KEY = 4;
	
	public UserDAO() {
		conn = DBConnection.getConnection();
	}
	
	//아이디 중복 확인
	public boolean checkID(String userid) {
		String sql = "SELECT COUNT(*) FROM MOVIE_USER WHERE USERID=?";
		int result = 0;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return result == 0;
	}
	//비밀번호 사용가능 확인
	public String checkPass(String userpw) {
		String checkList = "";
		char[] specWord = {'!','@','#','$','%','^','&','*','(',')','/','\\','?','<','>',';',':'};
		boolean flag = true;
		int wordFlag = 0;
		int capFlag = 0;
		int lowFlag = 0;
		int numFlag = 0;
			
		for(char word : specWord) {
			for (int i = 0; i < userpw.length(); i++) {
				if (word==userpw.charAt(i)) {
					wordFlag += 1;
				}
			}
		}
			
		for (int i = 65; i < 91; i++) {
			for (int j = 0; j < userpw.length(); j++) {
				if ((char)i==userpw.charAt(j)) {
					capFlag += 1;
				}
			}
		}
		for (int i = 97; i < 123; i++) {
			for (int j = 0; j < userpw.length(); j++) {
				if ((char)i==userpw.charAt(j)) {
					lowFlag += 1;
				}
			}
		}
		for (int i = 48; i < 58; i++) {
			for (int j = 0; j < userpw.length(); j++) {
				if ((char)i==userpw.charAt(j)) {
					numFlag += 1;
				}
			}
		}
		if(userpw.length() < 8) {
			checkList += "비밀번호는 8자리 이상이어야 합니다.\n";
			flag = false;
		}
		if (wordFlag==0) {
			checkList += "비밀번호에 특수문자가 최소 1개는 있어야 합니다.\n";
			flag = false;
		}
		if (lowFlag == 0 && capFlag == 0) {
			checkList += "비밀번호에 대문자가 1개 이상 그리고 소문자가 1개 이상 있어야 합니다.\n";
			flag = false;
		}else if(lowFlag == 0) {
			checkList += "비밀번호에 소문자가 1개 이상 있어야 합니다.\n";
			flag = false;
		}else if(capFlag == 0) {
			checkList += "비밀번호에 대문자가 1개 이상 있어야 합니다.\n";
			flag = false;
		}
		if (numFlag==0) {
			checkList += "비밀번호에 숫자가 1개 이상 있어야 합니다.\n";
			flag = false;
		}
		if (flag==true) {
			checkList = "사용가능한 비밀번호입니다.";
		}
		return checkList;
	}
	public void join(UserDTO newUser) {
		String sql = "INSERT INTO MOVIE_USER VALUES(?,?,?,?,?,?,?,?)";
		String en_pw = encrypt(newUser.getUserpw());
		newUser.setUserpw(en_pw);
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, newUser.getUserid());
			pstm.setString(2, newUser.getUserpw());
			pstm.setString(3, newUser.getUsername());
			pstm.setString(4, newUser.getUseremail());
			pstm.setString(5, newUser.getUserphone());
			pstm.setString(6, newUser.getUseraddr());
			pstm.setString(7, newUser.getUserbday());
			pstm.setInt(8, newUser.getUsermoney());
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
	}
	public String encrypt(String userpw) {
		String en_pw = "";

		for (int i = 0; i < userpw.length(); i++) {
			en_pw += (char) (userpw.charAt(i) + KEY);
		}
		return en_pw;
	}

	public String decrypt(String en_pw) {
		String de_pw = "";

		for (int i = 0; i < en_pw.length(); i++) {
			de_pw += (char) (en_pw.charAt(i) - KEY);
		}
		return de_pw;
	}
	//이메일 중복 확인
	public String dupEmail(String useremail) {
		String sql = "SELECT COUNT(*) FROM MOVIE_USER WHERE USEREMAIL=?";
		String result = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, useremail);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return result;
	}
	//이메일 사용가능 여부 확인
	public String checkEmail(String useremail) {		
		String emailcheck = "잘못된 이메일 형식입니다. (@ 뒤에 도메인을 적어주십시오.)";
			for (int i = 0; i < useremail.length(); i++) {
				if ((char)64==(useremail.charAt(i))) {
					emailcheck = "사용가능한 이메일 입니다.";
				}
			}
		return emailcheck;
	}

//	------------------------------------------------------------------------------------
//	LoginView 메소드
	
	public UserDTO login(String userid, String userpw) {
		//String userid, String userpw, String username, String email, String userphone,
		//String useraddr, String bday, int coupon, int money
		
		String sql = "SELECT * FROM MOVIE_USER WHERE USERID=? AND USERPW=?";
		UserDTO user = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setString(2, encrypt(userpw));
			rs = pstm.executeQuery();
			if (rs.next()) {
				user = new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return user;
	}
	
	public String logincheck(String userid) {
		String sql = "SELECT USERID FROM MOVIE_USER WHERE USERID=?";
		String existid="";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			rs = pstm.executeQuery();
			if(rs.next()) {
				existid = rs.getString(1);
			}			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		} return existid;
	}
	
	public void find_id(int idx, String userinfo) {
		String userid = "";
		String[] columns = { "USEREMAIL", "USERPHONE" };
		String sql = "SELECT USERID FROM MOVIE_USER WHERE " + columns[idx] + " = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userinfo);
			rs = pstm.executeQuery();
			if(rs.next()) {
				userid = rs.getString(1);
			}
			System.out.println("아이디는 "+userid+" 입니다.\n");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
	}
	
	public boolean checkid(String userid) {
		//입력한 아이디가 존재하는지 체크
		boolean check = false;
		String sql = "SELECT * FROM MOVIE_USER WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			rs = pstm.executeQuery();
			if(rs.next()) {
				check=true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return check;
	}

	public void updatepw(String userid, String newuserpw) {
		String sql = "UPDATE MOVIE_USER SET USERPW = ? WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, encrypt(newuserpw));
			pstm.setString(2, userid);
			pstm.executeUpdate();
			System.out.println("비밀번호 수정 완료!\n");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
	}
	
	//수정
	public void modify(int idx, String newData) {
		String[] columns = { "USERPW","USEREMAIL","USERPHONE","USERADDR"};
		String sql = "UPDATE MOVIE_USER SET " + columns[idx] + " = ? WHERE USERID=?";
		if(idx==0) {
			String checkpass = checkPass(newData);
			System.out.println(checkpass);
			if (checkpass.equals(checkpassView)) {
				modifyDbms(sql, newData);
			}
		}else if(idx==1) {
			String checkemail = checkEmail(newData);
			System.out.println(checkemail);
			if (checkemail.equals(checkemailView)) {
				modifyDbms(sql, newData);
			}
		}else {
			modifyDbms(sql, newData);
		}
	}
	public void modifyDbms(String sql,String newData) {
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, newData);
			pstm.setString(2, Session.get("session_id"));
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
	}
	//회원탈퇴
	public boolean leaveId(String userpw) {
		//탈퇴를 위해 재입력한 비밀번호가 로그인된 유저의 비밀번호와 같은지 비교하기 위해서
		//먼저 USER 테이블에서 세션의 비밀번호를 검색해 와야 한다.
		String sql = "SELECT USERPW FROM MOVIE_USER WHERE USERID=?";
		String en_pw = "";
		int result = 0;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, Session.get("session_id"));
			rs = pstm.executeQuery();
			if (rs.next()) {
				en_pw = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		//en_pw에 검색이 되었다면 암호화된 비밀번호가 담겨있으므로, 밖에서 받아온 userpw와
		//그 암호화된 비밀번호를 복호화 시켜서 비교해준다.
		if (userpw.equals(decrypt(en_pw))) {
			sql = "DELETE FROM MOVIE_USER WHERE USERID=?";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, Session.get("session_id"));
				result = pstm.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out.println("알 수 없는 오류");
				}
			}
		}
		return result==1;
	}
	//비밀번호 재설정 창으로 이동
	public void moveTopw(String userid) {
		if(checkid(userid)) {
			String newuserpw="";
			while(true) {
				//비밀번호 사용가능한지 체크
				String checkpassView = "사용가능한 비밀번호입니다.";
				System.out.print("재설정할 비밀번호를 입력하세요 : ");	
				newuserpw=sc.next();
				String checkpass = checkPass(newuserpw);
				System.out.println(checkpass);
				if (checkpass.equals(checkpassView)) {
					break;
				}
			}
			updatepw(userid, newuserpw);
		}
		else {
			System.out.println("존재하지 않는 아이디입니다.");
		}
	}
	
	//충전하기
	public int charge(int addMoney,String userId) {
		String sql = "SELECT USERMONEY FROM MOVIE_USER WHERE USERID=?";
		String sqlTwo = "UPDATE MOVIE_USER SET USERMONEY = ? WHERE USERID=?";
		int money = 0;
		int sum = 0;
		if (addMoney<0) {
			return -1;
		}else if(addMoney==0){
			return 0;
		}else if(addMoney>0) {
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, userId);
				rs = pstm.executeQuery();
				if (rs.next()) {
					//rs.getInt(1) : 1번째 컬럼의 정수값 가져오기
					money = rs.getInt(1);
				}
				sum = money + addMoney;
				pstm = conn.prepareStatement(sqlTwo);
				pstm.setInt(1, sum);
				pstm.setString(2, userId);
				rs = pstm.executeQuery();
				
				
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out.println("알 수 없는 오류");
				}
			}
			return sum;
		}else {
			return -2;
		}
	}
//--------------------------------------Pay View 메소드------------------------------------------	
	public String findAge(String userid) {
		String result="";
		String sql = "SELECT userbday FROM MOVIE_USER WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			rs = pstm.executeQuery();
			if(rs.next()) {
				result = rs.getString(1);
			}	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return result;	//19990101 
	}
	
	public int bringMoney(String userid) {
		int result=0;
		String sql = "SELECT usermoney FROM MOVIE_USER WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			rs = pstm.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return result;
	}
	
	public void updateMoney(String userid, int change) {
		String sql = "UPDATE MOVIE_USER SET USERMONEY=? WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, change);
			pstm.setString(2, userid);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
	}
//------------------------------------------------------------------------------------------------	
	
}
