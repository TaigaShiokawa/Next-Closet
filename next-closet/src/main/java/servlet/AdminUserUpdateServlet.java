package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.UserBean;
import model.dao.UserDAO;


@WebServlet("/AdminUserUpdateServlet")
public class AdminUserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			UserDAO userDao = new UserDAO();
			
			List<UserBean> userLists  = userDao.getUserDetail(userId);
			request.getSession().setAttribute("userLists", userLists);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin-user-edit.jsp");
			dispatcher.forward(request, response);
			
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
		
		
	}
}
