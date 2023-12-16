package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import model.bean.ProductBean;
import model.dao.AdminProductDAO;

@WebServlet("/AdminProductEditlServlet")
public class AdminProductEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServlet response) 
			throws ServletException, IOException {
		try {
			int pruductId = Integer.parseInt(request.getParameter("productId"));
			AdminProductDAO productDao = new AdminProductDAO();
			List<ProductBean> productList = productDao.editAdminProductList(pruductId);
			
			request.setAttribute("productList", productList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, (ServletResponse) response);
	} catch (NumberFormatException | SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
  }
}