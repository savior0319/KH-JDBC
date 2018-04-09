package kh.java.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdk.nashorn.internal.objects.annotations.Where;
import kh.java.jdbc.model.vo.Member;

public class MemberDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rset = null;

	public MemberDAO() {
	}

	public ArrayList<Member> selectAll() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		ArrayList<Member> aList = new ArrayList<Member>();

		try {
			String query = "SELECT * FROM MEMBER";

			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAdress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
				aList.add(m);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return aList;
	}

	public Member selectOneId(String memberId) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		Member m = null;

		try {
			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + memberId + "'";
			rset = stmt.executeQuery(query);
			while (rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPwd(rset.getString("member_pwd"));
				m.setMemberName(rset.getString("member_name"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAdress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enroll_date"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return m;
	}

	public ArrayList<Member> selectName(String memberName) {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		ArrayList<Member> aList = new ArrayList<Member>();

		try {
			String query = "SELECT MEMBER_NAME FROM MEMBER WHERE MEMBER_NAME LIKE '%" + memberName + "'";

			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Member m = new Member();
				m.setMemberName(rset.getString("member_name"));
				aList.add(m);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return aList;
	}

	public int insertMember(Member m) {

		int result = 0;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();
			/* INSERT 는 ResultSet이 필요없음 */

			String query = "INSERT INTO MEMBER VALUES('" + m.getMemberId() + "','" + m.getMemberPwd() + "','"
					+ m.getMemberName() + "','" + m.getGender() + "'," + m.getAge() + ",'" + m.getEmail() + "','"
					+ m.getPhone() + "','" + m.getAdress() + "','" + m.getHobby() + "', SYSDATE)";

			result = stmt.executeUpdate(query);
			// INSERT DELETE UPDATE 문은 executeUpdate를 사용함
			// 결과 값 INT 타입(실행 개수) 리턴
			// 해당 쿼리문을 작동 실행 시킬 뿐 적용 x (commit & rollback 해줘야 함)

			if (result > 0) {
				conn.commit(); // 정상처리
			} else {
				conn.rollback(); // 정상처리 x
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public int updateMember(Member me) {
		int result = 0;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();

			String query = "UPDATE MEMBER SET MEMBER_PWD ='" + me.getMemberPwd() + "', EMAIL = '" + me.getEmail()
					+ "', PHONE = '" + me.getPhone() + "', ADDRESS = '" + me.getAdress() + "'"
					+ "WHERE MEMBER_ID LIKE '" + me.getMemberId() + "'";

			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();

			String query = "DELETE FROM MEMBER WHERE MEMBER_ID LIKE '" + memberId + "'";

			result = stmt.executeUpdate(query);

			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
