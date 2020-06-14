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
    <title>Hall reservation</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="reservation.reserve"/> <strong>"${hall.hallName}"</strong></h2>
    <hr>
    <div class="smallbox">
        <form action="${pageContext.request.contextPath}/reserve_hall?chosenHallId=${hall.id}" method="post">
            <p><label>
                <fmt:message key="reservation.date"/>: <input class="choose-date" id="d-c" type="date" min="${minDate}" max="${maxDate}" name="reservationDate">
            </label></p>
            <c:if test="${error!=null}">
                <p class="error-msg">${error}</p>
            </c:if>
            <p><label>
                <fmt:message key="reservation.contactTime"/> <input type="time" min="${minTime}" max="${maxTime}" name="contactTime">
            </label></p>
            <p><label>
                <fmt:message key="reservation.contactPhone"/> <input type="text" value="${phone}" placeholder="Contact phone" name="contactPhone">
            </label></p>
            <p><label>
                <input type="submit" value="<fmt:message key="reservation.reserve"/> ${hall.hallName}">
            </label></p>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>