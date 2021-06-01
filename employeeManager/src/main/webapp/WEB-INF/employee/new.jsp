<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>社員管理</h1>
<h2>社員新規登録</h2>
<form action="/employeeManager/EmployeeServlet" method="post">
氏名:<input type="text" name="name" placeholder="例:山田太郎"><br>
郵便番号:<input type="text" name="zip" placeholder="例:123-4567"><br>
住所1:<input type="text" name="address1"><br>
住所2:<input type="text" name="address2"><br>
電話番号:<input type="text" name="tel" placeholder="例:080-1234-5678"><br>
FAX:<input type="text" name="fax"><br>
メールアドレス:<input type="text" name="email" placeholder="例:abc@example.com"><br>
生年月日:<input type="text" name="birthday" placeholder="例:1980-04-01"><br>
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
<input type="submit" value="新規登録">
<button type="button" onclick="history.back()">戻る</button>
</form>
</body>
</html>