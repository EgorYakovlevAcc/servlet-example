<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Index.jsp example</title>
  </head>
  <body>
  Welcome! <br/>
  Let's check <%= 2 + 2%> = 4 ? <br/>
  <%
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    String time = simpleDateFormat.format(date);
  %>
  <h2><%= time %></h2>
  </body>
</html>
