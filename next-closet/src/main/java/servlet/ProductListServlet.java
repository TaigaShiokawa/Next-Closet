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

import model.bean.ProductBean;
import model.dao.ProductDAO;
import model.dao.SearchDAO;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int categoryId = -1;
		int gender = 0;
		String categoryIdStr = request.getParameter("categoryId");
		String genderStr =  request.getParameter("gender");
		
		String searchName = request.getParameter("searchName");
		
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
		
		List<ProductBean> searchProducts = new ArrayList<>();
		
		try {
			request.setAttribute("categoryList", dao.categoryList());
			
			if (searchName != null && !searchName.isEmpty()) {
				//検索がある場合、検索機能を使用
				SearchDAO searchDao = new SearchDAO();
				searchProducts = searchDao.searchProductList(searchName);
				request.setAttribute("searchProducts", searchProducts);
				request.setAttribute("title", searchName + "の検索結果");
			} else {
			
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
			}
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
						+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
		        response.sendRedirect("error.jsp");
		        return;
			} catch(SQLException e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください"
						+ "。問題が続く場合は、お問い合わせより管理者にご連絡ください。");
		        response.sendRedirect("error.jsp");
		        return;
			} catch(Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
						+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
		        response.sendRedirect("error.jsp");
		        return;
			}

		 RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
   	     dispatcher.forward(request, response);
 

	}
	
}
