package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AdminDAO;
import model.dao.SearchDAO;


@WebServlet("/AdminrListServlet")
public class AdminListServlet extends HttpServlet {
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		String searchName = request.getParameter("searchName");
		SearchDAO searchDao = new SearchDAO();
		AdminDAO adminDao = new AdminDAO();
		
		try {
					request.setAttribute("adminList",adminDao.getAllStatusAdminList()); //ユーザー一覧
					
					 if (searchName != null && !searchName.isEmpty()) {
						//検索がある場合、検索機能を使用
						request.setAttribute("searchAdmins", searchDao.searchStatusAdminList(searchName));
						request.setAttribute("title", searchName + "の検索結果");				
						
					} else {
								request.setAttribute("title","管理者一覧表示");
					}
			 
		} catch (SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-list.jsp");
   	     dispatcher.forward(request, response);	
    	
    	}
}
