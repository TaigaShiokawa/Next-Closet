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
import model.bean.AddressBean;
import model.bean.UserBean;
import model.dao.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
//		if(userStatus == false) {
//			request.getSession().setAttribute("notFound", "退会済みのようです");
//			response.sendRedirect("login.jsp");
//			return;
//		}
		
		UserDAO uDao = new UserDAO();
		
		try {
			String hashedPass = HashPW.hashPass(password);
			UserBean loginUser = uDao.userLogin(email, hashedPass);
			int userId = uDao.getUserId(email);
			AddressBean loginUserAddress = uDao.getUserAddressId(userId);
			if((loginUser != null) && (loginUser.isUserStatus() == true)) { //退会済みのチェック
				request.getSession().setAttribute("user", loginUser);
				request.getSession().setAttribute("userAddress", loginUserAddress);
				request.getSession().setAttribute("userId", userId);
				response.sendRedirect("test.jsp"); //ナビゲーションからマイページへの遷移を確認するため, test.jspという仮の商品一覧ページを作って検証している.
			} else {
				request.getSession().setAttribute("loginError", "ログインに失敗しました...");
				response.sendRedirect("login.jsp");
			}
		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}