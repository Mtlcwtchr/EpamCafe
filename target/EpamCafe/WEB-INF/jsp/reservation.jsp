<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Забронировать зал <strong>"${hall.hallName}"</strong></h2>
    <hr>
    <div class="smallbox">
        <form action="${pageContext.request.contextPath}/reserve_hall?chosenHallId=${hall.id}" method="post">
            <p><label>
                Дата брони: <input class="choose-date" id="d-c" type="date" min="${minDate}" max="${maxDate}" name="reservationDate">
            </label></p>
            <c:if test="${error!=null}">
                <p class="error-msg">${error}</p>
            </c:if>
            <p><label>
                Время для созвона: <input type="time" min="${minTime}" max="${maxTime}" name="contactTime">
            </label></p>
            <p><label>
                Контактный телефон: <input type="text" value="${phone}" placeholder="контактный телефон" name="contactPhone">
            </label></p>
            <p><label>
                <input type="submit" value="Забронировать зал ${hall.hallName}">
            </label></p>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>