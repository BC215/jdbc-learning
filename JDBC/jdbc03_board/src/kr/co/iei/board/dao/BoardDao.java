package kr.co.iei.board.dao;

import java.sql.*;
import java.util.ArrayList;
import kr.co.iei.board.vo.Board;

public class BoardDao {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 전체 게시글 조회
    public ArrayList<Board> selectAllBoard() {
        ArrayList<Board> list = new ArrayList<>();
        String query = "select * from board_tbl order by board_no desc";

        try {
            // 1. 드라이버 등록
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 커넥션 생성
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "jdbc_board",
                    "1234"
            );
            conn.setAutoCommit(true); // 자동 커밋

            // 3. 쿼리 실행
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            // 4. 결과 처리
            while (rs.next()) {
                Board b = new Board();
                b.setBoardNo(rs.getInt("board_no"));
                b.setBoardTitle(rs.getString("board_title"));
                b.setBoardWriter(rs.getString("board_writer"));
                b.setReadCount(rs.getInt("read_count"));
                b.setRegDate(rs.getString("reg_date"));
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    // 게시글 상세 조회 (조회수 증가 포함)
    public Board selectOneBoard(int no) {
        Board b = null;

        String updateQuery =
            "update board_tbl set read_count = read_count + 1 where board_no = ?";
        String selectQuery =
            "select * from board_tbl where board_no = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "jdbc_board",
                    "1234"
            );
            conn.setAutoCommit(false); 

            // 조회수 증가
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, no);
            int result = pstmt.executeUpdate();

            if (result == 0) {
                conn.rollback();
                return null;
            }

            // 상세 조회
            pstmt = conn.prepareStatement(selectQuery);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                b = new Board();
                b.setBoardNo(rs.getInt("board_no"));
                b.setBoardTitle(rs.getString("board_title"));
                b.setBoardContent(rs.getString("board_content"));
                b.setBoardWriter(rs.getString("board_writer"));
                b.setReadCount(rs.getInt("read_count"));
                b.setRegDate(rs.getString("reg_date"));
            }

            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            close();
        }
        return b;
    }

    // 게시글 등록
    public int insertBoard(Board b) {
        int result = 0;
        String query =
            "insert into board_tbl values (board_seq.nextval, ?, ?, ?, 0, sysdate)";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "jdbc_board",
                    "1234"
            );
            conn.setAutoCommit(true);

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, b.getBoardTitle());
            pstmt.setString(2, b.getBoardContent());
            pstmt.setString(3, b.getBoardWriter());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    // 게시글 수정
    public int updateBoard(Board b) {
        int result = 0;
        String query =
            "update board_tbl set board_title = ?, board_content = ? where board_no = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "jdbc_board",
                    "1234"
            );
            conn.setAutoCommit(true);

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, b.getBoardTitle());
            pstmt.setString(2, b.getBoardContent());
            pstmt.setInt(3, b.getBoardNo());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    // 게시글 삭제
    public int deleteBoard(int no) {
        int result = 0;
        String query = "delete from board_tbl where board_no = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                    "jdbc_board",
                    "1234"
            );
            conn.setAutoCommit(true);

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, no);

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

 
    private void close() {
        try {
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}