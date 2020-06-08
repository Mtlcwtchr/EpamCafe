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
    <title>Брони зала</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Брони зала <strong>"${hall.hallName}"</strong></h2>
    <hr>
    <table>
        <c:forEach var="reservation" items="${reservations}">
            <c:set var="count" value="${count+1}"/>
            <c:if test="${count%5!=0}">
                <td>
                    <div class="smallbox">
                        <form action="${pageContext.request.contextPath}/delete_reservation?chosenReservationId=${reservation.id}&chosenHallId=${hall.id}" method="post">
                            <p>Бронь ${reservation.id}</p>
                            <p>Дата брони: ${reservation.reservationDate}</p>
                            <p>Время для созвона: ${reservation.contactTime}</p>
                            <p>Контактный телефон: ${reservation.contactPhone}</p>
                            <p><label>
                                <input type="submit" value="Закрыть бронь ${reservation.id}">
                            </label></p>
                        </form>
                    </div>
                </td>
            </c:if>
            <c:if test="${count%5==0}">
            </tr>
            <tr>
                <td>
                    <div class="smallbox">
                        <form action="${pageContext.request.contextPath}/delete_reservation?chosenReservationId=${reservation.id}" method="post">
                            <p>Бронь ${reservation.id}</p>
                            <p>Дата брони: ${reservation.reservationDate}</p>
                            <p>Время для созвона: ${reservation.contactTime}</p>
                            <p>Контактный телефон: ${reservation.contactPhone}</p>
                            <p><label>
                                <input type="submit" value="Закрыть бронь ${reservation.id}">
                            </label></p>
                        </form>
                    </div>
                </td>
            </c:if>
        </c:forEach>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>