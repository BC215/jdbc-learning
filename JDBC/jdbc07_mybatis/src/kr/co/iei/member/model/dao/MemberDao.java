package kr.co.iei.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.iei.member.model.vo.Member;

public class MemberDao {

	public List<Member> selectAllMember(SqlSession session) {
		return session.selectList("mybatis.selectAllMember");
	}

	public Member selectMemberId(SqlSession session, String memberId) {
		// TODO Auto-generated method stub

		return session.selectOne("mybatis.selectMemberId", memberId);
	}

	public int insertMember(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("mybatis.insertMember", m);

	}

	public int updateMember(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return session.update("mybatis.updateMember", m);
	}

	public int deleteMember(SqlSession session, String memberId) {
		// TODO Auto-generated method stub
		
		return session.delete("mybatis.deleteMember", memberId);
	}


	public int insertDelMember(SqlSession session, Member m) {
	    return session.insert("mybatis.insertDelMember", m);
	}

}
