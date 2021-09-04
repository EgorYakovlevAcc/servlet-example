<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form method="post" action="/accounts/">
        Login: <input name="login" />
        <br>
        Password: <input type="password" name="password" />
        <br>
        Birthday: <input type="date" name="birthdate" />
        <br>
        <input type="submit" value="OK" />
    </form>
</body>
</html>
