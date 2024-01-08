<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.dto.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全てのTodo</title>
</head>
<body>
<!-- UserID 確認 -->
<% Integer userId = (Integer)request.getSession().getAttribute("userId"); %>
<% if(userId != null) { %>
<p><%=userId %></p>
<% } %>

<!-- Todo一覧 -->



	<!-- Todo追加 -->
	<form action="AddTodoServlet" method="post">
	
		<label for="title">タイトル：</label>
		<input type="text" id="title" name="title" required><br>
		
		<label for="todo">Todo：</label>
		<input type="text" id="todo" name="todo" required><br>
		
		<label>ステータス：</label>
		<select name="status">
		<option value="未完了">未完了</option>
		<option value="実行中">実行中</option>
		<option value="完了">完了</option>
		</select>
		
		<label for="start">いつまで？：</label>
		<input type="date" id="start" name="createdAt">
		
		<button type="submit">追加</button>
	</form>

</body>
</html>