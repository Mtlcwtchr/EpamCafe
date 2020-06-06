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
    <title>Home</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Your <strong>profile</strong></h2>
    <hr>
    <table>
        <tr>
            <td>
                <div class="smallbox">
                <p>Name: ${actor.name}</p>
                <form action="${pageContext.request.contextPath}/change_profile" method="post">
                <p>Username: ${actor.user.username}</p>
                <p><label for="fieldName"><input type="text" id="fieldName" name="name" value="${actor.name}" placeholder="Name"></label></p>
                <p><label for="fieldEmail"><input type="text" id="fieldEmail" name="email" value="${actor.user.email}" placeholder="email"></label></p>
                <p><label for="fieldPhone"><input type="text" id="fieldPhone" name="phone" value="${actor.user.phone}" placeholder="phone"></label></p>
                <p>Loyalty points: ${actor.loyaltyPoints}</p>
                <p>Bonuses: ${actor.bonuses}</p>
                <p><input class="sign-butt" type="submit" value="Apply changes"></p>
                </form >
                <form action="${pageContext.request.contextPath}/sign_out" method="post">
                    <input class="sign-butt sign-out-butt" type="submit" value="Sign Out">
                </form>
                </div>
            </td>

            <td>
                <div class="smallbox">
                    <ul>
                        <p class="text-center">Active orders:</p>
                        <c:forEach var="order" items="${actor.orders}">
                            <c:if test="${!order.taken}">
                                <hr>
                                <li>
                                    <p>Order ${order.id} | Ordered for: ${order.orderDate}</p>
                                    <p>Status:
                                        <c:choose>
                                            <c:when test="${order.paid}"> paid, preparing</c:when>
                                            <c:when test="${order.prepared}"> prepared, please, take off your order</c:when>
                                            <c:otherwise> need to be paid first</c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p>Consists of:</p>
                                    <ul>
                                        <c:forEach var="meal" items="${order.meals}">
                                            <li>
                                                <div class="popup-window p-w-${meal.id}">
                                                    <p class="close">x</p>
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
                    <a href="${pageContext.request.contextPath}/client_orders" class="invis-ref intro-text centered text-center">Orders history</a>
                </div>
            </td>

            <td>
                <div class="smallbox">
                    <p>My basket:</p>
                    <ul>
                    <c:forEach var="meal" items="${actor.currentOrder.meals}">
                    <li>
                        <div class="popup-window p-w-${meal.id}">
                            <p class="close">x</p>
                            <form action="${pageContext.request.contextPath}/remove_meal_from_order?chosenMealId=${meal.id}" method="post">
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
                        <p class="popup-open" about="${meal.id}">Meal: ${meal.name} | Price: ${meal.price}</p>
                    </li>
                    </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/client_order" class="invis-ref intro-text text-center centered">My order</a>
                </div>
            </td>
        </tr>
    </table>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>