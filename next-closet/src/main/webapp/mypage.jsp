<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<% UserBean loginUser = (UserBean)request.getSession().getAttribute("user"); %>
<% AddressBean loginUserAddress = (AddressBean)request.getSession().getAttribute("userAddress"); %>
<% if(loginUser == null) { %>
<% response.sendRedirect("product-list.jsp"); %>
<% } %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/navbar.css">
<meta charset="UTF-8">
<title>マイページ</title>

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
        <td><%=loginUser.getKanaName() %></td>
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
        <td><a href="SubAddressServlet">その他の住所</a></td> <!-- サブ住所の追加 SubAddressServletへ -->
    </tr>
    <tr>
        <td><label for="phone">電話番号</label></td>
        <td><%=loginUser.getTelNumber() %></td>
    </tr>
    <tr>
        <td><label for="email">メールアドレス</label></td>
        <td><%=loginUser.getEmail() %></td>
    </tr>
</table>

        <p>
            <a href="mypage-edit.jsp"><button type="submit" class="one-button">変更</button></a>
            <a href="OrderHistoryServlet"><button type="submit" class="twe-button">購入履歴</button></a>
        </p>
        
        <a href="product-list.jsp" class="one-a">商品一覧へ戻る</a>
        
         <a href="WithdrawalServlet?userId=<%=loginUser.getUserId()%>">退会はこちら</a>


</body>
</html>
