<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean" %>
<%
UserBean loginUser = (UserBean) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>メニュー</h1>
<p>
ログインユーザー:<%= loginUser.getName() %><br>
</p>
<ul>
<li><a href="/employeeManager/EmployeeServlet?lvl=<%= loginUser.getLvl() %>">社員管理</a></li>
</ul>
<a href="/employeeManager/LogoutServlet"><button>ログアウト</button></a>
</body>
</html>