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


@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int userId = (int)request.getSession().getAttribute("userId");

		CartDAO cartDao = new CartDAO();
        List<CartItemBean> cartItems = null;
		try {
			cartItems = cartDao.getCartItems(userId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
	        response.sendRedirect("error.jsp");
	        return;
		} catch (SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
					+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
			return;
		}
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		int userId = (int)request.getSession().getAttribute("userId");
		int productId = Integer.parseInt(request.getParameter("productId"));
		int sizeId = Integer.parseInt(request.getParameter("sizeId"));
		String quantityStr = request.getParameter("quantity");
		
		if(userId < 1) {
			request.getSession().setAttribute("userNotFound", "ユーザーが見つかりませんでした。"
					+ "再度お試しいただくか、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
			return;
		} else if(productId < 1) {
			request.getSession().setAttribute("productNotFound", "商品が見つかりませんでした。"
					+ "再度お試しいただくか、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("cart.jsp");
			return;
		} else if(sizeId < 1) {
			request.getSession().setAttribute("sizeNotFound", "サイズが選択されていません。");
			response.sendRedirect("cart.jsp");
			return;
		} 
		
		if(quantityStr.isEmpty()) {
			request.getSession().setAttribute("productNotFound", "数量が選択されていません。");
			response.sendRedirect("cart.jsp");
			return;
		}
		
		CartDAO cartDao = new CartDAO();
		try {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cartDao.addCartItem( userId,  productId, sizeId, quantity);
		} catch (NumberFormatException e) {
		    request.getSession().setAttribute("quantityError", "無効な数量が入力されました。");
		    response.sendRedirect("cart.jsp");
		    return;
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
					+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
	        response.sendRedirect("error.jsp");
	        return;
		} catch (SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
					+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("error.jsp");
			return;
		}
		
		
		List<CartItemBean> cartItems;
		try {
			cartItems = cartDao.getCartItems(userId);
			if(cartItems.isEmpty()) {
				request.getSession().setAttribute("productNotFound", "数量またはサイズが選択されていません。");
				response.sendRedirect("product-detail.jsp");
				return;
			}
			request.setAttribute("cartItems", cartItems);
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
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("error.jsp");
			return;
		}
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
	
}
