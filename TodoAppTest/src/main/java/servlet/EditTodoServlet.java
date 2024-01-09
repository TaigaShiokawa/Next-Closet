package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TodoDAO;
import model.dto.TodoDTO;

/**
 * Servlet implementation class EditTodoServlet
 */
@WebServlet("/EditTodo")
public class EditTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		TodoDAO tDao = new TodoDAO();
		TodoDTO td = new TodoDTO();
		int todoId = Integer.parseInt(request.getParameter("todoId"));
		try {
			td = tDao.getTodo(todoId);
			if(td == null) {
				request.setAttribute("noTodoDetails", "書籍の詳細がありません。");
			} else {
				request.setAttribute("todoDetails", td);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/views/edit-todo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		TodoDAO tDao = new TodoDAO();
		
		int todoId = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String todo = request.getParameter("todo");
		String status = request.getParameter("status");
		Date timeLimit = Date.valueOf(request.getParameter("timeLimit"));
		
		try {
			int row = tDao.editTodo(todoId, title, todo, status, timeLimit);
			if(row != 1) {
				request.getSession().setAttribute("editFailure", "編集に失敗しました。");
				request.getRequestDispatcher("WEB-INF/views/edit-todo.jsp").forward(request, response);
			} else {
				response.sendRedirect("TodoList");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
