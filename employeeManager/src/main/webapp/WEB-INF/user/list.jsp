<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean, java.util.*" %>
<%
UserBean loginUser = (UserBean) session.getAttribute("user");
List<UserBean> list = (List<UserBean>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>ユーザー管理</h1>
<h2>ユーザー一覧</h2>

<%if(list != null && list.size() > 0){ %>
<table border="1">
<tr>
<th>ＩＤ</th>
<th>ログインID</th>
<th>氏名</th>
<th>ユーザーレベル</th>
<th>操作</th>
</tr>


<% for(UserBean u:list) { %>
<tr>
<td><%= u.getId() %></td>
<td><%= u.getLogin() %></td>
<td><%= u.getName() %></td>
<td><%= u.getLvl() %></td>

<td><a href="/employeeManager/UserServlet?action=update&id=<%=u.getId()%>">編集</a>
<a href="/employeeManager/UserServlet?action=delete&id=<%=u.getId()%>" onclick="return confirm('id=<%=u.getId()%>を削除してよろしいですか？');">削除</a></td>

</tr>
<% } %>
</table>
<% } %>
<a href="/employeeManager/UserServlet?action=new"><button>新規登録</button></a>
<a href="/employeeManager/LoginServlet?action=menu&lvl=<%= loginUser.getLvl() %>"><button>メニュー画面</button></a>
<a href="/employeeManager/LogoutServlet"><button>ログアウト</button></a>
</body>
</html>