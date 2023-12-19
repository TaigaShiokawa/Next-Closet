package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.UserBean;
import model.dao.UserDAO;


@WebServlet("/AdminUserDetailServlet")
public class AdminUserDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			UserDAO userDao = new UserDAO();
			
			List<UserBean> userList  = userDao.getUserDetail(userId);
			
			request.setAttribute("userList", userList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin-user-detail.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			UserDAO userDao = new UserDAO();
			
			List<UserBean> userList  = userDao.getUserDetail(userId);
			
			request.setAttribute("userList", userList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin-user-detail.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
