<%@ page import="org.example.Account" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<%
    List<Account> accounts = (List<Account>) request.getAttribute("users");
%>
<table>
    <%
        for (int i = 0; i < accounts.size(); i++) {
    %>
    <tr>
        <td><%= accounts.get(i).getId() %>
        </td>
        <td><%= accounts.get(i).getLogin() %>
        </td>
        <td><%= accounts.get(i).getPassword() %>
        </td>
        <td><%= accounts.get(i).getBirthdate() %>
        </td>
        <td>
            <button onclick="javascript:deleteAccountById(<%= accounts.get(i).getId() %>)">delete</button>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
<script type="text/javascript">
    function deleteAccountById(id) {
        return fetch('http://localhost:8091/accounts/' + id + '/', {
            method: 'DELETE'
        })
    }
</script>
</html>
