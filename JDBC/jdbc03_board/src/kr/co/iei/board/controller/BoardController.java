package kr.co.iei.board.controller;

import java.util.ArrayList;
import kr.co.iei.board.dao.BoardDao;
import kr.co.iei.board.view.BoardView;
import kr.co.iei.board.vo.Board;

public class BoardController {
    private BoardView view = new BoardView();
    private BoardDao dao = new BoardDao();

    public void main() {
        while (true) {
            int menu = view.main();

            switch (menu) {
                case 1:
                    selectAllBoard();
                    break;
                case 2:
                    selectOneBoard();
                    break;
                case 3:
                    insertBoard();
                    break;
                case 4:
                    updateBoard();
                    break;
                case 5:
                    deleteBoard();
                    break;
                case 0:
                    view.printMsg("프로그램 종료");
                    return;
                default:
                    view.printMsg("메뉴를 다시 선택하세요.");
            }
        }
    }

    public void selectAllBoard() {
        ArrayList<Board> list = dao.selectAllBoard();
        view.printAllBoard(list);
    }//selectAllBoard

    public void selectOneBoard() {
        int boardNo = view.inputBoardNo();
        Board b = dao.selectOneBoard(boardNo);

        if (b != null) {
            view.printOneBoard(b);
        } else {
            view.printMsg("게시글을 찾을수 없습니다.");
        }
    }//selectOneBoard

    public void insertBoard() {
        Board b = view.insertBoard();
        int result = dao.insertBoard(b);

        if (result > 0) {
            view.printMsg("게시글 등록 완료");
        } else {
            view.printMsg("게시글 등록 실패");
        }
    }//insertBoard

    public void updateBoard() {
        int boardNo = view.inputBoardNo();
        Board b = view.updateInputBoard();
        b.setBoardNo(boardNo);

        int result = dao.updateBoard(b);

        if (result > 0) {
            view.printMsg("게시글 수정 완료");
        } else {
            view.printMsg("게시글 수정 실패");
        }
    }//updateBoard

    public void deleteBoard() {
        int boardNo = view.inputBoardNo();
        int result = dao.deleteBoard(boardNo);

        if (result > 0) {
            view.printMsg("게시글 삭제 완료");
        } else {
            view.printMsg("게시글 삭제 실패");
        }
    }//deleteBoard
}