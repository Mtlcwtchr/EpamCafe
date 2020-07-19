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
    <title>Orders history</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><strong><fmt:message key="orders.history"/></strong></h2>
        <hr>
        <table class="table-w-10 table-border-collapsed">
            <c:forEach var="order" items="${actor.orders}">
                <div class="popup-window white-wrap p-w-${order.id}">
                    <p class="close">x</p>
                    <a class="info-sign info-sign-push" href="${pageContext.request.contextPath}/order?key=${order.id}">i</a>
                    <hr style="width: 100%;">
                    <div class="popup-inner centered">
                        <fmt:message key="orders.order"/> ${order.id} <br>
                            ${order.orderDate} <br>
                        <c:if test="${order.clientMark==0}">
                            <form action="${pageContext.request.contextPath}/rate_order?key=${order.id}" method="post">
                                <div class="rating_block">
                                    <div class="rating-area">
                                        <input type="radio" id="star-5" name="rating" value="5">
                                        <label for="star-5" title="«5»"></label>
                                        <input type="radio" id="star-4" name="rating" value="4">
                                        <label for="star-4" title="«4»"></label>
                                        <input type="radio" id="star-3" name="rating" value="3">
                                        <label for="star-3" title="«3»"></label>
                                        <input type="radio" id="star-2" name="rating" value="2">
                                        <label for="star-2" title="«2»"></label>
                                        <input type="radio" id="star-1" name="rating" value="1">
                                        <label for="star-1" title="«1»"></label>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-sm-8 col-sm-push-1">
                                        <label for="message"></label>
                                            <textarea name="message" id="message" cols="25" rows="4" placeholder="<fmt:message key="orders.comm"/>" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="centered">
                                        <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="orders.comment"/>">
                                    </div>
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${!order.paid}">
                            <form action="${pageContext.request.contextPath}/cancel_order?key=${order.id}" method="post">
                                <div class="row form-group">
                                    <div class="centered">
                                        <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="orders.cancel"/>">
                                    </div>
                                </div>
                            </form>
                        </c:if>
                    </div>
                </div>
                <tr class="list-element popup-open" about="${order.id}">
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                        <fmt:message key="orders.order"/> ${order.id}
                    </td>
                    <td style="width: 60%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                        ${order.orderDate}
                        <c:if test="${!order.taken}">
                            *
                        </c:if>
                    </td>
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                        ${order.totalPrice}$
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%--<div class="box">

    <table>
        <c:forEach var="order" items="${actor.orders}">
            <c:set var="count" value="${count+1}"/>
            <c:if test="${count%5!=0}">
                <td>
                <div class="smallbox text-center">
                    <p><fmt:message key="orders.order"/> ${order.id} | <fmt:message key="orders.date"/>: ${order.orderDate}</p>
                    <ul>
                        <c:forEach var="meal" items="${order.meals}">
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
                                <p class="popup-open" about="${meal.id}">${meal.name}</p>
                            </li>
                        </c:forEach>
                        <c:if test="${order.clientMark==0}">
                        <form action="${pageContext.request.contextPath}/rate_order?chosenOrderId=${order.id}" method="post">
                            <c:if test="${order.clientMark==0}">
                            <form action="${pageContext.request.contextPath}/rate_order?key=${order.id}" method="post">
                                <div class="rating_block">
                                    <div class="rating-area">
                                        <input type="radio" id="star-5" name="rating" value="5">
                                        <label for="star-5" title="«5»"></label>
                                        <input type="radio" id="star-4" name="rating" value="4">
                                        <label for="star-4" title="«4»"></label>
                                        <input type="radio" id="star-3" name="rating" value="3">
                                        <label for="star-3" title="«3»"></label>
                                        <input type="radio" id="star-2" name="rating" value="2">
                                        <label for="star-2" title="«2»"></label>
                                        <input type="radio" id="star-1" name="rating" value="1">
                                        <label for="star-1" title="«1»"></label>
                                    </div>
                                </div>
                                <label>
                                    <textarea cols="24" rows="4" placeholder="<fmt:message key="orders.comment"/>" name="clientComment"></textarea>
                                </label>
                                <p><input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="orders.comment"/>"></p>
                            </form>
                            </c:if>
                    </ul>
                </div>
                </td>
            </c:if>
            <c:if test="${count%5==0}">
                </tr>
                <tr>
                <td>
                    <div class="smallbox text-center">
                        <p><fmt:message key="orders.order"/> ${order.id} | <fmt:message key="orders.date"/>: ${order.orderDate}</p>
                        <ul>
                            <c:forEach var="meal" items="${order.meals}">
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
                                    <p class="popup-open" about="${meal.id}">${meal.name}</p>
                                </li>
                            </c:forEach>
                            <c:if test="${order.clientMark==0}">
                                <form action="${pageContext.request.contextPath}/rate_order?chosenOrderId=${order.id}" method="post">
                                    <div class="rating-area">
                                        <input type="radio" id="star-5-2" name="rating" value="5">
                                        <label for="star-5-2" title="«5»"></label>
                                        <input type="radio" id="star-4-2" name="rating" value="4">
                                        <label for="star-4-2" title="«4»"></label>
                                        <input type="radio" id="star-3-2" name="rating" value="3">
                                        <label for="star-3-2" title="«3»"></label>
                                        <input type="radio" id="star-2-2" name="rating" value="2">
                                        <label for="star-2-2" title="«2»"></label>
                                        <input type="radio" id="star-1-2" name="rating" value="1">
                                        <label for="star-1-2" title="«1»"></label>
                                    </div>
                                    <label>
                                        <textarea cols="24" rows="4" placeholder="<fmt:message key="orders.comment"/>" name="clientComment"></textarea>
                                    </label>
                                    <p><input type="submit" value="<fmt:message key="orders.comment"/>"></p>
                                </form>
                            </c:if>
                            <c:if test="${!order.paid}">
                                <form action="${pageContext.request.contextPath}/cancel_order?chosenOrderId=${order.id}" method="post">
                                    <input type="submit" value="<fmt:message key="orders.cancel"/>">
                                </form>
                            </c:if>
                        </ul>
                    </div>
                </td>
             </c:if>
        </c:forEach>
    </table>
</div>--%>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
