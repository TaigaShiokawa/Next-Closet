package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hashedPassword.HashPW;
import model.dao.UserDAO;
import model.dto.UserDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.sendRedirect("login.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		try {
			UserDAO uDao = new UserDAO();
			UserDTO user = new UserDTO();
			String userEmail = request.getParameter("userEmail");
			String userPassword = request.getParameter("userPassword");
			String hashedPassword = hashPassword(request, response, userPassword);
			
			
			user = uDao.userLogin(userEmail, hashedPassword);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("userId", uDao.getUserId(userEmail));
			userLoginResult(request, response, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String hashPassword(HttpServletRequest request, HttpServletResponse response, String pass) throws NoSuchAlgorithmException {
			return HashPW.hashedPass(pass); 
	}
	
	private void userLoginResult(HttpServletRequest request, HttpServletResponse response, UserDTO user) throws ServletException, IOException {
		if(user == null) {
			request.setAttribute("loginFailure", "ログインに失敗しました。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else {
			response.sendRedirect("TodoList");
		}
	}

}
