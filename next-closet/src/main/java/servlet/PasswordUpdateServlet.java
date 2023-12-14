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
		
		String userIdStr = (String)request.getAttribute("userId");
		if(userIdStr == null) {
			request.getSession().setAttribute("userNotFound", "ユーザーが存在しません。");
			response.sendRedirect("error.jsp");
			return;
		}
		
		String password = request.getParameter("password");
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
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
