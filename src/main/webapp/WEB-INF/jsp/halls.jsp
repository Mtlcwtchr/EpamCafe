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
    <title>Залы</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Наши залы</strong></h2>
    <hr>
    <c:if test="${actor==null}">
        <p align="center">Для бронирования залов необходимо войти в аккаунт или зарегестрироваться</p>
    </c:if>
    <table>
        <c:forEach var="hall" items="${halls}">
            <c:set var="count" value="${count+1}"/>
            <c:if test="${count%5==0}">
            </tr>
            <tr>
                <td>
                    <div class="smallbox">
                        <p class="intro-text text-center">Зал "${hall.hallName}" на ${hall.guestsNumber} человек</p>
                        <p align="center">${hall.hallDescription}</p>
                        <c:if test="${actor!=null}">
                            <hr>
                            <p align="center">
                                <a class="invis-ref intro-text text-center" href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}">Забронировать</a>
                            </p>
                            <hr>
                        </c:if>
                    </div>
                </td>
            </c:if>
            <c:if test="${count%5!=0}">
                <td>
                    <div class="smallbox">
                        <p class="intro-text text-center">Зал "${hall.hallName}" на ${hall.guestsNumber} человек</p>
                        <p align="center">${hall.hallDescription}</p>
                        <c:if test="${actor!=null}">
                            <hr>
                            <p align="center">
                                <a class="invis-ref intro-text text-center" href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}">Забронировать</a>
                            </p>
                            <hr>
                        </c:if>
                    </div>
                </td>
            </c:if>
        </c:forEach>
    </table>
    <div class="popup-window p-w-p smallbox">
        <p class="close">x</p>
        <div class="popup-inner">
            <p>
            Вы успешно забронировали зал
            </p>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>