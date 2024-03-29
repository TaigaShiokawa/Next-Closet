package servlet;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.AdminBean;
import model.bean.ProductBean;
import model.bean.SizeBean;
import model.dao.AdminProductDAO;
import model.dao.ProductDAO;

@WebServlet("/AdminProductEditServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class AdminProductEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
   	 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
     
     if ( admin == null) {
     	response.sendRedirect("AdminLoginServlet");
         return;
     }
     
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            AdminProductDAO adminProductDao = new AdminProductDAO();
            ProductDAO productDao = new ProductDAO();
            List<ProductBean> productList = adminProductDao.editAdminProductList(productId);

            request.setAttribute("productList", productList);
            request.setAttribute("categoryList", productDao.categoryList());
            RequestDispatcher dispatcher = request.getRequestDispatcher("product-edit.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessageToAdmin", "内部の設定エラーが発生しました。"
					+ "早急に対応してください。");
	        response.sendRedirect("errorToAdmin.jsp");
	        return;
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");
            int gender = Integer.parseInt(request.getParameter("gender"));
            int categoryId = Integer.parseInt(request.getParameter("category"));
            
           Part filePart = request.getPart("image");
          
  
           String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            AdminProductDAO productDao = new AdminProductDAO();
            List<ProductBean> productList = productDao.editAdminProductList(productId);
            ProductBean productToUpdate = productList.stream()
                                                     .filter(p -> p.getProductId() == productId)
                                                     .findFirst()
                                                     .orElse(new ProductBean());
            if (fileName == null || fileName.isEmpty()) {
            	fileName = productToUpdate.getImage();
            } else {
            	filePart.write(fileName);
            }
            
            productToUpdate.setProductName(productName);
            productToUpdate.setPrice(price);
            productToUpdate.setDescription(description);
            productToUpdate.setImage(fileName);
            productToUpdate.setGender(gender);
            productToUpdate.setCategoryId(categoryId);

            Map<String, Integer> stockQuantities = new HashMap<>();
            for (ProductBean product : productList) {
                for (SizeBean size : product.getSizes()) {
                    String inputName = "stockQuantity_" + product.getProductId() + "_" + size.getSizeName();
                    int stockQuantity = Integer.parseInt(request.getParameter(inputName));
                    stockQuantities.put(product.getProductId() + "_" + size.getSizeName(), stockQuantity);
                }
            }

            AdminProductDAO dao = new AdminProductDAO();
            dao.updateProduct(productToUpdate, stockQuantities);
            
            response.sendRedirect("AdminProductDetailServlet?productId=" + productId);
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
