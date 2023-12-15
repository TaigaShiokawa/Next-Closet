package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;

@WebServlet("/ProductCategoriesSevlet")
public class ProductCategoriesSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		try {
			request.setAttribute("categoryList", categoryDao.getCategoryList()); //カテゴリーの全て
		} catch ( SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("product-categories.jsp");
   	     dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDAO categoryDao = new CategoryDAO();
		String delete = request.getParameter("delete");
		String add = request.getParameter("add");
		String addCategoryName = request.getParameter("AddCategoryName");
		
		try {
			request.setAttribute("categoryList", categoryDao.getCategoryList());
		} catch ( SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		try {
			if( delete != null ) {
				if(categoryDao.getNoProductCategory(delete)) {  // もし今このカテゴリーに属する商品がなければ
					categoryDao.deleteCategory(delete); //削除
				} else {
					request.setAttribute("message","このカテゴリーに属する商品があるので削除できません");
				}

	
			}
		} catch ( SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		if( add != null ) { //新規登録していたら
				
			try {
				if(categoryDao.getCategory(addCategoryName)){
					categoryDao.addCategory(addCategoryName);
					request.setAttribute("message","カテゴリーに" + addCategoryName + "を追加しました");
					
				} else {
					
					request.setAttribute("message","既に存在しているカテゴリー名です");
					
				}
				
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} //新規登録終了
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-categories.jsp");
   	    dispatcher.forward(request, response);
	
	}

}
