package kr.co.iei.board.controller;

import java.util.ArrayList;

import kr.co.iei.board.service.BoardService;
import kr.co.iei.board.view.BoardView;
import kr.co.iei.board.vo.Board;

public class BoardController {

	private BoardView view = new BoardView();
	private BoardService service = new BoardService();

	public void main() {
		while (true) {
			int menu = view.main();

			switch (menu) {
			case 1:// 전체 조회
				selectAllBoard();
				break;
			case 2:// 상세 조회
				selectOneBoard();
				break;
			case 3:// 등록
				insertBoard();
				break;
			case 4:// 삭제
				deleteBoard();
				break;
			case 0:// 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:// 잘못된 입력시
				System.out.println("메뉴를 다시 선택하세요.");
			}
		}
	}

	public void selectAllBoard() { // 전체 조회
		ArrayList<Board> list = service.selectAllBoard();
		view.printAllBoard(list);
	} // selectAllBoard

	public void selectOneBoard() {// 상세 조회
		int boardNo = view.inputBoardNo();
		Board b = service.selectOneBoard(boardNo);
		view.printOneBoard(b);
	} // selectOneBoard

	public void insertBoard() {// 등록
		Board b = view.insertBoard();
		int result = service.insertBoard(b);
		view.printInsertResult(result);
	} // insertBoard

	public void deleteBoard() {// 삭제
		int boardNo = view.inputBoardNo();
		int result = service.deleteBoard(boardNo);
		view.printDeleteResult(result);
	}// deleteBoard
}
