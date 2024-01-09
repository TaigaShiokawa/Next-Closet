package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.dto.TodoDTO;

public class TodoDAO {
	
	//Todo追加
	public int addTodo(String title, String todo, String status, Date time_limit, int userId) throws ClassNotFoundException, SQLException {
		int row = 0;
		String sql = "INSERT INTO todos (title, todo, status, time_limit, user_id) VALUES (?,?,?,?,?)";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setString(2, todo);
			pstmt.setString(3, status);
			pstmt.setDate(4, time_limit);
			pstmt.setInt(5, userId);
			
			row = pstmt.executeUpdate();
		}
		return row;
	}
	
	//全てのTodoを取得
	public List<TodoDTO> getAllTodos(int userId) throws ClassNotFoundException, SQLException {
		List<TodoDTO> todoList = new ArrayList<>();
		TodoDTO td = new TodoDTO();
		String sql = "SELECT * FROM todos WHERE user_id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				td = new TodoDTO();
				td.setId(res.getInt("id"));
				td.setTitle(res.getString("title"));
				td.setTodo(res.getString("todo"));
				td.setStatus(res.getString("status"));
				td.setTime_limit(res.getDate("time_limit"));
				
				todoList.add(td);
			}
		}
		return todoList;
	}
	
	//編集するためにTodo取得
	public TodoDTO getTodo(int id) throws ClassNotFoundException, SQLException {
		TodoDTO td = null;
		String sql = "SELECT * FROM todos WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				td = new TodoDTO();
				td.setId(res.getInt("id"));
				td.setTitle(res.getString("title"));
				td.setTodo(res.getString("todo"));
				td.setStatus(res.getString("status"));
				td.setTime_limit(res.getDate("time_limit"));
			}
		}
		return td;
	}
	
	//Todo編集
	public int editTodo(int todoId, String title, String todo, String status, Date time_limit) throws ClassNotFoundException, SQLException {
		int row = 0;
		String sql = "UPDATE todos SET title = ?, todo = ?, status = ?, time_limit = ? WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setString(2, todo);
			pstmt.setString(3, status);
			pstmt.setDate(4, time_limit);
			pstmt.setInt(5, todoId);
			
			row = pstmt.executeUpdate();
		}
		return row;
	}
}
