package kr.co.iei.board.controller;

import java.util.List;

import kr.co.iei.board.service.BoardService;
import kr.co.iei.board.view.BoardView;
import kr.co.iei.board.vo.Board;

public class BoardController {

    private BoardView view = new BoardView();
    private BoardService service = new BoardService();


    public void selectAllBoard() {
        List<Board> list = service.selectAllBoard();
        view.printAllBoard(list);
    }

    public void selectOneBoard() {
        int boardNo = view.selectBoardNo();

        Board b = service.selectOneBoardWithReadCount(boardNo);
        view.printOneBoard(b);
    }

    public void insertBoard() {
        Board b = view.insertBoard();
        int result = service.insertBoard(b);
        view.insertBoardResult(result);
    }


    public void updateBoard() {
        Board b = view.updateBoard();
        int result = service.updateBoard(b);
        view.updateBoardResult(result);
    }


    public void deleteBoard() {
        int boardNo = view.deleteBoard();
        int result = service.deleteBoard(boardNo);
        view.deleteBoardResult(result);
    }
}
