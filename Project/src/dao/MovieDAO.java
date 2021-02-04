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

	// �� ��ȭ ���(����,����,�帣,����,���)
	public String NowList() {
		String sql = "SELECT * FROM MOVIE_LIST ORDER BY M_RATE DESC";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "��ȭ ���� : \"" + rs.getString(1) + "\"\n\t-���� : " + rs.getString(5) + "\n\t-�帣 : "
						+ rs.getString(4) + "\n\t-���� : \"" + rs.getString(2) + "\"\n\t-��� : \"" + rs.getString(3)
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
		String sql = "SELECT * FROM MOVIE_LIST_SOON";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "��ȭ ���� : \"" + rs.getString(1) + "\"\n\t-�帣 : " + rs.getString(4) + "\n\t-���� : \""
						+ rs.getString(2) + "\"\n\t-��� : \"" + rs.getString(3) + "\"\n\n";
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
	// ������ �󿵰� ���̺� ������ �߰�
	public void input(String name) {
		String sql = "INSERT INTO TBL_THEATER(T_NAME) VALUES(?)";
		try {
			// �󿵰� �̸��� CGV�� ���Ե� �ִٸ� ����
			if (name.contains("CGV")) {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, name);
				pstm.executeUpdate();
			}
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

		String sql = "DELETE FROM MOVIE_LIST";
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

		String sql = "DELETE FROM MOVIE_LIST_SOON";
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

		String sql = "DELETE FROM THEATER";
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
		String sql = "INSERT INTO MOVIE_LIST_SOON(M_NAME,M_DIRECTOR,M_ACTOR,M_GENRE) VALUES(?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title_s);
			pstm.setString(2, director_s);
			pstm.setString(3, actor_s);
			pstm.setString(4, genre_s);
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

		String sql = "INSERT INTO FAV_USER VALUES(?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");// session.id �� ����
			pstm.setString(2, title);
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

		String sql = "DELETE FROM SELECTED WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");// session.id�� ������
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

	// �� ��� ���� ����
	public void delete_c(String title) {

		String sql = "DELETE FROM SELECTED WHERE USERID=? AND TITLE = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");// session.id�� ������
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
		String sql = "SELECT * FROM SELECTED WHERE USERID=?";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "id");
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "���̵� : " + rs.getString(2) + "\n\t��ȭ���� : " + rs.getString(1) + "\n";
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

	// TheaterView - ��ȭ�� ã��
	public void findlo(String theaterlo) {
		String sql = "SELECT * FROM THEATER WHERE T_CITY=?";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, theaterlo);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result = rs.getString(1) + "��\n\t" + rs.getString(3) + "-" + rs.getString(2);
				System.out.println(result);
			}
		} catch (SQLException e) {
			System.out.println("�ٽ� �Է����ּ���.");
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
	// moviechoiceview���� �����ϰ� ��ȭ ���� ������ ������ �� �ְ� �Ϸ��� ��
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
				System.out.println("�� �� ���� ����");
			}
		}
	}

//�󿵰����� �󿵽ð� ã��
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
		return ans;
	}
//cgv�� ��ȭ������ ���̺� �ִ��� Ȯ�����ִ� �޼ҵ�
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
		if (ans.equals("")) {
			return false;
		} else {
			return true;
		}
	}

//cgv�� ��ȭ����, �󿵽ð��� ���̺� �־��ִ� �޼ҵ�
	public void name_time(String cgv, String title, String time) {
		String sql = "INSERT INTO SCREEN_THEATER(T_NAME,m_name,S_SCHEDULE_TIME) VALUES(?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgv);
			pstm.setString(2, title);
			pstm.setString(3, time);
			rs = pstm.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("��ȭ �ð� ����_sql");
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

//cgv�� ��ȭ����, �󿵽ð��� ���̺� �ִ��� Ȯ�����ִ� �޼ҵ�
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
		if (ans.equals("")) {
			return false;
		} else {
			return true;
		}
	}

//cgv�� ��ȭ����, �󿵽ð�, �¼��� ���̺� �־��ִ� �޼ҵ�
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
				System.out.println("�� �� ���� ����");
			}
		}
		
	}
//cgv�� ��ȭ����, �󿵽ð�, �¼��� ���̺� �ִ��� Ȯ�����ִ� �޼ҵ�
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
	}

}
