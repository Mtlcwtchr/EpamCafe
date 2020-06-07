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
    <title>Корзина</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Сделать <strong>заказ</strong></h2>
    <hr>
    <ul>
        <c:forEach var="meal" items="${actor.currentOrder.meals}">
        <li>
            <div class="popup-window p-w-${meal.id}">
                <p class="close">x</p>
                <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}" method="post">
                    <div class="popup-inner">
                        <p>Блюдо: ${meal.name}</p>
                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                        <p>Категория: <a href="${pageContext.request.contextPath}/categories">${meal.category.name}</a></p>
                        <p>Цена: ${meal.price} рос. руб</p>
                        <p>Состав: </p>
                        <c:forEach var="ingredient" items="${meal.ingredients}">
                            <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ингредиент: ${ingredient.name} | Массой: ${ingredient.mass} г.</p>
                        </c:forEach>
                        <input type="submit" value="Убрать блюдо из заказа">
                    </div>
                </form>
            </div>
            <p class="popup-open" about="${meal.id}">${meal.name}</p>
        </li>
    </c:forEach>
    </ul>
    <p>Итоговая сумма: ${totalPrice}</p>
    <div>
        <form class="prof-order" action="${pageContext.request.contextPath}/place_order" method="post">
        <label>Желаемое время готовности заказа: <input type="datetime-local" required name="orderDate" min="${minOrderDatetime}" value="${minOrderDatetime}"></label>
        <p>
            <input class="sign-butt" type="submit" value="Заказать с оплатой наличными" name="offlinePayment">
                или
            <input class="sign-butt" type="submit" value="Заказать с оплатой онлайн" name="onlinePayment">
        </p>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
