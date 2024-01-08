<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.dto.*" %>
<%@ page import="model.dao.*" %>
<% UserBean user = (UserBean)request.getSession().getAttribute("user"); %>

<ul>
<% if(user != null) { %>
<li><a href="#">日にちが新しいTodo</a></li>
<li><a href="#">日にちが古いTodo</a></li>
<li><a href="#">未完了のTodo</a></li>
<li><a href="#">完了済みのTodo</a></li>
<li><a href="#">logout</a></li>
<% } else { %>
<li><a href="#">新規登録</a></li>
<% } %>
</ul>