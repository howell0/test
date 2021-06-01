<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*, java.util.*" %>
<%
UserBean loginUser = (UserBean) session.getAttribute("user");
List<EmployeeBean> list = (List<EmployeeBean>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body>
<h1>社員管理</h1>
<h2>社員一覧</h2>

<%if(list != null && list.size() > 0){ %>
<table border="1">
<tr>
<th>ＩＤ</th>
<th>氏名</th>
<th>郵便番号</th>
<th>住所1</th>
<th>住所2</th>
<th>電話番号</th>
<th>FAX</th>
<th>メールアドレス</th>
<th>生年月日</th>
<th>部署</th>
<th>役職</th>
<th>操作</th>
</tr>


<% for(EmployeeBean e:list) { %>
<tr>
<td><%= e.getId() %></td>
<td><%= e.getName() %></td>
<td><%= e.getZip() %></td>
<td><%= e.getAddress1() %></td>
<td><%= e.getAddress2() %></td>
<td><%= e.getTel() %></td>
<td><%= e.getFax() %></td>
<td><%= e.getEmail() %></td>
<td><%= e.getBirthday() %></td>
<td><%= e.getDpt() %></td>
<td><%= e.getPost() %></td>

<td><a href="/employeeManager/EmployeeServlet?action=update&id=<%=e.getId()%>">編集</a>
<a href="/employeeManager/EmployeeServlet?action=delete&id=<%=e.getId()%>" onclick="return confirm('id=<%=e.getId()%>を削除してよろしいですか？');">削除</a></td>

</tr>
<% } %>
</table>
<% } %>
<a href="/employeeManager/EmployeeServlet?action=new"><button>新規登録</button></a>
<a href="/employeeManager/LoginServlet?action=menu&lvl=<%= loginUser.getLvl() %>"><button>メニュー画面へ</button></a>
<a href="/employeeManager/LogoutServlet"><button>ログアウト</button></a>
</body>
</html>