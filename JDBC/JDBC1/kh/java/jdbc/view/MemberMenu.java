package kh.java.jdbc.view;

import java.util.Scanner;

import kh.java.jdbc.controller.MemberController;

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
				selectOneName();
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
		mc.selectAll();
	}

	public void selectOneId() {

	}

	public void selectOneName() {
	}

	public void insertMember() {

	}

	public void updateMember() {

	}

	public void deleteMember() {

	}
}
