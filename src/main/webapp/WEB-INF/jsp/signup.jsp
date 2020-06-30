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

<!DOCTYPE html>
<html xml:lang="${locale}">
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
        <label for="fieldUsername"><fmt:message key="sign.login"/>: </label><input class="uname" type="text" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{4,20}$" title="From 4 to 20 latin any-case letters, numbers, -, _, ." id="fieldUsername" required name="username">
        <label for="fieldPassword"><fmt:message key="sign.password"/>: </label><input class="pass" type="password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="8 or more latin uppercase letter, latin lowercase letters, numbers and special symbols" id="fieldPassword" required name="password">
        <label for="fieldEmail"><fmt:message key="sign.mail"/>: </label><input class="email" type="text" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$" id="fieldEmail" required name="email">
        <label for="fieldPhone"><fmt:message key="sign.phone"/>: </label><input class="phone" type="text" pattern="80[0-9]{9}" title="Phone number starting with 80" id="fieldPhone" required name="phone">
        <label for="fieldName"><fmt:message key="sign.name"/>: </label><input type="text" id="fieldName" required name="name">
        <input class="signbutt sign" type="submit" value="<fmt:message key="sign.up"/>">
    </form>
    <c:if test="${error!=null}">
        <p class="error-msg text-center">${error}</p>
    </c:if>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
