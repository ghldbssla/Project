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
				result += rs.getString(1)+". 영화 제목 : "+rs.getString(2) + "\n\tΔ장르 : " + rs.getString(3) + "\n\tΔ개봉 날짜 : " + rs.getString(5) + "\n";
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
	public String SoonList() {
		String sql = "SELECT * FROM MOVIE_INFO WHERE M_DATE>SYSDATE ORDER BY TO_number(M_SEIRAL)";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += rs.getString(1)+". 영화 제목 : "+rs.getString(2) + "\n\tΔ장르 : " + rs.getString(3) + "\n\tΔ개봉 날짜 : " + rs.getString(5) + "\n";
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
				result += "Δ영화 제목 : "+rs.getString(1) + "Δ영화 평점 : " + rs.getFloat(5) + "Δ영화 정보 : " + rs.getString(8) + " Δ개봉 날짜 : " + rs.getString(4) + "\n";
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

	public String myticket(String session_id) {
		String sql = "SELECT * FROM MOVIE_TICKET WHERE user_id=?";
		String result = "";
		try {
			//pstm 객체생성
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, Session.get("session_id"));
			//SELECT 문이기 때문에 검색된 결과가 존재하고 그것을 rs객체로 받아온다.
			rs = pstm.executeQuery();
			//rs.next() : 다음 행(존재하면 true)
			if (rs.next()) {
				//rs.getInt(1) : 1번째 컬럼의 정수값 가져오기
				result += rs.getString(1);
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
		//result가 0이면 중복되지 않는다는 뜻이므로 true 리턴
		return result;
	}
	
	
	//-------------------------------크롤링
	//-------------------------------크롤링
	//-------------------------------크롤링
	//-------------------------------크롤링
	
	
}
