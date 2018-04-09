package kh.java.jdbc.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import kh.java.jdbc.controller.MemberController;
import kh.java.jdbc.model.vo.Member;

public class MemberMenu {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();

	public MemberMenu() {
	}

	public void mainMenu() {
		while (true) {
			System.out.println("\n ========= 회원관리 프로그램 =========");
			System.out.println("1. 회원 정보 전체 조회");
			System.out.println("2. 회원 아이디 조회");
			System.out.println("3. 회원 이름으로 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 (0 ~ 6) -> ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				selectAll();
				break;
			case 2:
				selectOneId();
				break;
			case 3:
				selectName();
				break;
			case 4:
				insertMember();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.print("\n정말 끝내시겠습니까? (y/n) -> ");
				char yn;
				yn = sc.next().toLowerCase().charAt(0);
				if (yn == 'y') {
					System.out.println("\n※ 종료합니다");
					System.exit(0);
				} else if (yn == 'n') {
					break;
				} else
					System.out.println("\n※   y 또는 n만 입력해주세요");
			default:
				System.out.println("\n번호을 잘못 선택 하셨습니다");
				break;
			}
		}
	}

	public void selectAll() {
		ArrayList<Member> aList = new ArrayList<Member>();
		aList = mc.selectAll();
		if (aList == null) {
			System.out.println("\n※ 조회 할 회원이 없습니다");
		} else {
			Iterator<Member> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		}
	}

	public void selectOneId() {
		Member m = null;
		System.out.print("검색할 회원 아이디 입력 -> ");
		String memberId = sc.next();
		m = mc.selectOneId(memberId);
		if (m != null) {
			System.out.println("\n────────────<회원 조회>────────────");
			System.out.println("아이디 -> " + m.getMemberId());
			System.out.println("비밀번호 -> " + m.getMemberPwd());
			System.out.println("이름 -> " + m.getMemberName());
			System.out.println("나이 -> " + m.getAge());
			if(m.getGender().equals("M")) {
			System.out.println("성별 -> 남자");
			} else 	System.out.println("성별 -> 여자");
			System.out.println("이메일 -> " + m.getEmail());
			System.out.println("주소 -> " + m.getAdress());
			System.out.println("취미 -> " + m.getHobby());
			System.out.println("가입일 -> " + m.getEnrollDate());
			System.out.println("─────────────────────────────────");
			System.out.println("※ 조회 완료");
		} else
			System.out.println("\n※ 검색한 회원이 없습니다");
	}

	public void selectName() {
		ArrayList<Member> aList = new ArrayList<Member>();
		System.out.print("검색 할 이름 입력 -> ");
		String memberName = sc.next();
		aList = mc.selectName(memberName);
		if (aList == null) {
			System.out.println("\n※ 검색 한 이름이 없습니다");
		} else {
			System.out.println("\n'" + memberName + "' 이(가) 포함된 이름 조회");
			System.out.println("───────────────────────");
			Iterator<Member> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().getMemberName());
			}
			System.out.println("───────────────────────");
			System.out.println("※ 조회 완료");
		}
	}

	public void insertMember() {

	}

	public void updateMember() {

	}

	public void deleteMember() {

	}
}
