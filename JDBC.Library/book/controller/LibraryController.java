package book.controller;

import java.util.ArrayList;

import book.model.VO.LibraryVO;
import book.model.service.LibraryService;

public class LibraryController {

	private LibraryService lService = new LibraryService();

	public LibraryController() {
	}

	public ArrayList<LibraryVO> rentManagerAll() {
		if (lService.rentManagerAll().isEmpty()) {
			return null;
		} else
			return lService.rentManagerAll();
	}

	public ArrayList<LibraryVO> rentSearchId(String userID) {
		return lService.rentSearchId(userID);
	}

	public LibraryVO rentSearchBookName(String bookName) {
		return lService.rentSearchBookName(bookName);
	}

	public int rentInfoAdd(LibraryVO lvo) {
		return lService.rentInfoAdd(lvo);
	}
}
