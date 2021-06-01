<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>ユーザー管理</h1>
<h2>ユーザー新規登録</h2>
<form action="/employeeManager/UserServlet" method="post">
ログイン名:<input type="text" name="login"><br>
ユーザーレベル:<input type="number" name="lvl" min="0" max="2"><br>
名前:<input type="text" name="name"><br>
パスワード:<input type="password" name="pwd"><br>
<input type="submit" value="新規登録">
<button type="button" onclick="history.back()">戻る</button>
</form>
</body>
</html>