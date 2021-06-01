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
<h1>社員管理</h1>
<h2>社員登録完了</h2>
<a href="/employeeManager/EmployeeServlet?lvl=<%= loginUser.getLvl() %>"><button>社員管理画面へ</button></a>
</body>
</html>