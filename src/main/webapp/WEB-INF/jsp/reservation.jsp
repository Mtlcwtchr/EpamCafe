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
    <title>Hall reservation</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><fmt:message key="reservation.reserve"/> <strong>"${hall.hallName}"</strong></h2>
        <hr>
    </div>
    <div class="row">
        <div class="col-sm-3 col-sm-push-3"  id="form-wrap">
            <form action="${pageContext.request.contextPath}/reserve_hall?key=${hall.id}" method="post">
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldDate"><fmt:message key="reservation.date"/></label>
                            <input  type="date" id="fieldDate" class="form-control" min="${minDate}" max="${maxDate}" name="reservationDate">
                    </div>
                </div>
                <c:if test="${error!=null}">
                    <p class="error-msg">${error}</p>
                </c:if>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldContactTime"><fmt:message key="reservation.contactTime"/></label>
                        <input type="time" id="fieldContactTime" class="form-control" min="${minTime}" max="${maxTime}" name="contactTime">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldContactPhone"><fmt:message key="reservation.contactPhone"/></label>
                        <input type="text" id="fieldContactPhone" class="form-control" value="${phone}" placeholder="80" name="contactPhone">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldSubmit"></label>
                        <p>
                            <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="reservation.reserve"/> ${hall.hallName}">
                        </p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>