<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.EmployeeBean, java.util.*" %>
<%
EmployeeBean emp = (EmployeeBean) request.getAttribute("eb");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>社員管理</h1>
<h2>社員編集</h2>
<form action="/employeeManager/EmployeeServlet?action=update&id=<%= emp.getId()%>" method="post">
氏名:<input type="text" name="name" value="<%= emp.getName() %>"><br>
郵便番号:<input type="text" name="zip" value="<%= emp.getZip() %>"><br>
住所1:<input type="text" name="address1" value="<%= emp.getAddress1() %>"><br>
住所2:<input type="text" name="address2" value="<%= emp.getAddress2() %>"><br>
電話番号:<input type="text" name="tel" value="<%= emp.getTel() %>"><br>
FAX:<input type="text" name="fax" value="<%= emp.getFax() %>"><br>
メールアドレス:<input type="text" name="email" value="<%= emp.getEmail() %>"><br>
生年月日:<input type="text" name="birthday" value="<%= emp.getBirthday() %>"><br>
部署:<select name="dpt">
		<option>総務部</option>
		<option>営業部</option>
		<option>開発部</option>
	</select><br>
役職:<select name="post">
		<option>社長</option>
		<option>部長</option>
		<option>次長</option>
		<option>課長</option>
		<option>係長</option>
		<option>一般社員</option>
	</select><br>
<input type="submit" value="更新">
<button type="button" onclick="history.back()">戻る</button>
</form>
</body>
</html>