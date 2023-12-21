package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AdminBean;
import model.dao.AdminDAO;

@WebServlet("/AdminDetailServlet")
public class AdminDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    int adminId = Integer.parseInt(request.getParameter("adminId"));

	    AdminBean loginadmin = new AdminBean();
	    AdminDAO aDao = new AdminDAO();

	    try {
	        loginadmin = aDao.getDetailadmin(adminId);
	        request.setAttribute("admin", loginadmin);
	        request.getRequestDispatcher("admin-detail.jsp").forward(request, response);
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    int adminId = Integer.parseInt(request.getParameter("adminId"));

	    AdminBean loginadmin = new AdminBean();
	    AdminDAO aDao = new AdminDAO();

	    try {
	        loginadmin = aDao.getDetailadmin(adminId);
	        request.setAttribute("admin", loginadmin);
	        request.getRequestDispatcher("admin-detail.jsp").forward(request, response);
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}

}
