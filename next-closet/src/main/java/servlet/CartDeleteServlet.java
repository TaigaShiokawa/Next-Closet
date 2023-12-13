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
		
		int id = Integer.parseInt(request.getParameter("cartItemId"));
		
		CartDAO cartDao = new CartDAO();
		
		try {
			cartDao.destroyCartItem(id);
			int userId = (int)request.getSession().getAttribute("userId");
			List<CartItemBean> cartItems = cartDao.getCartItems(userId);
			request.setAttribute("cartItems", cartItems);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("cart.jsp").forward(request, response);	
	}
}
