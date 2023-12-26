package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AdminBean;
import model.dao.UserDAO;


@WebServlet("/AdminUserRestorationServlet")
public class AdminUserRestorationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
         
         if ( admin == null) {
         	response.sendRedirect("AdminLoginServlet");
             return;
         }
         int userId = Integer.parseInt(request.getParameter("userId"));
		UserDAO uDao = new UserDAO();
		
		try {
			int res = uDao.userTrueStatus(userId);
			if(res== 0 ) {
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessageToAdmin", "内部の設定エラーが発生しました。"
					+ "早急に対応してください。");
	        response.sendRedirect("errorToAdmin.jsp");
	        return;
		} catch (SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessageToAdmin", "データベースにアクセスできません。"
					+ "早急に対応してください。");
			response.sendRedirect("errorToAdmin.jsp");
			return;
		} catch(Exception e) {
		  e.printStackTrace();
		  request.getSession().setAttribute("errorMessageToAdmin", "システムエラーが発生しました。早急に対応してください。");
		  response.sendRedirect("errorToAdmin.jsp");
		  return;
	  }
		
		response.sendRedirect("AdminUserListServlet");
	}

}
