package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AdminBean;
import model.dao.UserDAO;
import regexp.AddressValidator;
import regexp.EmailValidator;
import regexp.KanaNameValidator;
import regexp.PostCodeValidator;
import regexp.TelNumberValidator;
import regexp.UserNameValidator;


@WebServlet("/AdminUserEditServlet")
public class AdminUserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("NewFile.jsp");
//        dispatcher.forward(request, response);
		
		 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
         
         if ( admin == null) {
         	response.sendRedirect("AdminLoginServlet");
             return;
         }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	List<String> errorMessages = new ArrayList<>();	
		
	request.setCharacterEncoding("UTF-8");
	
	//パラメータを取得
	String userName = request.getParameter("username");
	String kanaName = request.getParameter("kananame");
	String postCode = request.getParameter("postcode");
	String prefectures = request.getParameter("prefectures");
	String address = request.getParameter("address");
	String telNumber = request.getParameter("telnumber");
	String email = request.getParameter("email");
	int userId = Integer.parseInt(request.getParameter("userId"));
	
	//名前: 全角スペースを必ず含める(半角は許可しない)
	if(!UserNameValidator.validate(userName)) {
		errorMessages.add("名前の入力が正しくありません");
		saveFormDataInSession(request, userName, kanaName, postCode, prefectures, address, telNumber, email);
	} else {
		request.getSession().setAttribute("userName", userName);
	}
	
	//フリガナ: 全角スペースを必ず含める(半角は許可しない)
	if(!KanaNameValidator.validate(kanaName)) {
		errorMessages.add("フリガナの入力が正しくありません");
		saveFormDataInSession(request, userName, kanaName, postCode, prefectures, address, telNumber, email);
	} else {
		request.getSession().setAttribute("kanaName", kanaName);
	}
	
	//郵便番号チェック: 全角を半角に置換
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
	//郵便番号: ハイフンを許可しない
	if(!PostCodeValidator.validate(convertPostCode)) {
		errorMessages.add("郵便番号の形式が正しくありません");
		saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, address, telNumber, email);
	} else {
		request.getSession().setAttribute("postCode", convertPostCode);
	}
	
	//都道府県を格納
	request.getSession().setAttribute("prefectues", prefectures);
	
	//住所の空文字チェック
	String normalizedAddress = null;
	if(address.isEmpty()) {
		errorMessages.add("住所を入力してください");
		saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, address, telNumber, email);
	} else {
		//住所に含まれる全角数字を半角に置換
		normalizedAddress = AddressValidator.convertFullWidthNumbersToHalfwidth(address);
		request.getSession().setAttribute("address", normalizedAddress);
	}
	
	//電話番号: 全角から半角へ
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
	
	//電話番号: ハイフンを許可しない
	if(!TelNumberValidator.validate(convertTelNumber)) {
		errorMessages.add("無効な電話番号です");
		saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, normalizedAddress, convertTelNumber, email);
	} else {
		request.getSession().setAttribute("telNumber", convertTelNumber);
	}
	
	//メールアドレス: 一般的な形式に則っていない場合
	if(!EmailValidator.validate(email)) {
		errorMessages.add("無効なEメールアドレスです");
		saveFormDataInSession(request, userName, kanaName, convertPostCode, prefectures, normalizedAddress, convertTelNumber, email);
	} else {
		request.getSession().setAttribute("eamil", email);
	}
	
	//エラーメッセージを格納
	if(!errorMessages.isEmpty()) {
		request.getSession().setAttribute("errorMessages", errorMessages);
		response.sendRedirect("admin-user-edit.jsp");
		return;
	}
		
	// データベース内のユーザー情報を更新
     try {
         UserDAO uDao = new UserDAO();
         int userUpdate = uDao.loginUserUpdate(userName, kanaName, telNumber, email, userId);
         int addressUpdate = uDao.loginUserAddressUpdate(postCode, prefectures, address, userId);
         
//         request.getSession().setAttribute("user", userUpdate);
//			request.getSession().setAttribute("userAddress", addressUpdate);
         
         request.setAttribute("user", userUpdate);
			request.setAttribute("userAddress", addressUpdate);
			

         if (userUpdate > 0 || addressUpdate > 0) {
             // 更新成功時は成功ページにリダイレクト
        	 String forward = "/AdminUserDetailServlet?userId=" + userId;
             RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
             dispatcher.forward(request, response);
             
//        	 System.out.println("成功");             
         } else {
             // 更新失敗時はエラーページにリダイレクト
        	 request.getRequestDispatcher("admin-user-edit.jsp").forward(request, response);
//             response.sendRedirect("admin-user-edit.jsp");

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
