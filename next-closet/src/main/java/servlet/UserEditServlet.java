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
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		response.sendRedirect("mypage-edit.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		String userName = request.getParameter("username");
		String kanaName = request.getParameter("kananame");
		String postCode = request.getParameter("postcode");
		String prefectures = request.getParameter("prefectures");
		String address = request.getParameter("address");
		String telNumber = request.getParameter("telnumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			String hashedPass = HashPW.hashPass(password);
			
			UserDAO uDao = new UserDAO();
			int updateUser = uDao.loginUserUpDate(userName, kanaName, telNumber, email, hashedPass);
			if(updateUser == 1) {
				try {
					int updateUserAddress = uDao.loginUserAddressUpDate(postCode, prefectures, address);
					if(updateUserAddress == 1) {
						request.getRequestDispatcher("mypage.jsp").forward(request, response);
					} else {
						request.getRequestDispatcher("mypage-edit.jsp").forward(request, response);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (NoSuchAlgorithmException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
