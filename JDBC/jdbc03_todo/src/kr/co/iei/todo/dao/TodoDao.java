package kr.co.iei.todo.dao;

import java.sql.*;
import java.util.ArrayList;
import kr.co.iei.todo.vo.Todo;

public class TodoDao {

	public ArrayList<Todo> selectAllTodo() {
		ArrayList<Todo> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		String query = "select * from todo_tbl order by todo_no";

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "todo_test", "1234");

			conn.setAutoCommit(true);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				Todo t = new Todo();
				t.setTodoNo(rset.getInt("todo_no"));
				t.setMemberName(rset.getString("member_name"));
				t.setTodoCheck(rset.getString("todo_check"));
				t.setEnrollDate(rset.getString("enroll_date"));
				t.setTodoContent(rset.getString("todo_content"));
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}

	public int insertTodo(Todo t) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "insert into todo_tbl values (todo_seq.nextval, ?, 'N', sysdate, ?)";

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "todo_test", "1234");
			conn.setAutoCommit(true);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getMemberName());
			pstmt.setString(2, t.getTodoContent());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	public int completeTodo(int no) {
		String query = "update todo_tbl set todo_check='Y' where todo_no=?"; // 완료로 변경

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "todo_test",
				"1234"); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteTodo(int no) {
		String query = "delete from todo_tbl where todo_no=?"; 		// 해당 번호 삭제

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "todo_test",
				"1234"); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}