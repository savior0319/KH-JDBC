package kh.mb.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.mb.Exception.MemberBoardException;
import kh.mb.common.JDBCTemplate;
import kh.mb.model.DAO.MemberDAO;
import kh.mb.model.VO.MemberVo;

public class MemberService {

	private Connection conn = null;
	private MemberDAO mDao = new MemberDAO();

	public ArrayList<MemberVo> memberSearchAll() throws MemberBoardException {
		conn = JDBCTemplate.getConnect(conn);
		ArrayList<MemberVo> aList = mDao.memberSearchAll(conn);
		JDBCTemplate.close(conn);
		return aList;
	}

	public MemberVo memberSearchId(String memberId) {
		conn = JDBCTemplate.getConnect(conn);
		MemberVo mv = mDao.memberSearchId(conn, memberId);
		JDBCTemplate.close(conn);
		return mv;
	}

	public ArrayList<MemberVo> memberSearchName(String memberName) {
		conn = JDBCTemplate.getConnect(conn);
		ArrayList<MemberVo> aList = mDao.memberSearchName(conn, memberName);
		JDBCTemplate.close(conn);
		return aList;
	}

	public int memberSignUp(MemberVo mv) {
		conn = JDBCTemplate.getConnect(conn);
		int result = mDao.memberSignUp(conn, mv);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else
			JDBCTemplate.rollBack(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	public int memberInfoModify(MemberVo mvm) {
		conn = JDBCTemplate.getConnect(conn);
		int result = mDao.memberInfoModify(conn, mvm);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else
			JDBCTemplate.rollBack(conn);
		JDBCTemplate.close(conn);
		return result;
	}

	public int memberDelete(String memberId) {
		conn = JDBCTemplate.getConnect(conn);
		int result = mDao.memberDelete(conn, memberId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else
			JDBCTemplate.rollBack(conn);
		JDBCTemplate.close(conn);
		return result;
	}

}
