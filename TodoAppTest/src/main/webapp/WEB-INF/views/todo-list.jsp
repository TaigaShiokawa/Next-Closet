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
<title>全てのTodo</title>
</head>
<body>
<jsp:include page="/includes/navbar.jsp" />
<!-- UserID 確認 -->
<% Integer userId = (Integer)request.getSession().getAttribute("userId"); %>
<% if(userId != null) { %>
<p><%=userId %></p>
<% } %>

<!-- Todo一覧 -->
<% List<TodoDTO> todoList = (List<TodoDTO>)request.getAttribute("todoList"); %>
<% String notTodo = (String)request.getAttribute("notTodo"); %>
<% if(todoList == null || todoList.isEmpty()) { %>
<p><%=notTodo %></p>
<% } else { %>
	<% for(TodoDTO todos : todoList) { %>
	<p>タイトル：<%=todos.getTitle() %></p>
	<p>Todo：<%=todos.getTodo() %></p>
	<p>ステータス：<%=todos.getStatus() %>：<%=todos.getTime_limit() %>まで</p>
	<a href="EditTodo?todoId=<%=todos.getId() %>">編集</a>
	<a href="DeteleTodo?todoId=<%=todos.getId() %>">削除</a>
	<hr>
	<% } %>
<% } %>




	<!-- Todo追加 -->
	<form action="AddTodo" method="post">
	
		<label for="title">タイトル：</label>
		<input type="text" id="title" name="title" required><br>
		
		<label for="todo">Todo：</label>
		<input type="text" id="todo" name="todo" required><br>
		
		<label>ステータス：</label>
		<select name="status">
		<option value="未完了">未完了</option>
		<option value="実行中">実行中</option>
		<option value="完了">完了</option>
		</select><br>
		
		<label for="timeLimit">いつまで？：</label>
		<input type="date" id="timeLimit" name="timeLimit"><br>
		
		<button type="submit">追加</button>
	</form>
	
	<form action="NewDate" method="post">
	<button type="submit">日にちが新しいTodo</button>
	</form>
	
	<form action="OldDate" method="post">
	<button type="submit">日にちが古いTodo</button>
	</form>
	
	<form action="NoCompleteTodo" method="post">
	<button type="submit">未完了のTodo</button>
	</form>
	
	<form action="CompleteTodo" method="post">
	<button type="submit">完了済みのTodo</button>
	</form>

</body>
</html>