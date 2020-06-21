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
    <title>Main</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="admin.activeOrders"/>: <strong>${activesNumber}</strong></h2>
    <hr>
    <p align="center"><a class="invis-ref" href="${pageContext.request.contextPath}/active_orders"><fmt:message key="admin.forward"/>/a> </p>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>