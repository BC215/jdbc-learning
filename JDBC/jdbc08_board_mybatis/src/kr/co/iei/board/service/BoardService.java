package kr.co.iei.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import kr.co.iei.board.dao.BoardDao;
import kr.co.iei.board.vo.Board;
import kr.co.iei.commons.SqlSessionTemplate;
import kr.co.iei.board.dao.BoardDao;

public class BoardService {

	private BoardDao dao = new BoardDao();

	/** 전체 게시글 조회 */
	public List<Board> selectAllBoard() {
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			return dao.selectAllBoard(session);
		}
	}

	/** 단일 게시글 조회 */
	public Board selectOneBoard(int boardNo) {
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			return dao.selectOneBoard(session, boardNo);
		}
	}

	/** 게시글 등록 */
	public int insertBoard(Board b) {
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			int result = dao.insertBoard(session, b);
			session.commit();
			return result;
		}
	}

	/** 게시글 수정 */
	public int updateBoard(Board b) {
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			int result = dao.updateBoard(session, b);
			session.commit();
			return result;
		}
	}

	/** 게시글 삭제 */
	public int deleteBoard(int boardNo) {
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			int result = dao.deleteBoard(session, boardNo);
			session.commit();
			return result;
		}
	}
	
	public Board selectOneBoardWithReadCount(int boardNo) {
		try (SqlSession session = SqlSessionTemplate.getSqlSession()) {
			int result = dao.increaseReadCount(session, boardNo);
			if (result > 0) {
				session.commit();
				return dao.selectOneBoard(session, boardNo);
			} else {
				session.rollback();
				return null;
			}
		}
	}
}
