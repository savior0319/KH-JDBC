package book.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.model.VO.BookVO;

public class BookDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public BookDAO() {

	}

	public ArrayList<BookVO> bookSearchAll() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		ArrayList<BookVO> aList = new ArrayList<BookVO>();

		String query = "SELECT * FROM BOOK";
		try {
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
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aList;
	}

	public BookVO bookSearchCode(int bookCode) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		BookVO bv = null;
		String query = "SELECT * FROM BOOK WHERE BOOK_NO LIKE '" + bookCode + "'";

		try {
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
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bv;
	}

	public int bookAdd(BookVO bv) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int result = 0;

		String query = "INSERT INTO BOOK VALUES(" + bv.getBookNo() + ", '" + bv.getBookName() + "','"
				+ bv.getBookWriter() + "'," + bv.getBookPrice() + ",'" + bv.getPublisher() + "','" + bv.getGenre()
				+ "')";

		try {
			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else if (result == 0) {
				conn.rollback();
			}
		} catch (Exception e) {
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

	public int bookDelete(int bookCode) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		int result = 0;
		
		String query = "DELETE FROM BOOK WHERE BOOK_NO LIKE '" + bookCode + "'";
		
		try {
			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else if (result == 0) {
				conn.rollback();
			}
		} catch (Exception e) {
			System.out.println("\n※ 현재 대여 중인 책은 삭제 불가능 합니다");
			
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
