package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import model.bean.UserBean;
import model.dao.UserDAO;


@WebServlet("/AdminUserDetailServlet")
public class AdminUserDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
//		UserBean userBean = new UserBean();
		UserDAO userDao = new UserDAO();
		
		try {
			request.setAttribute("userDetailList", userDao.getUserDetail(userId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-user-detail.jsp");
		dispatcher.forward(request, response);
	}

}
