package model.dto;

import java.sql.Timestamp;

public class TodoDTO {
	
	private int id;
	private String title;
	private String todo;
	private String status;
	private Timestamp time_limit;
	
	public TodoDTO() {
		super();
	}

	public TodoDTO(int id, String title, String todo, String status, Timestamp time_limit) {
		super();
		this.id = id;
		this.title = title;
		this.todo = todo;
		this.status = status;
		this.time_limit = time_limit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(Timestamp time_limit) {
		this.time_limit = time_limit;
	}
	
}
