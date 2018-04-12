package book.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import book.model.DAO.LibraryDAO;
import book.model.VO.LibraryVO;

public class LibraryService {
	
	private LibraryDAO lDao = new LibraryDAO();
	private Connection conn = null;

	public ArrayList<LibraryVO> rentManagerAll() {
		
		ArrayList<LibraryVO> aList = new ArrayList<LibraryVO>();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			aList = lDao.rentManagerAll(conn);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	public ArrayList<LibraryVO> rentSearchId(String userID) {
		
		ArrayList<LibraryVO> aList = new ArrayList<LibraryVO>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			aList = lDao.rentSearchId(conn, userID);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	public LibraryVO rentSearchBookName(String bookName) {
		
		LibraryVO lVo = new LibraryVO();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			lVo = lDao.rentSearchBookName(conn, bookName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return lVo;
	}

	public int rentInfoAdd(LibraryVO lvo) {
		
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			result = lDao.rentInfoAdd(conn, lvo);
			
			if (result > 0) {
				conn.commit();
			} else if (result == 0) {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
