package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ProductBean;
import model.dao.ProductDAO;

@WebServlet("/AdminProductDetailServlet")
public class AdminProductDetailServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		ProductDAO productDao = new ProductDAO();
		
		List<ProductBean> productList = productDao.detailAdminProductList(productId);
		request.setAttribute("productList", productList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-product-detail.jsp");
		dispatcher.forward(request, response);
	}
}
