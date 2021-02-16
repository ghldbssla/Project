package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
	public void create(String id, String title) {

		String sql = "INSERT INTO FAV_USER VALUES(?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);// session.id �� ����
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
				ans = rs.getString(2);
				// ����
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
		if (ans.equals("") || ans == null) {
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
				ans = rs.getString(2);
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

	// cgv�� ��ȭ����, �󿵽ð�, �¼��� ���̺� �־��ִ� �޼ҵ�
	public void sit_insert(String m_time, int ticketNum, String m_name, String cgvCode, String sit) {
		boolean payment = false;
		String userId = Session.get("session_id");
		String sql = "INSERT INTO SCREEN_THEATER(userid,S_SCHEDULE_TIME,S_SEAT_CNT,m_name,T_NAME,SEATNUM,PAYMENT) VALUES(?,?,?,?,?,?,?)";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, m_time);
			pstm.setInt(3, ticketNum);
			pstm.setString(4, m_name);
			pstm.setString(5, cgvCode);
			pstm.setString(6, sit);
			pstm.setBoolean(7, payment);
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
//???
//cgv�� ��ȭ����, �󿵽ð�, �¼��� ���̺� �־��ִ� �޼ҵ�
	// id, m_name, cgv, m_time, sit, person_cnt, payment
	public void sit_insert(String id, String m_name, String cgvCode, String m_time, String sit, int person_cnt) {
		String sql = "INSERT INTO SCREEN_THEATER VALUES(?,?,?,?,?,?,?)";
		boolean payment = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, m_time);
			pstm.setInt(3, person_cnt);
			pstm.setString(4, m_name);
			pstm.setString(5, cgvCode);
			pstm.setString(6, sit);
			pstm.setBoolean(7, payment);
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
		String sql = "SELECT * FROM SCREEN_THEATER WHERE T_NAME=? and  m_name=? and S_SCHEDULE_TIME=? and SEATNUM=?";
		String ans = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgvCode);
			pstm.setString(2, m_name);
			pstm.setString(3, m_time);
			pstm.setString(4, sit);
			rs = pstm.executeQuery();

			while (rs.next()) {
				ans = rs.getString(2);
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

//	----------------------------------------MovieChoiceView()�޼ҵ�--------------------------------------------------------------
	// MovieChoiceView���� ���� ���ϴ� ��ȭ �̸��� ����ϱ� ���� �޼ҵ�
	public String NowListM_Name() {
		String sql = "SELECT M_NAME FROM MOVIE_LIST ORDER BY M_RATE DESC";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += rs.getString(1) + ";";
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

	// �Է¹��� �����ȿ� �ִ� ��� �󿵰� �����ֱ�
	public String choiceTheater(String area) {
		String sql = "SELECT T_NAME FROM THEATER WHERE T_CITY=?";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, area);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += rs.getString(1) + "\n";
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

	public void bringTicketNum() {

	}

	// ===============�� ��� ����
	// �� ��� ��ü ����
	public void delete_a(String id) {

		String sql = "DELETE FROM FAV_USER WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);// session.id�� ������
			pstm.executeUpdate();
			System.out.println("���� ����");
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
	public void delete_c(String id, String title) {

		String sql = "DELETE FROM FAV_USER WHERE USERID=? AND M_TITLE =?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);// session.id�� ������
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

	public String getlist(String id) {
		String sql = "SELECT * FROM FAV_USER WHERE USERID=?";
		String result = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				result += "���̵� : " + rs.getString(1) + "\n\t��ȭ���� : " + rs.getString(2) + "\n";
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

	// ===============���� ��� ����
	// �� ��� ��ü ����
	public void delete_a_book() {

		String sql = "DELETE FROM SCREEN_THEATER WHERE USERID=? and payment='false'";
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

	public String getlist_a() {
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


	public void delete_a_B(String id, boolean payment) {
		String sql = "DELETE FROM SCREEN_THEATER WHERE USERID=? and payment=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);// session.id�� ������
			pstm.setBoolean(2, payment);
			pstm.executeUpdate();
			System.out.println("���� ��� ����");
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

	public void delete_c_B(String id, String cgvcode, String mname, String m_time,boolean payment) {
		String sql = "DELETE FROM SCREEN_THEATER WHERE USERID=? AND S_SCHEDULE_TIME=? AND M_NAME =? AND T_NAME =? AND PAYMENT = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);// session.id�� ������
			pstm.setString(2, m_time);
			pstm.setString(3, mname);
			pstm.setString(4, cgvcode);
			pstm.setBoolean(5, payment);
			pstm.executeUpdate();
			System.out.println("���� ��� ���� ����");
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

	public Boolean check_n(String cgvCode, String m_name, String id, String sit, boolean payment) {
		String sql = "SELECT * FROM SCREEN_THEATER WHERE T_NAME=? and  m_name=? and USERID=? and SEATNUM=? and payment=?";
		String ans = "";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cgvCode);
			pstm.setString(2, m_name);
			pstm.setString(3, id);
			pstm.setString(4, sit);
			pstm.setBoolean(5,payment);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ans = rs.getString(2);
				// ����
			}
		} catch (SQLException e) {
			System.out.println("���� ����");
			System.out.println(e);
		} finally {
			try {
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
		if (ans.equals("") || ans == null) {
			return false;
		} else {
			return true;
		}
	}

	// --------------------------------------Pay View
	// �޼ҵ�------------------------------------------
	// ������ ǥ ���� �������ֱ�
	public int bringTicketNum(String userid, boolean payment) {
		int result = 0;
		String sql = "SELECT S_SEAT_CNT FROM SCREEN_THEATER WHERE USERID=? AND PAYMENT=?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setBoolean(2, payment);
			rs = pstm.executeQuery();
			if (rs.next()) {
				result = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
		return result;
	}

	// ������ ��ȭǥ ���� �����ֱ�
	public String payCheck(String userid, boolean payment) {
		String result = "";
		String seat = "";
		String sql = "SELECT S_SCHEDULE_TIME, m_name, T_NAME, SEATNUM FROM SCREEN_THEATER WHERE userid=? AND payment=?";
		/*
		 * �󿵰� : T_NAME ��ȭ : m_name �ð�ǥ : S_SCHEDULE_TIME �¼���ȣ : SEATNUM
		 */
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setBoolean(2, payment);
			rs = pstm.executeQuery();
			while (rs.next()) {
				seat += rs.getString(4) + " ";
				result = "�󿵰� : " + rs.getString(3) + "\n��ȭ : " + rs.getString(2) + "\n�ð�ǥ : " + rs.getString(1)
						+ "\n�¼���ȣ : " + seat;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
		return result;

	}

	// payment true�� �ٲ��ֱ�
	public void updatePayment(String userid, boolean payment) {
		String sql = "UPDATE SCREEN_THEATER SET PAYMENT=? WHERE USERID=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setBoolean(1, payment);
			pstm.setString(2, userid);
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
	public String bringCoupon(String userid) {
		String result = "";
		String sql = "SELECT COUPON_NAME FROM COUPON WHERE USERID=?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			rs = pstm.executeQuery();
			while(rs.next()) {
				result+=rs.getString(1)+";";
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				System.out.println("�� �� ���� ����");
			}
		}
		return result;
	}
	
	public int useCoupon(String coupon_name) {
		int result=0;
		if (coupon_name.equals("1000�� ����")) {
			result=1000;
		}else if (coupon_name.equals("2000�� ����")) {
			result=2000;
		}else if (coupon_name.equals("5000�� ����")) {
			result=5000;
		}else if (coupon_name.equals("8000�� ����")) {
			result=8000;
		}return result;
	}
	
	public void deleteCoupon(String userid, String coupon_name) {
		String sql = "DELETE FROM COUPON WHERE USERID=? AND COUPON_NAME=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, userid);
			pstm.setString(2, coupon_name);
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
	//-----------------------------RandomCoupon View �޼ҵ�--------------------------------------	
	
		public String coupon() {
			String result = "";
			Random rand=new Random();
			int r = rand.nextInt(4);
			switch(r) {
			case 0 :
				result = "1000�� ����"; 
				break;
			case 1 :
				result = "2000�� ����"; 
				break;
			case 2 :
				result = "5000�� ����"; 
				break;
			case 3 : 
				result = "8000�� ����"; 
				break;
			}
			return result;
		}

		public void insertCoupon(String userid, String coupon_name) {
			String sql = "INSERT INTO COUPON(USERID, COUPON_NAME) VALUES(?,?)";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, userid);
				pstm.setString(2, coupon_name);
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
	// ------------------------------------------------------------------------------------------------

		public String watchFavList(String userid) {
			String result = "";
			String sql = "SELECT M_TITLE FROM FAV_USER WHERE USERID=?";
			
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, userid);
				rs = pstm.executeQuery();
				while(rs.next()) {
					result+=rs.getString(1)+";";
				}
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				try {
					pstm.close();
				} catch (SQLException e) {
					System.out.println("�� �� ���� ����");
				}
			}
			return result;
			
		}
		public String myticket2(String userid) {
	         String result = "";
	         String seat = "";
	         String sql = "SELECT S_SCHEDULE_TIME, m_name, T_NAME, SEATNUM FROM SCREEN_THEATER WHERE userid=?";
	         
	         try {
	            pstm = conn.prepareStatement(sql);
	            pstm.setString(1, userid);
	            rs = pstm.executeQuery();
	            while(rs.next()) {
	               seat+=rs.getString(4)+" ";
	               result="�󿵰� : "+rs.getString(3)+"\n��ȭ : "+rs.getString(2)+"\n�ð�ǥ : "+
	                     rs.getString(1)+"\n�¼���ȣ : "+seat;
	            }
	         } catch (SQLException e) {
	            System.out.println(e);
	         } finally {
	            try {
	               pstm.close();
	            } catch (SQLException e) {
	               System.out.println("�� �� ���� ����");
	            }
	         }
	         return result;
	         
	      }
}
