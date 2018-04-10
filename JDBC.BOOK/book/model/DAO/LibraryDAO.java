package book.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.model.VO.LibraryVO;

public class LibraryDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public LibraryDAO() {
	}

	public ArrayList<LibraryVO> rentManagerAll() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ArrayList<LibraryVO> aList = new ArrayList<LibraryVO>();

		String query = "SELECT LEASE_NO, L.USER_ID, USER_NAME, BOOK_NAME FROM BOOK B, CUSTOMER C, LIBRARY L WHERE C.USER_ID"
				+ " = L.USER_ID AND B.BOOK_NO = L.BOOK_NO";

		try {
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				LibraryVO lVo = new LibraryVO();
				lVo.setLeaseNo(rs.getInt("LEASE_NO"));
				lVo.setUserId(rs.getString("USER_ID"));
				lVo.setCvoUserName(rs.getString("USER_NAME"));
				lVo.setBvoBookName(rs.getString("BOOK_NAME"));

				aList.add(lVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aList;
	}

	public LibraryVO rentSearchId(String userID) {

		LibraryVO lvo = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String query = "SELECT LEASE_NO, L.USER_ID, USER_NAME, BOOK_NAME FROM BOOK B, CUSTOMER C, LIBRARY L WHERE C.USER_ID"
				+ " = L.USER_ID AND B.BOOK_NO = L.BOOK_NO AND L.USER_ID = '" + userID + "'";

		try {
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				lvo = new LibraryVO();
				lvo.setLeaseNo(rs.getInt("LEASE_NO"));
				lvo.setUserId(rs.getString("USER_ID"));
				lvo.setCvoUserName(rs.getString("USER_NAME"));
				lvo.setBvoBookName(rs.getString("BOOK_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lvo;
	}

	public LibraryVO rentSearchBookName(String bookName) {

		LibraryVO lvo = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String query = "SELECT LEASE_NO, L.USER_ID, USER_NAME, BOOK_NAME FROM BOOK B, CUSTOMER C, LIBRARY L WHERE C.USER_ID"
				+ " = L.USER_ID AND B.BOOK_NO = L.BOOK_NO AND BOOK_NAME = '" + bookName + "'";

		try {
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				lvo = new LibraryVO();
				lvo.setLeaseNo(rs.getInt("LEASE_NO"));
				lvo.setUserId(rs.getString("USER_ID"));
				lvo.setCvoUserName(rs.getString("USER_NAME"));
				lvo.setBvoBookName(rs.getString("BOOK_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lvo;
	}

	public int rentInfoAdd(LibraryVO lvo) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String query = "INSERT INTO LIBRARY VALUES(" + lvo.getLeaseNo()
				+ ", (SELECT BOOK_NO FROM BOOK WHERE BOOK_NAME LIKE '" + lvo.getBvoBookName() + "'), '"
				+ lvo.getUserId() + "', DEFAULT, SYSDATE + 2)";

		try {
			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else if (result == 0) {
				conn.rollback();
			}
		} catch (Exception e) {
			System.out.println("※ 대여 번호가 중복 됩니다");
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
}
