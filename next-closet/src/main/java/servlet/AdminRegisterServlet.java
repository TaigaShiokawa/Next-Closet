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
import regexp.EmailValidator;
import regexp.KanaNameValidator;
import regexp.UserNameValidator;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("admin-register.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String adminName = request.getParameter("adminname");
		String kanaName = request.getParameter("kananame");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//名前の空文字をチェック
		if(!UserNameValidator.validate(adminName)) {
			request.getSession().setAttribute("adminNameError", "名前の入力が正しくありません");
			response.sendRedirect("admin-register.jsp");
			return;
		}
		//カタカナ以外は進めないようにするやつ
		if(!KanaNameValidator.validate(kanaName)) {
			request.getSession().setAttribute("kanaNameError", "フリガナの入力が正しくありません");
	        response.sendRedirect("admin-register.jsp");
	        return;
		}
		
		//パスワードの文字数チェック
		if((password.length() < 8) && (password.trim().isEmpty())) { //8文字以上かつ空文字を許可しない
			request.getSession().setAttribute("passError", "8文字以上で設定してください");
			response.sendRedirect("admin-register.jsp");
			return;
		} 
		
		if (!EmailValidator.validate(email)) { //正規表現を含んだEmailValidatorクラスを使用.
	        // Eメールが無効な形式の場合の処理
	        request.getSession().setAttribute("emailError", "無効なEメールアドレスです");
	        response.sendRedirect("admin-register.jsp");
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
			int setAdmin = aDao.registerAdmin(adminName, kanaName, email, hashedPass);
			if(setAdmin==1) { //ユーザーの住所情報が1行追加されたら...
				request.getSession().setAttribute("success", "登録完了！");
				response.sendRedirect("admin-register.jsp");
			} else {
				request.getSession().setAttribute("failure", "登録済みです");
				response.sendRedirect("admin-register.jsp");
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "システムエラーが発生しました");
	        response.sendRedirect("errorToAdmin.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "データベースエラーが発生しました。しばらくしてから再開してください");
	        response.sendRedirect("errorToAdmin.jsp");
		}
	}

}

