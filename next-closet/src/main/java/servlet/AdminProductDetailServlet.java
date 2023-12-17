package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ProductBean;
import model.dao.AdminProductDAO;

@WebServlet("/AdminProductDetailServlet")
public class AdminProductDetailServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            if(productId < 1) {
            	request.getSession().setAttribute("productIdNotFound", "商品IDが見つかりませんでした.");
            	return;
            }

            AdminProductDAO productDao = new AdminProductDAO();
            List<ProductBean> productList = productDao.detailAdminProductList(productId);
            request.setAttribute("productList", productList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-product-detail.jsp");
            dispatcher.forward(request, response);
            
        } catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessageToAdmin", "内部の設定エラーが発生しました。"
					+ "早急に対応してください。");
	        response.sendRedirect("errorToAdmin.jsp");
	        return;
		} catch (SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessageToAdmin", "データベースにアクセスできません。"
					+ "早急に対応してください。");
			response.sendRedirect("errorToAdmin.jsp");
			return;
		} catch(Exception e) {
		  e.printStackTrace();
		  request.getSession().setAttribute("errorMessageToAdmin", "システムエラーが発生しました。早急に対応してください。");
		  response.sendRedirect("errorToAdmin.jsp");
		  return;
	  }
    }
}
