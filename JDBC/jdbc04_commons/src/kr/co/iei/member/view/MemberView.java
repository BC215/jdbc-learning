package kr.co.iei.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.member.vo.Member;

public class MemberView {
	Scanner sc = new Scanner(System.in);

	public MemberView() {
		super();
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
	}

	public int main() {

		System.out.println("\n---------- 회원 관리 프로그램 v4 ----------\n");
		System.out.println("1. 전체 회원 조회");
		System.out.println("2. 아이디로 회원 조회");
		System.out.println("3. 이름으로 회원 조회");
		System.out.println("4. 회원 정보 등록");
		System.out.println("5. 회원 정보 수정");
		System.out.println("6. 회원 정보 삭제");	
		System.out.println("7. 계정 삭제 이력 조회");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >> ");
		int select = sc.nextInt();
		return select;
	}

	public String selectMemberId() {
		System.out.println("\n----- 아이디로 회원 조회 -----\n");
		System.out.print("조회할 아이디를 입력하세요 : ");
		String searchId = sc.next();

		return searchId;
	}// selectMemberId

	public void selcetMemberIdResult(Member m) {
		if (m == null) {
			System.out.println("조회한 아이디의 회원이 존재하지 않습니다.");
		} else {
			System.out.println("아이디 : " + m.getMemberId());
			System.out.println("비밀번호 : " + m.getMemberPw());
			System.out.println("이름 : " + m.getMemberName());
			System.out.println("전화번호 : " + m.getMemberPhone());
			System.out.println("나이 : " + m.getMemberAge());
			System.out.println("성별 : " + m.getMemberGender());
			System.out.println("가입일 : " + m.getEnrollDate());
		}
	}

	// 전체 회원 조회 결과 출력
	public void printAllMember(ArrayList<Member> list) {
		System.out.println("\n----- 전체 회원 조회 -----\n");

		if (list == null || list.isEmpty()) {
			System.out.println("조회된 회원이 없습니다.");
			return;
		}

		// 컬럼 제목 출력
		System.out.println("아이디\t비밀번호\t이름\t연락처\t\t나이\t성별\t가입일");
		System.out.println("---------------------------------------------------------------");

		// 회원 정보 출력
		for (Member m : list) {
			System.out.println(
					m.getMemberId() + "\t" + m.getMemberPw() + "\t" + m.getMemberName() + "\t" + m.getMemberPhone()
							+ "\t" + m.getMemberAge() + "\t" + m.getMemberGender() + "\t" + m.getEnrollDate());
		}
	}// printAllMember

	public Member insertMember() {
		System.out.println("\n----- 회원 정보 등록 -----\n");
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		System.out.print("이름 입력 : ");
		String memberName = sc.next();
		System.out.print("전화번호 입력 : ");
		String memberPhone = sc.next();
		System.out.print("나이 입력 : ");
		int memberAge = sc.nextInt();
		System.out.print("성별 입력(남/여) : ");
		String memberGender = sc.next();

		// 매개변수 생성자로 객체 생성과 동시에 값 대입
		Member m = new Member(memberId, memberPw, memberName, memberPhone, memberAge, memberGender, null);
		return m;
	}// insertMember

	public void insertMemberResult(int result) {
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 등록되었습니다.");
		} else {
			System.out.println("회원 정보 등록에 실패했습니다.");
		}
	}// insertMemberResult

	public Member deleteMember() {
		System.out.println("\n----- 회원 정보 삭제 -----\n");
		System.out.print("삭제할 회원의 아이디를 입력하세요 : ");
		String deleteId = sc.next();

		Member m = new Member();
		m.setMemberId(deleteId);
		return m;
	}// deleteMember

	public void deleteMemberResult(int result) {
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 삭제되었습니다.");
		} else {
			System.out.println("삭제할 회원이 존재하지 않습니다.");
		}
	}// deleteMemberResult

	public String searchMemberName() {
		// TODO Auto-generated method stub
		System.out.println("\n----- 이름으로 회원 조회 -----\n");
		System.out.print("조회할 이름을 입력하세요 : ");
		String searchName = sc.next();
		return searchName;

	}// searchMemberName
	
	public String updateMemberId() {
		System.out.println("\n----- 회원 정보 수정 -----\n");
		System.out.print("수정할 회원의 아이디를 입력하세요 : ");
		String oldMemberId = sc.next();
		return oldMemberId;
	}
	
	public Member updateMember() {
		System.out.print("수정할 비밀번호를 입력하세요 : ");
		String updatePw = sc.next();

		System.out.print("수정할 전화번호를 입력하세요 : ");
		String updatePhone = sc.next();

		Member m = new Member();
		m.setMemberPw(updatePw);
		m.setMemberPhone(updatePhone);

		return m;
	}

	public void updateMemberResult(int result) {
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 수정되었습니다.");
		} else {
			System.out.println("수정할 회원이 존재하지 않습니다.");
		}
	}// updateMemberResult
	
	
	public void deletedMember(ArrayList<Member> list) {
	    System.out.println("\n----- 계정 삭제 이력 조회 -----\n");

	    if (list == null || list.isEmpty()) {
	        System.out.println("삭제된 회원이 없습니다.");
	        return;
	    }


	    System.out.println("아이디\t이름\t삭제일");
	    System.out.println("------------------------------------");

	    for (Member m : list) {
	        System.out.println(
	            m.getMemberId() + "\t"
	          + m.getMemberName() + "\t"
	          + m.getOutDate()
	        );
	    }
	}

	


}
