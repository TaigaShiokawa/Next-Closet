package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;

/**
 * Servlet implementation class WithdrawalServlet
 */
@WebServlet("/WithdrawalServlet")
public class WithdrawalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		UserDAO uDao = new UserDAO();
		try {
			int res = uDao.chageUserStatus(userId);
			if(res == 1) {
				request.getSession().invalidate();	
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("withdrawal.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
