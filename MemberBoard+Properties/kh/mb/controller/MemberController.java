package kh.mb.controller;

import java.util.ArrayList;

import kh.mb.model.VO.MemberVo;
import kh.mb.model.service.MemberService;

public class MemberController {

	private MemberService ms = new MemberService();

	public MemberController() {
	}

	public ArrayList<MemberVo> memberSearchAll() {
		ArrayList<MemberVo> aList = ms.memberSearchAll();
		if (aList.isEmpty()) {
			return null;
		} else
			return aList;
	}

	public MemberVo memberSearchId(String memberId) {
		MemberVo mv = ms.memberSearchId(memberId);
		return mv;
	}

	public ArrayList<MemberVo> memberSearchName(String memberName) {
		ArrayList<MemberVo> aList = ms.memberSearchName(memberName);
		if (aList.isEmpty()) {
			return null;
		} else
			return aList;
	}

	public int memberSignUp(MemberVo mv) {
		int result = ms.memberSignUp(mv);
		return result;
	}

	public int memberInfoModify(MemberVo mvm) {
		int result = ms.memberInfoModify(mvm);
		return result;
	}


	public int memberDelete(String memberId) {
		int result = ms.memberDelete(memberId);
		return result;
	}
}
