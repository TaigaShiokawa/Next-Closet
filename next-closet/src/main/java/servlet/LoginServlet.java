package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hashedPassword.HashPW;
import model.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDAO uDao = new UserDAO();
		try {
			String hashedPass = HashPW.hashPass(password);
			boolean loginUser = uDao.validate(email, hashedPass);
			if(loginUser) {
				request.getSession().setAttribute("user", uDao.getUserId(email));
				request.getRequestDispatcher("product-list.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("loginError", "ログインに失敗しました...");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}
}
