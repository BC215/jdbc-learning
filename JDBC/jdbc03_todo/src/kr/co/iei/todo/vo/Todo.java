package kr.co.iei.todo.vo;

public class Todo {
    private int todoNo;
    private String memberName;
    private String todoCheck;
    private String enrollDate;
    private String todoContent;
    
    public Todo() {}

    public int getTodoNo() {
        return todoNo;
    }
    public void setTodoNo(int todoNo) {
        this.todoNo = todoNo;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getTodoCheck() {
        return todoCheck;
    }
    public void setTodoCheck(String todoCheck) {
        this.todoCheck = todoCheck;
    }
    public String getEnrollDate() {
        return enrollDate;
    }
    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }
    public String getTodoContent() {
        return todoContent;
    }
    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
    }
}