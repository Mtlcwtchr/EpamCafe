<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:51 PM
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
    <title>SignUp</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/authorizationheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="sign.up"/></h2>
    <hr>
    <br>
    <form class="intro-text text-center" action="${pageContext.request.contextPath}/sign_up" method="post">
        <label for="fieldUser"><fmt:message key="sign.login"/>: </label><input type="text" id="fieldUser" name="username">
        <label for="fieldPassword"><fmt:message key="sign.password"/>: </label><input type="password" id="fieldPassword" name="password">
        <label for="fieldEmail"><fmt:message key="sign.mail"/>: </label><input type="text" id="fieldEmail" name="email">
        <label for="fieldPhone"><fmt:message key="sign.phone"/>: </label><input type="text" id="fieldPhone" name="phone">
        <label for="fieldName"><fmt:message key="sign.name"/>: </label><input type="text" id="fieldName" name="name">
        <input class="signbutt" type="submit" value="<fmt:message key="sign.up"/>">
    </form>
    <c:if test="${error!=null}">
        <p class="error-msg text-center">${error}</p>
    </c:if>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
