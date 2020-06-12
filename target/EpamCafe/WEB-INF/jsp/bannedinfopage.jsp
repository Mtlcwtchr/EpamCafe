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
    <title>Problem</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<div class="project-bar"><a href="${pageContext.request.contextPath}/home">Epam <strong>Cafe</strong></a></div>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="err.bannedLight"/> <strong><fmt:message key="err.bannedStrong"/> ¯\_(ツ)_/¯</strong></h2>
    <hr>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>