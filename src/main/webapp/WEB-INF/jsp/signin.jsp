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
    <title>SignIn</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/authorizationheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="sign.in"/></h2>
    <hr>
    <br>
    <form class="intro-text text-center" action="${pageContext.request.contextPath}/sign_in" method="post">
        <label for="fieldUser"><fmt:message key="sign.login"/>: </label><input type="text" id="fieldUser" name="username">
        <label for="fieldPassword"><fmt:message key="sign.password"/>: </label><input type="password" id="fieldPassword" name="password">
        <input class="signbutt" type="submit" value="<fmt:message key="sign.in"/>">
    </form>
    <c:if test="${error!=null}">
        <p class="error-msg text-center">${error}</p>
    </c:if>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
