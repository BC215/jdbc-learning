package kr.co.iei.model.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import kr.co.iei.commons.JDBCTemplate;
import java.sql.Statement;
import java.util.List;
import kr.co.iei.model.vo.Member;

//실제 DB와 접속해서 SQL문을 수행하고 결과를 받아오는 역할
//메소드 1개가 1개의 쿼리를 수행하도록 작성(재사용성을 높이기 위함)
public class MemberDao {

	public ArrayList<Member> selectAllMember(Connection conn) {
		// 반환할 객체 선언

		// Connetion conn = JDBCTemplate.getConnection();
		// -> 커넥션 관리는 Service가 담당하므로 Dao에서는 conn을 매개변수로 받아옴
		ArrayList<Member> list = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM MEMBER_TBL";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Member m = new Member();
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setMemberAge(rset.getInt("MEMBER_AGE"));
				m.setMemberGender(rset.getString("MEMBER_GENDER"));
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberPhone(rset.getString("MEMBER_PHONE"));
				m.setMemberPw(rset.getString("MEMBER_PW"));
				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}// selectAllMember

	public Member selectMemberId(Connection conn, String searchId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member m = null;
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				m = new Member();
				m.setEnrollDate(rset.getDate("ENROLL_DATE"));
				m.setMemberAge(rset.getInt("MEMBER_AGE"));
				m.setMemberGender(rset.getString("MEMBER_GENDER"));
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberPhone(rset.getString("MEMBER_PHONE"));
				m.setMemberPw(rset.getString("MEMBER_PW"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return m;
	}// selectMemberId

	public int insertMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, SYSDATE)";
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setString(i++, m.getMemberId());
			pstmt.setString(i++, m.getMemberPw());
			pstmt.setString(i++, m.getMemberName());
			pstmt.setString(i++, m.getMemberPhone());
			pstmt.setInt(i++, m.getMemberAge());
			pstmt.setString(i++, m.getMemberGender());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int updateMember(Connection conn, Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_PHONE = ? WHERE MEMBER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setString(i++, member.getMemberPw());
			pstmt.setString(i++, member.getMemberPhone());
			pstmt.setString(i++, member.getMemberId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return 0;
	}

	public int insertDelMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO DEL_MEMBER_TBL VALUES(?, ?, SYSDATE)";

		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			pstmt.setString(i++, m.getMemberId());
			pstmt.setString(i++, m.getMemberName());
			result = pstmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}// class
