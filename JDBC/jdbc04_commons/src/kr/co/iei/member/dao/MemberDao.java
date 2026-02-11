package kr.co.iei.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.commons.JDBCTemplate;
import kr.co.iei.member.vo.Member;

public class MemberDao {

	public Member selectMemberId(String searchId) {
		Connection conn = JDBCTemplate.getConnection();// Connection 객체 생성해서 JDBCTemplate 대입
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member m = null;

		String query = "select * from member_tbl where member_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				m = getMember(rset);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}

		return m;
	}

	// 회원조회결과에서 각 컬럼 값을 추출해서 Member객체에 담아서 반환하는 메소드
	// 단. 회원정보의 모든컬럼을 조회하는경우에만 사용
	public Member getMember(ResultSet rset) {
		Member m = new Member();
		try {
			m.setEnrollDate(rset.getDate("enroll_date"));
			m.setMemberAge(rset.getInt("member_age"));
			m.setMemberGender(rset.getString("member_gender"));
			m.setMemberId(rset.getString("member_id"));
			m.setMemberName(rset.getString("member_name"));
			m.setMemberPhone(rset.getString("member_phone"));
			m.setMemberPw(rset.getString("member_pw"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m;
	}

	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "insert into member_tbl values (?, ?, ?, ?, ?, ?, sysdate)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setInt(5, m.getMemberAge());
			pstmt.setString(6, m.getMemberGender());

			result = pstmt.executeUpdate();

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JDBCTemplate.rollback(conn);

		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}

		return result;
	}

	public int deleteMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt1 = null; // 삭제 로그 insert
		PreparedStatement pstmt2 = null; // member_tbl delete
		ResultSet rset = null;
		int result = 0;

		try {

			String selectQuery = "select member_name from member_tbl where member_id = ?";
			pstmt1 = conn.prepareStatement(selectQuery);
			pstmt1.setString(1, m.getMemberId());
			rset = pstmt1.executeQuery();

			if (rset.next()) {
				String memberName = rset.getString("member_name");

		
				String insertQuery = "insert into del_member_tbl values (?, ?, sysdate)";
				pstmt2 = conn.prepareStatement(insertQuery);
				pstmt2.setString(1, m.getMemberId());
				pstmt2.setString(2, memberName);
				pstmt2.executeUpdate();

				String deleteQuery = "delete from member_tbl where member_id = ?";
				pstmt2 = conn.prepareStatement(deleteQuery);
				pstmt2.setString(1, m.getMemberId());
				result = pstmt2.executeUpdate();

				if (result > 0) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}

			} else {
				result = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCTemplate.rollback(conn);

		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt1);
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(conn);
		}

		return result;
	}

	public ArrayList<Member> printAllMember() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();

		String query = "select * from member_tbl order by enroll_date";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member m = getMember(rset);
				list.add(m);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}

		return list;
	}

	public ArrayList<Member> searchMemberName(String searchName) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member_tbl where member_name = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchName);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Member m = getMember(rset);
				list.add(m);
			}

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return null;
	}

	public int updateMember(String oldMemberId, Member m) {
		Connection conn = JDBCTemplate.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update member_tbl "
		             + "set member_pw = ?, member_phone = ? "
		             + "where member_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberPhone());
			pstmt.setString(3, oldMemberId);

			result = pstmt.executeUpdate();

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JDBCTemplate.rollback(conn);
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}

		return result;
	}

	public ArrayList<Member> deletedMember() {
	    Connection conn = JDBCTemplate.getConnection();
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    ArrayList<Member> list = new ArrayList<Member>();
	    String query = "select * from del_member_tbl";

	    try {
	        pstmt = conn.prepareStatement(query);
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            Member m = new Member();
	            m.setMemberId(rset.getString("member_id"));
	            m.setMemberName(rset.getString("member_name"));
	            m.setOutDate(rset.getDate("out_date"));
	            list.add(m);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	        JDBCTemplate.close(conn);
	    }

	    return list;
	}
}
