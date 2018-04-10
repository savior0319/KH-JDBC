package book.controller;

import java.util.ArrayList;

import book.model.DAO.LibraryDAO;
import book.model.VO.LibraryVO;

public class LibraryController {

	private LibraryDAO lDao = new LibraryDAO();

	public LibraryController() {
	}

	public ArrayList<LibraryVO> rentManagerAll() {
		if (lDao.rentManagerAll().isEmpty()) {
			return null;
		} else
			return lDao.rentManagerAll();
	}

	public LibraryVO rentSearchId(String userID) {
		return lDao.rentSearchId(userID);
	}

	public LibraryVO rentSearchBookName(String bookName) {
		return lDao.rentSearchBookName(bookName);
	}

	public int rentInfoAdd(LibraryVO lvo) {
		return lDao.rentInfoAdd(lvo);
	}
}
