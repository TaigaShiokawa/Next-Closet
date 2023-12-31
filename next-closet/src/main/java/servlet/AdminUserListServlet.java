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
import model.dao.SearchDAO;
import model.dao.UserDAO;


@WebServlet("/AdminUserListServlet")
public class AdminUserListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
         
         if ( admin == null) {
         	response.sendRedirect("AdminLoginServlet");
             return;
         }
         
		String searchName = request.getParameter("searchName");
		SearchDAO searchDao = new SearchDAO();
		UserDAO userDao = new UserDAO();
		String status = request.getParameter("status");
		
		
		try {

			
			//request.setAttribute("userList",userDao.getAllStatusUserList()); ユーザー一覧 dao変わった？
      request.setAttribute("userList",userDao.getAllUserInfo()); //ユーザー一覧
			
			if(status == null) {
				
				if (searchName != null && !searchName.isEmpty()) {
					//検索がある場合、検索機能を使用
					request.setAttribute("searchUsers", searchDao.searchStatusUserList(searchName));
					request.setAttribute("title", "ユーザー一覧 / " + searchName + " \"の検索結果<br><a class=\"list\" href=\"AdminUserListServlet\">一覧表示する</a>");				
					
				} else {
							request.setAttribute("title","ユーザー一覧表示");
				}
				
				 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-user-list.jsp");
			     dispatcher.forward(request, response);	
				
				
				
			} else if(status.equals("0")) { //削除済み
				
				if (searchName != null && !searchName.isEmpty()) {
					//検索がある場合、検索機能を使用
					request.setAttribute("searchUsers", searchDao.searchStatusUserList(searchName));
					request.setAttribute("title", "削除済みユーザー一覧 / " + searchName + " \"の検索結果<br><a class=\"list\" href=\"AdminUserListServlet?status=0\">一覧表示する</a>");				

				

					
				} else {
							request.setAttribute("title","削除済みユーザー一覧表示");
				}
				
				 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-user-delete-list.jsp");
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


//　管理者側ユーザーリストサーブレット
//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    
//	String searchName = request.getParameter("searchName");
//	SearchDAO searchDao = new SearchDAO();
//	UserDAO userDao = new UserDAO();
//	
//	try {
//				request.setAttribute("userList",userDao.getAllStatusUserList()); //ユーザー一覧
//				
//				 if (searchName != null && !searchName.isEmpty()) {
//					 System.out.println(searchName);
//					//検索がある場合、検索機能を使用
//					request.setAttribute("searchUsers", searchDao.searchStatusUserList(searchName));
//					request.setAttribute("title", "ユーザー一覧 / " + searchName + "の検索結果");				
//					
//				} else {
//							request.setAttribute("title","ユーザー一覧表示");
//				}
//		 
//	} catch(ClassNotFoundException e) {
//		e.printStackTrace();
//		request.getSession().setAttribute("errorMessageToAdmin", "内部の設定エラーが発生しました。"
//				+ "早急に対応してください。");
//        response.sendRedirect("errorToAdmin.jsp");
//        return;
//	} catch (SQLException e) {
//		e.printStackTrace();
//		request.getSession().setAttribute("errorMessageToAdmin", "データベースにアクセスできません。"
//				+ "早急に対応してください。");
//		response.sendRedirect("errorToAdmin.jsp");
//		return;
//	}
//	
//	 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-user-list.jsp");
//	     dispatcher.forward(request, response);	
//	
//	}