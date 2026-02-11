package kr.co.iei.board.vo;

import java.sql.Date;

public class Board {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriter;
    private int readCount;
    private Date regDate; // 날짜 타입을 Date로 통일

    public Board() {}

    // 등록/조회용 생성자
    public Board(int boardNo, String boardTitle, String boardContent, String boardWriter) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardWriter = boardWriter;
    }

    // Getter / Setter
    public int getBoardNo() { return boardNo; }
    public void setBoardNo(int boardNo) { this.boardNo = boardNo; }

    public String getBoardTitle() { return boardTitle; }
    public void setBoardTitle(String boardTitle) { this.boardTitle = boardTitle; }

    public String getBoardContent() { return boardContent; }
    public void setBoardContent(String boardContent) { this.boardContent = boardContent; }

    public String getBoardWriter() { return boardWriter; }
    public void setBoardWriter(String boardWriter) { this.boardWriter = boardWriter; }

    public int getReadCount() { return readCount; }
    public void setReadCount(int readCount) { this.readCount = readCount; }

    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }

    @Override
    public String toString() {
        return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle +
               ", boardContent=" + boardContent + ", boardWriter=" + boardWriter +
               ", readCount=" + readCount + ", regDate=" + regDate + "]";
    }
}
