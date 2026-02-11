package kr.co.iei.member.model.service;

import java.util.List;


import org.apache.ibatis.session.SqlSession;

import kr.co.iei.common.SqlSessionTemplate;
import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

public class MemberService {
	MemberDao dao;

	public MemberService() {
		super();
		dao = new MemberDao();
	}

	public List<Member> selectAllMember() {
	    SqlSession session = SqlSessionTemplate.getSqlSession();
	    List<Member> list = dao.selectAllMember(session);
	    session.close();
	    return list;
	}

	public Member selectMemberId(String memberId) {
		// TODO Auto-generated method stub
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Member m = dao.selectMemberId(session, memberId);
		session.close();
		return m;
	}

	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = dao.insertMember(session, m);
		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = dao.updateMember(session, m);
		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int deleteMember(String memberId) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = 0;

		try {

			Member m = dao.selectMemberId(session, memberId);
			if (m == null) {
				session.rollback();
				return 0;
			}

			result = dao.insertDelMember(session, m);
			if (result == 0) {
				session.rollback();
				return 0;
			}

			result = dao.deleteMember(session, memberId);
			if (result == 0) {
				session.rollback();
				return 0;
			}

			session.commit();

		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

}
