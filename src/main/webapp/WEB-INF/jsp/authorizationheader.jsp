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
    <title>Авторизация</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<div class="project-bar"><a href="${pageContext.request.contextPath}/home">Кафе <strong>КриссКросс</strong></a></div>
<div class="address-bar">localhost:8080</div>

<nav class="box">
    <ul class="nlist intro-text text-center">
        <li><a href="${pageContext.request.contextPath}/sign_in">Войти</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/sign_up">Зарегестрироваться</a></li>
    </ul>
</nav>
</body>
</html>
