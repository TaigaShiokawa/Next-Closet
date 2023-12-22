<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bean.*"%>
<%
UserBean loginUser = (UserBean) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<h1>About(会社概要)</h1>

	<h2>next closet...についての説明をする</h2>
	<p>中西の中西による中西のための中西</p>
	
	<%@ include file="includes/footer.jsp" %>
</body>
</html>