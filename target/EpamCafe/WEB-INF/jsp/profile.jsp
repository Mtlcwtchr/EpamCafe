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
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Your <strong>profile</strong></h2>
    <hr>
    <form action="${pageContext.request.contextPath}/change_profile" method="post">
        <p>Username: ${actor.user.username}</p>
        <p><label for="fieldName"><input type="text" id="fieldName" name="name" value="${actor.name}" placeholder="Name"></label></p>
        <p><label for="fieldEmail"><input type="text" id="fieldEmail" name="email" value="${actor.user.email}" placeholder="email"></label></p>
        <p><label for="fieldPhone"><input type="text" id="fieldPhone" name="phone" value="${actor.user.phone}" placeholder="phone"></label></p>
        <p>Loyalty points: ${actor.loyaltyPoints}</p>
        <p>Bonuses: ${actor.bonuses}</p>
        <p><input class="signbuttut" type="submit" value="Apply changes"></p>
    </form >
    <p><form action="${pageContext.request.contextPath}/sign_out" method="post"><input class="signbuttut signoutbutt" type="submit" value="Sign Out"></form></p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>