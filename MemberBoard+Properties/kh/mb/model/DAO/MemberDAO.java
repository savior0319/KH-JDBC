package kh.mb.model.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import kh.mb.common.JDBCTemplate;
import kh.mb.model.VO.MemberVo;

public class MemberDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public MemberDAO() {
	}

	public ArrayList<MemberVo> memberSearchAll(Connection conn) {

		ArrayList<MemberVo> aList = new ArrayList<MemberVo>();

		try {
			Properties pp = new Properties();
			pp.load(new FileReader("C:\\workspace\\KH-JDBC\\MemberBoard+Properties\\resource\\query.properties"));
			String selectAll = pp.getProperty("selectAll");
			System.out.println(selectAll);
			// pstmt = conn.prepareStatement(selectAll);
			// pstmt.setString(1, "MEMBER");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectAll);

			while (rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setMemberNo(rs.getInt("MEMBER_NO"));
				mv.setMemberId(rs.getString("MEMBER_ID"));
				mv.setMemberPwd(rs.getString("MEMBER_PWD"));
				mv.setMemberName(rs.getString("MEMBER_NAME"));
				mv.setEmail(rs.getString("EMAIL"));
				mv.setAddress(rs.getString("ADDRESS"));
				mv.setPhone(rs.getString("PHONE"));
				mv.setEnrollDate(rs.getDate("ENROLL_DATE"));

				aList.add(mv);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return aList;
	}

	public MemberVo memberSearchId(Connection conn, String memberId) {

		MemberVo mv = null;
		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + memberId + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				mv = new MemberVo();
				mv.setMemberNo(rs.getInt("MEMBER_NO"));
				mv.setMemberId(rs.getString("MEMBER_ID"));
				mv.setMemberPwd(rs.getString("MEMBER_PWD"));
				mv.setMemberName(rs.getString("MEMBER_NAME"));
				mv.setEmail(rs.getString("EMAIL"));
				mv.setAddress(rs.getString("ADDRESS"));
				mv.setPhone(rs.getString("PHONE"));
				mv.setEnrollDate(rs.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return mv;
	}

	public ArrayList<MemberVo> memberSearchName(Connection conn, String memberName) {

		ArrayList<MemberVo> aList = new ArrayList<MemberVo>();
		String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME = '" + memberName + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				MemberVo mv = new MemberVo();
				mv.setMemberNo(rs.getInt("MEMBER_NO"));
				mv.setMemberId(rs.getString("MEMBER_ID"));
				mv.setMemberPwd(rs.getString("MEMBER_PWD"));
				mv.setMemberName(rs.getString("MEMBER_NAME"));
				mv.setEmail(rs.getString("EMAIL"));
				mv.setAddress(rs.getString("ADDRESS"));
				mv.setPhone(rs.getString("PHONE"));
				mv.setEnrollDate(rs.getDate("ENROLL_DATE"));

				aList.add(mv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return aList;
	}

	public int memberSearchName(Connection conn, MemberVo mv) {

		int result = 0;
		String query = "INSERT INTO MEMBER VALUES(MEMBER_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mv.getMemberId());
			pstmt.setString(2, mv.getMemberPwd());
			pstmt.setString(3, mv.getMemberName());
			pstmt.setString(4, mv.getEmail());
			pstmt.setString(5, mv.getAddress());
			pstmt.setString(6, mv.getPhone());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int memberInfoModify(Connection conn, MemberVo mvm) {

		int result = 0;
		String query = "UPDATE MEMBER SET ADDRESS = ?, PHONE = ?, EMAIL =? WHERE MEMBER_ID = '" + mvm.getMemberId()
				+ "'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mvm.getAddress());
			pstmt.setString(2, mvm.getPhone());
			pstmt.setString(3, mvm.getEmail());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int memberDelete(Connection conn, String memberId) {

		int result = 0;
		String query = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
