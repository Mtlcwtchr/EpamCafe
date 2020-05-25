<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h2>Hello ${pageContext.request.getSession(true).getAttribute("actor").getName()}</h2>
    <p>This is home page</p>
    <a href="${pageContext.request.contextPath}/meals">meals</a>
    <a href="${pageContext.request.contextPath}/ingredients">ingredients</a>
</body>
</html>
