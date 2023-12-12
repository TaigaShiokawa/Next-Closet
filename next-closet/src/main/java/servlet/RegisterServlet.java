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
import regexp.EmailValidator;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.sendRedirect("register.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("username");
		String kanaName = request.getParameter("kananame");
		String postCode = request.getParameter("postcode");
		String prefectures = request.getParameter("prefectures");
		String address = request.getParameter("address");
		String telNumber = request.getParameter("telnumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(password.length() < 8) {
			//パスワードの文字数チェック
			request.getSession().setAttribute("passError", "8文字以上で設定してください");
			response.sendRedirect("register.jsp");
			return;
		} else if(telNumber.length() > 11) {
			//電話番号の文字数チェック
			request.getSession().setAttribute("telNumberError", "無効な電話番号です");
			response.sendRedirect("register.jsp");
		}
		
		if (!EmailValidator.validate(email)) {
	        // Eメールが無効な形式の場合の処理
	        request.getSession().setAttribute("emailError", "無効なEメールアドレスです");
	        response.sendRedirect("register.jsp");
	        return;
	    }
		
		String hashedPass = null;
		try {
			hashedPass = HashPW.hashPass(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		UserDAO uDao = new UserDAO();
		try {
			int setUser = uDao.registerUser(userName, kanaName, email, hashedPass, telNumber);
			if(setUser == 1) {
				int userId = uDao.getUserId(email);
				try {
					int setAddress = uDao.registerAddress(userId, postCode, prefectures, address);
					if(setAddress == 1) {
						request.getSession().setAttribute("success", "登録完了！ ログインへお進みください");
						response.sendRedirect("register.jsp");
					} else {
						request.getSession().setAttribute("failure", "登録済みです。ログインへお進みください");
						response.sendRedirect("register.jsp");
					}
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "システムエラーが発生しました。管理者に連絡してください");
			        response.sendRedirect("error.jsp");
				} catch(SQLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessage", "データベースエラーが発生しました。しばらくしてから再開してください");
			        response.sendRedirect("error.jsp");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessageAll", "システムエラーが発生しました。管理者に連絡してください");
	        response.sendRedirect("error.jsp");
		}
	}
}