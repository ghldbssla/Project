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

	// �� ��ȭ ���(����,����,�帣,����,���)
	public String NowList() {
		String sql = "SELECT * FROM TBL_MOVIE_INFO ORDER BY rates DESC";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "��ȭ ���� : \"" + rs.getString(1) + "\"\n\t-���� : " + rs.getString(2) + "\n\t-�帣 : "
						+ rs.getString(3) + "\n\t-���� : \"" + rs.getString(4) + "\"\n\t-��� : \"" + rs.getString(5)
						+ "\"\n\n";
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

	// ���� ���� ��ȭ ���(����,�帣,����,���)
	public String SoonList() {
		String sql = "SELECT * FROM TBL_MOVIE_INFO_SOON ORDER BY title";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "��ȭ ���� : \"" + rs.getString(1) + "\"\n\t-�帣 : " + rs.getString(2) + "\n\t-���� : \""
						+ rs.getString(3) + "\"\n\t-��� : \"" + rs.getString(4) + "\"\n\n";
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
		String sql = "SELECT * FROM TBL_MOVIE_TICKET WHERE user_id=?";
		String result = "";
		try {
			// pstm ��ü����
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, Session.get("session_id"));
			// SELECT ���̱� ������ �˻��� ����� �����ϰ� �װ��� rs��ü�� �޾ƿ´�.
			rs = pstm.executeQuery();
			// rs.next() : ���� ��(�����ϸ� true)
			if (rs.next()) {
				// rs.getInt(1) : 1��° �÷��� ������ ��������
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
		// result�� 0�̸� �ߺ����� �ʴ´ٴ� ���̹Ƿ� true ����
		return result;
	}
	// -------------------------------ũ�Ѹ�
	// -------------------------------ũ�Ѹ�
	//������ �󿵰� ���̺� ������ �߰�
	public void input(String name) {
		String sql = "INSERT INTO TBL_THEATER(name) VALUES(?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("���� ����");
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}

		
	// ���� ���� ���̺� ������ ����
	public void delete() {

		String sql = "DELETE FROM TBL_MOVIE_INFO";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			System.out.println("���� ���� ���̺� ������ ����");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}

	// �� ������ ���̺� ������ ����
	public void delete_soon() {

		String sql = "DELETE FROM TBL_MOVIE_INFO_SOON";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			System.out.println("�� ������ ���̺� ������ ����");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}
	// �󿵰� ���̺� ������ ����
		public void delete_cgv() {

			String sql = "DELETE FROM TBL_THEATER";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.executeUpdate();
				System.out.println("�󿵰� ���̺� ������ ����");
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out.println("�� �� ���� ����");
				}
			}
		}

	// ������ �߰�(����,����,�帣,����,���)
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
			System.out.println("���� ����");
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}

//sql�� �� ������ ������ �߰�
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
			System.out.println("���� ����_s");
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}

	}
	
	// -------------------------------ũ�Ѹ�
	// -------------------------------ũ�Ѹ�
//��ȭ �� ���̺�(��ȭ����, id)�� ���� �ֱ�.
	public void create(String title) {

		String sql = "INSERT INTO BOOK_TBL VALUES(?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.setString(2, "id");//session.id �� ����
			pstm.executeUpdate();
			System.out.println("���ϱ� �Ϸ�!");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}
//�� ��� ��ü ����
	public void delete_a() {

		String sql = "DELETE FROM BOOK_TBL WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");//session.id�� ������
			pstm.executeUpdate();
			System.out.println("���� ���� ����");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
	}
	//�� ��� ���� ����
		public void delete_c(String title) {

			String sql = "DELETE FROM BOOK_TBL WHERE USERID=? AND TITLE = ?";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, "id");//session.id�� ������
				pstm.setString(2, title);
				pstm.executeUpdate();
				System.out.println("���� ���� ����");
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out.println("�� �� ���� ����");
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
					result += "���̵� : "+rs.getString(2)+"\n\t��ȭ���� : "+rs.getString(1)+"\n";
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
		//�����Է� �� �ش� ��¥ �� ��ȭ�� ��¥ �ð����� �����ش�.
		//SQL ���̺� theaterlo(��ȭ�� ��ġ) �����
		public String Movietimedate(String Theaterlo) {
			String sql = "SELECT title,moviedate, theatertime FROM MOVIE_INFO WHERE theaterlo=? ORDER BY title";		
			String result = "";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1,Theaterlo);
				rs = pstm.executeQuery();
				while (rs.next()) {
					result += "�Ŀ�ȭ ���� : "+rs.getString(1) + "�Ŀ�ȭ ��¥ : "+rs.getString(2) + "�Ŀ�ȭ �ð� : "+rs.getString(4) + "\n";
				}
			} catch (SQLException e) {
				System.out.println("��ȭ�� ��ġ ����");
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
//		//��ȭ ���� �� �ð��� �¼�
//		//SQL SIT(�¼�)���̺� �����
//		public String Movietimesit() {
//			String sql = "SELECT M_TIME, M_SIT FROM MOVIE_INFO ORDER BY M_TIME";		
//			String result = "";
//			try {
//				pstm = conn.prepareStatement(sql);
//				rs = pstm.executeQuery();
//				while (rs.next()) {
//					result += "�Ŀ�ȭ �ð� : "+rs.getString(8) + "�Ŀ�ȭ �¼� : "+rs.getString(9) + "\n";
//				}
//			} catch (SQLException e) {
//				System.out.println("�ð�, �¼� ���� ����");
//				System.out.println(e);
//			} finally {
//				try {
//					rs.close();
//					pstm.close();
//				} catch (SQLException e) {
//					System.out.println("�� �� ���� ����");
//				}
//			}
//
//			return result;
//		}
		//moviechoiceview���� �����ϰ� ��ȭ ���� ������ ������ �� �ְ� �Ϸ��� �� 
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
				System.out.println("��ȭ ���� �� ����");
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
//		//�¼� db�� �߰�
//		public void Sit(String Sit) {
//			String sql = "INSERT INTO MOVIE_INFO VALUES(?)";
//			try {
//				pstm = conn.prepareStatement(sql);
//				pstm.setString(1, Sit);
//				pstm.executeUpdate();
//			} catch (SQLException e) {
//				System.out.println("�ڸ� ���� ����");
//				System.out.println(e);
//			} finally {
//				try {
//					pstm.close();
//				} catch (SQLException e) {
//					System.out.println("�� �� ���� ����");
//				}
//
//			}
//		}


}


