package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TodoDAO;

/**
 * Servlet implementation class AddTodoServlet
 */
@WebServlet("/AddTodo")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		TodoDAO tDao = new TodoDAO();
		
		int userId = (int)request.getSession().getAttribute("userId");
		String title = request.getParameter("title");
		String todo = request.getParameter("todo");
		String status = request.getParameter("status");
		Date timeLimit = Date.valueOf(request.getParameter("timeLimit"));
		
		try {
			int row = tDao.addTodo(title, todo, status, timeLimit, userId);
			if(row != 1) {
				request.getSession().setAttribute("failure", "失敗...");
			} else {
				request.getSession().setAttribute("success", "成功！");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("TodoList");
	}

}
