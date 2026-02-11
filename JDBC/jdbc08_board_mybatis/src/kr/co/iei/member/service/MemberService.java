package kr.co.iei.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import kr.co.iei.member.dao.MemberDao;
import kr.co.iei.board.vo.Member;  
import kr.co.iei.commons.SqlSessionTemplate;

public class MemberService {

    private MemberDao dao = new MemberDao();

    /** 전체 회원 조회 */
    public List<Member> selectAllMember() {
        try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
            return dao.selectAllMember(session);
        }
    }

    /** 아이디로 회원 조회 */
    public Member selectMemberById(String memberId) {
        try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
            return dao.selectMemberById(session, memberId);
        }
    }

    /** 회원 등록 */
    public int insertMember(Member m) {
        try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
            int result = dao.insertMember(session, m);
            session.commit();
            return result;
        }
    }

    /** 회원 정보 수정 */
    public int updateMember(Member m) {
        try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
            int result = dao.updateMember(session, m);
            session.commit();
            return result;
        }
    }

    /** 회원 탈퇴 */
    public int deleteMember(String memberId) {
        try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
            int result = dao.deleteMember(session, memberId);
            session.commit();
            return result;
        }
    }

	public Member myInfo(String memberId) {
		// TODO Auto-generated method stub
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			return dao.selectMemberById(session, memberId);
		}

	}

	public int updateMyInfo(Member m) {
		// TODO Auto-generated method stub
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			int result = dao.updateMember(session, m);
			session.commit();
			return result;
		}

	}
}
