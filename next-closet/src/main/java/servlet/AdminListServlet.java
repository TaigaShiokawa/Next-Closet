package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AdminBean;
import model.dao.AdminDAO;
import model.dao.SearchDAO;


@WebServlet("/AdminListServlet")
public class AdminListServlet extends HttpServlet {
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
   	 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
     
     if ( admin == null) {
     	response.sendRedirect("AdminLoginServlet");
         return;
     }
     
		String searchName = request.getParameter("searchName");
		SearchDAO searchDao = new SearchDAO();
		AdminDAO adminDao = new AdminDAO();
		String status = request.getParameter("status");
		
		try {
			
			
					request.setAttribute("adminList",adminDao.getAllStatusAdminList()); //ユーザー一覧
					
					if(status == null) {
					
							 if (searchName != null && !searchName.isEmpty()) {
								//検索がある場合、検索機能を使用
						        request.setAttribute("searchAdmins", searchDao.searchStatusAdminList(searchName));
								request.setAttribute("title", "管理者一覧 / " + searchName + "の検索結果<br><a class=\"list\" href=\"AdminListServlet\">一覧表示する</a>");				
								
							} else {
										request.setAttribute("title","管理者一覧表示");
							}
							 
							 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-list.jsp");
					   	     dispatcher.forward(request, response);	
					   	     
					} else if(status.equals("0")) {  //削除済み
						
						 if (searchName != null && !searchName.isEmpty()) {
								//検索がある場合、検索機能を使用
						        request.setAttribute("searchAdmins", searchDao.searchStatusAdminList(searchName));
								request.setAttribute("title", "削除済み管理者一覧 / " + searchName + "の検索結果<br><a class=\"list\" href=\"AdminListServlet?status=0\">一覧表示する</a>");				
								
							} else {
										request.setAttribute("title","削除済み管理者一覧表示");
							}
							 
							 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-delete-list.jsp");
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
		} 
		
		 
    	
    	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String searchName = request.getParameter("searchName");
		SearchDAO searchDao = new SearchDAO();
		AdminDAO adminDao = new AdminDAO();
		String status = request.getParameter("status");
		
		try {
			
			
					request.setAttribute("adminList",adminDao.getAllStatusAdminList()); //ユーザー一覧
					
					if(status == null) {
					
							 if (searchName != null && !searchName.isEmpty()) {
								//検索がある場合、検索機能を使用
						        request.setAttribute("searchAdmins", searchDao.searchStatusAdminList(searchName));
								request.setAttribute("title", "管理者一覧 / " + searchName + "の検索結果<br><a class=\"list\" href=\"AdminListServlet\">一覧表示する</a>");				
								
							} else {
										request.setAttribute("title","管理者一覧表示");
							}
							 
							 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-list.jsp");
					   	     dispatcher.forward(request, response);	
					   	     
					} else if(status.equals("0")) {  //削除済み
						
						 if (searchName != null && !searchName.isEmpty()) {
								//検索がある場合、検索機能を使用
						        request.setAttribute("searchAdmins", searchDao.searchStatusAdminList(searchName));
								request.setAttribute("title", "削除済み管理者一覧 / " + searchName + "の検索結果<br><a class=\"list\" href=\"AdminListServlet?status=0\">一覧表示する</a>");				
								
							} else {
										request.setAttribute("title","削除済み管理者一覧表示");
							}
							 
							 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-delete-list.jsp");
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
		} 
		
		 
    	
    	}
}
