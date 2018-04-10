package book.controller;

import java.util.ArrayList;

import book.model.DAO.BookDAO;
import book.model.VO.BookVO;

public class BookController {

	private BookDAO bDao = new BookDAO();

	public ArrayList<BookVO> bookSearchAll() {
		if (bDao.bookSearchAll().isEmpty()) {
			return null;
		} else
			return bDao.bookSearchAll();
	}

	public BookVO bookSearchCode(int bookCode) {
		return bDao.bookSearchCode(bookCode);
	}

	public int bookAdd(BookVO bv) {
		return bDao.bookAdd(bv);
	}

	public int bookDelete(int bookCode) {
		return bDao.bookDelete(bookCode);
	}

	public BookVO searchBookName(String bookName) {
		return bDao.searchBookName(bookName);
	}

}
