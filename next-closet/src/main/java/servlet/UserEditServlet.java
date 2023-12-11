package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hashedPassword.HashPW;
import model.bean.AddressBean;
import model.bean.UserBean;
import model.dao.UserDAO;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		response.sendRedirect("mypage-edit.jsp");
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
		
		String hashedPass = null;
		try {
			hashedPass = HashPW.hashPass(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		UserBean loginUser = new UserBean();
		AddressBean loginAddress = new AddressBean();
		
		loginUser.setUserName(userName);
		loginUser.setKanaName(kanaName);
		loginUser.setTelNumber(telNumber);
		loginUser.setHashPass(hashedPass);
		
		loginAddress.setPostCode(postCode);
		loginAddress.setPrefectures(prefectures);
		loginAddress.setAddress(address);
		
		UserDAO uDao = new UserDAO();
		try {
			int userId = uDao.getUserId(email);
			int updateUser = uDao.loginUserUpdate(userName, kanaName, telNumber, email, hashedPass, userId);
			if(updateUser == 1) {
				int updateUserAddress = uDao.loginUserAddressUpdate(postCode, prefectures, address, userId);
				if(updateUserAddress == 1) {
					response.sendRedirect("MypageServlet");
					return;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
