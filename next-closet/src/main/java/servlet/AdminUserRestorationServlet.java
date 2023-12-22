package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;


@WebServlet("/AdminUserRestorationServlet")
public class AdminUserRestorationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserDAO uDao = new UserDAO();
		
		try {
			int res = uDao.userTrueStatus(userId);
			if(res== 0 ) {
			}
		} catch (ClassNotFoundException | SQLException e){
			e.printStackTrace();
			
		}
		response.sendRedirect("AdminUserListServlet");
	}

}