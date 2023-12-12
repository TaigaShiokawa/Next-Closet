<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/mypage.css">
<meta charset="UTF-8">
<title>フォーム</title>

</head>
<body>
 <%@ include file="includes/navbar.jsp" %>  

    <h2>マイページ</h2>
        <table>
    <tr>
        <td><label for="name">お名前</label></td>
      <td><%=loginUser.getUserName() %></td> 
    </tr>
    <tr>
        <td><label for="furigana">フリガナ</label></td>
        <td><!-- Javaでフリガナを表示 --></td>
    </tr>
    <tr>
        <td><label for="postalcode">郵便番号</label></td>
        <td><!-- Javaで郵便番号を表示 --></td>
    </tr>
    <tr>
        <td><label for="address">都道府県</label></td>
        <td><!-- Javaで都道府県を表示 --></td>
    </tr>
    <tr>
        <td><label for="city">住所</label></td>
        <td><!-- Javaで市区町村を表示 --></td>
    </tr>
    <tr>
        <td><label for="phone">電話番号</label></td>
        <td><!-- Javaで電話番号を表示 --></td>
    </tr>
    <tr>
        <td><label for="email">メールアドレス</label></td>
        <td><!-- Javaでメールアドレスを表示 --></td>
    </tr>
</table>

        <p>
            <a href="mypage-edit.jsp"><button type="submit" class="one-button">変更</button></a>
            <a href="order-history.jsp"><button type="submit" class="twe-button">購入履歴</button></a>
        </p>
        
        <a href="product-list.jsp" class="one-a">商品一覧へ戻る</a>

</body>
</html>
