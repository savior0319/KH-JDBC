package kh.java.jdbc.controller;

import java.util.ArrayList;

import kh.java.jdbc.model.dao.MemberDAO;
import kh.java.jdbc.model.vo.Member;

public class MemberController {

	private MemberDAO mDAO = new MemberDAO();

	/* 기본 생성자 */
	public MemberController() {
	}

	/*
	 * View에서 요청이 들어온 작업은 Controller에서 DAO로 요청 작업을 해야 함
	 */
	public ArrayList<Member> selectAll() {
		ArrayList<Member> aList = new ArrayList<Member>();
		aList = mDAO.selectAll();
		if (aList.isEmpty()) {
			return null;
		} else {
			return aList;
		}
	}

	public Member selectOneId(String memberId) {
		Member m = mDAO.selectOneId(memberId);
		return m;
	}

	public ArrayList<Member> selectName(String memberName) {
		ArrayList<Member> aList = new ArrayList<Member>();
		aList = mDAO.selectName(memberName);
		if (aList.isEmpty()) {
			return null;
		} else {
			return aList;
		}
	}

	public int insertMember(Member m) {
		int result = mDAO.insertMember(m);
		return result;
	}

	public int updateMember(Member me) {
		int result = mDAO.updateMember(me);
		return result;
	}

	public int deleteMember(String memberId) {
		int result = mDAO.deleteMember(memberId);
		return result;
	}
}
