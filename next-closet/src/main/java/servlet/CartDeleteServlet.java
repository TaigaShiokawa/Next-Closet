package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CartItemBean;
import model.dao.CartDAO;

@WebServlet("/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
		try {
			if(cartItemId < 1) {
				request.getSession().setAttribute("cartItemNotFound", "削除する商品が見つかりませんでした。"
						+ "再度お試しいただくか、お問い合わせより管理者にご連絡ください。");
				response.sendRedirect("cart.jsp");
				return;
			}
		} catch(NumberFormatException e) { //文字列が適切な数値形式になっていない場合. 例えば, 文字や記号が含まれているなど.
			e.printStackTrace();
			request.getSession().setAttribute("cartItemNotFound", "削除する商品が見つかりませんでした。"
					+ "再度お試しいただくか、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
			return;
		}
		
		CartDAO cartDao = new CartDAO();
		
		try {
			cartDao.destroyCartItem(cartItemId);
			int userId = (int)request.getSession().getAttribute("userId");
			//ログインユーザーのセッション確認
			if(userId < 1) {
				request.getSession().setAttribute("userNotFound", "ユーザーが見つかりませんでした。"
						+ "再度お試しいただくか、お問い合わせより管理者にご連絡ください。");
				response.sendRedirect("error.jsp");
				return;
			}
			List<CartItemBean> cartItems = cartDao.getCartItems(userId);
			request.setAttribute("cartItems", cartItems);
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
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
					+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
		}
		
		request.getRequestDispatcher("cart.jsp").forward(request, response);	
	}
}
