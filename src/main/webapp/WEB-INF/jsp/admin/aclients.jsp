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
    <title>Active clients</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Active clients</strong></h2>
    <hr>

    <ul>
        <c:forEach var="client" items="${clients}">
            <li>
                <div class="popup-window p-w-${client.id}">
                    <p class="close">x</p>
                    <form action="${pageContext.request.contextPath}/update_client?chosenClientId=${client.id}" method="post">
                        <div class="popup-inner">
                            <p>Id: ${client.id}</p>
                            <p>Client: ${client.name}</p>
                            <label>Loyalty points: <input type="text" value=" ${client.loyaltyPoints}" placeholder="loyalty points" name="clientLoyalty"></label>
                            <label>Bonuses: <input type="text" value=" ${client.bonuses}" placeholder="bonuses" name="clientBonuses"> </label>
                            <a href="${pageContext.request.contextPath}/aorders?clientId=${client.user.id}">Client orders</a>
                            <p>Account info: </p>
                            <p>Id: ${client.user.id}</p>
                            <p>Username: ${client.user.username}</p>
                            <p>Email: ${client.user.email}</p>
                            <p>Phone: ${client.user.phone}</p>
                            <label>Banned: <input type="checkbox" value="${client.banned}" name="isBanned"></label>
                            <input type="submit" value="Update">
                        </div>
                    </form>
                </div>
                <p class="popup-open" about="${client.id}">Id:${client.id} | Client: ${client.name}</p>
            </li>
        </c:forEach>
    </ul>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
