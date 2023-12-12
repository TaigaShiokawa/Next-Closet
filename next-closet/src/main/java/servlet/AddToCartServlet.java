package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CartItemBean;
import model.bean.UserBean;
import model.dao.CartDAO;


@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			UserBean loginUser = (UserBean)request.getSession().getAttribute("user");
			CartDAO cartDao = new CartDAO();
			List<CartItemBean> cartItems = cartDao.getCartItems(loginUser.getUserId());
			request.setAttribute("cartItems", cartItems);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}
	
}
