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
		} catch (SQLException e) {
			e.printStackTrace();
		}
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		int userId = Integer.parseInt(request.getParameter("id"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		int sizeId = Integer.parseInt(request.getParameter("sizeId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		CartDAO cartDao = new CartDAO();
		try {
			cartDao.addCartItem( userId,  productId, sizeId, quantity);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("cart.jsp");
	}
	
}
