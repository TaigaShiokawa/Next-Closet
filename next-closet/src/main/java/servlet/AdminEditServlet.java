package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AdminBean;
import model.dao.AdminDAO;
import regexp.EmailValidator;
import regexp.KanaNameValidator;
import regexp.UserNameValidator;

@WebServlet("/AdminEditServlet")
public class AdminEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
         
         if ( admin == null) {
         	response.sendRedirect("AdminLoginServlet");
             return;
         }
         
		int adminId = Integer.parseInt(request.getParameter("adminId"));

	    AdminBean loginadmin = new AdminBean();
	    AdminDAO aDao = new AdminDAO();

	    try {
	        loginadmin = aDao.getDetailadmin(adminId);
	        request.setAttribute("admin", loginadmin);
	        request.getRequestDispatcher("admin-edit.jsp").forward(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	    int adminId = Integer.parseInt(request.getParameter("adminId"));
		String adminName = request.getParameter("adminname");
		String kanaName = request.getParameter("kananame");
		String email = request.getParameter("email");
		
		//名前の入力チェック
		if(!UserNameValidator.validate(adminName)) {
			request.getSession().setAttribute("adminNameError", "名前の入力が正しくありません");
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
		
		AdminDAO aDao = new AdminDAO();
		try {
			if(adminId > 0) {
				int updateAdmin = aDao.updateAdmin(adminName, kanaName,email,adminId);
				if(updateAdmin == 1) {
					request.setAttribute("adminId", adminId);
					request.getRequestDispatcher("AdminDetailServlet").forward(request, response);
					}
				}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
	        response.sendRedirect("errorToAdmin.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
					+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("errorToAdmin.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("errorToadmin.jsp");
		}
	}
}