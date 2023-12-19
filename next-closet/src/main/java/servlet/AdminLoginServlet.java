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
import model.bean.AdminBean;
import model.dao.AdminDAO;


@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String view = "admin-login.jsp";
    	request.getRequestDispatcher(view).forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashedPass = null;
        
        try {
			hashedPass = HashPW.hashPass(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessageToAdmin", "不正なパスワードです。再度、入力してください。");
			response.sendRedirect("errorToAdmin.jsp");
			return;
		}
       
          AdminDAO adminDao = new AdminDAO();
          
          try {
  			AdminBean loginAdmin = adminDao.validate(email, hashedPass);
  			int adminId = adminDao.getAdminId(email);
  			if((loginAdmin != null) && (loginAdmin.isAdminStatus() == true)) { //logout済みのチェック
  				request.getSession().setAttribute("admin", loginAdmin);
  				request.getSession().setAttribute("adminId", adminId);
  				request.getSession().setAttribute("password", password);				
  				response.sendRedirect("AdminProductListServlet");
  			} else {
  				request.getSession().setAttribute("loginError", "ログインに失敗しました...");
  				response.sendRedirect("admin-login.jsp");
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
			} catch(Exception e) {
			  e.printStackTrace();
			  request.getSession().setAttribute("errorMessageToAdmin", "システムエラーが発生しました。早急に対応してください。");
			  response.sendRedirect("errorToAdmin.jsp");
			  return;
		  }
      }
}
