package kr.co.iei.member.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import kr.co.iei.member.vo.Member;

public class MemberController {
    Scanner sc;

    public MemberController() {
        super();
        this.sc = new Scanner(System.in);
        // TODO Auto-generated constructor stub
    }

    public void main() {
        while (true) {
            System.out.println("\n---------- 회원관리 프로그램v2 ----------\n");
            System.out.println("1. 전체 회원 조회");
            System.out.println("2. 아이디로 회원 조회");
            System.out.println("3. 이름으로 회원 조회");
            System.out.println("4. 회원 정보 등록");
            System.out.println("5. 회원 정보 수정");
            System.out.println("6. 회원 정보 삭제");
            System.out.println("0. 프로그램 종료");
            System.out.print("선택>>  ");
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
            }
        }
    }// main()
    public void selectAllMember() {

        // 1. DB 작업 필요
        // 2. query = select * from member_tbl
        // 3. 반환 결과 : 모든 회원 정보

        System.out.println("\n---------- 전체 회원 조회 ----------\n");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        ArrayList<Member> list = new ArrayList<>();
        String query = "SELECT * FROM member_tbl ORDER BY member_id"; // member_id 기준 정렬

        try {
            // 1. 드라이버 등록
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. 커넥션 생성
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
            conn.setAutoCommit(true); // 자동 커밋

            // 3. 쿼리 실행
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                Member m = new Member();
                m.setMemberId(rset.getString("member_id"));
                m.setMemberPw(rset.getString("member_pw"));
                m.setMemberName(rset.getString("member_name"));
                m.setMemberPhone(rset.getString("member_phone"));
                m.setMemberAge(rset.getInt("member_age"));
                m.setMemberGender(rset.getString("member_gender"));
                m.setEnrollDate(rset.getDate("enroll_date"));

                list.add(m);
            }

            if (list.size() == 0) {
                System.out.println("등록된 회원이 없습니다.");
            } else {
                System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t회원가입일");
                for (Member m : list) {
                    System.out.printf("%s\t%s\t%s\t%s\t%d\t%s\t%s\n",
                            m.getMemberId(),
                            m.getMemberPw(),
                            m.getMemberName(),
                            m.getMemberPhone(),
                            m.getMemberAge(),
                            m.getMemberGender(),
                            m.getEnrollDate());
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void selectMemberId()
    // 1. DB 작업 필요
    // 2. query = select * from member where member_id ='입력받은아이디'
    // 3. 입력받아야 할 정보 : 아이디
    {
        System.out.println("\n---------- 아이디로 회원 조회 ----------\n");
        System.out.print("조회할 아이디 입력 : ");
        String searchId = sc.next();

        // 0-1 반환할객체
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        // 0-2. 결과 저장할 변수 선언
        Member m = null;

        // 0-3. 쿼리문 작성
        String query = "select * from member_tbl where member_id = ?";

        // 1.드라이버등록
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 2.커넥션생성
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 3.쿼리문 실행준비
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, searchId);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                m = new Member();
                m.setMemberId(rset.getString("member_id"));
                m.setMemberPw(rset.getString("member_pw"));
                m.setMemberName(rset.getString("member_name"));
                m.setMemberPhone(rset.getString("member_phone"));
                m.setMemberAge(rset.getInt("member_age"));
                m.setMemberGender(rset.getString("member_gender"));
                m.setEnrollDate(rset.getDate("enroll_date"));
            }

            if (m == null) {
                System.out.println("회원 정보를 찾을 수 없습니다.");
            } else {
                System.out.println("아이디 : " + m.getMemberId());
                System.out.println("비밀번호 : " + m.getMemberPw());
                System.out.println("이름 : " + m.getMemberName());
                System.out.println("전화번호 : " + m.getMemberPhone());
                System.out.println("나이 : " + m.getMemberAge());
                System.out.println("성별 : " + m.getMemberGender());
                System.out.println("가입일 : " + m.getEnrollDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }// selectMemberId

    public void selectMemberName() {

        // 1. DB 작업 필요
        // 2. query = select * from member where member_name ='입력받은이름'
        // 3. 입력받아야 할 정보 : 이름
        System.out.println("\n---------- 이름으로 회원 조회 ----------\n");
        System.out.print("조회할 이름 입력 : ");
        String searchName = sc.next();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        ArrayList<Member> list = new ArrayList<>();
        String query = "select * from member_tbl where member_name like '%'||?||'%'";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, searchName);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                Member m = new Member();
                m.setMemberId(rset.getString("member_id"));
                m.setMemberPw(rset.getString("member_pw"));
                m.setMemberName(rset.getString("member_name"));
                m.setMemberPhone(rset.getString("member_phone"));
                m.setMemberAge(rset.getInt("member_age"));
                m.setMemberGender(rset.getString("member_gender"));
                m.setEnrollDate(rset.getDate("enroll_date"));

                list.add(m);
            }

            if (list.size() == 0) {
                System.out.println("조회된 회원이 없습니다.");
            } else {
                System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t회원가입일");
                for (Member m : list) {
                    System.out.printf(m.getMemberId() + "\t" + m.getMemberPw() + "\t" + m.getMemberName() + "\t"
                            + m.getMemberPhone() + "\t" + m.getMemberAge() + "\t" + m.getMemberGender() + "\t"
                            + m.getEnrollDate() + "\n");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rset != null) rset.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertMember() {

        // 1. DB 작업 필요
        // 2. query = insert into member_tbl values(아이디,비밀번호,이름,전화번호,나이,성별,가입일)
        // 3. 입력받아야 할 정보 : 아이디,비밀번호,이름,전화번호,나이,성별
        System.out.println("\n---------- 회원 정보 등록 ----------\n");
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
        System.out.print("성별(남/여) : ");
        String memberGender = sc.next();

        Connection conn = null;
        PreparedStatement pstmt = null;

        String query = "INSERT INTO member_tbl(member_id, member_pw, member_name, member_phone, member_age, member_gender, enroll_date) "
                     + "VALUES (?, ?, ?, ?, ?, ?, SYSDATE)";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user", "1234");
            conn.setAutoCommit(true); // 자동 커밋

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
            pstmt.setString(2, memberPw);
            pstmt.setString(3, memberName);
            pstmt.setString(4, memberPhone);
            pstmt.setInt(5, memberAge);
            pstmt.setString(6, memberGender);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("회원 등록이 완료되었습니다.");
            } else {
                System.out.println("회원 등록에 실패하였습니다.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateMember() {
        System.out.println("\n---------- 회원 정보 수정 ----------\n");
        System.out.print("수정할 회원 아이디 입력 : ");
        String memberId = sc.next();

        // 1. 아이디 존재 여부 확인 쿼리
        String checkQuery = "SELECT COUNT(*) FROM member_tbl WHERE member_id = ?";
        // 2. 정보 수정 쿼리
        String updateQuery = "UPDATE member_tbl SET member_pw = ?, member_phone = ? WHERE member_id = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user",
                    "1234")) {

                conn.setAutoCommit(true);

                // 아이디 여부 확인
                try (PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {
                    pstmt.setString(1, memberId);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            int count = rs.getInt(1);
                            if (count == 0) {
                                System.out.println("존재하지 않는 아이디입니다. 수정을 중단합니다.");
                                return;
                            }
                        }
                    }
                }

                // 아이디가 존재할 경우에만 수정 정보 입력 받기
                System.out.print("변경할 비밀번호 : ");
                String memberPw = sc.next();
                System.out.print("변경할 전화번호 : ");
                String memberPhone = sc.next();

                try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                    pstmt.setString(1, memberPw);
                    pstmt.setString(2, memberPhone);
                    pstmt.setString(3, memberId);

                    int result = pstmt.executeUpdate();
                    if (result > 0) {
                        System.out.println("회원 정보 수정이 완료되었습니다.");
                    } else {
                        System.out.println("회원 정보 수정에 실패하였습니다.");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember() {
        System.out.println("\n---------- 회원 정보 삭제 ----------\n");
        System.out.print("삭제할 회원 아이디 입력 : ");
        String memberId = sc.next();

        String deleteQuery = "DELETE FROM member_tbl WHERE member_id = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "jdbc_user",
                    "1234");
                    PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

                conn.setAutoCommit(true); // 자동 커밋

                pstmt.setString(1, memberId);

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("회원 정보 삭제가 완료되었습니다.");
                } else {
                    System.out.println("회원 정보 삭제에 실패하였습니다. 아이디를 확인해주세요.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}