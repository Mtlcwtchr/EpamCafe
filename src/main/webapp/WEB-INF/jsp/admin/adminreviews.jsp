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
    <title>Reviews</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="admin.reviews"/>:</h2>
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