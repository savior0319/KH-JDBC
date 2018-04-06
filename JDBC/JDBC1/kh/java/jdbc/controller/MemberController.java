package kh.java.jdbc.controller;

import kh.java.jdbc.model.dao.MemberDAO;

public class MemberController {

	private MemberDAO mDAO = new MemberDAO();

	/* 기본 생성자 */
	public MemberController() {
	}

	/*
	 * View에서 요청이 들어온 작업은 Controller에서 DAO로 요청 작업을 해야 함
	 */
	public void selectAll() {
		mDAO.selectAll();
	}

}
