package kh.mb.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.mb.common.JDBCTemplate;
import kh.mb.model.VO.BoardVO;

public class BoardDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public BoardDAO() {
	}

	public ArrayList<BoardVO> boardSearch(Connection conn) {

		ArrayList<BoardVO> aList = new ArrayList<BoardVO>();

		String query = "SELECT * FROM BOARD";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("BOARD_NO"));
				bv.setDiv(rs.getString("DIV"));
				bv.setTitle(rs.getString("TITLE"));
				bv.setContent(rs.getString("CONTENT"));
				bv.setWriter(rs.getString("WRITER"));
				bv.setWriteDate(rs.getDate("WRITE_DATE"));
				aList.add(bv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return aList;
	}

	public int boardRegister(Connection conn, BoardVO bv) {

		int result = 0;

		String query = "INSERT INTO BOARD VALUES(BOARD_NO.NEXTVAL, ?, ?, ?, ?, SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bv.getDiv());
			pstmt.setString(2, bv.getTitle());
			pstmt.setString(3, bv.getContent());
			pstmt.setString(4, bv.getWriter());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<BoardVO> boardSearchWriter(Connection conn, String memberId) {

		ArrayList<BoardVO> aList = new ArrayList<BoardVO>();

		String query = "SELECT * FROM BOARD WHERE WRITER = '" + memberId + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("BOARD_NO"));
				bv.setDiv(rs.getString("DIV"));
				bv.setTitle(rs.getString("TITLE"));
				bv.setContent(rs.getString("CONTENT"));
				bv.setWriter(rs.getString("WRITER"));
				bv.setWriteDate(rs.getDate("WRITE_DATE"));
				aList.add(bv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return aList;
	}

	public ArrayList<BoardVO> boardSearchTitle(Connection conn, String title) {

		ArrayList<BoardVO> aList = new ArrayList<BoardVO>();

		String query = "SELECT * FROM BOARD WHERE TITLE like '%" + title + "%'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("BOARD_NO"));
				bv.setDiv(rs.getString("DIV"));
				bv.setTitle(rs.getString("TITLE"));
				bv.setContent(rs.getString("CONTENT"));
				bv.setWriter(rs.getString("WRITER"));
				bv.setWriteDate(rs.getDate("WRITE_DATE"));
				aList.add(bv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return aList;
	}

	public int boardContentModify(Connection conn, BoardVO bvo) {

		int result = 0;
		String query = "UPDATE BOARD SET TITLE = ?, CONTENT =? WHERE BOARD_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContent());
			pstmt.setInt(3, bvo.getBoardNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public BoardVO boardSearchNo(Connection conn, int boardNo) {

		BoardVO bv = null;

		String query = "SELECT BOARD_NO FROM BOARD WHERE BOARD_NO = '" + boardNo + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				bv = new BoardVO();
				bv.setBoardNo(rs.getInt("BOARD_NO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return bv;
	}

	public int boardContentDelete(Connection conn, int boardNo) {

		int result = 0;
		String query = "DELETE FROM BOARD WHERE BOARD_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
