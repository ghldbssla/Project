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
				result += "영화 제목 : \"" + rs.getString(1) + "\"\n\t-평점 : " + rs.getString(2) + "\n\t-장르 : "
						+ rs.getString(3) + "\n\t-감독 : \"" + rs.getString(4) + "\"\n\t-배우 : \"" + rs.getString(5)
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
		String sql = "SELECT * FROM TBL_MOVIE_INFO_SOON ORDER BY title";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "영화 제목 : \"" + rs.getString(1) + "\"\n\t-장르 : " + rs.getString(2) + "\n\t-감독 : \""
						+ rs.getString(3) + "\"\n\t-배우 : \"" + rs.getString(4) + "\"\n\n";
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
	//지역별 상영관 테이블에 데이터 추가
	public void input(String name) {
		String sql = "INSERT INTO TBL_THEATER(name) VALUES(?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
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

		
	// 현재 상영작 테이블 데이터 삭제
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

	// 상영 예정작 테이블 데이터 삭제
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
	// 상영관 테이블 데이터 삭제
		public void delete_cgv() {

			String sql = "DELETE FROM TBL_THEATER";
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
		String sql = "INSERT INTO TBL_MOVIE_INFO(title,rates,genre,director,actor) VALUES(?,?,?,?,?)";
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
		String sql = "INSERT INTO TBL_MOVIE_INFO_SOON(title,genre,director,actor) VALUES(?,?,?,?)";
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
//영화 찜 테이블(영화제목, id)에 정보 넣기.
	public void create(String title) {

		String sql = "INSERT INTO BOOK_TBL VALUES(?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.setString(2, "id");//session.id 쓸 예정
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

		String sql = "DELETE FROM BOOK_TBL WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");//session.id를 가져옴
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
	//찜 목록 선택 삭제
		public void delete_c(String title) {

			String sql = "DELETE FROM BOOK_TBL WHERE USERID=? AND TITLE = ?";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, "id");//session.id를 가져옴
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
			String sql = "SELECT * FROM BOOK_TBL WHERE USERID=?";
			String result = "";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, "id");
				rs = pstm.executeQuery();
				while (rs.next()) {
					result += "아이디 : "+rs.getString(2)+"\n\t영화제목 : "+rs.getString(1)+"\n";
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
		//극장입력 시 해당 날짜 상영 영화와 날짜 시간등을 보여준다.
		//SQL 테이블 theaterlo(영화관 위치) 만들기
		public String Movietimedate(String Theaterlo) {
			String sql = "SELECT title,moviedate, theatertime FROM MOVIE_INFO WHERE theaterlo=? ORDER BY title";		
			String result = "";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1,Theaterlo);
				rs = pstm.executeQuery();
				while (rs.next()) {
					result += "Δ영화 제목 : "+rs.getString(1) + "Δ영화 날짜 : "+rs.getString(2) + "Δ영화 시간 : "+rs.getString(4) + "\n";
				}
			} catch (SQLException e) {
				System.out.println("영화관 위치 오류");
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
		//moviechoiceview에서 간단하게 영화 제목만 나오면 선택할 수 있게 하려고 함 
		public String MovieChoice(String MovieChoice) {
			String sql = "SELECT title FROM TBL_MOVIE_INFO WHERE title IN ?";
			String result = "";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1,MovieChoice);
				rs = pstm.executeQuery();
				while (rs.next()) {
					result +=rs.getString(1) ;
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


}


