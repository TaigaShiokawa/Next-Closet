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
import regexp.PasswordStrengthChecker;
import regexp.PasswordValidator;

/**
 * Servlet implementation class PasswordUpdateServlet
 */
@WebServlet("/PasswordUpdateServlet")
public class PasswordUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String password = request.getParameter("password");
		if(!PasswordValidator.isHalfWidth(password)) {
			request.getSession().setAttribute("passError", "パスワードが不正です。正しく入力してください");
			response.sendRedirect("mypage-edit.jsp");
			return;
		} else if((password.length() < 8) || (password.trim().isEmpty())) { 
			request.getSession().setAttribute("passError", "8文字以上で設定してください");
			response.sendRedirect("mypage-edit.jsp");
			return;
		}
		int passwordStrength = PasswordStrengthChecker.calculatePasswordStrength(password);
		request.getSession().setAttribute("passwordStrength", passwordStrength);
		
		String hashedPass = null;
		try {
			hashedPass = HashPW.hashPass(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		UserDAO uDao = new UserDAO();
		try {
			 int res =uDao.userPasswordUpdate(userId, hashedPass);
			 if(res == 1) {
				 response.sendRedirect("MypageServlet");
			 }
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
	        response.sendRedirect("error.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
					+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("error.jsp");
		}
	}

}
