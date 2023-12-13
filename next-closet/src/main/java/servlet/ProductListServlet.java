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

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int categoryId = -1;
		int gender = 0;
		String categoryIdStr = request.getParameter("categoryId");
		String genderStr =  request.getParameter("gender");
		
		if( categoryIdStr != null ) {
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
		}
		
		if( genderStr != null ) {
			gender = Integer.parseInt(request.getParameter("gender"));
		}
		
		if (gender == 0 ) {
			genderStr = "MENS";
		} else if( gender == 1 ) {
			genderStr = "WOMENS";
		} else {
			genderStr = "ALL";
		}
		
		String categoryName = request.getParameter("categoryName");		
		ProductDAO dao = new ProductDAO();
		
		try {
			
			request.setAttribute("categoryList", dao.categoryList());
			
			if( categoryId == -1 ) {
				request.setAttribute("title","ALL / 商品一覧");
				request.setAttribute("productList",dao.allProductList());
				
			} else {
				
				if(  gender == -1 ){
					request.setAttribute("title",  " ALL / " + categoryName + "/ 商品一覧");
					request.setAttribute("productList",dao.allCategoryProductList(categoryId));
					
				}else {
					request.setAttribute("title", genderStr + "/" + categoryName + "/ 商品一覧");
					request.setAttribute("productList",dao.categoryProductList(categoryId , gender));
				}
			}
		
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		 RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
   	     dispatcher.forward(request, response);
 

	}
	
}
