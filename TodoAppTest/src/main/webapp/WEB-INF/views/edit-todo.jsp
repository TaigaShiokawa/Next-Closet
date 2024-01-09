<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.dto.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.*" %>
<% UserDTO user = (UserDTO)request.getSession().getAttribute("user"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todoの詳細</title>
</head>
<body>
<jsp:include page="/includes/navbar.jsp" />

	<% TodoDTO todoDetails = (TodoDTO)request.getAttribute("todoDetails"); %>
	<% String noTodoDetails = (String)request.getAttribute("noTodoDetails"); %>
	<% if(todoDetails == null) { %>
	<p><%=noTodoDetails %></p>
	<% } else { %>
	
	<form action="EditTodo" method="post">
		<label for="title">タイトル：</label>
		<input type="text" id="title" name="title" value="<%=todoDetails.getTitle() %>"><br>
		
		<label for="todo">Todo：</label>
		<input type="text" id="todo" name="todo" value="<%=todoDetails.getTodo() %>"><br>
		
		<!-- optionを、すでに選択されているようにする -->
		<label>ステータス</label>
		<select name="status">
		<option value="<%=todoDetails.getStatus() %>"><%=todoDetails.getStatus() %></option>
		<option value="未完了">未完了</option>
		<option value="実行中">実行中</option>
		<option value="完了">完了</option>
		</select><br>
		
		<label for="timeLimit">いつまで？：</label>
		<input type="date" id="timeLimit" name="timeLimit" value="<%=todoDetails.getTime_limit() %>"><br>
		
		<input type="hidden" name="id" value="<%=todoDetails.getId() %>">
		
		<button type="submit">編集する</button>
	</form>
<% } %>
</body>
</html>