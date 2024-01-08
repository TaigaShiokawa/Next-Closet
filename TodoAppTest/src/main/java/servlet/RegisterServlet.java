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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().invalidate();
		response.sendRedirect("register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		try {
			UserDAO uDao = new UserDAO();
			String userName = request.getParameter("userName");
			String userEmail = request.getParameter("userEmail");
			String userPassword = request.getParameter("userPassword");
			String hashedPassword = hashPassword(request, response, userPassword);

			if (hashedPassword != null) {
				int row = uDao.userRegister(userName, userEmail, hashedPassword);
				userRegisterResult(request, response, row);
				request.getSession().setAttribute("userEmail", userEmail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String hashPassword(HttpServletRequest request, HttpServletResponse response, String pass)
			throws IOException, NoSuchAlgorithmException {
		if (pass.length() < 8) {
			request.getSession().setAttribute("passError", "8文字以上で設定してください。");
			response.sendRedirect("register.jsp");
			return null;
		} else {
			return HashPW.hashedPass(pass); 
		}
	}

	private void userRegisterResult(HttpServletRequest request, HttpServletResponse response, int row)
			throws IOException {
		if (row != 1) {
			request.getSession().setAttribute("failure", "登録に失敗しました。");
			response.sendRedirect("register.jsp");
			return;
		} else {
			request.getSession().setAttribute("success", "登録成功！");
			response.sendRedirect("login.jsp");
		}
	}

}
