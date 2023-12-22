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
<title>利用規約</title>
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>

	<h2>第1条</h2>
	<p>このウェブサイトの目的と範囲について説明します。</p>

	<h2>第2条</h2>
	<p>ユーザーが遵守すべき基本的なルールについて記述します。</p>

	<h2>第3条</h2>
	<p>プライバシーポリシーと個人情報の取り扱いについて述べます。</p>

	<h2>第4条</h2>
	<p>サービスの利用規約や制限に関する詳細情報。</p>

	<h2>第5条</h2>
	<p>著作権や知的財産権に関する事項。</p>

	<h2>第6条</h2>
	<p>利用規約の違反に対する処置や責任について。</p>

	<h2>第7条</h2>
	<p>利用規約の変更や通知に関する規定。</p>


</body>
</html>