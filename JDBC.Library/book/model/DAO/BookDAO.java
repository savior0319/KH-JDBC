package book.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.model.VO.BookVO;

public class BookDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public BookDAO() {

	}

	public ArrayList<BookVO> bookSearchAll(Connection conn) {
		
		ArrayList<BookVO> aList = new ArrayList<BookVO>();

		String query = "SELECT * FROM BOOK";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				BookVO bv = new BookVO();
				bv.setBookNo(rs.getInt("Book_No"));
				bv.setBookName(rs.getString("Book_Name"));
				bv.setBookWriter(rs.getString("Book_Writer"));
				bv.setBookPrice(rs.getInt("Book_Price"));
				bv.setPublisher(rs.getString("publisher"));
				bv.setGenre(rs.getString("genre"));

				aList.add(bv);
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

	public BookVO bookSearchCode(Connection conn, int bookCode) {

	
		BookVO bv = null;
		String query = "SELECT * FROM BOOK WHERE BOOK_NO LIKE '" + bookCode + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				bv = new BookVO();
				bv.setBookNo(rs.getInt("Book_No"));
				bv.setBookName(rs.getString("Book_Name"));
				bv.setBookWriter(rs.getString("Book_Writer"));
				bv.setBookPrice(rs.getInt("Book_Price"));
				bv.setPublisher(rs.getString("publisher"));
				bv.setGenre(rs.getString("genre"));
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
		return bv;
	}

	public int bookAdd(Connection conn, BookVO bv) {

		int result = 0;

		String query = "INSERT INTO BOOK VALUES(?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, bv.getBookNo());
			pstmt.setString(2, bv.getBookName());
			pstmt.setString(3, bv.getBookWriter());
			pstmt.setInt(4, bv.getBookPrice());
			pstmt.setString(5, bv.getPublisher());
			pstmt.setString(6, bv.getGenre());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else conn.rollback();

		} catch (Exception e) {
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

	public int bookDelete(Connection conn, int bookCode) {

		int result = 0;

		String query = "DELETE FROM BOOK WHERE BOOK_NO LIKE '" + bookCode + "'";

		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			
			if(result > 0) {
				conn.commit();
			} else conn.rollback();

		} catch (Exception e) {
			System.out.println("\n※ 현재 대여 중인 책은 삭제 불가능 합니다");

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public BookVO searchBookName(Connection conn, String bookName) {

		BookVO bv = null;
		String query = "SELECT BOOK_NAME FROM BOOK WHERE BOOK_NAME LIKE '" + bookName + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				bv = new BookVO();
				bv.setBookName(rs.getString("BOOK_NAME"));
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
		return bv;
	}
}
