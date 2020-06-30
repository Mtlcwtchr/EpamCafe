<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/25/2020
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Header</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div align="left"> <details>
    <summary> Language </summary>
    <a class="invis-ref" href="?locale=en">
        EN
    </a>
    <a class="invis-ref" href="?locale=by">
        BY
    </a>
    <a class="invis-ref" href="?locale=ru">
        RU
    </a>
</details></div>
<div class="project-bar"><a href="${pageContext.request.contextPath}/home">Epam <strong>Cafe</strong></a></div>

<nav class="box">
    <ul class="nlist intro-text text-center">
        <li><a href="${pageContext.request.contextPath}/home">
            <img src="${pageContext.servletContext.contextPath}/load_image?url=/ecafe/media/pictures/ecafe-home-icon.png" alt="home icon" width="48" height="48"/>
        </a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/meals"><fmt:message key="admin.meals"/></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/categories"><fmt:message key="admin.categories"/></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/aingredients"><fmt:message key="admin.ingredients"/></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/halls"><fmt:message key="admin.halls"/></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/aclients"><fmt:message key="admin.clients"/></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/reviews"><fmt:message key="admin.reviews"/></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/profile">
            <img src="${pageContext.servletContext.contextPath}/load_image?url=/ecafe/media/pictures/ecafe-profile-icon.png" alt="profile icon" width="48" height="48"/>
        </a></li>
    </ul>
</nav>
</body>
</html>
