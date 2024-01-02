package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AdminBean;
import model.dao.AdminProductDAO;

@WebServlet("/ProductStatusChangeServlet")
public class ProductStatusChangeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
   	 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
     
     if ( admin == null) {
     	response.sendRedirect("AdminLoginServlet");
         return;
     }
    	
    	int productId = Integer.parseInt(request.getParameter("productId"));
        AdminProductDAO productDao = new AdminProductDAO();
        
        try {
        	boolean currentStatus = productDao.getProductStatus(productId);
            productDao.updateProductStatus(productId, !currentStatus);
            productDao.cartDeleteItem(productId);
            response.sendRedirect("AdminProductListServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
