package book.controller;

import java.util.ArrayList;

import book.model.VO.BookVO;
import book.model.service.BookService;

public class BookController {

	private BookService bService = new BookService();

	public ArrayList<BookVO> bookSearchAll() {
		if (bService.bookSearchAll().isEmpty()) {
			return null;
		} else
			return bService.bookSearchAll();
	}

	public BookVO bookSearchCode(int bookCode) {
		return bService.bookSearchCode(bookCode);
	}

	public int bookAdd(BookVO bv) {
		return bService.bookAdd(bv);
	}

	public int bookDelete(int bookCode) {
		return bService.bookDelete(bookCode);
	}

	public BookVO searchBookName(String bookName) {
		return bService.searchBookName(bookName);
	}

}
