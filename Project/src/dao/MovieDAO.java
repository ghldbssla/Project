package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String sql = "SELECT * FROM MOVIE_LIST ORDER BY M_RATE DESC";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "영화 제목 : \"" + rs.getString(1) + "\"\n\t-평점 : " + rs.getString(5) + "\n\t-장르 : "
						+ rs.getString(4) + "\n\t-감독 : \"" + rs.getString(2) + "\"\n\t-배우 : \"" + rs.getString(3)
						+ "\"\n\n";
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
		String sql = "SELECT * FROM MOVIE_LIST_SOON";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "영화 제목 : \"" + rs.getString(1) + "\"\n\t-장르 : " + rs.getString(4) + "\n\t-감독 : \""
						+ rs.getString(2) + "\"\n\t-배우 : \"" + rs.getString(3) + "\"\n\n";
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
		String sql = "SELECT * FROM TBL_MOVIE_TICKET WHERE user_id=?";
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
	// 지역별 상영관 테이블에 데이터 추가
	public void input(String name) {
		String sql = "INSERT INTO TBL_THEATER(T_NAME) VALUES(?)";
		try {
			// 상영관 이름에 CGV가 포함되 있다면 실행
			if (name.contains("CGV")) {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, name);
				pstm.executeUpdate();
			}
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

	// 현재 상영작 테이블 데이터 삭제
	public void delete() {

		String sql = "DELETE FROM MOVIE_LIST";
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

	// 상영 예정작 테이블 데이터 삭제
	public void delete_soon() {

		String sql = "DELETE FROM MOVIE_LIST_SOON";
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

	// 상영관 테이블 데이터 삭제
	public void delete_cgv() {

		String sql = "DELETE FROM THEATER";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			System.out.println("상영관 테이블 데이터 삭제");
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
	// titleAr.get(i),ratesAr.get(i),genreAr.get(i),directorAr.get(i),actorAr.get(i)
	public void input(String title, String rates, String genre, String director, String actor) {
		String sql = "INSERT INTO MOVIE_LIST(M_NAME,M_DIRECTOR,M_ACTOR,M_GENRE,M_RATE) VALUES(?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.setString(2, director);
			pstm.setString(3, actor);
			pstm.setString(4, genre);
			pstm.setString(5, rates);
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
		String sql = "INSERT INTO MOVIE_LIST_SOON(M_NAME,M_DIRECTOR,M_ACTOR,M_GENRE) VALUES(?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title_s);
			pstm.setString(2, director_s);
			pstm.setString(3, actor_s);
			pstm.setString(4, genre_s);
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
//영화 찜 테이블(영화제목, id)에 정보 넣기.
	public void create(String title) {

		String sql = "INSERT INTO FAV_USER VALUES(?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");// session.id 쓸 예정
			pstm.setString(2, title);
			pstm.executeUpdate();
			System.out.println("찜하기 완료!");
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

//찜 목록 전체 삭제
	public void delete_a() {

		String sql = "DELETE FROM SELECTED WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");// session.id를 가져옴
			pstm.executeUpdate();
			System.out.println("찜목록 선택 삭제");
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

	// 찜 목록 선택 삭제
	public void delete_c(String title) {

		String sql = "DELETE FROM SELECTED WHERE USERID=? AND TITLE = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");// session.id를 가져옴
			pstm.setString(2, title);
			pstm.executeUpdate();
			System.out.println("찜목록 선택 삭제");
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

	public String getlist() {
		String sql = "SELECT * FROM SELECTED WHERE USERID=?";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "아이디 : " + rs.getString(2) + "\n\t영화제목 : " + rs.getString(1) + "\n";
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

	// TheaterView - 영화관 찾기
	public void findlo(String theaterlo) {
		String sql = "SELECT * FROM THEATER WHERE T_CITY=?";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, theaterlo);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result = rs.getString(1) + "번\n\t" + rs.getString(3) + "-" + rs.getString(2);
				System.out.println(result);
			}
		} catch (SQLException e) {
			System.out.println("다시 입력해주세요.");
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

//		//영화 선택 후 시간과 좌석
//		//SQL SIT(좌석)테이블 만들기
//		public String Movietimesit() {
//			String sql = "SELECT M_TIME, M_SIT FROM MOVIE_INFO ORDER BY M_TIME";		
//			String result = "";
//			try {
//				pstm = conn.prepareStatement(sql);
//				rs = pstm.executeQuery();
//				while (rs.next()) {
//					result += "Δ영화 시간 : "+rs.getString(8) + "Δ영화 좌석 : "+rs.getString(9) + "\n";
//				}
//			} catch (SQLException e) {
//				System.out.println("시간, 좌석 선택 오류");
//				System.out.println(e);
//			} finally {
//				try {
//					rs.close();
//					pstm.close();
//				} catch (SQLException e) {
//					System.out.println("알 수 없는 오류");
//				}
//			}
//
//			return result;
//		}
	// moviechoiceview에서 간단하게 영화 제목만 나오면 선택할 수 있게 하려고 함
	public String MovieChoice(String MovieChoice) {
		String sql = "SELECT title FROM MOVIE_LIST WHERE title IN ?";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, MovieChoice);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("영화 선택 뷰 오류");
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
//		//좌석 db에 추가
//		public void Sit(String Sit) {
//			String sql = "INSERT INTO MOVIE_INFO VALUES(?)";
//			try {
//				pstm = conn.prepareStatement(sql);
//				pstm.setString(1, Sit);
//				pstm.executeUpdate();
//			} catch (SQLException e) {
//				System.out.println("자리 선택 오류");
//				System.out.println(e);
//			} finally {
//				try {
//					pstm.close();
//				} catch (SQLException e) {
//					System.out.println("알 수 없는 오류");
//				}
//
//			}
//		}

	public void insert_THEATER(int i, String name, String string) {

		String sql = "INSERT INTO THEATER(T_SERIAL,T_CITY,T_NAME) VALUES(?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, i);
			pstm.setString(2, string);
			pstm.setString(3, name);
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

//상영관으로 상영시간 찾기
//	String serial = mdao.count("T_SERIAL",cgvCode);
	public String count(String str) {

		String sql = "SELECT * FROM THEATER WHERE T_NAME=?";
		String ans = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, str);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ans = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("영화 선택 뷰 오류");
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		return ans;
	}
//cgv와 영화제목이 테이블에 있는지 확인해주는 메소드
	public Boolean check_n(String cgvCode, String m_name) {
		String sql = "SELECT * FROM SCREEN_THEATER WHERE T_NAME=? and  m_name=?";
		String ans = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgvCode);
			pstm.setString(2, m_name);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ans = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("영화 선택 뷰 오류");
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		if (ans.equals("")) {
			return false;
		} else {
			return true;
		}
	}

//cgv와 영화제목, 상영시간을 테이블에 넣어주는 메소드
	public void name_time(String cgv, String title, String time) {
		String sql = "INSERT INTO SCREEN_THEATER(T_NAME,m_name,S_SCHEDULE_TIME) VALUES(?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgv);
			pstm.setString(2, title);
			pstm.setString(3, time);
			rs = pstm.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("영화 시간 오류_sql");
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

//cgv와 영화제목, 상영시간이 테이블에 있는지 확인해주는 메소드
	public boolean check_t(String cgvCode, String m_name, String m_time) {
		String sql = "SELECT * FROM SCREEN_THEATER WHERE T_NAME=? and  m_name=? and S_SCHEDULE_TIME=?";
		String ans = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgvCode);
			pstm.setString(2, m_name);
			pstm.setString(3, m_time);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ans = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("영화 선택 뷰 오류");
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		if (ans.equals("")) {
			return false;
		} else {
			return true;
		}
	}

//cgv와 영화제목, 상영시간, 좌석을 테이블에 넣어주는 메소드
	public void sit_insert(String cgvCode, String m_name, String m_time, String sit) {
		String sql = "INSERT INTO SCREEN_THEATER(T_NAME,m_name,S_SCHEDULE_TIME,S_SEAT_CNT) VALUES(?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgvCode);
			pstm.setString(2, m_name);
			pstm.setString(3, m_time);
			pstm.setString(4, sit);
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
//cgv와 영화제목, 상영시간, 좌석이 테이블에 있는지 확인해주는 메소드
	public Boolean check_S(String cgvCode, String m_name, String m_time, String sit) {
		String sql = "SELECT * FROM SCREEN_THEATER WHERE T_NAME=? and  m_name=? and S_SCHEDULE_TIME=? and S_SEAT_CNT=?";
		String ans = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgvCode);
			pstm.setString(2, m_name);
			pstm.setString(3, m_time);
			pstm.setString(4, sit);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ans = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("영화 선택 뷰 오류");
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("알 수 없는 오류");
			}
		}
		if (ans.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void name_time_del() {
//		DELETE FROM SCREEN_THEATER WHERE S_SEAT_CNT IS NULL
		String sql = "DELETE FROM SCREEN_THEATER WHERE S_SEAT_CNT IS NULL";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

		} catch (SQLException e) {
			System.out.println("영화 선택 뷰 오류");
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

}
