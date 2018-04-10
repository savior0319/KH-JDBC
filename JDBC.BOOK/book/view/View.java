package book.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.I2F;

import book.controller.BookController;
import book.controller.CustomerController;
import book.controller.LibraryController;
import book.model.VO.BookVO;
import book.model.VO.CustomerVO;
import book.model.VO.LibraryVO;
import kh.java.jdbc.controller.MemberController;

public class View {

	private Scanner sc = new Scanner(System.in);
	private BookController bc = new BookController();
	private CustomerController cc = new CustomerController();
	private LibraryController lc = new LibraryController();

	public View() {
	}

	public void bookMenu() {
		while (true) {
			System.out.println("\n메인 메뉴");
			System.out.println("────────────────────");
			System.out.println("1. 책 관리");
			System.out.println("2. 회원 관리");
			System.out.println("3. 대여 관리");
			System.out.println("4. 종료");
			System.out.println("────────────────────");
			System.out.print("메뉴 선택 -> ");
			switch (sc.nextInt()) {
			case 1:
				bookMananger();
				break;
			case 2:
				memberManager();
				break;
			case 3:
				RepaintManager();
				break;
			case 4:
				System.out.println("※ 프로그램 종료");
				System.exit(0);
			default:
				System.out.println("※ 메뉴선택을 잘못 했습니다");
				break;
			}
		}
	}

	public void bookMananger() {
		while (true) {
			System.out.println("\n책 관리 서브 메뉴");
			System.out.println("────────────────────");
			System.out.println("1. 전체 책 조회");
			System.out.println("2. 책 번호로 조회");
			System.out.println("3. 책 추가하기");
			System.out.println("4. 책 삭제하기");
			System.out.println("5. 메인 메뉴로 돌아가기");
			System.out.println("────────────────────");
			System.out.print("메뉴 선택 -> ");
			switch (sc.nextInt()) {
			case 1:
				bookSearchAll();
				break;
			case 2:
				bookSearchCode();
				break;
			case 3:
				bookAdd();
				break;
			case 4:
				bookDelete();
				break;
			case 5:
				bookMenu();
				break;
			default:
				System.out.println("\n※ 메뉴선택을 잘못 했습니다");
				break;
			}
		}
	}

	private void bookSearchAll() {
		ArrayList<BookVO> aList = bc.bookSearchAll();
		if (aList == null) {
			System.out.println("\n※ 조회 할 책 정보가 없습니다");
		} else {
			System.out.println("\n전체 책 조회");
			System.out.println("────────────────────────────────" + "──────────────────────────────────────");
			Iterator<BookVO> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("───────────────────────────────" + "───────────────────────────────────────");
			System.out.println("※ 조회완료\n");
		}
	}

	private void bookSearchCode() {
		System.out.print("조회 할 책 번호 입력 -> ");
		int bookCode = sc.nextInt();
		BookVO bv = bc.bookSearchCode(bookCode);
		if (bv == null) {
			System.out.println("\n※ 입력한 번호의 책이 없습니다");
		} else {
			System.out.println("\n입력한 '" + bookCode + "' 책 조회");
			System.out.println("────────────────────────────────" + "──────────────────────────────────────");
			System.out.println(bv.toString());
			System.out.println("───────────────────────────────" + "───────────────────────────────────────");
			System.out.println("※ 조회완료\n");
		}
	}

	private void bookAdd() {
		BookVO bv = new BookVO();
		System.out.print("책 번호 입력 -> ");
		bv.setBookNo(sc.nextInt());
		sc.nextLine();
		System.out.print("책 이름 입력 -> ");
		bv.setBookName(sc.nextLine());
		System.out.print("책 작가 입력 -> ");
		bv.setBookWriter(sc.nextLine());
		System.out.print("책 가격 입력 -> ");
		bv.setBookPrice(sc.nextInt());
		sc.nextLine();
		System.out.print("책 출판사 입력 -> ");
		bv.setPublisher(sc.nextLine());
		System.out.print("책 장르입력 -> ");
		bv.setGenre(sc.nextLine());

		int result = bc.bookAdd(bv);

		if (result > 0) {
			System.out.println("\n※ 책 추가 완료");
		} else
			System.out.println("\n※ 책 추가 실패");
	}

	private void bookDelete() {
		System.out.print("삭제 할 책 번호 입력 -> ");
		int bookCode = sc.nextInt();
		BookVO bv = bc.bookSearchCode(bookCode);
		if (bv == null) {
			System.out.println("\n※ 삭제 할 번호의 책이 없습니다");
		} else {
			int result = bc.bookDelete(bookCode);
			if (result > 0) {
				System.out.println("\n※ 책이 삭제 되었습니다");
			}
		}
	}

