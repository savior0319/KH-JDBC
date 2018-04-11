package src.kh.java.jdbc.model.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kh.java.jdbc.model.vo.Member;

public class MemberDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public MemberDAO() {
	}

	public ArrayList<Member> selectAll() {

		ArrayList<Member> aList = new ArrayList<Member>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			stmt = conn.createStatement();

			String query = "SELECT * FROM MEMBER";

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Member m = new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setAdress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enroll_date"));
				aList.add(m);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return aList;
	}

	public Member selectOneId(String memberId) {

		Member m = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				m = new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setAdress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enroll_date"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}

	public ArrayList<Member> selectName(String memberName) {

		ArrayList<Member> aList = new ArrayList<Member>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

			String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, "%" + memberName + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member m = new Member();
				m.setMemberName(rs.getString("member_name"));
				aList.add(m);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return aList;
	}

	public int insertMember(Member m) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

			String query = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAdress());
			pstmt.setString(9, m.getHobby());

			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			} else
				conn.rollback();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Member me) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

			String query = "UPDATE MEMBER SET MEMBER_PWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE MEMBER_ID = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, me.getMemberPwd());
			pstmt.setString(2, me.getEmail());
			pstmt.setString(3, me.getPhone());
			pstmt.setString(4, me.getAdress());
			pstmt.setString(5, me.getMemberId());

			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			} else
				conn.rollback();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(String memberId) {

		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

			String query = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();

			if (result > 0) {
				conn.commit();
			} else
				conn.rollback();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
