package kr.co.iei.board.view;

import java.util.ArrayList;
import java.util.Scanner;
import kr.co.iei.board.vo.Board;

public class BoardView {
    private Scanner sc = new Scanner(System.in);

    public int main() {
        System.out.println("\n===== 게시판 =====");
        System.out.println("1. 전체 게시글 조회");
        System.out.println("2. 게시글 상세 조회");
        System.out.println("3. 게시글 등록");
        System.out.println("4. 게시글 수정");
        System.out.println("5. 게시글 삭제");
        System.out.println("0. 종료");
        System.out.print("선택 >> ");
        return sc.nextInt();
    }

    public int inputBoardNo() {
        System.out.print("게시글 번호 입력 : ");
        return sc.nextInt();
    }

    public Board insertBoard() {
        sc.nextLine(); 
        Board b = new Board();

        System.out.print("제목 : ");
        b.setBoardTitle(sc.nextLine());

        System.out.print("내용 : ");
        b.setBoardContent(sc.nextLine());
        
        System.out.print("작성자 : ");
        b.setBoardWriter(sc.nextLine());

        return b;
    }

    public Board updateInputBoard() {
        sc.nextLine(); 
        Board b = new Board();

        System.out.print("수정할 제목 : ");
        b.setBoardTitle(sc.nextLine());

        System.out.print("수정할 내용 : ");
        b.setBoardContent(sc.nextLine());

        return b;
    }

    public void printAllBoard(ArrayList<Board> list) {
        System.out.println("\n번호\t제목\t작성자\t조회수\t작성일");
        for (Board b : list) {
            System.out.println(
                b.getBoardNo() + "\t" +
                b.getBoardTitle() + "\t" +
                b.getBoardWriter() + "\t" +
                b.getReadCount() + "\t" +
                b.getRegDate()
            );
        }
    }

    public void printOneBoard(Board b) {
        System.out.println("\n----- 게시글 상세내용 조회 -----");
        System.out.println("번호 : " + b.getBoardNo());
        System.out.println("제목 : " + b.getBoardTitle());
        System.out.println("작성자 : " + b.getBoardWriter());
        System.out.println("조회수 : " + b.getReadCount());
        System.out.println("작성일 : " + b.getRegDate());
        System.out.println("내용 : " + b.getBoardContent());
    }

    public void printMsg(String msg) {
        System.out.println(msg);
    }
}