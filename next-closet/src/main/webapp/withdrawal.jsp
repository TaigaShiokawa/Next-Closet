<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bean.*"%>
<%@ page import="junit.model.dao.*"%>
<%
UserBean loginUser = (UserBean) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退会ページ</title>
<link rel="icon" href="image/favicon.png" id="favicon">
<link rel="stylesheet" href="css/withdrawal.css">
<link rel="stylesheet" href="css/navbar.css">
</head>
<body>
	<%@ include file="includes/navbar.jsp"%>
	<main>
		<div class="container">
			<div class="thanks">
				<p>退会手続きを完了しました<br>
				<p>ご利用ありがとうございました<br>
				<p>またのご利用をお待ちしております</p>
			</div>
			<div class=reason>
				<form>
				<label for="feedback" id="feedback">よろしければ退会理由を教えてください</label><br>
				<textarea id="feedback" name="feedback" rows="6" cols="50"></textarea><br>
				<input class="submit" type="reset" value="送信">
				</form>
				<br>
			</div>
	
			<a href="ProductListServlet">商品一覧に戻る</a>
		</main>
		<%@ include file="includes/footer.jsp" %>  
</body>
</html>