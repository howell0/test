<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>ログイン</h1>
<form action="/employeeManager/LoginServlet" method="post">
ログイン名:<input type="text" name="login"><br>
パスワード:<input type="password" name="pwd"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>