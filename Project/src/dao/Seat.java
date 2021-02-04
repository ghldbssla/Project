package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Seat {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	public Seat() {
		conn = DBConnection.getConnection();

	}

	// 좌석 만들기
	 // 좌석 선택
	   public String make_seat1(String T_NAME,String M_NAME) {
	      //전체
	      String sql="SELECT T_NAME, M_NAME FROM SCREEN_THEATER";
	      //나와있는
	      sql = "SELECT * FROM SCREEN_THEATER WHERE T_NAME=? AND M_NAME=?";
	      try {
	         pstm = conn.prepareStatement(sql);
	         pstm.setString(1, T_NAME);
	         pstm.setString(2, M_NAME);
	         String result="";
	         rs = pstm.executeQuery();
	         while (rs.next()) {
	            result += "영화관 이름 : \""+rs.getString(1) + "\"\n\t-영화 이름 : " + rs.getString(2) 
	            + "\"\n\n";;
	         }
	      } catch (SQLException e) {
	         System.out.println("make_seat() 선택 오류");
	         System.out.println(e);
	      } finally {
	         try {
	            rs.close();
	            pstm.close();
	         } catch (SQLException e) {
	            System.out.println("알 수 없는 오류");
	         }
	      }
	      return sql;
	   }
	      
	   
	   public void make_seat2(String S_SEAT_CNT){
	      String sql = "INSERT INTO SCREEN_THEATER(S_SEAT_CNT)VALUES=?";
	      try {
	         pstm = conn.prepareStatement(sql);
	         pstm.setString(1, S_SEAT_CNT);
	         String result="";
	         rs = pstm.executeQuery();
	         while (rs.next()) {
	            result += "좌석 선택 : \""+rs.getString(1)+ "\"\n\n";;
	         }
	      } catch (SQLException e) {
	         System.out.println("좌석 선택 오류");
	         System.out.println(e);
	      } finally {
	         try {
	            rs.close();
	            pstm.close();
	         } catch (SQLException e) {
	            System.out.println("알 수 없는 오류");
	         }
	      }
	   }

	// 영화 선택 후 시간과 좌석
	// SQL SIT(좌석)테이블 만들기
	public String Movietimesit(String MovieChoice) {
		String sql = "SELECT theatertime, moviesit FROM TBL_MOVIE_INFO WHERE title=? ORDER BY theatertime";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, MovieChoice);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "영화 시간 : " + rs.getString(8) + "영화 좌석 : " + rs.getString(9) + "\n";
			}
		} catch (SQLException e) {
			System.out.println("시간, 좌석 선택 오류");
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

	// 좌석 db에 추가
	public void Sit(String Sit) {
		String sql = "INSERT INTO MOVIE_INFO(moviesit) VALUES(?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, Sit);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("자리 선택 오류");
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
