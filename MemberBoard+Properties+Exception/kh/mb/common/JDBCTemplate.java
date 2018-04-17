package kh.mb.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	// private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	// private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	// private static final String USER = "khstudent";
	// private static final String PASSWORD = "khstudent";

	private JDBCTemplate() {
	}

	public static Connection getConnect(Connection conn) {
		try {
			Properties pp = new Properties();
			pp.load(new FileReader("C:\\workspace\\KH-JDBC\\MemberBoard+Properties+Exception\\resource\\driver.properties"));
			String driver = pp.getProperty("driver");
			String url = pp.getProperty("url");
			String user = pp.getProperty("user");
			String password = pp.getProperty("password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("JDBCTemplate : " + e.getMessage());
		}
	}

	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void rollBack(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
