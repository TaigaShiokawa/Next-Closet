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
import model.dao.OrderDAO;

@WebServlet("/AdminOrderHistoryDetailServlet")
public class AdminOrderHistoryDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
   	 AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
     
     if ( admin == null) {
     	response.sendRedirect("AdminLoginServlet");
         return;
     }
     
		OrderDAO orderDao = new OrderDAO();
		String orderItemIdStr = (String)request.getParameter("orderItemId");
		int orderItemId = Integer.parseInt(orderItemIdStr);
		
		try {	
			request.setAttribute("orderList",orderDao.getOrderDetailList(orderItemId)); //ユーザー一覧
		    //オーダーの情報を取得する	 
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
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-order-history-detail.jsp");
   	     dispatcher.forward(request, response);	
    	
    	}
}
