package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection conn = null;
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static String user = "web";
	public static String password = "web";
	
	private DBConnection() {}
	
	public static Connection getConnection() {
		if(conn==null) {
			try {
				Class.forName(driver);
				System.out.println("jdbc Driver 로딩 성공");
				conn=DriverManager.getConnection(url,user,password);
				System.out.println("오라클 연결 성공");
			} catch (ClassNotFoundException e) {
				System.out.println("jdbc Driver 로딩 실패");
			} catch (SQLException sqle) {
				System.out.println("오라클 연결 실패");
			}
		}
		return conn;
	}
}

