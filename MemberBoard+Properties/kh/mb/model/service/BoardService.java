package kh.mb.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.mb.common.JDBCTemplate;
import kh.mb.model.DAO.BoardDAO;
import kh.mb.model.VO.BoardVO;

public class BoardService {

	private BoardDAO bDao = new BoardDAO();
	private Connection conn = null;

	public BoardService() {
	}

	public ArrayList<BoardVO> boardSearch() {
		conn = JDBCTemplate.getConnect(conn);
		ArrayList<BoardVO> aList = bDao.boardSearch(conn);
		JDBCTemplate.close(conn);
		return aList;
	}

	public int boardRegister(BoardVO bv) {
		conn = JDBCTemplate.getConnect(conn);
		int result = bDao.boardRegister(conn, bv);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollBack(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<BoardVO> boardSearchWriter(String memberId) {
		conn = JDBCTemplate.getConnect(conn);
		ArrayList<BoardVO> aList = bDao.boardSearchWriter(conn, memberId);
		JDBCTemplate.close(conn);
		return aList;
	}

	public ArrayList<BoardVO> boardSearchTitle(String title) {
		conn = JDBCTemplate.getConnect(conn);
		ArrayList<BoardVO> aList = bDao.boardSearchTitle(conn, title);
		JDBCTemplate.close(conn);
		return aList;
	}

	public int boardContentModify(BoardVO bvo) {
		conn = JDBCTemplate.getConnect(conn);
		int result = bDao.boardContentModify(conn, bvo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollBack(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public BoardVO boardSearchNo(int boardNo) {
		conn = JDBCTemplate.getConnect(conn);
		BoardVO bv = bDao.boardSearchNo(conn, boardNo);
		JDBCTemplate.close(conn);
		return bv;
	}

	public int boardContentDelete(int boardNo) {
		conn = JDBCTemplate.getConnect(conn);
		int result = bDao.boardContentDelete(conn, boardNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollBack(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}
