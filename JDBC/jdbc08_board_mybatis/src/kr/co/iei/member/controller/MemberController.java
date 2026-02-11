package kr.co.iei.member.controller;

import kr.co.iei.board.vo.Member;

import kr.co.iei.member.service.MemberService;
import kr.co.iei.board.view.BoardView;

public class MemberController {

    private MemberService service = new MemberService();
    private BoardView view = new BoardView(); // 메뉴 + 회원 View 출력 재사용

    /* ================= 회원 영역 ================= */
    public void myInfo() {
        String memberId = view.myInfo();
        Member m = service.myInfo(memberId);
        view.printMyInfo(m);
    }

    public void updateMyInfo() {
        Member m = view.updateMyInfo();
        int result = service.updateMyInfo(m);
        view.updateMyInfoResult(result);
    }

    public void deleteMember() {
        String memberId = view.deleteMember();
        int result = service.deleteMember(memberId);
        view.deleteMemberResult(result);
    }
}
