package kr.co.iei.member.controller;

import kr.co.iei.member.model.dao.MemberDao;

import kr.co.iei.member.view.MemberView;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class MemberController {
	MemberView view;
	MemberService service;

	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
		view = new MemberView();
		service = new MemberService();

	}

	public void main() {

		while (true) {
			int select = view.main();

			switch (select) {
			case 1:
				// 전체 회원 조회
				selectAllMember();
				break;
			case 2:
				// 아이디로 회원 조회
				selectMemberID();
				break;
			case 3:
				// 회원정보등록
				insertMember();
				break;
			case 4:
				// 회원정보수정
				updateMember();
				break;
			case 5:
				// 회원정보삭제
				deleteMember();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}

	}

	public void selectAllMember() {
		// 1.DB작업 필요
		// 2.쿼리 = SELECT * FROM MEMBER_TBL
		// 3.입력할 정보 없음
		List<Member> list = service.selectAllMember();
		view.printAllMember(list);
	}

	public void selectMemberID() {
		// 1.DB작업 필요
		// 2.쿼리 = SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?
		// 3.입력할 정보 : 회원 아이디
		String memberId = view.selectMemberId();
		Member m = service.selectMemberId(memberId);
		view.printOneMember(m);

	}

	public void insertMember() {
		// 1.DB작업 필요
		// 2.쿼리 = INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,SYSDATE)
		// 3.입력할 정보 : 회원 아이디, 비밀번호, 이름, 전화번호, 나이, 성별
		Member m = view.insertMember();
		int result = service.insertMember(m);
		view.insertResult(result);
	}

	public void updateMember() {
		// 1.DB작업 필요
		// 2.쿼리 = UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_PHONE = ? WHERE MEMBER_ID
		// = ?
		// 3.입력할 정보 : 회원 아이디, 변경할 비밀번호, 변경할 전화번호
		Member m = view.updateMember();
		int result = service.updateMember(m);
		view.updateResult(result);

	}

	public void deleteMember() {
		// 1.DB작업 필요
		// 2.쿼리 = DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
		// 3.입력할 정보 : 회원 아이디
		String memberId = view.deleteMember();
		int result = service.deleteMember(memberId);
		view.deleteResult(result);
		
	}

}
