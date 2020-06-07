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
    <h2 class="intro-text text-center"><strong>Our halls</strong></h2>
    <hr>
    <table>
        <c:forEach var="hall" items="${halls}">
            <c:set var="count" scope="request" value="${count+1}"/>
            <c:if test="${count%4!=0}">
                <td>
                    <div class="smallbox">
                        <p class="intro-text text-center">Hall ${hall.id}</p>
                        <p>Guests number: ${hall.guestsNumber}</p>
                        <p>${hall.hallDescription}</p>
                        <a href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}">Reserve this hall</a>
                    </div>
                </td>
            </c:if>
            <c:if test="${count%4==0}">
            </tr>
            <tr>
                <td>
                    <div class="smallbox">
                        <p class="intro-text text-center">Hall ${hall.id}</p>
                        <p>Guests number: ${hall.guestsNumber}</p>
                        <p>${hall.hallDescription}</p>
                        <a href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}">Reserve this hall</a>
                    </div>
                </td>
                </c:if>
        </c:forEach>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>