<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Login, model.Account" %>
<%
Login l = (Login) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スッキリ商店</title>
</head>
<body>
<P>ようこそ<%= l.getUserId() %>さん</P>
<a href="/sukkiriShop/WelcomeServlet">トップへ</a>
</body>
</html>