<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% AddressBean loginUserAddress = (AddressBean)request.getSession().getAttribute("userAddress"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("LoginServlet"); %>
<% } %>  
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" href="css/mypage.css"> -->
<link rel="stylesheet" href="css/navbar.css">
<meta charset="UTF-8">
<title>登録ユーザー情報編集</title>

</head>
<body>
 <%@ include file="includes/navbar.jsp" %>  

        <table>
    <tr>
        <td><label for="name">お名前</label></td>
      <td><%=userDao.getUserName() %></td> 
    </tr>
    <tr>
        <td><label for="furigana">フリガナ</label></td>
        <td><%=userDao.getKanaName() %></td>
    </tr>
    <tr>
        <td><label for="postalcode">郵便番号</label></td>
        <td><%=loginUserAddress.getPostCode() %></td>
    </tr>
    <tr>
        <td><label for="address">都道府県</label></td>
        <td><%=loginUserAddress.getPrefectures() %></td>
    </tr>
    <tr>
        <td><label for="city">住所</label></td>
        <td><%=loginUserAddress.getAddress() %></td>
    </tr>
    <tr>
        <td><label for="phone">電話番号</label></td>
        <td><%=userDao.getTelNumber() %></td>
    </tr>
    <tr>
        <td><label for="email">メールアドレス</label></td>
        <td><%=userDao.getEmail() %></td>
    </tr>
</table>

        <p>
            <a href="admin-user-edit.jsp"><button type="submit" class="update">更新</button></a>
            <a href="<!-- デリート先 -->"><button type="submit" class="delete">削除</button></a>
        </p>
        
       


</body>
</html>
