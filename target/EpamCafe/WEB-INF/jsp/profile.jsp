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
    <title>Личный кабинет</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Личный <strong>кабинет</strong></h2>
    <hr>
    <table>
        <tr>
            <td>
                <div class="smallbox">
                <form action="${pageContext.request.contextPath}/change_profile" method="post">
                <p>Имя аккаунта: ${actor.user.username}</p>
                <p>Имя: <label for="fieldName"><input type="text" id="fieldName" name="name" value="${actor.name}" placeholder="Имя"></label></p>
                <p>Email: <label for="fieldEmail"><input type="text" id="fieldEmail" name="email" value="${actor.user.email}" placeholder="email"></label></p>
                <p>Контактный телефон: <label for="fieldPhone"><input type="text" id="fieldPhone" name="phone" value="${actor.user.phone}" placeholder="Контактный телефон"></label></p>
                <%--<p>Баллы лояльности: ${actor.loyaltyPoints}</p>
                <p>Бонусы: ${actor.bonuses}</p>--%>
                <p><input class="sign-butt" type="submit" value="Сохранить изменения"></p>
                </form >
                <form action="${pageContext.request.contextPath}/sign_out" method="post">
                    <input class="sign-butt sign-out-butt" type="submit" value="Выйти">
                </form>
                </div>
            </td>

            <td>
                <div class="smallbox">
                    <ul>
                        <p class="text-center">Активные заказы:</p>
                        <c:forEach var="order" items="${actor.orders}">
                            <c:if test="${!order.taken}">
                                <hr>
                                <li>
                                    <p>Заказ ${order.id} | На дату: ${order.orderDate}</p>
                                    <p>Статус:
                                        <c:choose>
                                            <c:when test="${order.prepared}"> готово, пожалуйста, заберите заказ</c:when>
                                            <c:when test="${order.paid}"> оплачено, готовится</c:when>
                                            <c:otherwise> сперва необходимо оплатить заказ</c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p>Состоит из:</p>
                                    <ul>
                                        <c:forEach var="meal" items="${order.meals}">
                                            <li>
                                                <div class="popup-window p-w-${meal.id}">
                                                    <p class="close">x</p>
                                                    <div class="popup-inner">
                                                        <p>Блюдо: ${meal.name}</p>
                                                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                                                        <p>Категория: <a href="${pageContext.request.contextPath}/categories">${meal.category.name}</a></p>
                                                        <p>Цена: ${meal.price} рос. руб.</p>
                                                        <p>Ингредиенты: </p>
                                                        <c:forEach var="ingredient" items="${meal.ingredients}">
                                                            <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ингредиент: ${ingredient.name} | Массой: ${ingredient.mass} г.</p>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <p class="popup-open" about="${meal.id}">${meal.name}</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                                <hr>
                            </c:if>
                        </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/client_orders" class="invis-ref intro-text centered text-center">История заказов</a>
                </div>
            </td>

            <td>
                <div class="smallbox">
                    <p>Корзина:</p>
                    <ul>
                    <c:forEach var="meal" items="${actor.currentOrder.meals}">
                    <li>
                        <div class="popup-window p-w-${meal.id}">
                            <p class="close">x</p>
                            <form action="${pageContext.request.contextPath}/remove_meal_from_order?chosenMealId=${meal.id}" method="post">
                                <div class="popup-inner">
                                    <p>Блюдо: ${meal.name}</p>
                                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                                    <p>Категория: <a href="${pageContext.request.contextPath}/categories">${meal.category.name}</a></p>
                                    <p>Цена: ${meal.price} рос. руб.</p>
                                    <p>Ингредиенты: </p>
                                        <c:forEach var="ingredient" items="${meal.ingredients}">
                                            <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ингредиент: ${ingredient.name} | Массой: ${ingredient.mass} г.</p>
                                        </c:forEach>
                                    <input type="submit" value="Убрать блюдо из заказа">
                                </div>
                            </form>
                        </div>
                        <p class="popup-open" about="${meal.id}">Блюдо: ${meal.name} | Ценой: ${meal.price} рос. руб.</p>
                    </li>
                    </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/client_order" class="invis-ref intro-text text-center centered">Сделать заказ</a>
                </div>
            </td>
        </tr>
    </table>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>