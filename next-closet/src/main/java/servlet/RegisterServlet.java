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
import regexp.AddressValidator;
import regexp.EmailValidator;
import regexp.KanaNameValidator;
import regexp.PostCodeValidator;

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
		
		//名前の空文字チェック
		if(userName.isEmpty()) {
			request.getSession().setAttribute("userNameError", "名前の入力が正しくありません");
			response.sendRedirect("register.jsp");
			return;
		}
		
		//フリガナの全角チェック (ひらがなは許可せず, カタカナのみ)
		if(!KanaNameValidator.validate(kanaName)) {
			request.getSession().setAttribute("kanaNameError", "フリガナの入力が正しくありません");
	        response.sendRedirect("register.jsp");
	        return;
		}
		
		//郵便番号チェック (ハイフンを含まない) 全角を半角に置換
		String convertPostCode = postCode.replaceAll("０", "0")
								 		 .replaceAll("１", "1")
								 		 .replaceAll("２", "2")
								 		 .replaceAll("３", "3")
								 		 .replaceAll("４", "4")
								 		 .replaceAll("５", "5")
								 		 .replaceAll("６", "6")
								 		 .replaceAll("７", "7")
								 		 .replaceAll("８", "8")
								 		 .replaceAll("９", "9");
		
		if(!PostCodeValidator.validate(convertPostCode)) {
			request.getSession().setAttribute("postCodeError", "郵便番号が正しくありません");
	        response.sendRedirect("register.jsp");
	        return;
		}
		
		//住所のデータを統一(全角を半角にする)
		if(address.isEmpty()) {
			request.getSession().setAttribute("addressError", "住所が正しくありません");
	        response.sendRedirect("register.jsp");
	        return;
		}
		
		String normalizedAddress = AddressValidator.normalizeAddress(address);
		
		
		//パスワードの文字数チェック
		if((password.length() < 8) && (password.trim().isEmpty())) { //8文字以上かつ空文字を許可しない
			request.getSession().setAttribute("passError", "8文字以上で設定してください");
			response.sendRedirect("register.jsp");
			return;
		} 
		//電話番号の文字数チェック
		if((telNumber.length() > 11)) { //全角を半角に置換する正規表現
			request.getSession().setAttribute("telNumberError", "無効な電話番号です");
			response.sendRedirect("register.jsp");
			return;
		}
		
		//パスワード 使用文字制限の正規表現
		//null, 空文字チェック
		
		//郵便番号でハイフンを使用できない正規表現
		//null, 空文字チェック
		
		//電話番号でハイフンを使用できない正規表現
		//null, 空文字チェック
		
		if (!EmailValidator.validate(email)) { //正規表現を含んだEmailValidatorクラスを使用.
	        // Eメールが無効な形式の場合の処理
	        request.getSession().setAttribute("emailError", "無効なEメールアドレスです");
	        response.sendRedirect("register.jsp");
	        return;
		}
		
		
		String hashedPass = null;
		try {
			hashedPass = HashPW.hashPass(password); //パスワードハッシュ化
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		UserDAO uDao = new UserDAO();
		try {
			int setUser = uDao.registerUser(userName, kanaName, email, hashedPass, telNumber);
			if(setUser == 1) { //ユーザー情報が1行追加されたら...
				int userId = uDao.getUserId(email);
				try {
					int setAddress = uDao.registerAddress(userId, convertPostCode, prefectures, normalizedAddress);
					if(setAddress == 1) { //ユーザーの住所情報が1行追加されたら...
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
			request.getSession().setAttribute("errorMessage", "システムエラーが発生しました。管理者に連絡してください");
	        response.sendRedirect("error.jsp");
		}
	}
}