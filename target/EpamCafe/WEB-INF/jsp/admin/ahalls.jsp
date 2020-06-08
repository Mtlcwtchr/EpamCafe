<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-16" language="java" %>
<html>
<head>
    <title>Залы</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Залы</strong></h2>
    <hr>
    <table>
        <c:forEach var="hall" items="${halls}">
        <tr>
            <td>
                <div class="smallbox">
                    <form action="${pageContext.request.contextPath}/update_hall?chosenHallId=${hall.id}" method="post">
                    <p class="intro-text text-center">Зал <label><input type="text" value="${hall.hallName}" name="hallName"></label> на <label><input type="text" value="${hall.guestsNumber}" name="hallGuestsNumber"></label> человек</p>
                    <p align="center"><textarea cols="128" rows="22" name="hallDescription">${hall.hallDescription}</textarea></p>
                        <p><label><input type="submit" value="Сохранить изменения"></label></p>
                    </form>
                    <form action="${pageContext.request.contextPath}/delete_hall?chosenHallId=${hall.id}" method="post">
                        <p><label><input type="submit" value="Удалить зал ${hall.hallName}"></label></p>
                    </form>
                    <hr>
                    <a class="invis-ref intro-text text-center" href="${pageContext.request.contextPath}/reservation?chosenHallId=${hall.id}">Открыть брони</a>
                    <hr>
                </div>
            </td>
        </tr>
        </c:forEach>
    </table>

    <div class="popup-window p-w-0">
        <form action="${pageContext.request.contextPath}/save_hall" method="post">
            <div class="close">x</div>
            <div class="popup-inner">
                    <p class="intro-text text-center">Зал <label><input type="text" value="" name="hallName"></label> на <label><input type="text" value="" name="hallGuestsNumber"></label> человек</p>
                    <p align="center"><textarea cols="128" rows="22" name="hallDescription"></textarea></p>
                    <p><label><input type="submit" value="Сохранить изменения"></label></p>
            </div>
        </form>
    </div>
    <p class="popup-open" about="0">Добавить новый зал</p>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>