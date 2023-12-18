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

@WebServlet("/AdminProductEditServlet")
public class AdminProductEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            AdminProductDAO productDao = new AdminProductDAO();
            List<ProductBean> productList = productDao.editAdminProductList(productId);

            request.setAttribute("productList", productList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product-edit.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
