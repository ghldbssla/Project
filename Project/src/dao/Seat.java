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

	// �¼� �����
	 // �¼� ����
	   public String make_seat1(String T_NAME,String M_NAME) {
	      //��ü
	      String sql="SELECT T_NAME, M_NAME FROM SCREEN_THEATER";
	      //�����ִ�
	      sql = "SELECT * FROM SCREEN_THEATER WHERE T_NAME=? AND M_NAME=?";
	      try {
	         pstm = conn.prepareStatement(sql);
	         pstm.setString(1, T_NAME);
	         pstm.setString(2, M_NAME);
	         String result="";
	         rs = pstm.executeQuery();
	         while (rs.next()) {
	            result += "��ȭ�� �̸� : \""+rs.getString(1) + "\"\n\t-��ȭ �̸� : " + rs.getString(2) 
	            + "\"\n\n";;
	         }
	      } catch (SQLException e) {
	         System.out.println("make_seat() ���� ����");
	         System.out.println(e);
	      } finally {
	         try {
	            rs.close();
	            pstm.close();
	         } catch (SQLException e) {
	            System.out.println("�� �� ���� ����");
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
	            result += "�¼� ���� : \""+rs.getString(1)+ "\"\n\n";;
	         }
	      } catch (SQLException e) {
	         System.out.println("�¼� ���� ����");
	         System.out.println(e);
	      } finally {
	         try {
	            rs.close();
	            pstm.close();
	         } catch (SQLException e) {
	            System.out.println("�� �� ���� ����");
	         }
	      }
	   }

	// ��ȭ ���� �� �ð��� �¼�
	// SQL SIT(�¼�)���̺� �����
	public String Movietimesit(String MovieChoice) {
		String sql = "SELECT theatertime, moviesit FROM TBL_MOVIE_INFO WHERE title=? ORDER BY theatertime";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, MovieChoice);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "��ȭ �ð� : " + rs.getString(8) + "��ȭ �¼� : " + rs.getString(9) + "\n";
			}
		} catch (SQLException e) {
			System.out.println("�ð�, �¼� ���� ����");
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

	// �¼� db�� �߰�
	public void Sit(String Sit) {
		String sql = "INSERT INTO MOVIE_INFO(moviesit) VALUES(?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, Sit);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("�ڸ� ���� ����");
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}

		}
	}
}
