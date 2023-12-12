package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.AddressBean;
import model.dao.UserDAO;

/**
 * Servlet implementation class AddressDeleteServlet
 */
@WebServlet("/AddressDeleteServlet")
public class AddressDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    UserDAO uDao = new UserDAO();

	    String[] addAddressIds = request.getParameterValues("addAddressId");
	    if(addAddressIds != null) {
	        for(String addressId : addAddressIds) {
	            int addAddressId = Integer.parseInt(addressId);
	            try {
	                uDao.deleteSubAddress(addAddressId);
	            } catch (ClassNotFoundException | SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        // 削除後に最新のアドレスリストを取得
	        int userId = (int) request.getSession().getAttribute("userId");
	        try {
	            List<AddressBean> addressList = uDao.getSubAddress(userId);
	            request.setAttribute("addressList", addressList);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // 更新されたアドレスリストと共にsub-address.jspにフォワード
	    request.getRequestDispatcher("sub-address.jsp").forward(request, response);
	}

}
