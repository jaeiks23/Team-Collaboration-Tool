package DTO;

import java.sql.Date;

public class TodoDTO {
	private int section_Id;
	private int todo_Id; // 할일 id
	private String part_Id; // 담당 ID
	private String todo_Content; // 작업 용
	private String todo_Start_Date;
	private String todo_End_Date;
	private String todo_info;
	private String todo_Status; //대기중, 진행중, 완료 /완료가 되면 체크 또는 체크하면 완료로 변환
	private String todo_Rank;
	
	public int getSection_Id() {
		return section_Id;
	}
	public void setSection_Id(int section_Id) {
		this.section_Id = section_Id;
	}
	public int getTodo_Id() {
		return todo_Id;
	}
	public void setTodo_Id(int todo_Id) {
		this.todo_Id = todo_Id;
	}
	public String getTodo_Content() {
		return todo_Content;
	}
	public void setTodo_Content(String todo_Content) {
		this.todo_Content = todo_Content;
	}
	public String getPart_Id() {
		return part_Id;
	}
	public void setPart_Id(String part_Id) {
		this.part_Id = part_Id;
	}
	public String getTodo_Start_Date() {
		return todo_Start_Date;
	}
	public void setTodo_Start_Date(String todo_Start_Date) {
		this.todo_Start_Date = todo_Start_Date;
	}
	public String getTodo_End_Date() {
		return todo_End_Date;
	}
	public void setTodo_End_Date(String todo_End_Date) {
		this.todo_End_Date = todo_End_Date;
	}
	public String getTodo_info() {
		return todo_info;
	}
	public void setTodo_info(String todo_info) {
		this.todo_info = todo_info;
	}
	public String getTodo_Status() {
		return todo_Status;
	}
	public void setTodo_Status(String todo_Status) {
		this.todo_Status = todo_Status;
	}
	public String getTodo_Rank() {
		return todo_Rank;
	}
	public void setTodo_Rank(String todo_Rank) {
		this.todo_Rank = todo_Rank;
	}
	
}
