<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.dto.*" %>
<%@ page import="model.dao.*" %>
<% UserDTO user = (UserDTO)request.getSession().getAttribute("user"); %>

<ul>
<% if(user != null) { %>
<li><a href="Logout">logout</a></li>
<% } else { %>
<li><a href="Register">新規登録</a></li>
<% } %>
</ul>