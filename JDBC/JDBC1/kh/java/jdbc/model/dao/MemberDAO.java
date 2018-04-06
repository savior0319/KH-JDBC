package kh.java.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kh.java.jdbc.model.vo.Member;
import oracle.net.aso.r;

public class MemberDAO {

	// DAO의 역할 DBMS에 연결 및 SQL 전송, 결과 리턴 JDBC
	//
	// 1. 드라이버 등록
	// 2. 커넥션 DMBS에 연결
	// 3. Statement 생성
	// 4. SQL 전송
	// 5. 결과 ResultSet
	// 6. 종료 Close

	/* 기본생성자 */
	public MemberDAO() {
	}

	public void selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 커넥션 (성공 시 Connection 값 리턴, 실패시 null 리턴)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");

			// 3. Statement 생성
			stmt = conn.createStatement();

			// 4. SQL 전송
			String query = "SELECT * FROM MEMBER";

			// 5. 결과 값 리턴 ResultSet
			rset = stmt.executeQuery(query);

			// rset.next() -> next 메소드는 다음 행을 가리키는 메소드. 다음행을 가리켰을 때 있으면 true 없으면 false

			ArrayList<Member> aList = new ArrayList<Member>();

			while (rset.next()) {
				Member m = new Member(); // 값을 담을 Member 객체
				m.setMemberName(rset.getString("member_id"));
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
