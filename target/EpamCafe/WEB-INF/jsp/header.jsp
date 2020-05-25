<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/25/2020
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<div class="project-bar"><a href="${pageContext.request.contextPath}/home">EPAM Cafe</a></div>
<div class="address-bar">localhost:8080</div>

<nav class="box">
    <ul class="nlist intro-text text-center">
        <li><a href="${pageContext.request.contextPath}/home"><img src="<c:url value="/resources/img/ecafe-home-icon.png"/>" alt="home icon" width="48" height="48" /></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/meals">Meals</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/categories">Meals categories</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/ingredients">Meals ingredients</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/profile"><img src="<c:url value="/resources/img/ecafe-profile-icon.png"/>" alt="profile icon" width="48" height="48" /></a></li>
    </ul>
</nav>
</body>
</html>
