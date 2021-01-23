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
				result += "��ȭ ���� : \"" +rs.getString(1) + "\"\n\t- ���� : " + rs.getString(2) + "\n\t-�帣 : " + rs.getString(3)
						+ "\n\t-���� : \"" + rs.getString(4)+ "\"\n\t-��� : \"" + rs.getString(5) + "\"\n\n";
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
					result += "��ȭ ���� : \"" +rs.getString(1) + "\"\n\t-�帣 : " + rs.getString(2)
					+ "\n\t-���� : \"" + rs.getString(3)+ "\"\n\t-��� : \"" + rs.getString(4) + "\"\n\n";				}
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
	//���� ���� ���̺� ������ ����
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
	//�� ������ ���̺� ������ ����
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
	// ������ �߰�(����,����,�帣,����,���)
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
		String sql = "INSERT INTO TBL_MOVIE_INFO_SOON VALUES(?,?,?,?)";
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

	}



