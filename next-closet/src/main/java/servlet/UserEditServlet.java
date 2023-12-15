package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AddressBean;
import model.bean.UserBean;
import model.dao.UserDAO;
import regexp.AddressValidator;
import regexp.EmailValidator;
import regexp.KanaNameValidator;
import regexp.PostCodeValidator;
import regexp.TelNumberValidator;
import regexp.UserNameValidator;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("mypage-edit.jsp").forward(request, response);
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
		
		//名前の入力チェック
		if(!UserNameValidator.validate(userName)) {
			request.getSession().setAttribute("userNameError", "名前の入力が正しくありません");
			response.sendRedirect("mypage-edit.jsp");
			return;
		}
		
		//フリガナの入力チェック
		if(!KanaNameValidator.validate(kanaName)) {
			request.getSession().setAttribute("kanaNameError", "フリガナの入力が正しくありません");
			response.sendRedirect("mypage-edit.jsp");
	        return;
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
			request.getSession().setAttribute("postCodeError", "郵便番号が正しくありません");
			response.sendRedirect("mypage-edit.jsp");
	        return;
		}
		
		//住所の空文字チェック
		if(address.isEmpty()) {
			request.getSession().setAttribute("addressError", "住所が正しくありません");
			response.sendRedirect("mypage-edit.jsp");
	        return;
		}
		//住所の数値を統一(全角を半角にする)
		String normalizedAddress = AddressValidator.normalizeAddress(address);
		
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
			request.getSession().setAttribute("telNumberError", "無効な電話番号です");
			response.sendRedirect("mypage-edit.jsp");
			return;
		}
		
		//メールアドレスチェック(一般的な形式に則っていなければ無効)
		if (!EmailValidator.validate(email)) { 
	        // Eメールが無効な形式の場合の処理
	        request.getSession().setAttribute("emailError", "無効なEメールアドレスです");
	        response.sendRedirect("mypage-edit.jsp");
	        return;
		}

		UserBean loginUser = new UserBean();
		AddressBean loginAddress = new AddressBean();
		
		
		
		//ユーザー情報セット 必要かわからん
		loginUser.setUserName(userName);
		loginUser.setKanaName(kanaName);
		loginUser.setTelNumber(convertTelNumber);
		//ユーザーの住所情報セット 必要かわからん
		loginAddress.setPostCode(convertPostCode);
		loginAddress.setPrefectures(prefectures);
		loginAddress.setAddress(normalizedAddress);
		
		UserDAO uDao = new UserDAO();
		try {
			int userId = uDao.getUserId(email);
			if(userId > 0) {
				int updateUser = uDao.loginUserUpdate(userName, kanaName, convertTelNumber, email, userId);
				if(updateUser == 1) {
					int updateUserAddress = uDao.loginUserAddressUpdate(convertPostCode, prefectures, normalizedAddress, userId);
					if(updateUserAddress == 1) {
						UserBean updatedUser = uDao.getUpdateUser(userId);
						AddressBean userAddress = uDao.getUserAddressId(userId);
						request.getSession().setAttribute("user", updatedUser);
						request.getSession().setAttribute("userAddress", userAddress);
						response.sendRedirect("MypageServlet");
					}
				}
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
	        response.sendRedirect("error.jsp");
	        return;
		} catch(SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
					+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
			return;
		}
		catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("error.jsp");
			return;
		}
	}

}
