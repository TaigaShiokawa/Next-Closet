package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AddressBean;
import model.dao.UserDAO;

/**
 * Servlet implementation class SubAddressServlet
 */
@WebServlet("/SubAddressServlet")
public class SubAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int userId = (int)request.getSession().getAttribute("userId");
		
		//Listで追加した住所を表示
		List<AddressBean> addressList = new ArrayList<>();
		UserDAO uDao = new UserDAO();
		try {
			addressList = uDao.getSubAddress(userId);
			request.setAttribute("addressList", addressList);
			request.getRequestDispatcher("sub-address.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String postCode = request.getParameter("postcode");
		String prefectures = request.getParameter("prefectures");
		String address = request.getParameter("address");
		int userId = (int)request.getSession().getAttribute("userId");
		
		UserDAO uDao = new UserDAO();
		try {
			int res = uDao.setSubAddress(userId, postCode, address, prefectures);
			if(res == 1) {
				List<AddressBean> addressList = uDao.getSubAddress(userId);
		        request.setAttribute("addressList", addressList);
				request.getRequestDispatcher("sub-address.jsp").forward(request, response);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
	        response.sendRedirect("error.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
					+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("error.jsp");
		}
	}

}
