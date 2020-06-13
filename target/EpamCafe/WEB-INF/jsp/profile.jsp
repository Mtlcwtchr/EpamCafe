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
    <title>Profile</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="profile.personal"/> <strong><fmt:message key="profile.account"/></strong></h2>
    <hr>
    <table>
        <tr>
            <td>
                <div class="smallbox">
                <form action="${pageContext.request.contextPath}/change_profile" method="post">
                <p><fmt:message key="profile.username"/>: ${actor.user.username}</p>
                <p><fmt:message key="profile.name"/>: <label for="fieldName"><input type="text" id="fieldName" name="name" value="${actor.name}" placeholder="Name"></label></p>
                <p><fmt:message key="profile.mail"/>: <label for="fieldEmail"><input type="text" id="fieldEmail" name="email" value="${actor.user.email}" placeholder="email"></label></p>
                <p><fmt:message key="profile.contactPhone"/>: <label for="fieldPhone"><input type="text" id="fieldPhone" name="phone" value="${actor.user.phone}" placeholder="Contact phone"></label></p>
                <p><fmt:message key="profile.loyalty"/>: ${actor.loyaltyPoints}</p>
                <p><fmt:message key="profile.bonuses"/>: ${actor.bonuses}</p>
                <p><input class="sign-butt" type="submit" value="<fmt:message key="profile.save"/>"></p>
                </form >
                <form action="${pageContext.request.contextPath}/sign_out" method="post">
                    <input class="sign-butt sign-out-butt" type="submit" value="<fmt:message key="profile.out"/>">
                </form>
                </div>
            </td>

            <td>
                <div class="smallbox">
                    <ul>
                        <p class="text-center"><fmt:message key="profile.activeOrders"/>:</p>
                        <c:forEach var="order" items="${actor.orders}">
                            <c:if test="${!order.taken}">
                                <hr>
                                <li>
                                    <p><fmt:message key="orders.order"/> ${order.id} | <fmt:message key="orders.date"/>: ${order.orderDate}</p>
                                    <p><fmt:message key="profile.status"/>:
                                        <c:choose>
                                            <c:when test="${order.prepared}"> <fmt:message key="profile.prep"/></c:when>
                                            <c:when test="${order.paid}"> <fmt:message key="profile.paid"/></c:when>
                                            <c:otherwise> <fmt:message key="profile.notPaid"/></c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p><fmt:message key="profile.consists"/>:</p>
                                    <ul>
                                        <c:forEach var="meal" items="${order.meals}">
                                            <li>
                                                <div class="popup-window p-w-${meal.id}">
                                                    <p class="close">x</p>
                                                    <div class="popup-inner">
                                                        <p><fmt:message key="profile.meal"/>: ${meal.name}</p>
                                                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                                                        <p><fmt:message key="meal.category"/>: <a class="invis-ref" href="${pageContext.request.contextPath}/meals?categoryId=${meal.category.id}">${meal.category.name}</a></p>
                                                        <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
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
                    <a href="${pageContext.request.contextPath}/client_orders" class="invis-ref intro-text centered text-center"><fmt:message key="orders.history"/></a>
                </div>
            </td>

            <td>
                <div class="smallbox">
                    <p><fmt:message key="profile.basket"/>:</p>
                    <ul>
                    <c:forEach var="meal" items="${actor.currentOrder.meals}">
                    <li>
                        <div class="popup-window p-w-${meal.id}">
                            <p class="close">x</p>
                            <form action="${pageContext.request.contextPath}/remove_meal_from_order?chosenMealId=${meal.id}" method="post">
                                <div class="popup-inner">
                                    <p><fmt:message key="profile.meal"/>: ${meal.name}</p>
                                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                                    <p><fmt:message key="meal.category"/>: <a class="invis-ref" href="${pageContext.request.contextPath}/meals?categoryId=${meal.category.id}">${meal.category.name}</a></p>
                                    <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
                                    <input type="submit" value="<fmt:message key="order.removeMeal"/>">
                                </div>
                            </form>
                        </div>
                        <p class="popup-open" about="${meal.id}"><fmt:message key="profile.meal"/>: ${meal.name} | <fmt:message key="meal.price"/>: ${meal.price} $</p>
                    </li>
                    </c:forEach>
                    </ul>
                    <a href="${pageContext.request.contextPath}/client_order" class="invis-ref intro-text text-center centered"><fmt:message key="order.place"/></a>
                </div>
            </td>
        </tr>
    </table>
    <div class="popup-window p-w-p smallbox">
        <p class="close">x</p>
        <div class="popup-inner">
            <p>
                <fmt:message key="order.success"/>
            </p>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>