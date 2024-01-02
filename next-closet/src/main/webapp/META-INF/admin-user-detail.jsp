<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.bean.*" %>
<%@ page import="model.dao.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%@ include file="includes/navbar.jsp" %>
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
    </tr>
    <tr>
        <td><label for="phone">電話番号</label></td>
        <td><%=loginUser.getTelNumber() %></td>
    </tr>
    <tr>
        <td><label for="email">メールアドレス</label></td>
        <td><%=loginUser.getEmail() %></td>
    </tr>
    <tr>
        <td><label for="password">パスワード</label></td>
        <td><%=loginUser.getEmail() %></td>
    </tr>
</table>

        <p>
            <a href="mypage-edit.jsp"><button type="submit" class="update">更新</button></a>
            <a href="OrderHistoryServlet"><button type="submit" class="delete">削除</button></a>
        </p>

</body>
</html>



</body>
</html>