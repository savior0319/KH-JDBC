package kh.java.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
}
