package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TodoDAO;
import model.dto.TodoDTO;

/**
 * Servlet implementation class TodoListServlet
 */
@WebServlet("/TodoList")
public class TodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int userId = (int)request.getSession().getAttribute("userId");
		
		List<TodoDTO> todoList = new ArrayList<>();
		TodoDAO tDao = new TodoDAO();
		
		try {
			todoList = tDao.getAllTodos(userId);
			if(todoList == null || todoList.isEmpty()) {
				request.setAttribute("notTodo", "登録されているTodoはありません。");
			} else {
				request.setAttribute("todoList", todoList);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("WEB-INF/views/todo-list.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
