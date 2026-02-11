package kr.co.iei.member.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import kr.co.iei.board.vo.Member;

public class MemberDao {
    public List<Member> selectAllMember(SqlSession session) {
        return session.selectList("kr.co.iei.member.dao.MemberDao.selectAllMember");
    }
    public Member selectMemberById(SqlSession session, String memberId) {
        return session.selectOne("kr.co.iei.member.dao.MemberDao.selectMemberById", memberId);
    }
    public int insertMember(SqlSession session, Member m) {
        return session.insert("kr.co.iei.member.dao.MemberDao.insertMember", m);
    }
    public int updateMember(SqlSession session, Member m) {
        return session.update("kr.co.iei.member.dao.MemberDao.updateMember", m);
    }
    public int deleteMember(SqlSession session, String memberId) {
        return session.delete("kr.co.iei.member.dao.MemberDao.deleteMember", memberId);
    }
}
