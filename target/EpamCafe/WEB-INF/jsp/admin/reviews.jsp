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
    <title>Главная</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Отзывы <strong>посетителей:</strong></h2>
    <hr>
    <c:forEach var="comment" items="${comments}">
    <c:set var="count" value="${count+1}"/>
    <c:if test="${count%5!=0}">
        <td>
            <div class="smallbox">
                <p class="intro-text text-center">${comment.authorName}</p>
                <p align="center">${comment.authorPhone}</p>
                <p align="center">${comment.message}</p>
            </div>
        </td>
    </c:if>
    <c:if test="${count%5==0}">
    </tr>
    <tr>
        <td>
            <div class="smallbox">
                <p class="intro-text text-center">${comment.authorName}</p>
                <p align="center">${comment.authorPhone}</p>
                <p align="center">${comment.message}</p>
            </div>
        </td>
        </c:if>
        </c:forEach>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>