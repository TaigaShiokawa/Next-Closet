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

import model.bean.CategoryBean;
import model.dao.ProductDAO;


@WebServlet("/AdminProductListServlet")
public class AdminProductListServlet extends HttpServlet {
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int categoryId = -1;
		int gender = 0;
		String categoryIdStr = request.getParameter("categoryId");
		String genderStr =  request.getParameter("gender");
		// String searchName = request.getParameter("searchName");
		
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
		
		//List<ProductBean> searchProducts = new ArrayList<>();
		
		try {
			request.setAttribute("categoryList", dao.categoryList());
			List<CategoryBean> cb = dao.categoryList();
			
//			if (searchName != null && !searchName.isEmpty()) {
//				//検索がある場合、検索機能を使用
//				SearchDAO searchDao = new SearchDAO();
//				searchProducts = searchDao.searchProductList(searchName);
//				request.setAttribute("searchProducts", searchProducts);
//				request.setAttribute("title", searchName + "の検索結果");
//			} else {
			
			if( categoryId == -1 ) {
				request.setAttribute("title","ALL / 商品一覧");
				request.setAttribute("productList",dao.allStatusProductList());
				
			} else {
				
				if(  gender == -1 ){
					request.setAttribute("title",  " ALL / " + categoryName + "/ 商品一覧");
					request.setAttribute("productList",dao.allStatusCategoryProductList(categoryId));
					
				}else {
					request.setAttribute("title", genderStr + "/" + categoryName + "/ 商品一覧");
					request.setAttribute("productList",dao.categoryStatusProductList(categoryId , gender));
				}
			}
			// }
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println( "-----キャッチ-------");
			}

		 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-product-list.jsp");
   	     dispatcher.forward(request, response);	
    	
    	}
	}
