package kr.co.iei.main.controller;

import kr.co.iei.board.controller.BoardController;
import kr.co.iei.member.controller.MemberController;
import kr.co.iei.board.view.BoardView;

public class MainController {

    private BoardView view = new BoardView(); // 메뉴 출력
    private BoardController boardController = new BoardController();
    private MemberController memberController = new MemberController();

    public void main() {
        while(true) {
            int menu = view.main(); // 메뉴 선택

            switch(menu) {
                case 1: boardController.selectAllBoard(); break;
                case 2: boardController.selectOneBoard(); break;
                case 3: boardController.insertBoard(); break;
                case 4: boardController.updateBoard(); break;
                case 5: boardController.deleteBoard(); break;
                case 6: memberController.myInfo(); break;
                case 7: memberController.updateMyInfo(); break;
                case 8: memberController.deleteMember(); break;
                case 0: 
                    System.out.println("로그아웃합니다.");
                    return;
                default: 
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }
}
