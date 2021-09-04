<%@ page import="org.example.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    User user = (User) request.getAttribute("user");

%>
<head>
    <title>Account <%=  user.getLogin() %></title>
</head>
<body>
    <table>
        <tr>
            <td> <%= user.getLogin() %></td>
            <td> <%= user.getPassword() %></td>
            <td> <%= user.getBirthdate() %></td>
        </tr>
    </table>
</body>
</html>
