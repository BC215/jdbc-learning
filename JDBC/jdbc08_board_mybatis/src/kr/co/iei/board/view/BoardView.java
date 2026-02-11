package kr.co.iei.board.view;

import java.util.List;
import java.util.Scanner;
import kr.co.iei.board.vo.Board;
import kr.co.iei.board.vo.Member;

public class BoardView {

    private Scanner sc = new Scanner(System.in);

    /** 메인 메뉴 */
    public int main() {
        System.out.println("\n===== 게시판 메뉴 =====");
        System.out.println("1. 게시글 목록 보기");
        System.out.println("2. 게시글 상세 보기");
        System.out.println("3. 게시물 등록");
        System.out.println("4. 게시물 수정");
        System.out.println("5. 게시물 삭제");
        System.out.println("6. 내 정보 보기");
        System.out.println("7. 내 정보 변경");
        System.out.println("8. 회원 탈퇴");
        System.out.println("0. 로그아웃");
        System.out.print("메뉴 선택 : ");
        return sc.nextInt();
    }


    public void printAllBoard(List<Board> list) {
        System.out.println("\n---------- 게시글 목록 ----------\n");
        System.out.printf("%-7s %-5s %-5s %-12s %-20s\n", 
                          "글번호", "작성자", "조회수", "작성일", "제목");
        System.out.println("----------------------------------------------------------");

        for (Board b : list) {
            System.out.printf("%-7d %-10s %-5d %-12s %-20s\n", 
                              b.getBoardNo(),
                              b.getBoardWriter(),
                              b.getReadCount(),
                              b.getRegDate(),
                              b.getBoardTitle());
        }
        System.out.println();
    }
    public void printOneBoard(Board b) {
        if (b == null) {
            System.out.println("조회된 게시글이 없습니다.");
            return;
        }

        System.out.println("\n========== 게시글 상세 ==========\n");
        System.out.println("글번호 : " + b.getBoardNo());
        System.out.println("제목   : " + b.getBoardTitle());
        System.out.println("작성자 : " + b.getBoardWriter());
        System.out.println("조회수 : " + b.getReadCount());
        System.out.println("작성일 : " + b.getRegDate());
        System.out.println("내용   : " + b.getBoardContent());
        System.out.println();
    }
    public Board insertBoard() {
        Board b = new Board();
        System.out.print("제목 : ");
        b.setBoardTitle(sc.next());
        System.out.print("내용 : ");
        b.setBoardContent(sc.next());
        System.out.print("작성자 : ");
        b.setBoardWriter(sc.next());
        return b;
    }

    public int selectBoardNo() {
        System.out.print("게시글 번호 입력 : ");
        return sc.nextInt();
    }


    public void printMyInfo(Member m) {
        if(m != null) {
            System.out.println("=== 내 정보 ===");
            System.out.println("ID : " + m.getMemberId());
            System.out.println("이름 : " + m.getMemberName());
            System.out.println("전화번호 : " + m.getMemberPhone());
        } else {
            System.out.println("회원 정보가 없습니다.");
        }
    }

    public Member updateMyInfo() {
        Member m = new Member();
        System.out.print("ID : ");
        m.setMemberId(sc.next());
        System.out.print("PW : ");
        m.setMemberPw(sc.next());
        System.out.print("이름 : ");
        m.setMemberName(sc.next());
        System.out.print("전화번호 : ");
        m.setMemberPhone(sc.next());
        return m;
    }

    public String myInfo() {
        System.out.print("조회할 내 ID 입력 : ");
        return sc.next();
    }

    public String deleteMember() {
        System.out.print("탈퇴할 ID 입력 : ");
        return sc.next();
    }

	public void insertBoardResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("게시물 등록완료.");
		} else {
			System.out.println("게시물 등록실패.");
		}

	}

	public void updateBoardResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("게시글 수정완료.");
		} else {
			System.out.println("게시글 수정실패.");
		}
	}

	public void deleteBoardResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("게시글 삭제완료.");
		} else {
			System.out.println("게시글 삭제실패.");
		}
		
	}

	public void updateMyInfoResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("회원정보 수정 완료.");
		} else {
			System.out.println("회원정보 수정 실패.");
		}
		
	}

	public void deleteMemberResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("회원 탈퇴 완료.");
		} else {
			System.out.println("회원 탈퇴 실패.");
		}
		
	}

	public Board updateBoard() {
	    System.out.println("\n========== 게시글 수정 ==========\n");
	    Board b = new Board();

	    System.out.print("수정할 게시글 번호 : ");
	    b.setBoardNo(sc.nextInt());
	    sc.nextLine();

	    System.out.print("새 제목 : ");
	    b.setBoardTitle(sc.nextLine());

	    System.out.print("새 내용 : ");
	    b.setBoardContent(sc.nextLine());

	    return b;
	}

	public int deleteBoard() {
	    System.out.print("삭제할 게시글 번호 입력 : ");
	    int boardNo = sc.nextInt();
	    return boardNo;
	}






}
