package kr.co.iei.board.view;

import java.util.ArrayList;
import java.util.Scanner;
import kr.co.iei.board.vo.Board;

public class BoardView {

    private Scanner sc = new Scanner(System.in);

    public int main() {
        System.out.println("\n==================== 게시판 ====================");
        System.out.println("1. 게시글 전체 조회");
        System.out.println("2. 게시글 상세 조회");
        System.out.println("3. 게시글 등록");
        System.out.println("4. 게시글 삭제");
        System.out.println("0. 종료");
        System.out.print("선택 >> ");
        return sc.nextInt();
    }

    public int inputBoardNo() {
        System.out.print("게시글 번호 입력 : ");
        return sc.nextInt();
    }

    public Board insertBoard() {
        Board b = new Board();
        sc.nextLine(); 

        System.out.print("제목 : ");
        b.setBoardTitle(sc.nextLine());

        System.out.print("작성자 : ");
        b.setBoardWriter(sc.nextLine());

        System.out.print("내용 : ");
        b.setBoardContent(sc.nextLine());

        return b;
    }

    public void printAllBoard(ArrayList<Board> list) {
        System.out.println("\n==================== 게시판 ====================");
        System.out.printf("%-4s | %-15s | %-8s | %-6s | %s\n",
                "번호", "제목", "작성자", "조회수", "작성일");
        System.out.println("------------------------------------------------");

        if (list.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        for (Board b : list) {
            System.out.printf("%-4d | %-15s | %-8s | %-6d | %s\n",
                    b.getBoardNo(),
                    b.getBoardTitle(),
                    b.getBoardWriter(),
                    b.getReadCount(),
                    b.getRegDate());
        }

        System.out.println("------------------------------------------------");
    }

    public void printOneBoard(Board b) {
        if (b == null) {
            System.out.println("게시글이 존재하지 않습니다.");
            return;
        }

        System.out.println("\n================ 게시글 상세 =================");
        System.out.println("번호     : " + b.getBoardNo());
        System.out.println("제목     : " + b.getBoardTitle());
        System.out.println("작성자   : " + b.getBoardWriter());
        System.out.println("조회수   : " + b.getReadCount());
        System.out.println("작성일   : " + b.getRegDate());
        System.out.println("----------------------------------------------");
        System.out.println("내용");
        System.out.println(b.getBoardContent());
        System.out.println("==============================================");
    }

    public void printInsertResult(int result) {
        if (result > 0) {
            System.out.println("게시글 등록이 완료되었습니다.");
        } else {
            System.out.println("게시글 등록에 실패했습니다.");
        }
    }

    public void printDeleteResult(int result) {
        if (result > 0) {
            System.out.println("게시글 삭제가 완료되었습니다.");
        } else {
            System.out.println("게시글 삭제에 실패했습니다.");
        }
    }
}
