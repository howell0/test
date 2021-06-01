<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean, java.util.*" %>
<%
UserBean user = (UserBean) request.getAttribute("ub");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>ユーザー管理</h1>
<h2>ユーザー編集</h2>
<form action="/employeeManager/UserServlet?action=update&id=<%= user.getId()%>" method="post">
ログイン名:<input type="text" name="login" value="<%= user.getLogin() %>"><br>
ユーザーレベル:<input type="number" name="lvl" min="0" max="2" value="<%= user.getLvl() %>"><br>
名前:<input type="text" name="name" value="<%= user.getName() %>"><br>
パスワード:<input type="password" name="pwd" value="<%= user.getPwd() %>"><br>
<input type="submit" value="更新">
<button type="button" onclick="history.back()">戻る</button>
</form>
</body>
</html>