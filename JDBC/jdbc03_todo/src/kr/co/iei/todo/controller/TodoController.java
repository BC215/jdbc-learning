package kr.co.iei.todo.controller;

import java.util.ArrayList;
import kr.co.iei.todo.dao.TodoDao;
import kr.co.iei.todo.view.TodoView;
import kr.co.iei.todo.vo.Todo;

public class TodoController {
	private TodoView view = new TodoView();
	private TodoDao dao = new TodoDao();

	public void main() {
		while (true) {
			int menu = view.mainMenu();
			switch (menu) {
			case 1:
				selectAllTodo(); // 전체 할일 목록 보기
				break;
			case 2:
				insertTodo(); // 할일 등록
				break;
			case 3:
				completeTodo();// 할일 완료
				break;
			case 4:
				deleteTodo();// 할일 삭제
				break;
			case 0:
				view.printMsg("프로그램을 종료합니다.");
				return; // 종료
			default:
				view.printMsg("0~4 사이 숫자를 입력해주세요."); // 잘못된 입력시 경고문
				break;
			}
		}
	}

	public void selectAllTodo() {
		ArrayList<Todo> list = dao.selectAllTodo();
		view.printAllTodo(list);
	}

	public void insertTodo() {
		Todo t = view.inputTodo();
		int result = dao.insertTodo(t);

		if (result > 0) {
			view.printMsg("작업 완료");

		} else {
			view.printMsg("작업 실패 _오류코드 확인바랍니다");
		}
	}

	public void completeTodo() {
		int no = view.inputTodoNo();
		int result = dao.completeTodo(no);

		if (result > 0) {
			view.printMsg("작업 완료");

		} else {
			view.printMsg("번호 확인 바람.");
		}

	}

	public void deleteTodo() {
		int no = view.inputTodoNo();
		int result = dao.deleteTodo(no);

		if (result > 0) {
			view.printMsg("작업 완료");

		} else {
			view.printMsg("작업 실패 번호확인바랍니다.");

		}

	}
}