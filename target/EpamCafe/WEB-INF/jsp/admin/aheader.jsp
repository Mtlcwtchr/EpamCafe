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

<div class="project-bar"><a href="${pageContext.request.contextPath}/home">JCafe</a></div>
<div class="address-bar">localhost:8080</div>

<nav class="box">
    <ul class="nlist intro-text text-center">
        <li><a href="${pageContext.request.contextPath}/home">
            <img src="${pageContext.servletContext.contextPath}/load_image?url=/ecafe/media/pictures/ecafe-home-icon.png" alt="home icon" width="48" height="48"/>
        </a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/meals">Блюда</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/categories">Категории</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/aingredients">Ингредиенты</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/halls">Залы</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/aclients">Клиенты</a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/profile">
            <img src="${pageContext.servletContext.contextPath}/load_image?url=/ecafe/media/pictures/ecafe-profile-icon.png" alt="profile icon" width="48" height="48"/>
        </a></li>
    </ul>
</nav>
</body>
</html>
