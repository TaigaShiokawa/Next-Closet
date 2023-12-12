<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %> --%>
<%-- <% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <%@ include file="includes/navbar.jsp" %> --%>
<div class=thanks>
<p>退会手続きを完了しました</p>
<p>ご利用ありがとうございました</p>
<p>またのご利用をお待ちしております</p>
</div>
<div class=reason>
<label for="feedback" id="feedback">よろしければ退会理由を教えてください</label><br>
<textarea id="feedback" name="feedback" rows="4" cols="50"></textarea><br>
</div>

<a href="ProductListServlet">商品一覧に戻る</a>
</body>
</html>