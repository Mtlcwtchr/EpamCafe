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
    <title>Halls</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><strong><fmt:message key="halls.label"/></strong></h2>
        <hr>
        <c:if test="${actor==null}">
            <p align="center"><fmt:message key="halls.guestmsg"/></p>
        </c:if>
        <table>
            <c:forEach var="hall" items="${halls}">
            <c:set var="count" value="${count+1}"/>
            <c:if test="${count%5==0}">
            </tr>
            <tr>
                <td>
                    <div class="smallbox">
                        <p class="intro-text text-center"><fmt:message key="halls.hall"/> "${hall.hallName}" <fmt:message key="halls.for"/> ${hall.guestsNumber} <fmt:message key="halls.ppl"/></p>
                        <p align="center">${hall.hallDescription}</p>
                        <c:if test="${actor!=null}">
                            <hr>
                            <p align="center">
                                <a class="invis-ref intro-text text-center" href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}"><fmt:message key="halls.reserve"/></a>
                            </p>
                            <hr>
                        </c:if>
                    </div>
                </td>
                </c:if>
                <c:if test="${count%5!=0}">
                <td>
                    <div class="smallbox">
                        <p class="intro-text text-center"><fmt:message key="halls.hall"/> "${hall.hallName}" <fmt:message key="halls.for"/> ${hall.guestsNumber} <fmt:message key="halls.ppl"/></p>
                        <p align="center">${hall.hallDescription}</p>
                        <c:if test="${actor!=null}">
                            <hr>
                            <p align="center">
                                <a class="invis-ref intro-text text-center" href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}"><fmt:message key="halls.reserve"/></a>
                            </p>
                            <hr>
                        </c:if>
                    </div>
                </td>
                </c:if>
                </c:forEach>
        </table>
    </div>
    <div class="popup-window msgbox p-w-p">
        <p class="close">x</p>
        <div class="popup-inner">
            <p>
                <fmt:message key="halls.success"/>
            </p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>