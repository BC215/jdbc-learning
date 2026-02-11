package kr.co.iei.view;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.model.vo.Member;

// 사용자와 인접한 역할
// 화면에 내용을 표현, 사용자가 입력한 내용을 받음
// 받은 데이터는 Controller에게 전달
public class MemberView {

	Scanner sc;// 사용자 입력용 스캐너

	public MemberView() {
		super();
		sc = new Scanner(System.in);
	}

	public int main() {
		System.out.println("========== 회원 관리 프로그램v5 ==========");
		System.out.println("1. 전체 회원 조회");
		System.out.println("2. 아이디로 회원 조회");
		System.out.println("3. 회원 정보 등록");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int select = sc.nextInt();
		return select;
	}

	public void selectAllMember(ArrayList<Member> list) {
		System.out.println("\n========== 전체 회원 조회 ==========\n");
		System.out.println("아이디\t이름\t나이\t성별\t전화번호\t\t가입일");
		for (Member m : list) {
			System.out.println(m.getMemberId() + "\t" + m.getMemberName() + "\t" + m.getMemberAge() + "\t"
					+ m.getMemberGender() + "\t" + m.getMemberPhone() + "\t" + m.getEnrollDate());
		} // for

	}// selectAllMember

	public String selectMemberId() {
		System.out.println("\n========== 아이디로 회원 조회 ==========\n");
		System.out.print("조회할 아이디 입력 : ");
		String memberId = sc.next();
		return memberId;

	}

	public void printMember(Member m) {
		if (m == null) {
			System.out.println("회원 정보를 찾을수 없습니다.");
		} else {
			System.out.println("\n========== 회원 정보 ==========\n");
			System.out.println("아이디 : " + m.getMemberId());
			System.out.println("이름 : " + m.getMemberName());
			System.out.println("나이 : " + m.getMemberAge());
			System.out.println("성별 : " + m.getMemberGender());
			System.out.println("전화번호 : " + m.getMemberPhone());
			System.out.println("가입일 : " + m.getEnrollDate());
		}
		// TODO Auto-generated method stub

	}

	public Member insertMember() {
		// TODO Auto-generated method stub
		System.out.println("\n========== 회원 정보 등록 ==========\n");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("나이 : ");
		int memberAge = sc.nextInt();
		System.out.print("성별(남/여) : ");
		String memberGender = sc.next();
		System.out.print("전화번호 : ");
		String memberPhone = sc.next();

		Member m = new Member(memberId, memberPw, memberName, memberPhone, memberAge, memberGender, null);

		return m;

	}// insertMember

	public void insertResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 등록되었습니다.");
		} else {
			System.out.println("회원 정보 등록에 실패하였습니다.");
		}

	}

	public String updateMember1() {
		// TODO Auto-generated method stub
		System.out.println("\n========== 회원 정보 수정 ==========\n");
		System.out.print("수정할 회원의 아이디를 입력하세요 : ");
		String memberId = sc.next();

		return memberId;
	}

	public void searchFail() {
		// TODO Auto-generated method stub
		System.out.println("해당 아이디의 회원이 존재하지 않습니다.");
	}

	public Member updateMember2() {
		// TODO Auto-generated method stub
		System.out.print("수정할 비밀번호 입력: ");
		String memberPw = sc.next();
		System.out.print("수정할 전화번호 입력: ");
		String memberPhone = sc.next();
		Member m = new Member();
		m.setMemberPhone(memberPhone);
		m.setMemberPw(memberPw);
		return m;
	}

	public void printupdateResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 수정되었습니다.");
		} else {
			System.out.println("회원 정보 수정에 실패하였습니다.");
		}
	}

	public String deleteMember() {
		// TODO Auto-generated method stub
		System.out.println("\n========== 회원 정보 삭제 ==========\n");
		System.out.print("삭제할 회원의 아이디를 입력하세요 : ");
		String memberId = sc.next();

		return memberId;

	}// deleteMember

	public void deleteFail() {
		// TODO Auto-generated method stub
		System.out.println("회원 정보 삭제에 실패하였습니다.");
	}

	public void deleteSuccess() {
		// TODO Auto-generated method stub
		System.out.println("회원 정보가 성공적으로 삭제되었습니다.");
	}

}


