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
	//DB �����ϱ�
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	String checkpassView = "��밡���� ��й�ȣ�Դϴ�.";
	String checkemailView = "��밡���� �̸��� �Դϴ�.";
	Scanner sc = new Scanner(System.in);
	
	private static int KEY = 4;
	
	public UserDAO() {
		conn = DBConnection.getConnection();
	}
	
	//���̵� �ߺ� Ȯ��
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
				System.out.println("�� �� ���� ����");
			}
		}
		return result == 0;
	}
	//��й�ȣ ��밡�� Ȯ��
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
			checkList += "��й�ȣ�� 8�ڸ� �̻��̾�� �մϴ�.\n";
			flag = false;
		}
		if (wordFlag==0) {
			checkList += "��й�ȣ�� Ư�����ڰ� �ּ� 1���� �־�� �մϴ�.\n";
			flag = false;
		}
		if (lowFlag == 0 && capFlag == 0) {
			checkList += "��й�ȣ�� �빮�ڰ� 1�� �̻� �׸��� �ҹ��ڰ� 1�� �̻� �־�� �մϴ�.\n";
			flag = false;
		}else if(lowFlag == 0) {
			checkList += "��й�ȣ�� �ҹ��ڰ� 1�� �̻� �־�� �մϴ�.\n";
			flag = false;
		}else if(capFlag == 0) {
			checkList += "��й�ȣ�� �빮�ڰ� 1�� �̻� �־�� �մϴ�.\n";
			flag = false;
		}
		if (numFlag==0) {
			checkList += "��й�ȣ�� ���ڰ� 1�� �̻� �־�� �մϴ�.\n";
			flag = false;
		}
		if (flag==true) {
			checkList = "��밡���� ��й�ȣ�Դϴ�.";
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
				System.out.println("�� �� ���� ����");
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
	//�̸��� �ߺ� Ȯ��
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
				System.out.println("�� �� ���� ����");
			}
		}
		return result;
	}
	//�̸��� ��밡�� ���� Ȯ��
	public String checkEmail(String useremail) {		
		String emailcheck = "�߸��� �̸��� �����Դϴ�. (@ �ڿ� �������� �����ֽʽÿ�.)";
			for (int i = 0; i < useremail.length(); i++) {
				if ((char)64==(useremail.charAt(i))) {
					emailcheck = "��밡���� �̸��� �Դϴ�.";
				}
			}
		return emailcheck;
	}

//	------------------------------------------------------------------------------------
//	LoginView �޼ҵ�
	
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
				System.out.println("�� �� ���� ����");
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
				System.out.println("�� �� ���� ����");
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
			System.out.println("���̵�� "+userid+" �Դϴ�.\n");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}
	
	public boolean checkid(String userid) {
		//�Է��� ���̵� �����ϴ��� üũ
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
				System.out.println("�� �� ���� ����");
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
			System.out.println("��й�ȣ ���� �Ϸ�!\n");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}
	
	//����
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
				System.out.println("�� �� ���� ����");
			}
		}
	}
	//ȸ��Ż��
	public boolean leaveId(String userpw) {
		//Ż�� ���� ���Է��� ��й�ȣ�� �α��ε� ������ ��й�ȣ�� ������ ���ϱ� ���ؼ�
		//���� USER ���̺��� ������ ��й�ȣ�� �˻��� �;� �Ѵ�.
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
				System.out.println("�� �� ���� ����");
			}
		}
		//en_pw�� �˻��� �Ǿ��ٸ� ��ȣȭ�� ��й�ȣ�� ��������Ƿ�, �ۿ��� �޾ƿ� userpw��
		//�� ��ȣȭ�� ��й�ȣ�� ��ȣȭ ���Ѽ� �����ش�.
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
					System.out.println("�� �� ���� ����");
				}
			}
		}
		return result==1;
	}
	//��й�ȣ �缳�� â���� �̵�
	public void moveTopw(String userid) {
		if(checkid(userid)) {
			String newuserpw="";
			while(true) {
				//��й�ȣ ��밡������ üũ
				String checkpassView = "��밡���� ��й�ȣ�Դϴ�.";
				System.out.print("�缳���� ��й�ȣ�� �Է��ϼ��� : ");	
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
			System.out.println("�������� �ʴ� ���̵��Դϴ�.");
		}
	}
	
	//�����ϱ�
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
					//rs.getInt(1) : 1��° �÷��� ������ ��������
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
					System.out.println("�� �� ���� ����");
				}
			}
			return sum;
		}else {
			return -2;
		}
	}
//--------------------------------------Pay View �޼ҵ�------------------------------------------	
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
				System.out.println("�� �� ���� ����");
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
				System.out.println("�� �� ���� ����");
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
				System.out.println("�� �� ���� ����");
			}
		}
	}
//------------------------------------------------------------------------------------------------	
	
}
