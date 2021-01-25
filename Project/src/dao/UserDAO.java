package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dao.Session;
import dto.UserDTO;

public class UserDAO {
	//DB 연결하기
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
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
		String sql = "INSERT INTO MOVIE_USER VALUES(?,?,?,?,?,?,?,?,?)";
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
			pstm.setInt(8, newUser.getUsercoupon());
			pstm.setInt(9, newUser.getUsermoney());
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
						rs.getString(7), rs.getInt(8), rs.getInt(9));
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
	
	public HashMap<String, String> logincheck(String userid, String userpw) {
		HashMap<String, String> login_data = new HashMap<>();
		//아이디와 비밀번호 둘중 하나만 맞아도 회원 정보가지고 오기
		String sql = "SELECT * FROM MOVIE_USER WHERE USERID=? OR USERPW=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setString(2, encrypt(userpw));
			rs = pstm.executeQuery();
			if(rs.next()) {
				//올바른 회원정보와 비밀번호를 객체에 저장
				userid=rs.getString(1);
				userpw=rs.getString(2);
			}
			//올바른 정보가 담긴 객체를 hashmap에 저장
			login_data.put(userid, userpw);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		} return login_data;
	}
	
//	public void idfail(String userid, String userpw) {
////		boolean check = false;
//		String sql = "SELECT USERID FROM MOVIE_USER WHERE USERPW=?";
//		UserDTO user = null;
//		try {
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, userid);
//			pstm.setString(2, encrypt(userpw));
//			rs = pstm.executeQuery();
//			if(rs.next()) {
//				System.out.println(rs);
////				check=true;
//			}
//		} catch (SQLException e) {
//			System.out.println(e);
//		} finally {
//			try {
//				rs.close();
//				pstm.close();
//			} catch (SQLException e) {
//				System.out.println("알 수 없는 오류");
//			}
//		}
////		return check;
//	}
//	
//	public boolean pwfail(String userid, String userpw) {	
//		boolean check=false;
//		String sql = "SELECT USERPW FROM MOVIE_USER WHERE USERID=?";
//		UserDTO user = null;
//		try {
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, userid);
//			pstm.setString(2, encrypt(userpw));
//			rs = pstm.executeQuery();
//			if(rs.next()) {
//				check=true;
//			}
//		} catch (SQLException e) {
//			System.out.println(e);
//		} finally {
//			try {
//				rs.close();
//				pstm.close();
//			} catch (SQLException e) {
//				System.out.println("알 수 없는 오류");
//			}
//		}
//		return check;
//	}
	
		
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
}
