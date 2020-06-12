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
    <title>Menu</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong><fmt:message key="categories.label"/></strong></h2>
    <hr>

    <table class="table">
        <c:forEach var="category" items="${categories}">
            <c:set var="count" value="${count+1}"/>
            <c:if test="${count%5==0}">
            </tr>
            <tr>
                <td>
                    <div class="smallbox">
                    <a href="${pageContext.request.contextPath}/meals?categoryId=${category.id}" class="invis-ref">
                        <p class="intro-text text-center">${category.name}</p>
                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${category.pictureUrl}" class="centered text-center" alt="${category.name} image" width="128" height="128"/>
                    </a>
                    </div>
                </td>
            </c:if>
            <c:if test="${count%5!=0}">
                <td>
                    <div class="smallbox">
                    <a href="${pageContext.request.contextPath}/meals?categoryId=${category.id}" class="invis-ref">
                        <p class="intro-text text-center">${category.name}</p>
                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${category.pictureUrl}" class="centered text-center" alt="${category.name} image" width="128" height="128"/>
                    </a>
                    </div>
                </td>
            </c:if>
        </c:forEach>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
