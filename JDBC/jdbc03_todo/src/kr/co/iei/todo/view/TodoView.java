package kr.co.iei.todo.view;

import java.util.ArrayList;
import java.util.Scanner;
import kr.co.iei.todo.vo.Todo;

public class TodoView {
	private Scanner sc = new Scanner(System.in);

	public int mainMenu() {
		System.out.println("\n----- 전체 할일 목록 -----");
		System.out.println("1. 전체 할일 목록 보기");
		System.out.println("2. 할일 등록");
		System.out.println("3. 할일 완료");
		System.out.println("4. 할일 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >> ");

		try {
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public Todo inputTodo() {
		Todo t = new Todo();

		System.out.print("작성자 : ");
		t.setMemberName(sc.nextLine());

		System.out.print("내용 : ");
		t.setTodoContent(sc.nextLine());

		return t;
	}

	public int inputTodoNo() {
		System.out.print("번호 입력 : ");

		try {
			return Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public void printAllTodo(ArrayList<Todo> list) {
		System.out.println("\n번호\t작성자\t완료\t작성일\t\t\t내용");
		for (Todo t : list) {
			System.out.println(t.getTodoNo() + "\t" + t.getMemberName() + "\t" + t.getTodoCheck() + "\t"
					+ t.getEnrollDate() + "\t" + t.getTodoContent());
		}

	}

	public void printMsg(String msg) {
		System.out.println(msg);
	}
}