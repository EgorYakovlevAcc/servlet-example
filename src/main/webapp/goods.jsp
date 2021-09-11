<%@ page import="org.example.Good" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online shop</title>
</head>
<body>
<%
    List<Good> goods = (List<Good>) request.getAttribute("goods");
%>
<table>
    <%
        for (int i = 0; i < goods.size(); i++) {
    %>
    <tr>
        <td><%= goods.get(i).getId() %>
        </td>
        <td><%= goods.get(i).getName() %>
        </td>
        <td><%= goods.get(i).getPrice() %>
        </td>
        <td>
            <button onclick="javascript:buyGood(<%= goods.get(i).getId() %>)">buy</button>
        </td>
    </tr>
    <%
        }
    %>
</table>
<table>
    <%
        List<org.example.session.Good> sessionGoods = ((List<org.example.session.Good>) session.getAttribute("goods"));
        if (sessionGoods != null) {
            if (sessionGoods.size() > 0) {
                for (int i = 0; i < sessionGoods.size(); i++) {
    %>
    <tr>
        <td><%= sessionGoods.get(i).getId() %>
        </td>
        <td><%= sessionGoods.get(i).getAmount() %>
        </td>
    </tr>
    <%
                }
            }
        }
    %>

</table>
</body>
<script type="text/javascript">
    function buyGood(id) {
        return fetch('http://localhost:8091/goods/', {
            method: 'POST',
            body: JSON.stringify({id: id, increment: 1})
        })
    }
</script>
</html>
