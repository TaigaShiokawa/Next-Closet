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
import model.dao.AdminDAO;

@WebServlet("/AdminPasswordEdit")
public class AdminPasswordEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("admin-edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    int adminId = Integer.parseInt(request.getParameter("adminId"));
		String password = request.getParameter("password");
		
		//パスワードの文字数チェック
		if((password.length() < 8) && (password.trim().isEmpty())) { //8文字以上かつ空文字を許可しない
			request.getSession().setAttribute("passError", "8文字以上で設定してください");
			response.sendRedirect("admin-edit.jsp");
			return;
		}
		String hashedPass = null;
		try {
			hashedPass = HashPW.hashPass(password); //パスワードハッシュ化
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		AdminDAO aDao = new AdminDAO();
		try {
			if(adminId > 0) {
				int updateAdmin = aDao.updatePass(hashedPass,adminId);
				if(updateAdmin == 1) {
				request.setAttribute("adminId",adminId);
				request.getRequestDispatcher("AdminDetailServlet").forward(request, response);
				}
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
	        response.sendRedirect("errorToAdmin.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。");
			response.sendRedirect("errorToAdmin.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("errorToAdmin.jsp");
		}
	}

}
