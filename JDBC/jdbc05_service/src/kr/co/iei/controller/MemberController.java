package kr.co.iei.controller;

import java.util.ArrayList;
import kr.co.iei.model.service.MemberService;
import kr.co.iei.view.MemberView;
import kr.co.iei.model.vo.Member;

//전체운영 : 사용자의 요구사항을 받아서 요청을 처리
// -> View, Service 연결 역할
// -> 사용자가 봐야하는 화면을 만들어야 하면 view
// -> DB에서 데이터를 가져와야 하면 service
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
			case 1: // 전체 회원 조회
				selectAllMember();
				break;
			case 2: // 아이디로 회원 조회
				selectMemberId();
				break;
			case 3: // 회원 정보 등록
				insertMember();
				break;
			case 4: // 회원 정보 수정
				updateMember();
				break;
			case 5: // 회원 정보 삭제
				deleteMember();
				break;
			case 0: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				break;
			} // switch
		} // while
	} // main

	public void selectAllMember() {
		// 1. Service에게 전체 회원 조회 요청
		// 2. Service는 DAO에게 전체 회원 조회 쿼리 요청
		// 3. DAO는 DB에서 결과를 받아 Service에게 반환
		// 4. Service는 결과를 Controller에게 반환
		// 5. Controller는 View에게 결과 출력 요청
		// 6. View는 결과를 출력
		// 7. DB작업 필요
		// 8. query -> select * from member_tbl;
		// 9. 입력받아야 하는 정보 없음
		ArrayList<Member> list = service.selectAllMember();
		view.selectAllMember(list);

	} // selectAllMember

	public void selectMemberId() {
		// DB작업 필요
		// 쿼리 = select * from member_tbl where member_id = ?;
		// 입력받아야 하는 정보 = member_id
		String searchId = view.selectMemberId();
		Member m = service.selectMemberId(searchId);
		view.printMember(m);

	}// selectMemberId

	public void insertMember() {
		// DB작업 필요
		// 쿼리 = insert into member_tbl values(?,?,?,?,?,?,sysdate);
		// 입력받아야 하는 정보 = member_id, member_pw, member_name, member
		Member m = view.insertMember();
		int result = service.insertMember(m);
		view.insertResult(result);

	} // insertMember

	public void updateMember() {
		// DB작업 필요
		// 쿼리 = select * from member_tbl where member_id = ?;
		// -> update member_tbl set member_pw = ?, member_name = ?, member_age = ?,
		String memberID = view.updateMember1();
		Member m = service.selectMemberId(memberID);
		if (m == null) {// 회원이 존재하지 않을 때
			view.searchFail();// 회원 조회 실패
		} else {
			Member member = view.updateMember2();// 수정할 회원 정보 입력
			member.setMemberId(memberID);// memberId는 변경 불가하므로 기존 아이디로 세팅
			int result = service.updateMember(member);// 회원 정보 수정
			view.printupdateResult(result);// 수정 결과 출력

		}
	} // updateMember

	public void deleteMember() {
		// 1.DB작업 필요
		// 2. 쿼리 ->
		// 2-1 delete from member_tbl where member_id = ?;
		// 2-2insert into member_tbl values(아이디,이름,sysdate);
		// 2-3select * from member_tbl where member_id = ?;
		// 3. 입력받아야 하는 정보 = member_id
		String memberId = view.deleteMember();

		int result = service.deleteMember(memberId);
		if (result == -1) {
			view.searchFail();
		} else if (result == 0) {
			view.deleteFail();
		} else {
			view.deleteSuccess();
		}

	} // deleteMember
}// MemberController
