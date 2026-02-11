package kr.co.iei.member.view;

import java.util.List;
import java.util.Scanner;

import kr.co.iei.member.model.vo.Member;

public class MemberView {

	Scanner sc;

	public MemberView() {
		super();
		sc = new Scanner(System.in);
	}

	public int main() {

		System.out.println("========== 회원 관리 프로그램V7 ==========");
		System.out.println("1. 전체 회원 조회");
		System.out.println("2. 아이디로 회원 조회");
		System.out.println("3. 회원정보등록");
		System.out.println("4. 회원정보수정");
		System.out.println("5. 회원정보삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >> ");
		int select = sc.nextInt();
		return select;
	}

	public void printAllMember(List<Member> list) {
		// TODO Auto-generated method stub
		System.out.println("\n========== 전체 회원 조회 ==========\n");
		System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t가입일");
		for (Member m : list) {
			System.out.println(
					m.getMemberId() + "\t" + m.getMemberPw() + "\t" + m.getMemberName() + "\t" + m.getMemberPhone()
							+ "\t" + m.getMemberAge() + "\t" + m.getMemberGender() + "\t" + m.getEnrollDate());

		}

	}

	public String selectMemberId() {
		// TODO Auto-generated method stub
		System.out.print("조회할 회원 아이디 입력 : ");
		String memberId = sc.next();
		return memberId;

	}

	public void printOneMember(Member m) {
		// TODO Auto-generated method stub
		if (m == null) {
			System.out.println("조회한 아이디의 회원이 존재하지 않습니다.");

		} else {
			System.out.println("\n========== 회원 정보 조회 ==========\n");
			System.out.println("아이디 : " + m.getMemberId());
			System.out.println("비밀번호 : " + m.getMemberPw());
			System.out.println("이름 : " + m.getMemberName());
			System.out.println("전화번호 : " + m.getMemberPhone());
			System.out.println("나이 : " + m.getMemberAge());
			System.out.println("성별 : " + m.getMemberGender());
			System.out.println("가입일 : " + m.getEnrollDate());

		}
	}

	public Member insertMember() {
		System.out.println("\n========== 회원 정보 등록 ==========\n");

		sc.nextLine();

		System.out.print("아이디 : ");
		String memberId = sc.nextLine();

		System.out.print("비밀번호 : ");
		String memberPw = sc.nextLine();

		System.out.print("이름 : ");
		String memberName = sc.nextLine();

		System.out.print("전화번호 : ");
		String memberPhone = sc.nextLine();

		System.out.print("나이 : ");
		int memberAge = Integer.parseInt(sc.nextLine());

		System.out.print("성별(남/여) : ");
		String memberGender = sc.nextLine();
		Member m = new Member(memberId, memberPw, memberName, memberPhone, memberAge, memberGender, null);
		return m;
	}

	public void insertResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 등록되었습니다.");
		} else {
			System.out.println("회원 정보 등록에 실패하였습니다.");
		}

	}

	public Member updateMember() {
		System.out.println("\n========== 회원 정보 수정 ==========\n");

		sc.nextLine();

		System.out.print("수정할 회원 아이디 : ");
		String memberId = sc.nextLine();

		System.out.print("새 비밀번호 : ");
		String memberPw = sc.nextLine();

		System.out.print("새 이름 : ");
		String memberName = sc.nextLine();

		System.out.print("새 전화번호 : ");
		String memberPhone = sc.nextLine();

		System.out.print("새 나이 : ");
		int memberAge = Integer.parseInt(sc.nextLine());

		System.out.print("새 성별(남/여) : ");
		String memberGender = sc.nextLine();

		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPw(memberPw);
		m.setMemberName(memberName);
		m.setMemberPhone(memberPhone);
		m.setMemberAge(memberAge);
		m.setMemberGender(memberGender);

		return m;
	}

	public String deleteMember() {
		// TODO Auto-generated method stub
		System.out.println("\n========== 회원 정보 삭제 ==========\n");
		System.out.print("삭제할 회원 아이디 : ");
		String memberId = sc.next();
		return memberId;

	}

	public void updateResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 수정되었습니다.");
		} else {
			System.out.println("회원 정보 수정에 실패하였습니다.");
		}

	}

	public void deleteResult(int result) {
		// TODO Auto-generated method stub
		if (result > 0) {
			System.out.println("회원 정보가 성공적으로 삭제되었습니다.");
		} else {
			System.out.println("회원 정보 삭제에 실패하였습니다.");
		}

	}

}
