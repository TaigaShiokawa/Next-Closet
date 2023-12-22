package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;


@WebServlet("/AdminUserEditServlet")
public class AdminUserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("NewFile.jsp");
//        dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	
	//パラメータを取得
	String userName = request.getParameter("username");
	String kanaName = request.getParameter("kananame");
	String postCode = request.getParameter("postcode");
	String prefectures = request.getParameter("prefectures");
	String address = request.getParameter("address");
	String telNumber = request.getParameter("telnumber");
	String email = request.getParameter("email");
	int userId = Integer.parseInt(request.getParameter("userId"));
		
	// データベース内のユーザー情報を更新
     try {
         UserDAO uDao = new UserDAO();
         int userUpdate = uDao.loginUserUpdate(userName, kanaName, telNumber, email, userId);
         int addressUpdate = uDao.loginUserAddressUpdate(postCode, prefectures, address, userId);
         
//         request.getSession().setAttribute("user", userUpdate);
//			request.getSession().setAttribute("userAddress", addressUpdate);
         
         request.setAttribute("user", userUpdate);
			request.setAttribute("userAddress", addressUpdate);
			

         if (userUpdate > 0 || addressUpdate > 0) {
             // 更新成功時は成功ページにリダイレクト
        	 String forward = "/AdminUserDetailServlet?userId=" + userId;
             RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
             dispatcher.forward(request, response);
             
//        	 System.out.println("成功");             
         } else {
             // 更新失敗時はエラーページにリダイレクト
             response.sendRedirect("admin-user-edit.jsp");
//        	 System.out.println("失敗");

         }
     } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace(); 
         System.out.println("エラーが発生しました: " + e.getMessage());
     }

		
	}

}
