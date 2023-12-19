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

@WebServlet("/AdminEditServlet")
public class AdminEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("admin-edit.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String adminName = request.getParameter("adminname");
		String kanaName = request.getParameter("kananame");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//名前の入力チェック
		if(adminName.isEmpty()) {
			request.getSession().setAttribute("userNameError", "名前の入力が正しくありません");
			response.sendRedirect("admin-edit.jsp");
			return;
		}
		
		//フリガナの入力チェック
		if(!KanaNameValidator.validate(kanaName)) {
			request.getSession().setAttribute("kanaNameError", "フリガナの入力が正しくありません");
			response.sendRedirect("admin-edit.jsp");
	        return;
		}
		
		if (!EmailValidator.validate(email)) { //正規表現を含んだEmailValidatorクラスを使用.
	        // Eメールが無効な形式の場合の処理
	        request.getSession().setAttribute("emailError", "無効なEメールアドレスです");
	        response.sendRedirect("admin-edit.jsp");
	        return;
		}
		
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
			int adminId = aDao.getAdminId(email);
			if(adminId > 0) {
				int updateAdmin = aDao.updateAdmin(adminName, kanaName,email, hashedPass, adminId);
				if(updateAdmin == 1) {
					request.getSession().setAttribute("password", password);
						response.sendRedirect("AdminDetailServlet");
					}
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
		}
		catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("error.jsp");
		}
	}
}