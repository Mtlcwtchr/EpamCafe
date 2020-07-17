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
    <title>Basket</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><fmt:message key="order.place"/></h2>
        <hr>
        <ul>
            <c:forEach var="meal" items="${actor.currentOrder.meals}">
                <li>
                    <div class="popup-window p-w-${meal.id}">
                        <p class="close">x</p>
                        <div class="popup-inner">
                            <p>${meal.name}</p>
                            <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                            <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/categories" class="invis-ref">${meal.category.name}</a></p>
                            <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
                            <details>
                                <summary><fmt:message key="meal.composition"/></summary>
                                <c:forEach var="ingredient" items="${meal.ingredients}">
                                    <c:if test="${ingredient.mass!=0}">
                                        <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | ${ingredient.name} | <fmt:message key="ingredient.mass"/>: ${ingredient.mass}</p>
                                    </c:if>
                                </c:forEach>
                            </details>
                        </div>
                    </div>
                    <p class="popup-open" about="${meal.id}">
                        <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" alt="*" width="16" height="16"> ${meal.name} ${meal.price} $
                    </p>
                </li>
            </c:forEach>
        </ul>
    </div>

    <p><fmt:message key="order.totalPrice"/>: ${totalPrice} $</p>
    <div>
        <form class="prof-order" action="${pageContext.request.contextPath}/place_order" method="post">
        <label><fmt:message key="order.requiredTime"/>: <input type="time" required name="orderTime" min="${minTime}" max="${maxTime}"></label>
        <p>
            <input class="sign-butt" type="submit" value="<fmt:message key="order.placeOfflinePayment"/>" name="offlinePayment">
                <fmt:message key="order.or"/>
            <input class="sign-butt" type="submit" value="<fmt:message key="order.placeOnlinePayment"/>" name="onlinePayment">
        </p>
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
