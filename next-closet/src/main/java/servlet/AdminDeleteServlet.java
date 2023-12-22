package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AdminDAO;

@WebServlet("/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("admin-detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int adminId = Integer.parseInt(request.getParameter("adminId"));
	AdminDAO aDao = new AdminDAO();
	try {
		int res = aDao.adminDelete(adminId);
		if(res == 1) {
			request.setAttribute("success", "ID" + adminId + "を削除しました！");
			request.getRequestDispatcher("AdminListServlet").forward(request, response);
		}
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
		request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
				+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
        response.sendRedirect("errorToAdmin.jsp");
        return;
	} catch(SQLException e) {
		e.printStackTrace();
		request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
				+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
		response.sendRedirect("errorToAdmin.jsp");
		return;
	}
	catch(Exception e) {
		e.printStackTrace();
		request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
				+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
		response.sendRedirect("errorToAdmin.jsp");
		return;
	}
}
}
