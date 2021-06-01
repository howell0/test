<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>社員情報管理</h1>
<a href="/employeeManager/LoginServlet"><button>ログイン</button></a>
</body>
</html>


<%--

○説明
スッキリわかるJava入門第2版とスッキリわかるサーブレット&JSP入門第2版の2冊のテキストで勉強してきた
ことを活かして簡単な動きのある社員情報管理システムを自分の力で実装することを目的に作成したwebアプリ

○webアプリ名
employeeManager

○開発期間
5日間

○機能
ログイン/ログアウト/ユーザーレベルによる機能制限/情報の閲覧/情報の新規登録/情報の編集/情報の削除

○技術・使用ソフト
Java/Servlet/JSP/html/sql
eclipse/mysql

--%>

<%--
○ユーザーレベルによる機能制限
システム管理者:ユーザーレベル2…社員、ユーザー情報の閲覧、新規登録、編集、削除の全てが可能
管理職:ユーザーレベル1…社員情報の閲覧、新規登録、編集、削除が可能
一般ユーザー:ユーザーレベル0…社員情報の閲覧だけが可能
--%>
