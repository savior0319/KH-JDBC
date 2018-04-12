package book.model.VO;

import java.sql.Date;

public class LibraryVO {

	private String userId, cvoUserName, bvoBookName;
	private int leaseNo, bookNo;
	private Date leaseDate, returnDate;

	@Override
	public String toString() {
		return leaseNo + "  " + userId + "  " + cvoUserName + "  " + bvoBookName;
	}

	public LibraryVO() {
	}
	
	public String getCvoUserName() {
		return cvoUserName;
	}

	public void setCvoUserName(String cvoUserName) {
		this.cvoUserName = cvoUserName;
	}

	public String getBvoBookName() {
		return bvoBookName;
	}

	public void setBvoBookName(String bvoBookName) {
		this.bvoBookName = bvoBookName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLeaseNo() {
		return leaseNo;
	}

	public void setLeaseNo(int leaseNo) {
		this.leaseNo = leaseNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public Date getLeaseDate() {
		return leaseDate;
	}

	public void setLeaseDate(Date leaseDate) {
		this.leaseDate = leaseDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
