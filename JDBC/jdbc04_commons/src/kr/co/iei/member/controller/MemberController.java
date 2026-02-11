package kr.co.iei.member.controller;

import java.util.ArrayList;

import kr.co.iei.member.dao.MemberDao;
import kr.co.iei.member.view.MemberView;
import kr.co.iei.member.vo.Member;

public class MemberController {
	MemberView view = new MemberView();
	MemberDao dao = new MemberDao();

	public MemberController() {
		super();
		view = new MemberView();
		dao = new MemberDao();
	}

	public void main() {
		while (true) {
			int select = view.main();

			switch (select) {
			case 1:
				// 전체 회원 조회
				printAllMember();
				break;
			case 2:
				// 아이디로 회원 조회
				selectMemberId();
				break;
			case 3:
				// 이름으로 회원 조회
				searchMemberName();
				break;
			case 4:
				// 회원 정보 등록
				insertMember();
				break;
			case 5:
				// 회원 정보 수정
				updateMember();
				break;
			case 6:
				// 회원 정보 삭제
				deleteMember();
				break;
			case 7:
				deletedMember(); // 삭제된 회원 조회
				break;
			case 0:
				// 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("0~6 사이 숫자를 입력해주세요.");
				break;
			}
		}
	}// main

	public void printAllMember() {
		// 1. DB작업 필요
		// 2. 쿼리 - select * from member_tbl
		// 3. dao에서 전체 회원 조회
		// 4. 결과 출력 -> view
		ArrayList<Member> list = dao.printAllMember();
		view.printAllMember(list);
	}
	
	public void searchMemberName() {
		// 1. DB작업 필요
		// 2. 쿼리 - select * from member_tbl where member_name = ?
		// 3. 입력받을 정보 : 이름 -> view
		String searchName = view.searchMemberName();
		ArrayList<Member> list = dao.searchMemberName(searchName);
		// 4. 조회결과 출력 -> view
		view.printAllMember(list);
	}// searchMemberName

	public void selectMemberId() {
		// 1. DB작업 필요
		// 2. 쿼리 - select * from member_tbl where member_id = ?
		// 3. 입력받을 정보 : 아이디 -> view
		String searchId = view.selectMemberId();
		Member m = dao.selectMemberId(searchId);
		// 4. 조회결과 출력 -> view
		view.selcetMemberIdResult(m);
	}// selectMemberId

	public void insertMember() {
		// 1. DB작업 필요
		// 2. 쿼리 - insert into member_tbl values(?, ?, ?, ?, ?, ?, sysdate)
		// 3. 입력받을 정보 : 아이디, 비밀번호, 이름, 전화번호, 나이, 성별 -> view
		// 4. 입력받은 정보 dao로 전달해서 insert 수행 -> dao
		// 5. 결과에 따른 출력 -> view
		Member m = view.insertMember();
		int result = dao.insertMember(m);
		view.insertMemberResult(result);
	}// insertMember

	public void deleteMember() {
		// 1. DB작업 필요
		// 2. 쿼리 - delete from member_tbl where member_id = ?
		// 3. 입력받을 정보 : 아이디 -> view
		// 4. dao로 전달해서 delete 수행 -> dao
		// 5. 결과에 따른 출력 -> view
		Member m = view.deleteMember();
		int result = dao.deleteMember(m);
		view.deleteMemberResult(result);
	}// deleteMember
	
	public void updateMember() {
		String oldMemberId = view.updateMemberId(); 
		Member m = view.updateMember();             
		int result = dao.updateMember(oldMemberId, m);
		view.updateMemberResult(result);
	}
	
	// 삭제된 회원 조회
	public void deletedMember() {
		ArrayList<Member> list = dao.deletedMember();
		view.deletedMember(list);
	}// deletedMember
	
}

