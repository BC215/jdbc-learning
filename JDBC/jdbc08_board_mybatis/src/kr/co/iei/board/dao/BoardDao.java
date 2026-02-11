package kr.co.iei.board.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import kr.co.iei.board.vo.Board;

public class BoardDao {
    public List<Board> selectAllBoard(SqlSession session) {
        return session.selectList("kr.co.iei.board.dao.BoardDao.selectAllBoard");
    }
    public Board selectOneBoard(SqlSession session, int boardNo) {
        return session.selectOne("kr.co.iei.board.dao.BoardDao.selectOneBoard", boardNo);
    }
    public int insertBoard(SqlSession session, Board b) {
        return session.insert("kr.co.iei.board.dao.BoardDao.insertBoard", b);
    }
    public int updateBoard(SqlSession session, Board b) {
        return session.update("kr.co.iei.board.dao.BoardDao.updateBoard", b);
    }
    public int deleteBoard(SqlSession session, int boardNo) {
        return session.delete("kr.co.iei.board.dao.BoardDao.deleteBoard", boardNo);
    }
    public int increaseReadCount(SqlSession session, int boardNo) {
		return session.update("kr.co.iei.board.dao.BoardDao.increaseReadCount", boardNo);
    }
}
