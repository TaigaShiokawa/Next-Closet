<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<!-- Icons by Bootstrap (https://icons.getbootstrap.com/) - licensed under MIT (https://opensource.org/licenses/MIT)  -->
   	<%AdminBean admin = (AdminBean)request.getSession().getAttribute("admin"); 
     
     if ( admin == null) {
     	response.sendRedirect("AdminLoginServlet");
         return;
     }
     %> 
     
<header>
	<div class="header_inner">
		<h1 id="site_title"><a href='AdminProductListServlet'>next closet... 管理者画面</a></h1>
		<div class="header_right">
			<ul>
				<li><a href = "AdminLogoutServlet">ログアウト</a></li>
				<li><a href = "AdminProductListServlet">商品</a></li>
				<li><a href = "AdminUserListServlet">ユーザー</a></li>
				<li><a href = "AdminListServlet">管理者</a></li>
				<li><a href = "AdminOrderHistoryListServlet">通知</a></li>
			</ul>
		</div>
	</div>
</header>