package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.OrderDAO;

@WebServlet("/AdminOrderHistoryListServlet")
public class AdminOrderHistoryListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
		OrderDAO orderDao = new OrderDAO();
		
		try {	
			request.setAttribute("orderList",orderDao.getAllOrderList()); //ユーザー一覧
		    //オーダーの情報を取得する	 
		} catch (SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("admin-order-history-list.jsp");
   	     dispatcher.forward(request, response);	
    	
    	}
}
