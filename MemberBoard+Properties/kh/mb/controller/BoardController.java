package kh.mb.controller;

import java.util.ArrayList;

import kh.mb.model.VO.BoardVO;
import kh.mb.model.service.BoardService;

public class BoardController {

	private BoardService bs = new BoardService();

	public BoardController() {
	}

	public ArrayList<BoardVO> boardSearchAll() {
		ArrayList<BoardVO> aList = bs.boardSearch();
		if (aList.isEmpty()) {
			return null;
		} else
			return aList;
	}

	public int boardRegister(BoardVO bv) {
		int result = bs.boardRegister(bv);
		return result;
	}

	public ArrayList<BoardVO> boardSearchWriter(String memberId) {
		ArrayList<BoardVO> aList = bs.boardSearchWriter(memberId);
		if (aList.isEmpty()) {
			return null;
		} else
			return aList;
	}

	public ArrayList<BoardVO> boardSearchTitle(String title) {
		ArrayList<BoardVO> aList = bs.boardSearchTitle(title);
		if (aList.isEmpty()) {
			return null;
		} else
			return aList;
	}

	public int boardContentModify(BoardVO bvo) {
		int result = bs.boardContentModify(bvo);
		return result;
	}

	public BoardVO boardSearchNo(int boardNo) {
		return bs.boardSearchNo(boardNo);
	}

	public int boardContentDelete(int boardNo) {
		int result = bs.boardContentDelete(boardNo);
		return result;
	}
}
