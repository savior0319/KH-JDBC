package book.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.model.VO.CustomerVO;

public class CustomerDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public CustomerDAO() {
	}

	public ArrayList<CustomerVO> customerSearchAll() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ArrayList<CustomerVO> aList = new ArrayList<CustomerVO>();

		String query = "SELECT * FROM CUSTOMER";

		try {
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				CustomerVO cv = new CustomerVO();
				cv.setUserNo(rs.getInt("user_No"));
				cv.setUserId(rs.getString("User_Id"));
				cv.setUserName(rs.getString("user_Name"));
				cv.setUserAge(rs.getInt("user_Age"));
				cv.setAddr(rs.getString("Addr"));
				cv.setGender(rs.getString("Gender"));
				cv.setEnrollDate(rs.getDate("Enroll_Date"));

				aList.add(cv);
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

	public ArrayList<CustomerVO> customerSearchName(String userName) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ArrayList<CustomerVO> aList = new ArrayList<CustomerVO>();

		String query = "SELECT * FROM CUSTOMER WHERE USER_NAME LIKE '%" + userName + "'";

		try {
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				CustomerVO cv = new CustomerVO();
				cv.setUserNo(rs.getInt("user_No"));
				cv.setUserId(rs.getString("User_Id"));
				cv.setUserName(rs.getString("user_Name"));
				cv.setUserAge(rs.getInt("user_Age"));
				cv.setAddr(rs.getString("Addr"));
				cv.setGender(rs.getString("Gender"));
				cv.setEnrollDate(rs.getDate("Enroll_Date"));

				aList.add(cv);
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

	public CustomerVO customerSearchId(String userId) {

		CustomerVO cv = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String query = "SELECT * FROM CUSTOMER WHERE USER_ID LIKE '" + userId + "'";

		try {
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				cv = new CustomerVO();
				cv.setUserNo(rs.getInt("user_No"));
				cv.setUserId(rs.getString("User_Id"));
				cv.setUserName(rs.getString("user_Name"));
				cv.setUserAge(rs.getInt("user_Age"));
				cv.setAddr(rs.getString("Addr"));
				cv.setGender(rs.getString("Gender"));
				cv.setEnrollDate(rs.getDate("Enroll_Date"));
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
		return cv;
	}

	public int customerSignUp(CustomerVO cv) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String query = "INSERT INTO CUSTOMER VALUES(" + cv.getUserNo() + ",'" + cv.getUserId() + "','"
				+ cv.getUserName() + "'," + cv.getUserAge() + ",'" + cv.getAddr() + "','" + cv.getGender()
				+ "', SYSDATE)";

		try {
			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	public int customerInfoUpdate(CustomerVO cVo) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String query = "UPDATE CUSTOMER SET USER_NAME = '" + cVo.getUserName() + "', ADDR = '" + cVo.getAddr()
				+ "' WHERE USER_ID LIKE '" + cVo.getUserId() + "'";

		try {
			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	public int customerDelete(String userId) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String query = "DELETE FROM CUSTOMER WHERE USER_ID LIKE '" + userId + "'";

		try {
			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
