package book.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.model.VO.CustomerVO;

public class CustomerDAO {

	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	public CustomerDAO() {
	}

	public ArrayList<CustomerVO> customerSearchAll(Connection conn) {

		ArrayList<CustomerVO> aList = new ArrayList<CustomerVO>();

		String query = "SELECT * FROM CUSTOMER";

		try {
			stmt = conn.createStatement();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aList;
	}

	public ArrayList<CustomerVO> customerSearchName(Connection conn, String userName) {

		ArrayList<CustomerVO> aList = new ArrayList<CustomerVO>();

		String query = "SELECT * FROM CUSTOMER WHERE USER_NAME LIKE '%" + userName + "'";

		try {
			stmt = conn.createStatement();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aList;
	}

	public CustomerVO customerSearchId(Connection conn, String userId) {

		CustomerVO cv = null;

		String query = "SELECT * FROM CUSTOMER WHERE USER_ID LIKE '" + userId + "'";

		try {
			stmt = conn.createStatement();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cv;
	}

	public int customerSignUp(Connection conn, CustomerVO cv) {

		int result = 0;

		String query = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cv.getUserNo());
			pstmt.setString(2, cv.getUserId());
			pstmt.setString(3, cv.getUserName());
			pstmt.setInt(4, cv.getUserAge());
			pstmt.setString(5, cv.getAddr());
			pstmt.setString(6, cv.getGender());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int customerInfoUpdate(Connection conn, CustomerVO cVo) {

		int result = 0;
		
		String query = "UPDATE CUSTOMER SET USER_NAME = ?, ADDR = ? WHERE USER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cVo.getUserName());
			pstmt.setString(2, cVo.getAddr());
			pstmt.setString(3, cVo.getUserId());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public int customerDelete(Connection conn, String userId) {

		int result = 0;

		String query = "DELETE FROM CUSTOMER WHERE USER_ID LIKE '" + userId + "'";

		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
}
