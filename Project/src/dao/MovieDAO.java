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

	// 상영 영화 목록(제목,평점,장르,감독,배우)
	public String NowList() {
		String sql = "SELECT * FROM TBL_MOVIE_INFO ORDER BY rates DESC";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "영화 제목 : \"" +rs.getString(1) + "\"\n\t- 평점 : " + rs.getString(2) + "\n\t-장르 : " + rs.getString(3)
						+ "\n\t-감독 : \"" + rs.getString(4)+ "\"\n\t-배우 : \"" + rs.getString(5) + "\"\n\n";
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
	
	// 개봉 예정 영화 목록(제목,장르,감독,배우)
		public String SoonList() {
			String sql = "SELECT * FROM TBL_MOVIE_INFO_SOON ORDER BY title";
			String result = "";
			try {
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					result += "영화 제목 : \"" +rs.getString(1) + "\"\n\t-장르 : " + rs.getString(2)
					+ "\n\t-감독 : \"" + rs.getString(3)+ "\"\n\t-배우 : \"" + rs.getString(4) + "\"\n\n";				}
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
			// pstm 객체생성
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, Session.get("session_id"));
			// SELECT 문이기 때문에 검색된 결과가 존재하고 그것을 rs객체로 받아온다.
			rs = pstm.executeQuery();
			// rs.next() : 다음 행(존재하면 true)
			if (rs.next()) {
				// rs.getInt(1) : 1번째 컬럼의 정수값 가져오기
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
		// result가 0이면 중복되지 않는다는 뜻이므로 true 리턴
		return result;
	}

	// -------------------------------크롤링
	// -------------------------------크롤링
	//현재 상영작 테이블 데이터 삭제
	public void delete() {

		String sql = "DELETE FROM TBL_MOVIE_INFO";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			System.out.println("현재 상영작 테이블 데이터 삭제");
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
	//상영 예정작 테이블 데이터 삭제
	public void delete_soon() {

		String sql = "DELETE FROM TBL_MOVIE_INFO_SOON";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			System.out.println("상영 예정작 테이블 데이터 삭제");
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
	// 데이터 추가(제목,평점,장르,감독,배우)
	//titleAr.get(i),ratesAr.get(i),genreAr.get(i),directorAr.get(i),actorAr.get(i)
	public void input(String title, String rates, String genre, String director, String actor) {
		String sql = "INSERT INTO TBL_MOVIE_INFO VALUES(?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.setString(2, rates);
			pstm.setString(3, genre);
			pstm.setString(4, director);
			pstm.setString(5, actor);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("제목 오류");
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
	}
//sql로 상영 예정작 데이터 추가
	public void input_s(String title_s, String genre_s, String director_s, String actor_s) {
		String sql = "INSERT INTO TBL_MOVIE_INFO_SOON VALUES(?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title_s);
			pstm.setString(2, genre_s);
			pstm.setString(3, director_s);
			pstm.setString(4, actor_s);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("제목 오류_s");
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		
	}
		

	
		// -------------------------------크롤링
		// -------------------------------크롤링

	}



