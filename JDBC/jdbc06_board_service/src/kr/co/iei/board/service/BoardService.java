package kr.co.iei.board.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.board.dao.BoardDao;
import kr.co.iei.board.vo.Board;
import kr.co.iei.commons.JDBCTemplate;

public class BoardService {

    private BoardDao dao = new BoardDao();

    // 전체 조회
    public ArrayList<Board> selectAllBoard() {
        Connection conn = JDBCTemplate.getConnection();
        ArrayList<Board> list = dao.selectAllBoard(conn);
        JDBCTemplate.close(conn);
        return list;
    }

    // 상세 조회 + 조회수 증가
    public Board selectOneBoard(int boardNo) {
        Connection conn = JDBCTemplate.getConnection();

        int result = dao.updateReadCount(conn, boardNo);
        Board b = null;

        if (result > 0) {
            b = dao.selectOneBoard(conn, boardNo);
            JDBCTemplate.commit(conn);
        } else {
            JDBCTemplate.rollback(conn);
        }

        JDBCTemplate.close(conn);
        return b;
    }

    // 등록
    public int insertBoard(Board b) {
        Connection conn = JDBCTemplate.getConnection();
        int result = dao.insertBoard(conn, b);

        if (result > 0) {
            JDBCTemplate.commit(conn);
        } else {
            JDBCTemplate.rollback(conn);
        }

        JDBCTemplate.close(conn);
        return result;
    }

    // 삭제
    public int deleteBoard(int boardNo) {
        Connection conn = JDBCTemplate.getConnection();
        int result = dao.deleteBoard(conn, boardNo);

        if (result > 0) {
            JDBCTemplate.commit(conn);
        } else {
            JDBCTemplate.rollback(conn);
        }

        JDBCTemplate.close(conn);
        return result;
    }
}
