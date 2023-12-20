package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;


@WebServlet("/AdminUserWithdrawalServlet")
public class AdminUserWithdrawalServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		 UserDAO uDao = new UserDAO();
		
		try {
			int res = uDao.chageUserStatus(userId);
			if(res == 1) {
				String view = "/admin-user-list.jsp";
		        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		        dispatcher.forward(request, response);
			} else {
				response.sendRedirect("AdminUserDetail.jsp");
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	
	}

}
