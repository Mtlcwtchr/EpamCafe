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
    <title>Welcome</title>
</head>
<body>
    <h2>Welcome, Guest</h2>
    <br/><br/>
    <form action="${pageContext.request.contextPath}/join" method="post">
    <input type="submit" value="Join us">
</form>
</body>
</html>
