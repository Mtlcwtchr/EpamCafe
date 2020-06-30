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

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title><Profile></Profile></title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="profile.personal"/> <strong><fmt:message key="profile.account"/></strong></h2>
    <hr>
    <form action="${pageContext.request.contextPath}/change_admin_profile" method="post">
        <p><fmt:message key="profile.username"/>: <label for="fieldUsername"><input type="text" id="fieldUsername" name="username" value="${actor.user.username}" placeholder="username"></label></p>
        <p><fmt:message key="profile.password"/>: <label for="fieldPassword"><input type="password" id="fieldPassword" name="password" value="${actor.user.password}" placeholder="password"></label></p>
        <p><input class="sign-butt" type="submit" value="<fmt:message key="profile.save"/>"></p>
    </form >
    <p><form action="${pageContext.request.contextPath}/sign_out" method="post"><input class="sign-butt sign-out-butt" type="submit" value="<fmt:message key="profile.out"/>"></form></p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>