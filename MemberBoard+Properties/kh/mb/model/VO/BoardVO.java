package kh.mb.model.VO;

import java.sql.Date;

public class BoardVO {

	private int boardNo;
	private String div, title, content, writer;
	private Date writeDate;

	public BoardVO() {
	}

	@Override
	public String toString() {
		return boardNo + "  " + div + "  " + title + "  " + content + "  " + writer + "  " + writeDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
}