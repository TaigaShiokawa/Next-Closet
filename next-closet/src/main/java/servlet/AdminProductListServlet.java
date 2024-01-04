package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AdminBean;
import model.bean.ProductBean;
import model.dao.ProductDAO;
import model.dao.SearchDAO;


@WebServlet("/AdminProductListServlet")
public class AdminProductListServlet extends HttpServlet {
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	 
		 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
            
            if ( admin == null) {
            	response.sendRedirect("AdminLoginServlet");
                return;
            }
            
    	
    	int categoryId = 0;
		int gender = 0;
		String categoryIdStr = request.getParameter("categoryId");
		String genderStr =  request.getParameter("gender");
		String searchName = request.getParameter("searchName");
		String status = request.getParameter("status");
		
		if( categoryIdStr != null ) {
			try {
				categoryId = Integer.parseInt(request.getParameter("categoryId"));
			} catch (NumberFormatException e) {
				System.err.println("カテゴリーIDの解析に失敗しました: " + categoryIdStr);
			}
		}
		
		if( genderStr != null ) {
			try {
				gender = Integer.parseInt(request.getParameter("gender"));
			} catch(NumberFormatException e) { 
				System.out.println("性別番号の解析に失敗しました: " + genderStr);
			}
		}
		
		if (gender == 1 ) {
			genderStr = "MENS";
		} else if( gender == 2 ) {
			genderStr = "WOMENS";
		} else {
			genderStr = "ALL";
		}
		
		String categoryName = request.getParameter("categoryName");		
		ProductDAO dao = new ProductDAO();
		
		List<ProductBean> searchProducts = new ArrayList<>();
		
		try {
			request.setAttribute("categoryList", dao.categoryList());
			
			if(status == null) {
				
				 if (searchName != null && !searchName.isEmpty()) {
						//検索がある場合、検索機能を使用
						SearchDAO searchDao = new SearchDAO();
						searchProducts = searchDao.searchStatusProductList(searchName);
						request.setAttribute("searchProducts", searchProducts);
						request.setAttribute("productList",dao.allStatusProductList());
						request.setAttribute("title", searchName + "の検索結果");				
						
					} else {
					
							if( categoryId == 0 ) {
								request.setAttribute("title","ALL / 商品一覧");
								request.setAttribute("productList",dao.allStatusProductList());
							
								
								
							} else {
								
											if(  gender == 0 ){
												request.setAttribute("title",  " ALL / " + categoryName + "/ 商品一覧");
												request.setAttribute("productList",dao.allStatusCategoryProductList(categoryId));
											
												
											}else {
												
												request.setAttribute("title", genderStr + "/" + categoryName + "/ 商品一覧");
												request.setAttribute("productList",dao.categoryStatusProductList(categoryId , gender));
											
											}
							}
					}
				
			
			 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-product-list.jsp");
	   	     dispatcher.forward(request, response);	
	    	
				
			} else if(status.equals("0")) { //削除済み
				
				
				 if (searchName != null && !searchName.isEmpty()) {
						//検索がある場合、検索機能を使用
						SearchDAO searchDao = new SearchDAO();
						searchProducts = searchDao.searchStatusProductList(searchName);
						request.setAttribute("searchProducts", searchProducts);
						request.setAttribute("productList",dao.allStatusProductList());
						request.setAttribute("title", "削除済みユーザー一覧 /" +searchName + "の検索結果");				
						
					} else {
					
							if( categoryId == 0 ) {
								request.setAttribute("title","削除済み商品 / ALL / 商品一覧");
								request.setAttribute("productList",dao.allStatusProductList());
							
								
								
							} else {
								
											if(  gender == 0 ){
												request.setAttribute("title",  "削除済み商品 / ALL / " + categoryName + "/ 商品一覧");
												request.setAttribute("productList",dao.allStatusCategoryProductList(categoryId));
											
												
											}else {
												request.setAttribute("title", "削除済み商品 / "+ genderStr + "/" + categoryName + "/ 商品一覧");
												request.setAttribute("productList",dao.categoryStatusProductList(categoryId , gender));
											
											}
							}
					}
				
				 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-product-delete-list.jsp");
		   	     dispatcher.forward(request, response);	
		    	
				
				
				
			}
			
			
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
		
		request.setAttribute("gender", gender);
		request.setAttribute("categoryId",categoryId);

		
    	}
    
  
}
