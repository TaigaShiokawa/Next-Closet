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
<title>利用規約</title>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<main>
	<div class="container">
		<h2>利用規約</h2>
	
		<h3>第1条</h3>
		<p>このウェブサイトの目的と範囲について説明します。</p>
	
		<h3>第2条</h3>
		<p>ユーザーが遵守すべき基本的なルールについて記述します。</p>
	
		<h3>第3条</h3>
		<p>プライバシーポリシーと個人情報の取り扱いについて述べます。</p>
	
		<h3>第4条</h3>
		<p>サービスの利用規約や制限に関する詳細情報。</p>
	
		<h3>第5条</h3>
		<p>著作権や知的財産権に関する事項。</p>
	
		<h3>第6条</h3>
		<p>利用規約の違反に対する処置や責任について。</p>
	
		<h3>第7条</h3>
		<p>利用規約の変更や通知に関する規定。</p>
	</div>
	</main>
	<%@ include file="includes/footer.jsp" %>

</body>
</html>