	public void memberManager() {
		while (true) {
			System.out.println("\n회원 관리 서브 메뉴");
			System.out.println("────────────────────");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 이름으로 조회");
			System.out.println("3. 회원 아이디로 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 메인 메뉴로 이동");
			System.out.println("────────────────────");
			System.out.print("메뉴 선택 -> ");
			switch (sc.nextInt()) {
			case 1:
				customerSearchAll();
				break;
			case 2:
				customerSearchName();
				break;
			case 3:
				customerSearchId();
				break;
			case 4:
				customerSignUp();
				break;
			case 5:
				customerInfoUpdate();
				break;
			case 6:
				customerDelete();
				break;
			case 7:
				bookMenu();
				break;
			default:
				System.out.println("\n※ 메뉴선택을 잘못 했습니다");
				break;
			}
		}
	}

	public void customerSearchAll() {
		ArrayList<CustomerVO> aList = new ArrayList<CustomerVO>();
		aList = cc.customerSearchAll();

		if (aList == null) {
			System.out.println("\n※ 조회 할 회원이 없습니다");
		} else {
			System.out.println("\n전체 회원 조회");
			System.out.println("────────────────────────────────" + "──────────────────────────────────────");
			Iterator<CustomerVO> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("───────────────────────────────" + "───────────────────────────────────────");
			System.out.println("※ 조회완료\n");
		}
	}

	public void customerSearchName() {
		System.out.print("검색 할 회원 이름 -> ");
		String userName = sc.next();
		ArrayList<CustomerVO> aList = cc.customerSearchName(userName);
		if (aList == null) {
			System.out.println("\n 이름에 '" + userName + "' 이(가) 들어간 회원이 없습니다");
		} else {
			System.out.println("\n※이름에 '" + userName + "' 이(가) 들어간 회원 조회");
			System.out.println("────────────────────────────────" + "──────────────────────────────────────");
			Iterator<CustomerVO> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("───────────────────────────────" + "───────────────────────────────────────");
			System.out.println("※ 조회완료\n");
		}
	}

	public void customerSearchId() {
		System.out.print("검색 할 아이디 입력 -> ");
		String userId = sc.next();
		CustomerVO cv = cc.customerSearchId(userId);
		if (cv == null) {
			System.out.println("\n 검색한 '" + userId + "' 아이디를 가진 회원이 없습니다");
		} else {
			System.out.println("\n※ 검색한 '" + userId + "' 의 회원정보");
			System.out.println("───────────────────────────────────");
			System.out.println("회원 번호 -> " + cv.getUserNo());
			System.out.println("회원 아이디 -> " + cv.getUserId());
			System.out.println("회원 이름 -> " + cv.getUserName());
			System.out.println("회원 나이-> " + cv.getUserAge());
			System.out.println("회원 주소-> " + cv.getAddr());
			System.out.println("회원 성별-> " + cv.getGender());
			System.out.println("회원 가입일-> " + cv.getEnrollDate());
			System.out.println("───────────────────────────────────");
		}
	}

	public void customerSignUp() {
		CustomerVO cv = new CustomerVO();
		System.out.print("회원 번호 입력 -> ");
		cv.setUserNo(sc.nextInt());
		System.out.print("회원 아이디 입력 -> ");
		cv.setUserId(sc.next());
		System.out.print("회원 이름 입력 -> ");
		cv.setUserName(sc.next());
		System.out.print("회원 나이 입력 -> ");
		cv.setUserAge(sc.nextInt());
		sc.nextLine();
		System.out.print("회원 주소 입력 -> ");
		cv.setAddr(sc.nextLine());
		System.out.print("회원 성별 입력(남/여) -> ");
		String gender = sc.next();
		if (gender.equals("남")) {
			cv.setGender("M");
		} else if (gender.equals("여")) {
			cv.setGender("F");
		} else
			System.out.println("※ 잘못 입력했습니다");

		int result = cc.customerSignUp(cv);
		if (result > 0) {
			System.out.println("\n※ 가입 완료");
		} else
			System.out.println("\n※ 가입 실패");
	}

	public void customerInfoUpdate() {
		System.out.print("변경 할 아이디 입력 -> ");
		String userId = sc.next();
		CustomerVO cv = cc.customerSearchId(userId);
		if (cv == null) {
			System.out.println("\n 변경 할 '" + userId + "' 아이디를 가진 회원이 없습니다");
		} else {
			CustomerVO cVo = new CustomerVO();
			System.out.print("변경 할 이름 입력 -> ");
			cVo.setUserName(sc.next());
			System.out.print("변경 할 주소 입력 -> ");
			sc.nextLine();
			cVo.setAddr(sc.nextLine());
			cVo.setUserId(userId);

			int result = cc.customerInfoUpdate(cVo);
			if (result > 0) {
				System.out.println("\n※ '" + userId + "' 회원 정보 변경을 완료했습니다");
			} else
				System.out.println("\n※ '" + userId + "' 회원 정보 변경에 실패했습니다");
		}
	}

