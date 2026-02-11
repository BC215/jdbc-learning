package kr.co.iei.member.controller;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.management.Query;
import javax.management.monitor.MonitorSettingException;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import kr.co.iei.member.vo.Member;
import oracle.net.aso.m;

public class MemberController {
	Scanner sc;

	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
		sc = new Scanner(System.in);
	}

	public void main() {
		while (true) {
			System.out.println("\n---------- 회원 관리 프로그램v1 ----------\n");
			System.out.println("1.전체 회원 조회");
			System.out.println("2.아이디로 회원 조회"); // 입력받은 아이디와 일치하는 회원
			System.out.println("3.이름으로 회원 조회"); // 입력받은 이름이 포함되어있는 회원 모두 조회
			System.out.println("4.회원 가입");
			System.out.println("5.회원 정보 수정");
			System.out.println("6.회원 삭제");
			System.out.println("0.프로그램종료");
			System.out.print("선택>> ");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				selectAllMember();
				break;
			case 2:
				selectMemberId();
				break;
			case 3:
				selectMemberName();
				break;
			case 4:
				insertMember();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			}// switch

		} // while
	}// main

	public void selectAllMember() {
		// 1. 이 메소드를 구현하기 위해서 DB작업이 필요한지-> ○
		// 2. 이 메소드를 구현을 위해서 필요한 메소드 -> select * from member_tb1;
		// 3. 2번에서 작성 쿼리를 완성하려면 사용자한테 입력받을 데이터가 있는지 여부 체크 ->X
		// -> 사용자가 입력해야 할 데이터가 있으면 데이터 입력을 수행
		// 4. 데이터베이스 작업 시작

		// 0-1. 반환할 객체 미리 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // 수행할 쿼리가 select인경우에만 사용

		// 0-2. 쿼리문 수행결과를 처리할 변수를 선언
		// -> 쿼리문 수행결과를 자바에서 어떤 자료형으로 다룰지 고민후 변수 선언
		// -> 전체 회원 조회 -> 회원정보가 여러개
		// -> 회원1명이면 Member타입 -> 회원이 여러명 -> Member[] / ArrayList[Member]
		// -> 길이를 제한하지 않는 ArrayList를 사용
		// -> 변수 선언 후 초기화는 수행결과의 실패를 기준으로 초기화
		ArrayList<Member> list = new ArrayList<>();
		// 0-3. 쿼리문을 문자열 형식으로 저장 : 쿼리문 작성시에 세미콜론(;)은 반드시 제거(있으면 에러)
		String query = "select * from member_tbl";

		try {
			// 1.드라이버등록(데이터베이스와 연결할때 사용할 클래스 등록 -> ojdbc11.jar안에 포함된 클래스
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection객체(자바 app과 DB를 연결하는 객체)
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 3.Statemnet객체 생성(쿼리문을 DB에 전달하고 수행하고 수행결과를 받아오는객체)
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 4. query 실행
			// 5. 수행결과를 받아서 저장
			try {
				rset = stmt.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// select 수행 결과를 rset변수(ResultSet 객체)에 저장
			// 최종적으로 우리가 사용할 형태인 ArrayList<Member>타입으로 편집
			// ResultSet 객체는 내부에 커서가 존재하고 커서를 통해서 데이터 접근
			// rset.next(); : 커서를 아래로 한줄 내림 -> 데이터가 있으면 true 없으면 false 리턴
			try {
				while (rset.next()) {
					String memberId = rset.getString("member_id");
					String memberPw = rset.getString("member_Pw");
					String memberName = rset.getString("member_name");
					String memberPhone = rset.getString("member_phone");
					int memberAge = rset.getInt("member_age");
					char memberGender = rset.getString("member_gender").charAt(0);
					Date enrollDate = rset.getDate("enroll_date");

					Member m = new Member(memberId, memberPw, memberName, memberPhone, memberAge, memberGender,
							enrollDate);

					list.add(m);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 6.자원반환코드
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("\n---------- 전체 회원 출력 ----------\n");
		System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t회원가입일");
		for (int i = 0; i < list.size(); i++) {
			Member m = list.get(i);
			System.out.println(
					m.getMemberId() + "\t" + m.getMemberPw() + "\t" + m.getMemberName() + "\t" + m.getMemberPhone()
							+ "\t" + m.getMemberAge() + "\t" + m.getMemberGender() + "\t" + m.getEnrollDate());
		}
	}// selectAllMember

	// ID를 입력받아서 해당ID로 DB에서 회원을 조회해서 출력하는 메소드
	public void selectMemberId() {
		// 1. 메소드 구현을 위해 DB작업이 필요한지? ○
		// 2. 쿼리: select * from member_tbl where member_id = '입력받은아이디';
		// 3. 쿼리문 완성을 위해서 사용자에게 받아야할 정보가 있는지 -> 아이디 받아야함
		System.out.println("\n---------- 아이디로 회원 조회 ----------\n");
		System.out.print("조회할 회원 아이디 입력 : ");
		String searchId = sc.next();
		// 4. DB 작업 수행

		// 0-1. 반환할 객체 미리 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		// 0-2. 결과를 처리할 변수 선언
		// 회원정보 조회 -> 쿼리가 잘 수행되어서 최대한 많이 조회되면 1명 -> 조건이 member_id 일치하는 조건
		// 조회가 안되면 0명 -> 회원 1명 or 0명
		Member m = null; // 초기값을 실패를 가정하고 초기화
		// 0-3. 문자열로 쿼리문 작성
		String query = "select * from member_tbl where member_id = '" + searchId + "'";

		try {
			// 1.드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
			// 3. Statement 생성
			stmt = conn.createStatement();
			// 4. 쿼리문 수행하고 결과 받아옴
			// 5. 받아온 결과 저장
			rset = stmt.executeQuery(query);
			// 조건절이 member_id와 일치하는 데이터 조회 -> 결과는 0row or 1row 중 하나
			if (rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPw = rset.getString("member_pw");
				String memberName = rset.getString("member_name");
				String memberPhone = rset.getString("member_phone");
				int memberAge = rset.getInt("member_age");
				char memberGender = rset.getString("member_gender").charAt(0);
				Date enrollDate = rset.getDate("enroll_date");
				m = new Member(memberId, memberPw, memberName, memberPhone, memberAge, memberGender, enrollDate);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 6.자원반환
				rset.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (m == null) {
			System.out.println("회원 정보를 찾을수 없습니다");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("아이디 : " + m.getMemberId());
			System.out.println("비밀번호 : " + m.getMemberPw());
			System.out.println("이름 : " + m.getMemberName());
			System.out.println("전화번호 : " + m.getMemberPhone());
			System.out.println("나이 : " + m.getMemberAge());
			System.out.println("성별 : " + m.getMemberGender());
			System.out.println("회원가입일 : " + m.getEnrollDate());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}// selectMemberId

	public void selectMemberName() {
		System.out.println("\n---------- 이름으로 회원 조회 ----------\n");
		System.out.print("조회할 회원 이름 입력 : ");
		String searchName = sc.next();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		ArrayList<Member> list = new ArrayList<>();

		String query = "select * from member_tbl where member_name like '%" + searchName + "%'";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				String memberId = rset.getString("member_id");
				String memberPw = rset.getString("member_pw");
				String memberName = rset.getString("member_name");
				String memberPhone = rset.getString("member_phone");
				int memberAge = rset.getInt("member_age");
				char memberGender = rset.getString("member_gender").charAt(0);
				Date enrollDate = rset.getDate("enroll_date");

				Member m = new Member(memberId, memberPw, memberName, memberPhone, memberAge, memberGender, enrollDate);

				list.add(m);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				rset.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (list.isEmpty()) { // list.size() ==0 도 가능
			System.out.println("조회된 회원이 없습니다.");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("\n아이디\t이름\t전화번호\t\t나이\t성별\t가입일");
			for (Member m : list) {
				System.out.println(m.getMemberId() + "\t" + m.getMemberName() + "\t" + m.getMemberPhone() + "\t"
						+ m.getMemberAge() + "\t" + m.getMemberGender() + "\t" + m.getEnrollDate());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}// selectMemberName

	public void insertMember() {
		// DB작업 필요한지 : ○
		// 쿼리 : insert into member_tbl
		// values('아이디','비밀번호','이름','전화번호','나이','성별',sysdate);
		// 입력받아야하는 정보 : 아이디 비밀번호 이름 전화번호 나이 성별
		System.out.println("\n---------- 회원가입 ----------\n");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("전화번호 : ");
		String memberPhone = sc.next();
		System.out.print("나이 : ");
		int memberAge = sc.nextInt();
		System.out.print("성별(남/여) :");
		char memberGender = sc.next().charAt(0);

		// 반환객체 선언
		Connection conn = null;
		Statement stmt = null;
		// ResultSer rset - null;//select의 결과를 저장하는객체
		// 0-2. 결과를 처리할 변수 선언
		// insert/update/delete의 쿼리 수행결과는 해당쿼리로 인해서 변경된 행의 수를 리턴 -> 정수로 처리
		int result = 0; // 쿼리로 인해서 반영된 행의 수가 0이면 변경사항이 없음 -> 쿼리수행실패
		// 0-3.쿼리를 문자열 변수로 저장
		String query = "insert into member_tbl values(" + "'" + memberId + "'," + "'" + memberPw + "'," + "'"
				+ memberName + "'," + "'" + memberPhone + "'," + "" + memberAge + "," + "'" + memberGender + "'," + ""
				+ "sysdate)";
		// 4.DB작업 시작
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
			conn.setAutoCommit(true); // 자동커밋 설정
			// 3. Statement 객체 생성
			stmt = conn.createStatement();

			// insert/update/delete 수행할 때는 executeUpdate() 메소드 사용
			result = stmt.executeUpdate(query);
			// 5. 수행결과 처리
			if (result > 0) {
				System.out.println("회원가입 성공");
				// 성공후 2초간대기
				try {
					Thread.sleep(2000); // 2000밀리초 -> 2초
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("회원가입 실패");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// 6. 자원반환은 finally 블록에서
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
	}// insertMember

	// 회원정보 수정(수정할정보: 비밀번호,전화번호 수정)
	public void updateMember() {
		// 1. DB -> ○
		// 2. 쿼리 : update member_tbl set member_pw='수정할비밀번호', member_phone='수정할전화번호'
		// where member_id='수정할아이디';
		// 3. 쿼리완성을 위해서 사용자에게 받아야할 정보가 있는지? -> 아이디, 수정할비밀번호, 수정할전화번호
		// 4. DB작업 시작
		// 아이디를 입력받아서 회원정보 조회 -> 조회가 되면 수정정보 받아서 업데이트
		// -> 조회가 안되면 회원을 찾을수 없습니다.
		// 입력받기
		// 아이디를 조회하는 쿼리
		/*
		 * select * from member_tbl where member_id='입력받은아이디'; selectMember_id from
		 * member_tbl where member_id='입력받은아이디'; select count(*) from member_tbl where
		 * member_id='입력받은아이디'; -> 정수만출력
		 */
		System.out.println("\n---------- 회원정보 수정 ----------\n");
		System.out.print("수정할 회원 아이디 : ");
		String memberId = sc.next();

		Connection conn = null; // DB연결용
		Statement stmt = null; // 쿼리수행용
		ResultSet rset = null; // select쿼리 수행결과 저장용

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			// 1️. 회원 존재 여부 확인
			String checkQuery = "select count(*) from member_tbl where member_id='" + memberId + "'";
			rset = stmt.executeQuery(checkQuery);

			if (rset.next() && rset.getInt(1) == 0) {
				System.out.println("회원정보를 찾을 수 없습니다.");
				return;
			}

			// 2️. 수정 정보 입력
			System.out.print("수정할 비밀번호 : ");
			String memberPw = sc.next();
			System.out.print("수정할 전화번호 : ");
			String memberPhone = sc.next();

			// 3️. 수정 쿼리
			String updateQuery = "update member_tbl set " + "member_pw='" + memberPw + "', " + "member_phone='"
					+ memberPhone + "' where member_id='" + memberId + "'";

			int result = stmt.executeUpdate(updateQuery);

			if (result > 0) {
				conn.commit();
				System.out.println("회원정보 수정 성공");
			} else {
				conn.rollback();
				System.out.println("회원정보 수정 실패");
			}

		} catch (Exception e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteMember() {

		System.out.println("\n---------- 회원삭제 ----------\n");
		System.out.print("삭제할 회원 아이디 : ");
		String memberId = sc.next();

		Connection conn = null;
		Statement stmt = null;

		int result = 0;

		String query = "delete from member_tbl where member_id = '" + memberId + "'";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
			conn.setAutoCommit(false);

			stmt = conn.createStatement();

			result = stmt.executeUpdate(query);

			if (result > 0) {
				System.out.println("회원삭제 성공");
				conn.commit();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("일치하는 정보가 없습니다");
				conn.rollback();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// deleteMember
}
