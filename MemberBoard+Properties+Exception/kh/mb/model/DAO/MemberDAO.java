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

import kh.mb.Exception.MemberBoardException;
import kh.mb.common.JDBCTemplate;
import kh.mb.model.VO.MemberVo;

public class MemberDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties pp = new Properties();
	private final String PATH = "C:\\workspace\\KH-JDBC\\MemberBoard+Properties+Exception\\resource\\query.properties";

	public MemberDAO() {
		try {
			pp.load(new FileReader(PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MemberVo> memberSearchAll(Connection conn) throws MemberBoardException {

		ArrayList<MemberVo> aList = new ArrayList<MemberVo>();

		try {
			String selectAll = pp.getProperty("memberSearchAll");

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
		} catch (SQLException e) {
			System.out.println("DAO : " + e.getMessage());
			throw new MemberBoardException("DAO클래스의 memberSearchAll() 메소드 실행불가 " + e.getMessage());
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		return aList;
	}

	public MemberVo memberSearchId(Connection conn, String memberId) {

		MemberVo mv = null;

		try {
			String query = pp.getProperty("memberSearchId");

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

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
			JDBCTemplate.close(pstmt);
		}
		return mv;
	}

	public ArrayList<MemberVo> memberSearchName(Connection conn, String memberName) {

		ArrayList<MemberVo> aList = new ArrayList<MemberVo>();

		try {
			String query = pp.getProperty("memberSearchName");

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			rs = pstmt.executeQuery();

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
			JDBCTemplate.close(pstmt);
		}
		return aList;
	}

	public int memberSignUp(Connection conn, MemberVo mv) {

		int result = 0;

		try {
			String query = pp.getProperty("memberSignUp");

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

		try {
			String query = pp.getProperty("memberInfoModify");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mvm.getAddress());
			pstmt.setString(2, mvm.getPhone());
			pstmt.setString(3, mvm.getEmail());
			pstmt.setString(4, mvm.getMemberId());

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

		try {
			String query = pp.getProperty("memberDelete");
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
