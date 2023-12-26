package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hashedPassword.HashPW;
import model.bean.AdminBean;
import model.dao.UserDAO;
import regexp.AddressValidator;
import regexp.EmailValidator;
import regexp.KanaNameValidator;
import regexp.PasswordStrengthChecker;
import regexp.PasswordValidator;
import regexp.PostCodeValidator;
import regexp.TelNumberValidator;
import regexp.UserNameValidator;

/**
 * Servlet implementation class AdminUserRegisterServlet
 */
@WebServlet("/AdminUserRegisterServlet")
public class AdminUserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
         
         if ( admin == null) {
         	response.sendRedirect("AdminLoginServlet");
             return;
         }
		response.sendRedirect("admin-user-register.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("username");
		String kanaName = request.getParameter("kananame");
		String postCode = request.getParameter("userPostCode");
		String prefectures = request.getParameter("prefectures");
		String address = request.getParameter("userAddress");
		String telNumber = request.getParameter("telNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		List<String> errorMessages = new ArrayList<>();
		
		//名前の入力チェック
		if(!UserNameValidator.validate(userName)) {
			errorMessages.add("名前の入力が正しくありません");
			saveFormDataInSession(request, userName, kanaName, postCode, prefectures, address, telNumber, email);
		} else {
			request.getSession().setAttribute("userName", userName);
		}
		
		//フリガナの全角チェック (ひらがなは許可せず, カタカナのみ)
		if(!KanaNameValidator.validate(kanaName)) {
			errorMessages.add("フリガナの入力が正しくありません");
			saveFormDataInSession(request, userName, kanaName, postCode, prefectures, address, telNumber, email);
		} else {
			request.getSession().setAttribute("kanaName", kanaName);
		}
		
		//郵便番号チェック全角を半角に置換
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
		//郵便番号の入力に対してハイフン無しの形式を要求
		if(!PostCodeValidator.validate(convertPostCode)) {
			errorMessages.add("郵便番号が正しくありません");
			saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, address, telNumber, email);
		} else {
			request.getSession().setAttribute("postCode", convertPostCode);
		}
		
		//住所の空文字チェック
		String normalizedAddress = null;
		if(address.isEmpty()) {
			errorMessages.add("住所を入力してください");
			//	request.getSession().setAttribute("addressError", "住所を入力してください");
			saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, address, telNumber, email);
		} else {
			//住所に含まれる全角数字を半角に置換
			normalizedAddress = AddressValidator.normalizeAddress(address);
			request.getSession().setAttribute("address", normalizedAddress);
		}
		
		//電話番号チェック 全角を半角に置換
		String convertTelNumber = telNumber.replaceAll("０", "0")
								 		   .replaceAll("１", "1")
								 		   .replaceAll("２", "2")
								 		   .replaceAll("３", "3")
								 		   .replaceAll("４", "4")
								 		   .replaceAll("５", "5")
								 		   .replaceAll("６", "6")
								 		   .replaceAll("７", "7")
								 		   .replaceAll("８", "8")
								 		   .replaceAll("９", "9");
		
		//電話番号の入力に対してハイフン無しの形式を要求
		if(!TelNumberValidator.validate(convertTelNumber)) {
			errorMessages.add("無効な電話番号です");
			saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, normalizedAddress, telNumber, email);
		} else {
			request.getSession().setAttribute("telNumber", convertTelNumber);
		}
		
		//メールアドレスチェック(一般的な形式に則っていなければ無効)
		if (!EmailValidator.validate(email)) { 
	        // Eメールが無効な形式の場合の処理
			errorMessages.add("無効なEメールアドレスです");
			saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, address, convertTelNumber, email);
		} else {
			request.getSession().setAttribute("email", email);
		}
		
		//パスワード 半角であれば特殊文字を許可
		if(!PasswordValidator.isHalfWidth(password)) {
			errorMessages.add("パスワードが不正です。正しく入力してください。");
			//パスワードの文字数と空文字チェック
		} else if((password.length() < 8) || (password.trim().isEmpty())) { 
			errorMessages.add("8文字以上で設定してください");
		} 
		
		if(!errorMessages.isEmpty()) {
			request.getSession().setAttribute("errorMessages", errorMessages);
			response.sendRedirect("admin-user-register.jsp");
			return;
		}
		
		int passwordStrength =  PasswordStrengthChecker.calculatePasswordStrength(password);
		request.getSession().setAttribute("passwordStrength", passwordStrength);
		
		//パスワードハッシュ化
		String hashedPass = null;
		try {
			hashedPass = HashPW.hashPass(password); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		UserDAO uDao = new UserDAO();
		try {
			int setUser = uDao.registerUser(userName, kanaName, email, hashedPass, convertTelNumber);
			if(setUser == 1) { //ユーザー情報が1行追加されたら...
				int userId = uDao.getUserId(email);
				try {
					int setAddress = uDao.registerAddress(userId, convertPostCode, prefectures, normalizedAddress);
					request.getSession().setAttribute("postCode", "");
				    request.getSession().setAttribute("prefectures", "");
				    request.getSession().setAttribute("address","");
				    request.getSession().setAttribute("telNumber", "");
				    request.getSession().setAttribute("email", "");
					if(setAddress == 1) { //ユーザーの住所情報が1行追加されたら...
						request.getSession().setAttribute("success", "正常に登録できました。");
						response.sendRedirect("admin-user-register.jsp");
					} else {
						request.getSession().setAttribute("failure", "登録に失敗しました。");
						response.sendRedirect("admin-user-register.jsp");
						return;
					}
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessageToAdmin", "内部の設定エラーが発生しました。"
							+ "早急に対応してください。");
			        response.sendRedirect("errorToAdmin.jsp");
			        return;
				} catch (SQLException e) {
					e.printStackTrace();
					request.getSession().setAttribute("errorMessageToAdmin", "データベースにアクセスできません。"
							+ "早急に対応してください。");
					response.sendRedirect("errorToAdmin.jsp");
					return;
				}
			}
		} catch(Exception e) {
			  e.printStackTrace();
			  request.getSession().setAttribute("errorMessageToAdmin", "システムエラーが発生しました。早急に対応してください。");
			  response.sendRedirect("errorToAdmin.jsp");
			  return;
		  }
	}
	
	private void saveFormDataInSession(HttpServletRequest request, String userName, String kanaName, String postCode, String prefectures, String address, String telNumber, String email) {
	    request.getSession().setAttribute("userName", userName);
	    request.getSession().setAttribute("kanaName", kanaName);
	    request.getSession().setAttribute("postCode", postCode);
	    request.getSession().setAttribute("prefectures", prefectures);
	    request.getSession().setAttribute("address", address);
	    request.getSession().setAttribute("telNumber", telNumber);
	    request.getSession().setAttribute("email", email);
	}

}
