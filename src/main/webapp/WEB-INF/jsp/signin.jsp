<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/home">home</a>
    <h2>Sign In</h2>
    <br/><br/>
    <form action="${pageContext.request.contextPath}/sign_in" method="post">
        <label for="fieldUser">Username:</label><input type="text" id="fieldUser" name="username">
        <label for="fieldPassword">Password:</label><input type="text" id="fieldPassword" name="password">
        <input type="submit" value="Sign In">
    </form>
</body>
</html>
