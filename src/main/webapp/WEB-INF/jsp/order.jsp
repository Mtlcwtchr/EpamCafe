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
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-12" id="form-wrap">
            <table class="table-w-10 table-border-collapsed">
                <c:forEach var="meal" items="${actor.currentOrder.meals}">
                    <tr class="list-element">
                        <td style="width: 10%; padding-bottom: 5px">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" alt="*" width="64" height="64">
                        </td>
                        <td style="width: 60%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                            ${meal.name}
                        </td>
                        <td class="centered" style="width: 15%">
                            ${meal.mass}
                        </td>
                        <td class="centered" style="width: 15%">
                            ${meal.price}$
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p class="centered intro-text"><fmt:message key="order.totalPrice"/>: ${totalPrice} $</p>
            <form action="${pageContext.request.contextPath}/place_order" method="post">
                <div class="row form-group">
                    <div class="col-sm-3 col-sm-push-3 col-md-6">
                        <label for="fieldOrderTime" ><fmt:message key="order.requiredTime"/></label>
                        <input type="time" id="fieldOrderTime" class="form-control" min="${minTime}" max="${maxTime}" name="orderTime">
                    </div>
                </div>
                <table class="col-sm-push-2 col-sm-6">
                    <tr>
                        <td>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="fieldSubmitOfflinePayment"></label>
                                    <input type="submit" id="fieldSubmitOfflinePayment" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="order.placeOfflinePayment"/>" name="offlinePayment">
                                </div>
                            </div>
                        </td>
                        <td>
                            <fmt:message key="order.or"/>
                        </td>
                        <td>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="fieldSubmitOnlinePayment"></label>
                                    <input type="submit" id="fieldSubmitOnlinePayment" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="order.placeOnlinePayment"/>" name="onlinePayment">
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
