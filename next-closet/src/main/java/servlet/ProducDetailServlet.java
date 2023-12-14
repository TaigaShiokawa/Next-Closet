package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;

@WebServlet("/ProductDetailServlet")
public class ProducDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String productIdStr = request.getParameter("productId");
		if(productIdStr == null) {
			request.getSession().setAttribute("productNotFound", "サイズまたは数量が選択されていません。");
			response.sendRedirect("ProductListServlet");
			return;
		}
		
		
		 int productId =  Integer.parseInt(request.getParameter("productId"));
		 if(productId < 1) {
			 request.getSession().setAttribute("productNotFound", "商品が見つかりませんでした。"
					+ "再度お試しいただくか、お問い合わせより管理者にご連絡ください。");
			response.sendRedirect("cart.jsp");
			return;
		 }
		 
		 ProductDAO dao = new ProductDAO();
		 
		 try {
			 request.setAttribute("productList", dao.detailProductList(productId));
		 } catch (SQLException | ClassNotFoundException e){
			 e.printStackTrace();
		 }
		 

		 RequestDispatcher dispatcher = request.getRequestDispatcher("product-detail.jsp");
   	     dispatcher.forward(request, response);
   	     
   	     
 

	}
	
}
