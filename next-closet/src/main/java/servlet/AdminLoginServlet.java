package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hashedPassword.HashPW;
import model.bean.AdminBean;
import model.dao.AdminDao;


@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String view = "/WEB-INF/view/admin-login.jsp";
    	request.getRequestDispatcher(view).forward(request, response);
    	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        String errmessege = "";
        
  	  if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
		    errmessege = "※メールアドレスまたはパスワードが正しくありません";
		    request.setAttribute("errmessge", errmessege);
          String view = "/WEB-INF/view/admin-login.jsp";
          request.getRequestDispatcher(view).forward(request, response);
          return; 
  	  }
  	  
  	 String hashpass = null;
	  try {
		 hashpass = HashPW.hashPass(password);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
          
          AdminDao adminDao = new AdminDao();
          AdminBean adminBean = new AdminBean();
          
          adminBean.setEmail(email);
          adminBean.setPassword(hashpass);
          
          try {
			  if(adminDao.validate(email, hashpass)) {
					String view ="/WEB-INF/view/admin-product-list.jsp";
				    request.getRequestDispatcher(view).forward(request, response);
			  }else {
					String view ="/WEB-INF/view/admin-login.jsp";
				    request.getRequestDispatcher(view).forward(request, response);
			  }
		  }catch(Exception e) {
			  e.printStackTrace();
		  }

      }
	}
