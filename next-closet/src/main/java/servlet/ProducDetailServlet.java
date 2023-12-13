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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 int productId =  Integer.parseInt(request.getParameter("productId"));
		 
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
