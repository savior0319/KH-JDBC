package kh.mb.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import kh.mb.controller.BoardController;
import kh.mb.controller.MemberController;
import kh.mb.model.VO.BoardVO;
import kh.mb.model.VO.MemberVo;

public class MemberBoardView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	private BoardController bc = new BoardController();

	public MemberBoardView() {
	}

	public void menu() {
		while (true) {
			System.out.println("\n메인메뉴");
			System.out.println("1. 회원관리");
			System.out.println("2. 게시판관리");
			System.out.println("3. 프로그램 종료");
			System.out.print("메뉴 선택 -> ");
			switch (sc.nextInt()) {
			case 1:
				memberManagement();
				break;
			case 2:
				boardManagement();
				break;
			case 3:
				System.out.println("※ 프로그램을 종료합니다");
				System.exit(0);
			default:
				System.out.println("※ 번호를 잘못 입력했습니다");
				break;
			}
		}
	}

	public void memberManagement() {
		while (true) {
			System.out.println("\n회원관리 서브메뉴");
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 회원 아이디로 조회");
			System.out.println("3. 회원 이름으로 조회");
			System.out.println("4. 회원가입");
			System.out.println("5. 회원정보 수정(주소, 전화번호, 이메일)");
			System.out.println("6. 회원탈퇴");
			System.out.println("7. 메인메뉴");
			System.out.print("메뉴 선택 -> ");
			switch (sc.nextInt()) {
			case 1:
				memberSearchAll();
				break;
			case 2:
				memberSearchId();
				break;
			case 3:
				memberSearchName();
				break;
			case 4:
				memberSignUp();
				break;
			case 5:
				memeberInfoModify();
				break;
			case 6:
				memberDelete();
				break;
			case 7:
				menu();
				break;
			default:
				System.out.println("※ 번호를 잘못 입력했습니다");
				break;
			}
		}
	}

	public void memberSearchAll() {
		ArrayList<MemberVo> aList = mc.memberSearchAll();
		if (aList == null) {
			System.out.println("※ 조회할 회원이 없습니다");
		} else {
			Iterator<MemberVo> it = aList.iterator();
			System.out.println("\n전체 회원 조회");
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			System.out.println("\n※조회 완료\n");
		}
	}

	public void memberSearchId() {
		System.out.print("조회 할 아이디 입력 -> ");
		String memberId = sc.next();
		MemberVo mv = mc.memberSearchId(memberId);
		if (mv == null) {
			System.out.println("\n※ '" + memberId + "' 아이디를 가진 회원이 없습니다");
		} else {
			System.out.println("\n'" + memberId + "' 아이디를 가진 회원정보 조회");
			System.out.println("────────────────────────────────────────────────");
			System.out.println("회원번호 -> " + mv.getMemberNo());
			System.out.println("아이디 -> " + mv.getMemberId());
			System.out.println("비밀번호 -> " + mv.getMemberPwd());
			System.out.println("이름 -> " + mv.getMemberName());
			System.out.println("메일 -> " + mv.getAddress());
			System.out.println("전화번호 -> " + mv.getPhone());
			System.out.println("가입일 -> " + mv.getEnrollDate());
			System.out.println("────────────────────────────────────────────────");
			System.out.println("※ 조회완료");
		}
	}

	public void memberSearchName() {
		System.out.print("조회 할 이름 입력 -> ");
		String memberName = sc.next();
		ArrayList<MemberVo> aList = mc.memberSearchName(memberName);
		if (aList == null) {
			System.out.println("\n※ '" + memberName + "' 이름을 가진 회원이 없습니다");
		} else {
			Iterator<MemberVo> it = aList.iterator();
			System.out.println("\n'" + memberName + "' 이름을 가진 회원 조회");
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			System.out.println("※조회 완료\n");
		}
	}

	public void memberSignUp() {
		MemberVo mv = new MemberVo();
		System.out.print("아이디 입력 -> ");
		mv.setMemberId(sc.next());
		System.out.print("비밀번호 입력 -> ");
		mv.setMemberPwd(sc.next());
		System.out.print("이름 입력 -> ");
		mv.setMemberName(sc.next());
		System.out.print("메일 입력 -> ");
		mv.setEmail(sc.next());
		sc.nextLine();
		System.out.print("주소 입력 -> ");
		mv.setAddress(sc.nextLine());
		System.out.print("전화번호 입력[(-)제외] -> ");
		mv.setPhone(sc.next());
		int result = mc.memberSignUp(mv);
		if (result > 0) {
			System.out.println("\n※ 회원가입 완료\n");
		} else
			System.out.println("\n※ 회원가입 실패\n");
	}

	public void memeberInfoModify() {
		System.out.print("변경 할 아이디 입력 -> ");
		String memberId = sc.next();
		MemberVo mv = mc.memberSearchId(memberId);
		if (mv == null) {
			System.out.println("\n※ '" + memberId + "' 아이디를 가진 회원이 없습니다");
		} else {
			MemberVo mvm = new MemberVo();
			sc.nextLine();
			System.out.print("변경 할 주소 입력 -> ");
			mvm.setAddress(sc.nextLine());
			System.out.print("변경 할 전화번호 입력 -> ");
			mvm.setPhone(sc.next());
			System.out.print("변경 할 이메일 입력 -> ");
			mvm.setEmail(sc.next());
			mvm.setMemberId(memberId);
			int result = mc.memberInfoModify(mvm);
			if (result > 0) {
				System.out.println("\n※ 회원 정보 변경완료");
			} else
				System.out.println("\n※ 회원 정보 변경실패");
		}
	}

	public void memberDelete() {
		System.out.print("탈퇴 할 아이디 입력 -> ");
		String memberId = sc.next();
		MemberVo mv = mc.memberSearchId(memberId);
		if (mv == null) {
			System.out.println("\n※ '" + memberId + "' 아이디를 가진 회원이 없습니다");
		} else {
			int result = mc.memberDelete(memberId);
			if (result > 0) {
				System.out.println("\n※탈퇴 완료");
			} else
				System.out.println("\n※탈퇴 실패");
		}
	}

	public void boardManagement() {
		while (true) {
			System.out.println("\n게시판 서브메뉴");
			System.out.println("1. 게시판 전체 검색");
			System.out.println("2. 게시물 등록");
			System.out.println("3. 작성자로 검색");
			System.out.println("4. 제목으로 검색");
			System.out.println("5. 게시물 수정 (게시물 번호)");
			System.out.println("6. 게시물 삭제 (게시물 번호)");
			System.out.println("7. 메인메뉴");
			System.out.print("메뉴 선택 -> ");
			switch (sc.nextInt()) {
			case 1:
				boardSearchAll();
				break;
			case 2:
				boardRegister();
				break;
			case 3:
				boardSearchWriter();
				break;
			case 4:
				boardSearchTitle();
				break;
			case 5:
				boardContentModify();
				break;
			case 6:
				boardContentDelete();
				break;
			case 7:
				menu();
				break;
			default:
				System.out.println("※ 번호를 잘못 입력했습니다");
				break;
			}
		}
	}

	public void boardSearchAll() {
		ArrayList<BoardVO> aList = bc.boardSearchAll();
		if (aList == null) {
			System.out.println("\n※ 조회할 게시판 목록이 없습니다");
		} else {
			Iterator<BoardVO> it = aList.iterator();
			System.out.println("\n게시판 목록 조회");
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			System.out.println("※조회 완료\n");
		}
	}

	public void boardRegister() {
		boolean isDivChk = true;
		BoardVO bv = new BoardVO();
		while (isDivChk) {
			System.out.print("게시물 종류 입력[공지, 일반, 비밀] -> ");
			String div = sc.next();
			if (div.equals("공지") || div.equals("일반") || div.equals("비밀")) {
				bv.setDiv(div);
				isDivChk = false;
			} else {
				System.out.println("\n※ 게시물 종류를 잘못입력했습니다\n");
			}
		}
		sc.nextLine();
		System.out.print("제목 입력 -> ");
		bv.setTitle(sc.nextLine());
		System.out.print("내용 입력 -> ");
		bv.setContent(sc.nextLine());
		System.out.print("작성자(ID) 입력 -> ");
		bv.setWriter(sc.next());
		int result = bc.boardRegister(bv);
		if (result > 0) {
			System.out.println("\n※ 게시물 등록 완료");
		} else
			System.out.println("\n※ 게시물 등록 실패");
	}

	public void boardSearchWriter() {
		System.out.print("조회 할 게시물 작성자(ID) 입력 -> ");
		String memberId = sc.next();
		MemberVo mv = mc.memberSearchId(memberId);
		if (mv == null) {
			System.out.println("\n※ '" + memberId + "' 아이디를 가진 회원이 없습니다");
		} else {
			ArrayList<BoardVO> aList = bc.boardSearchWriter(memberId);
			if (aList == null) {
				System.out.println("\n※ '" + memberId + "' 의 회원의 게시글이 없습니다");
			} else {
				System.out.println("\n※ '" + memberId + "' 작성자의 게시글 조회");
				System.out.println("─────────────────────────────────────────────────────────────────────────");
				Iterator<BoardVO> it = aList.iterator();
				while (it.hasNext()) {
					System.out.println(it.next().toString());
				}
				System.out.println("─────────────────────────────────────────────────────────────────────────");
			}
		}
	}

	public void boardSearchTitle() {
		System.out.print("조회 할 게시물 제목 입력 -> ");
		String title = sc.nextLine();
		sc.nextLine();
		ArrayList<BoardVO> aList = bc.boardSearchTitle(title);
		if (aList == null) {
			System.out.println("※ '" + title + "' 이 포함된 게시물이 없습니다");
		} else {
			System.out.println("\n※ 제목에 '" + title + "' 이 포함된 게시글 조회");
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			Iterator<BoardVO> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("─────────────────────────────────────────────────────────────────────────");
		}
	}

	public void boardContentModify() {
		System.out.print("\n수정할 게시물 번호 입력 -> ");
		int boardNo = sc.nextInt();
		BoardVO bv = bc.boardSearchNo(boardNo);
		if (bv == null) {
			System.out.println("\n※ '" + boardNo + "' 번호를 가진 게시물이 없습니다");
		} else {
			BoardVO bvo = new BoardVO();
			sc.nextLine();
			System.out.print("변경 할 제목 입력 -> ");
			bvo.setTitle(sc.nextLine());
			System.out.print("변경 할 내용 입력 -> ");
			bvo.setContent(sc.nextLine());
			bvo.setBoardNo(boardNo);
			int result = bc.boardContentModify(bvo);
			if (result > 0) {
				System.out.println("\n※ 수정완료");
			} else
				System.out.println("\n※ 수정실패");
		}
	}

	public void boardContentDelete() {
		System.out.print("\n삭제할 게시물 번호 입력 -> ");
		int boardNo = sc.nextInt();
		BoardVO bv = bc.boardSearchNo(boardNo);
		if (bv == null) {
			System.out.println("\n※ '" + boardNo + "' 번호를 가진 게시물이 없습니다");
		} else {
			int result = bc.boardContentDelete(boardNo);
			if (result > 0) {
				System.out.println("\n※ 삭제완료");
			} else
				System.out.println("\n※ 삭제실패");
		}
	}
}
