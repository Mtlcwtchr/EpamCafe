<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Authorization</title>
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
        <li><a href="${pageContext.request.contextPath}/sign_in"><fmt:message key="sign.in"/></a></li>
        <li>|</li>
        <li><a href="${pageContext.request.contextPath}/sign_up"><fmt:message key="sign.up"/></a></li>
    </ul>
</nav>
</body>
</html>
