<%@ page import="org.example.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<table>
    <%
        for (int i = 0; i < users.size(); i++) {
    %>
    <tr>
        <td><%= users.get(i).getId() %>
        </td>
        <td><%= users.get(i).getLogin() %>
        </td>
        <td><%= users.get(i).getPassword() %>
        </td>
        <td><%= users.get(i).getBirthdate() %>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
