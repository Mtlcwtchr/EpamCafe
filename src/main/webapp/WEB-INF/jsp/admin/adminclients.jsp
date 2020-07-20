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
    <title>Registered clients</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong><fmt:message key="admin.clients"/></strong></h2>
    <hr>

    <ul>
        <c:forEach var="client" items="${clients}">
            <li>
                <div class="popup-window p-w-${client.id}">
                    <p class="close">x</p>
                    <form action="${pageContext.request.contextPath}/update_client?chosenClientId=${client.id}" method="post">
                        <div class="popup-inner">
                            <p>Id: ${client.id}</p>
                            <p><fmt:message key="profile.name"/>: ${client.name}</p>
                            <label><fmt:message key="profile.loyalty"/>: <input type="text" value=" ${client.loyaltyPoints}" placeholder="loyalty points" name="clientLoyalty"></label>
                            <label><fmt:message key="profile.bonuses"/>: <input type="text" value=" ${client.bonuses}" placeholder="bonuses" name="clientBonuses"> </label>
                            <a class="invis-ref" href="${pageContext.request.contextPath}/aorders?clientId=${client.id}"><fmt:message key="orders.history"/></a>
                            <p><fmt:message key="client.accountInfo"/>: </p>
                            <p>Id: ${client.user.id}</p>
                            <p><fmt:message key="profile.username"/>: ${client.user.username}</p>
                            <p><fmt:message key="profile.mail"/>: ${client.user.email}</p>
                            <p><fmt:message key="profile.contactPhone"/>: ${client.user.phone}</p>
                            <p><label> <fmt:message key="client.banned"/>:
                            <c:if test="${client.banned}">
                                <input type="checkbox" name="params" value="isBanned" checked/>
                            </c:if>
                            <c:if test="${!client.banned}">
                                <input type="checkbox" name="params" value="isBanned"/>
                            </c:if>
                            </label></p>
                            <input type="submit" value="<fmt:message key="profile.save"/>">
                        </div>
                    </form>
                </div>
                <p class="popup-open" about="${client.id}">Id:${client.id} | <fmt:message key="client.client"/>: ${client.name}</p>
            </li>
        </c:forEach>
    </ul>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
