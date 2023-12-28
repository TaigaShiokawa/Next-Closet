package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hashedPassword.HashPW;
import model.bean.AddressBean;
import model.bean.UserBean;
import model.dao.UserDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		UserDAO uDao = new UserDAO();
		
		try {
			String hashedPass = HashPW.hashPass(password);
			UserBean loginUser = uDao.userLogin(email, hashedPass);
			int userId = uDao.getUserId(email);
			AddressBean loginUserAddress = uDao.getUserAddressId(userId);
			if((loginUser != null) && (loginUser.isUserStatus() == true)) { //退会済みのチェック
				request.getSession().setAttribute("user", loginUser);
				request.getSession().setAttribute("userAddress", loginUserAddress);
				request.getSession().setAttribute("userId", userId);
				response.sendRedirect("ProductListServlet");
			} else {
				request.getSession().setAttribute("loginError", "ログインに失敗しました...");
				response.sendRedirect("login.jsp");
				return;
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
		catch (Exception e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("error.jsp");
			return;
		}
	}
}