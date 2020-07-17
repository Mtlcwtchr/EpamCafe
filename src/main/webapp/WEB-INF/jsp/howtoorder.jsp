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
    <title>FAQ</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><fmt:message key="faq.howTo"/> <strong><fmt:message key="faq.order"/></strong></h2>
        <hr>
        <ul>
            <li><fmt:message key="faq.auth"/></li>
            <li><fmt:message key="faq.addMeal"/></li>
            <li><fmt:message key="faq.goToProfile"/></li>
            <li><fmt:message key="faq.checkOrder"/></li>
            <li><fmt:message key="faq.choosePaymentSystem"/></li>
            <li><fmt:message key="faq.placeOrder"/></li>
        </ul>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>