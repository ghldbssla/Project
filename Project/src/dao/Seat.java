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
