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
	public String[][] make_sit() {
		String[][] seat = new String[9][9];
		String[] col = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] raw = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
		for (int i = 0; i < raw.length; i++) {
			for (int j = 0; j < col.length; j++) {
				seat[i][j] = raw[i] + col[j];
			}
		}
		return seat;
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
