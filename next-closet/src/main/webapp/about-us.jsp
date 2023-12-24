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
<link rel="stylesheet" href="css/terms-of-service.css">
<link rel = "stylesheet" href = "css/navbar.css">
<title>会社概要</title>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<main>
		<div class="container">
			<h2>About(会社概要)</h2>
			<h3>next closet...についての説明をする</h3>
			<p>中西の中西による中西のための中西</p>
		</div>
	</main>
	<%@ include file="includes/footer.jsp" %>
</body>
</html>