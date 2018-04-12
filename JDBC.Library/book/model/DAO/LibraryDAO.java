package book.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.model.VO.LibraryVO;

public class LibraryDAO {

	private Statement stmt = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	public LibraryDAO() {
	}

	public ArrayList<LibraryVO> rentManagerAll(Connection conn) {

		ArrayList<LibraryVO> aList = new ArrayList<LibraryVO>();

		String query = "SELECT LEASE_NO, L.USER_ID, USER_NAME, BOOK_NAME FROM BOOK B, CUSTOMER C, LIBRARY L WHERE C.USER_ID"
				+ " = L.USER_ID AND B.BOOK_NO = L.BOOK_NO";

		try {
			stmt = conn.createStatement();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aList;
	}

	public ArrayList<LibraryVO> rentSearchId(Connection conn, String userID) {

		ArrayList<LibraryVO> aList = new ArrayList<LibraryVO>();

		String query = "SELECT LEASE_NO, L.USER_ID, USER_NAME, BOOK_NAME FROM BOOK B, CUSTOMER C, LIBRARY L WHERE C.USER_ID"
				+ " = L.USER_ID AND B.BOOK_NO = L.BOOK_NO AND L.USER_ID = '" + userID + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				LibraryVO lvo = new LibraryVO();
				lvo = new LibraryVO();
				lvo.setLeaseNo(rs.getInt("LEASE_NO"));
				lvo.setUserId(rs.getString("USER_ID"));
				lvo.setCvoUserName(rs.getString("USER_NAME"));
				lvo.setBvoBookName(rs.getString("BOOK_NAME"));
				aList.add(lvo);
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

	public LibraryVO rentSearchBookName(Connection conn, String bookName) {

		LibraryVO lvo = null;

		String query = "SELECT LEASE_NO, L.USER_ID, USER_NAME, BOOK_NAME FROM BOOK B, CUSTOMER C, LIBRARY L WHERE C.USER_ID"
				+ " = L.USER_ID AND B.BOOK_NO = L.BOOK_NO AND BOOK_NAME = '" + bookName + "'";

		try {
			stmt = conn.createStatement();
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

	public int rentInfoAdd(Connection conn, LibraryVO lvo) {

		int result = 0;

		String query = "INSERT INTO LIBRARY VALUES(?, (SELECT BOOK_NO FROM BOOK WHERE BOOK_NAME = ?), ? , DEFAULT, SYSDATE + 2)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, lvo.getLeaseNo());
			pstmt.setString(2, lvo.getBvoBookName());
			pstmt.setString(3, lvo.getUserId());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("\n※ 대여 번호가 중복 됩니다");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
}
