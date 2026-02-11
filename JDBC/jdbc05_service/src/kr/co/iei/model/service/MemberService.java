package kr.co.iei.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import kr.co.iei.commons.JDBCTemplate;
import kr.co.iei.model.dao.MemberDao;
import kr.co.iei.model.vo.Member;

// 데이터베이스 접속 관리(Connection 생성, Coonnetion close, commit, rollback)
// 실제 SQL 수행 (SELECT, INSERT, UPDATE, DELETE)
// 데이터 편집(편집이 필요한경우)
// -> Controller와 DAO의 중간다리 역할
public class MemberService {
	MemberDao dao;

	public MemberService() {
		super();
		// TODO Auto-generated constructor stub
		dao = new MemberDao();
	}

	public ArrayList<Member> selectAllMember() {
		// 1. Connection 생성
		Connection conn = JDBCTemplate.getConnection();
		// service에서 생성한 Connection을 dao로 전달하면서 쿼리요청
		ArrayList<Member> list = dao.selectAllMember(conn);
		// DB작업이 끝났는지 체크
		// DB작업이 남은경우 : conn을 재사용해서 DB작업 수행
		// 최종 DB작업이 끝난경우 : commit or rollback이 필요한지 체크
		JDBCTemplate.close(conn);
		return list;
	}

	public Member selectMemberId(String searchId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectMemberId(conn, searchId);
		JDBCTemplate.close(conn);
		return m;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateMember(conn, member);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);

		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member m = dao.selectMemberId(conn, memberId);
		int result = -1;
		if (m == null) {
			result = -1;
		} else {
			// 조회 성공이면 추가 DB작업 있는지 -> insert 수행
			result = dao.insertDelMember(conn, m);
			if (result > 0) {
				// 삭제 회원 정보 등록이 성공한경우 추가 DB작업 있는지 -> delete 수행
				result = dao.deleteMember(conn, memberId);
				if (result > 0) {
					// 모두 성공한경우
					JDBCTemplate.commit(conn);
				} else {
					// 실패한경우
					JDBCTemplate.rollback(conn);
				}
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
