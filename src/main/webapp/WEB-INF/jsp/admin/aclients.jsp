<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Зарегестрированные пользователи</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Зарегестрированные пользователи</strong></h2>
    <hr>

    <ul>
        <c:forEach var="client" items="${clients}">
            <li>
                <div class="popup-window p-w-${client.id}">
                    <p class="close">x</p>
                    <form action="${pageContext.request.contextPath}/update_client?chosenClientId=${client.id}" method="post">
                        <div class="popup-inner">
                            <p>Id: ${client.id}</p>
                            <p>Имя клиента: ${client.name}</p>
                            <label>Баллов лояльности: <input type="text" value=" ${client.loyaltyPoints}" placeholder="loyalty points" name="clientLoyalty"></label>
                            <label>Бонусов: <input type="text" value=" ${client.bonuses}" placeholder="bonuses" name="clientBonuses"> </label>
                            <a class="invis-ref" href="${pageContext.request.contextPath}/aorders?clientId=${client.user.id}">История заказов</a>
                            <p>Информация об аккаунте: </p>
                            <p>Id: ${client.user.id}</p>
                            <p>Имя аккаунта: ${client.user.username}</p>
                            <p>Email: ${client.user.email}</p>
                            <p>Контактный телефон: ${client.user.phone}</p>
                            <p><label> Забанен:
                            <c:if test="${client.banned}">
                                <input type="checkbox" name="params" value="isBanned" checked/>
                            </c:if>
                            <c:if test="${!client.banned}">
                                <input type="checkbox" name="params" value="isBanned"/>
                            </c:if>
                            </label></p>
                            <input type="submit" value="Сохранить изменения">
                        </div>
                    </form>
                </div>
                <p class="popup-open" about="${client.id}">Id:${client.id} | Клиент: ${client.name}</p>
            </li>
        </c:forEach>
    </ul>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
