<%@ page import="org.example.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Account account = (Account) request.getAttribute("account");

%>
<head>
    <title>Account <%=  account.getLogin() %></title>
</head>
<body>
    <table>
        <tr>
            <td> <%= account.getLogin() %></td>
            <td> <%= account.getPassword() %></td>
            <td> <%= account.getBirthdate() %></td>
        </tr>
    </table>
</body>
</html>
