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
    <title>Profile</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <hr>
        <h2 class="intro-text text-center"><fmt:message key="profile.personal"/> <strong><fmt:message key="profile.account"/></strong></h2>
    <hr>
    <table>
        <tr>
            <td>
                <div class="row col-md-12 col-sm-12 col-sm-push-3">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <ul>
                                <hr>
                                <h2 class="intro-text text-center"><fmt:message key="profile.activeOrders"/></h2>
                                <hr>
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
                                        </li>
                                        <hr>
                                    </c:if>
                                </c:forEach>
                                <a class="col-sm-12 col-md-12 text-center invis-ref" href="${pageContext.request.contextPath}/client_orders"><fmt:message key="orders.history"/></a>
                            </ul>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-12">
                            <img src="${pageContext.request.contextPath}/get_local_image?key=profile" alt="basket image" height="32" width="32"> <fmt:message key="profile.basket"/>
                        </div>
                        <div class="row form-group">
                            <div class="col-md-12">
                                <ul>
                                    <c:forEach var="meal" items="${actor.currentOrder.meals}">
                                        <li>
                                            <div class="popup-window white-wrap p-w-${meal.id}">
                                                <p class="close">x</p>
                                                <a class="info-sign info-sign-push" href="${pageContext.request.contextPath}/meal?key=${meal.id}">i</a>
                                                <hr style="width: 100%;">
                                                <div class="popup-inner centered">
                                                    <p class="intro-text">${meal.name} <a class="invis-ref red-wrap" href="${pageContext.request.contextPath}/remove_meal_from_order?key=${meal.id}">-</a></p>
                                                    <p><img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" width="128" height="128" alt="${meal.name} image"/></p>
                                                    <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/menu?key=${meal.category.id}" class="invis-ref">${meal.category.name}</a></p>
                                                    <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
                                                </div>
                                            </div>
                                            <p class="popup-open" about="${meal.id}">
                                                    ${meal.name} ${meal.price} $ <a class="invis-ref red-wrap" href="${pageContext.request.contextPath}/remove_meal_from_order?chosenMealId=${meal.id}">-</a>
                                            </p>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <c:if test="${!actor.currentOrder.blank}">
                        <div class="col-md-12">
                            <form action="${pageContext.request.contextPath}/client_order" method="get">
                                <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="order.place"/>">
                            </form>
                        </div>
                        </c:if>
                    </div>
                </div>
            </td>
            <td>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-md-push-6 col-sm-push-6" id="form-wrap">
                        <table>
                            <tr>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <fmt:message key="profile.username"/>: ${actor.user.username}
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/sign_out" method="post">
                                        <div class="row form-group">
                                            <div class="col-sm-9 col-sm-push-2">
                                                <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="profile.out"/>">
                                            </div>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <fmt:message key="profile.loyalty"/>: ${actor.loyaltyPoints}
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-sm-12 col-sm-push-2">
                                            <fmt:message key="profile.bonuses"/>: ${actor.bonuses}
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <form action="${pageContext.request.contextPath}/change_profile" method="post">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="fieldName"><fmt:message key="profile.name"/></label>
                                    <input type="text" id="fieldName" class="form-control" value="${actor.name}" required name="name" >
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="fieldEmail"><fmt:message key="profile.mail"/></label>
                                    <input type="text" id="fieldEmail" class="form-control"  value="${actor.user.email}" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$" required name="email" >
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="fieldPhone"><fmt:message key="sign.phone"/></label>
                                    <input type="text"  id="fieldPhone" class="form-control" value="${actor.user.phone}" pattern="80[0-9]{9}" title="Phone number starting with 80" required name="phone">
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="profile.save"/>">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>