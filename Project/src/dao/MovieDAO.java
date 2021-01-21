package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MovieDTO;

public class MovieDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	public MovieDAO() {
		conn = DBConnection.getConnection();

	}

	public String NowList() {
		String sql = "SELECT * FROM MOVIE_INFO WHERE M_DATE<=SYSDATE ORDER BY TO_number(M_SEIRAL)";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += rs.getString(1)+". ��ȭ ���� : "+rs.getString(2) + "\n\t���帣 : " + rs.getString(3) + "\n\t�İ��� ��¥ : " + rs.getString(5) + "\n";
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
	public String SoonList() {
		String sql = "SELECT * FROM MOVIE_INFO WHERE M_DATE>SYSDATE ORDER BY TO_number(M_SEIRAL)";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += rs.getString(1)+". ��ȭ ���� : "+rs.getString(2) + "\n\t���帣 : " + rs.getString(3) + "\n\t�İ��� ��¥ : " + rs.getString(5) + "\n";
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

	public String NowList_genre() {
		// TODO Auto-generated method stub
		return null;
	}

	public String NowList_rate() {

		String sql = "SELECT * FROM MOVIE_INFO WHERE first_run<=SYSDATE ORDER BY rate DESC";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "�Ŀ�ȭ ���� : "+rs.getString(1) + "�Ŀ�ȭ ���� : " + rs.getFloat(5) + "�Ŀ�ȭ ���� : " + rs.getString(8) + " �İ��� ��¥ : " + rs.getString(4) + "\n";
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

	public String myticket(String session_id) {
		String sql = "SELECT * FROM MOVIE_TICKET WHERE user_id=?";
		String result = "";
		try {
			//pstm ��ü����
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, Session.get("session_id"));
			//SELECT ���̱� ������ �˻��� ����� �����ϰ� �װ��� rs��ü�� �޾ƿ´�.
			rs = pstm.executeQuery();
			//rs.next() : ���� ��(�����ϸ� true)
			if (rs.next()) {
				//rs.getInt(1) : 1��° �÷��� ������ ��������
				result += rs.getString(1);
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
		//result�� 0�̸� �ߺ����� �ʴ´ٴ� ���̹Ƿ� true ����
		return result;
	}
	
	
	//-------------------------------ũ�Ѹ�
	//-------------------------------ũ�Ѹ�
	//-------------------------------ũ�Ѹ�
	//-------------------------------ũ�Ѹ�
	
	
}
