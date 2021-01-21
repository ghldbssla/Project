package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import dto.UserDTO;

import dao.Session;
import dto.UserDTO;


public class UserDAO {
	//DB 연결하기
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	public UserDTO login(String userid, String userpw) {
		//String userid, String userpw, String username, String email, String userphone,
		//String useraddr, String bday, int coupon, int money
		
		String sql ="SELECT * FROM MOVIE_USER WHERE USERID=? AND USERPW=?";
		UserDTO user = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setString(2, encrypt(userpw));
			rs = pstm.executeQuery();

			if (rs.next()) {
				user = new UserDTO(rs.getString(0), rs.getString(1));
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
		
	private String encrypt(String userpw) {
		// TODO Auto-generated method stub
		return null;
	}

	public void find_id(int idx, String userinfo) {
		String[] columns = { "USEREMAIL", "USERPHONE" };
		String sql = "SELECT USERID FROM MV_USER WHERE" + columns[idx-1] + " = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userinfo);
			pstm.executeQuery();
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
		//아이디가 맞는지 체크
		String sql = "SELECT USERPW FROM MV_USER WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.executeQuery();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return true;
	}
	
}

