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

@WebServlet("/ProductCategoriesServlet")
public class ProductCategoriesSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDAO categoryDao = new CategoryDAO();
		
		try {
			request.setAttribute("categoryList", categoryDao.getCategoryList()); //カテゴリーの全て
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
		}
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("product-categories.jsp");
   	     dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		CategoryDAO categoryDao = new CategoryDAO();
		String delete = request.getParameter("delete");
		String add = request.getParameter("add");
		String addCategoryName = request.getParameter("AddCategoryName");
		
		System.out.println(addCategoryName);
		
		try {
			if( delete != null ) {
				int categoryId = Integer.parseInt(delete);
				System.out.println(categoryId);
				if(categoryDao.getNoProductCategory(categoryId)) {  // もし今このカテゴリーに属する商品がなければ
					categoryDao.deleteCategory(categoryId); //削除
				} else {
					request.setAttribute("message","このカテゴリーに属する商品があるので削除できません");
				}
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
		}
		
		if( add != null ) { //新規登録していたら
				
			try {
				if(categoryDao.getCategory(addCategoryName)){
					categoryDao.addCategory(addCategoryName);
					request.setAttribute("message","カテゴリーに" + addCategoryName + "を追加しました");	
				} else {
					request.setAttribute("message","既に存在しているカテゴリー名です");
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
			}
			
		} //新規登録終了
		
		try {
			request.setAttribute("categoryList", categoryDao.getCategoryList());
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
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-categories.jsp");
   	    dispatcher.forward(request, response);
	
	}

}
