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
    <title>Sign In</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/authorizationheader.jsp"/>
<div class="box">
    <hr>
    <h2 class="intro-text text-center">Sign <strong>In</strong></h2>
    <hr>
    <br>
    <form class="intro-text text-center" action="${pageContext.request.contextPath}/sign_in" method="post">
        <label for="fieldUser">Username: </label><input type="text" id="fieldUser" name="username">
        <label for="fieldPassword">Password: </label><input type="text" id="fieldPassword" name="password">
        <input class="signbutt" type="submit" value="Sign In">
    </form>
</div>
</body>
</html>
