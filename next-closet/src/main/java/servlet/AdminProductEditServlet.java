package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ProductBean;
import model.bean.SizeBean;
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");
            String image = request.getParameter("image");

            AdminProductDAO productDao = new AdminProductDAO();
            List<ProductBean> productList = productDao.editAdminProductList(productId);
            ProductBean productToUpdate = productList.stream()
                                                     .filter(p -> p.getProductId() == productId)
                                                     .findFirst()
                                                     .orElse(new ProductBean());
            productToUpdate.setProductName(productName);
            productToUpdate.setPrice(price);
            productToUpdate.setDescription(description);
            productToUpdate.setImage(image);

            Map<String, Integer> stockQuantities = new HashMap<>();
            for (SizeBean size : productToUpdate.getSizes()) {
                int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity_" + size.getSizeName()));
                stockQuantities.put(size.getSizeName(), stockQuantity);
            }

            AdminProductDAO dao = new AdminProductDAO();
            dao.updateProduct(productToUpdate, stockQuantities);

            response.sendRedirect("AdminProductDetailServlet?productId=" + productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
