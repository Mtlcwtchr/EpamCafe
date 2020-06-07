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
    <title>Our halls</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Наши залы</strong></h2>
    <hr>
    <table>
        <c:forEach var="hall" items="${halls}">
        <tr>
            <td>
            <div class="smallbox">
                <p class="intro-text text-center">Зал "${hall.hallName}" на ${hall.guestsNumber} человек</p>
                <p align="center">${hall.hallDescription}</p>
                <c:if test="${actor!=null}">
                    <hr>
                        <a class="invis-ref intro-text text-center" href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}">Забронировать</a>
                    <hr>
                </c:if>
            </div>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>