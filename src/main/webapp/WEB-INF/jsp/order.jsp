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
    <title>Placing Order</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Order</strong></h2>
    <hr>
    <ul>
        <c:forEach var="meal" items="${actor.currentOrder.meals}">
        <li>
            <div class="popup-window p-w-${meal.id}">
                <p class="close">x</p>
                <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}" method="post">
                    <div class="popup-inner">
                        <p>Meal: ${meal.name}</p>
                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                        <p>Category: <a href="${pageContext.request.contextPath}/categories">${meal.category.name}</a></p>
                        <p>Price: ${meal.price}</p>
                        <p>Ingredients: </p>
                        <c:forEach var="ingredient" items="${meal.ingredients}">
                            <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ingredient: ${ingredient.name} | Mass: ${ingredient.mass}</p>
                        </c:forEach>
                        <input type="submit" value="Remove meal from order">
                    </div>
                </form>
            </div>
            <p class="popup-open" about="${meal.id}">${meal.name}</p>
        </li>
    </c:forEach>
    </ul>
    <p>Total price: ${totalPrice}</p>
    <div>
        <form class="prof-order" action="${pageContext.request.contextPath}/place_order" method="post">
            <label>Order time <input type="datetime-local" required name="orderDate"></label>
            <p>
            <input class="sign-butt" type="submit" value="Place order with cash payment">
                    or
            <iframe src="https://money.yandex.ru/quickpay/button-widget?targets=Order%20payment&default-sum=${totalPrice}&button-text=12&any-card-payment-type=on&button-size=m&button-color=black&successURL=&quickpay=small&account=4100115365944689&" width="184" height="36" frameborder="0" allowtransparency="true" scrolling="no"></iframe>
            </p>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