	public void customerDelete() {
		System.out.print("탈퇴 할 아이디 입력 -> ");
		String userId = sc.next();
		CustomerVO cv = cc.customerSearchId(userId);
		if (cv == null) {
			System.out.println("\n 탈퇴 할 '" + userId + "' 아이디를 가진 회원이 없습니다");
		} else {
			int result = cc.customerDelete(userId);
			if (result > 0) {
				System.out.println("\n※ '" + userId + "' 회원 정보 탈퇴를 완료했습니다");
			} else
				System.out.println("\n※ '" + userId + "' 회원 정보 탈퇴에 실패했습니다");
		}
	}

	public void RepaintManager() {
		while (true) {
			System.out.println("\n대여 관리 서브 메뉴");
			System.out.println("────────────────────");
			System.out.println("1. 대여 관리 전체 조회");
			System.out.println("2. 회원 아이디로 대여 조회");
			System.out.println("3. 책 이름으로 대여 조회");
			System.out.println("4. 대여정보 추가");
			System.out.println("5. 메인 메뉴로 이동");
			System.out.println("────────────────────");
			System.out.print("메뉴 선택 -> ");
			switch (sc.nextInt()) {
			case 1:
				rentManagerAll();
				break;
			case 2:
				rentSearchId();
				break;
			case 3:
				rentSearchBookName();
				break;
			case 4:
				rentInfoAdd();
				break;
			case 5:
				bookMenu();
				break;
			default:
				System.out.println("\n※ 메뉴선택을 잘못 했습니다");
				break;
			}
		}
	}

	public void rentManagerAll() {
		ArrayList<LibraryVO> aList = lc.rentManagerAll();
		if (aList == null) {
			System.out.println("\n※ 대여 정보가 없습니다");
		} else {
			System.out.println("\n※ 대여 정보 조회");
			System.out.println("────────────────────────────────" + "──────────────────────────────────────");
			Iterator<LibraryVO> it = aList.iterator();
			while (it.hasNext()) {
				System.out.println(it.next().toString());
			}
			System.out.println("───────────────────────────────" + "───────────────────────────────────────");
			System.out.println("※ 조회완료\n");
		}
	}

	public void rentSearchId() {
		System.out.print("대여 조회를 할 회원 아이디 -> ");
		String userId = sc.next();
		CustomerVO cv = cc.customerSearchId(userId);
		if (cv == null) {
			System.out.println("\n※ 대여 조회를 할 '" + userId + "' 아이디를 가진 회원이 없습니다");
		} else {
			LibraryVO lvo = lc.rentSearchId(userId);
			if (lvo == null) {
				System.out.println("\n※ '" + userId + "' 회원의 대여 정보가 없습니다");
			} else {
				System.out.println("\n※ '" + userId + "' 회원의 대여 정보");
				System.out.println("────────────────────────────────────────────");
				System.out.println("대여번호 -> " + lvo.getLeaseNo());
				System.out.println("아이디 -> " + lvo.getUserId());
				System.out.println("이름 -> " + lvo.getCvoUserName());
				System.out.println("책 이름 -> " + lvo.getBvoBookName());
				System.out.println("────────────────────────────────────────────");
				System.out.println("※ 조회 완료");
			}
		}
	}

	public void rentSearchBookName() {
		System.out.print("대여 조회를 할 책 이름 -> ");
		String bookName = sc.next();
		LibraryVO lvo = lc.rentSearchBookName(bookName);
		if (lvo == null) {
			System.out.println("\n※ '" + bookName + "' 책의 대여 정보가 없습니다");
		} else {
			System.out.println("\n※ '" + bookName + "' 책의 대여 정보");
			System.out.println("────────────────────────────────────────────");
			System.out.println("대여번호 -> " + lvo.getLeaseNo());
			System.out.println("아이디 -> " + lvo.getUserId());
			System.out.println("이름 -> " + lvo.getCvoUserName());
			System.out.println("────────────────────────────────────────────");
			System.out.println("※ 조회 완료");
		}
	}

	public void rentInfoAdd() {
		System.out.print("대여를 할 회원 아이디 -> ");
		String userId = sc.next();
		CustomerVO cv = cc.customerSearchId(userId);
		if (cv == null) {
			System.out.println("\n※ 대여를 할 '" + userId + "' 아이디를 가진 회원이 없습니다");
		} else {
			LibraryVO lvo = new LibraryVO();
			System.out.print("대여 번호 입력 -> ");
			lvo.setLeaseNo(sc.nextInt());
			sc.nextLine();
			System.out.print("책 이름 입력 -> ");
			String bookName = sc.next();
			BookVO bv = bc.searchBookName(bookName);
			if (bv == null) {
				System.out.println("※ 대여 할 책이 없습니다");
				System.out.println("※ 대여를 실패 했습니다");
			} else {
				lvo.setBvoBookName(bookName);
				lvo.setUserId(userId);
				int result = lc.rentInfoAdd(lvo);
				if (result > 0) {
					System.out.println("\n※ 대여를 완료 했습니다");
				} else {
					System.out.println("※ 대여를 실패 했습니다");
				}
			}
		}
	}
}