

package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.OrderDAO;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userId = (int) request.getSession().getAttribute("userId");
		OrderDAO dao = new OrderDAO();
		try {
			request.setAttribute("orderList", dao.orderList(userId));
			request.getRequestDispatcher("order-history.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
	}
}

			
		
		
	
		

	