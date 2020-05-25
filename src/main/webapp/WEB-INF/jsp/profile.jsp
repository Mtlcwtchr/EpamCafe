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
    <p>Name: ${actor.name}</p>
    <p>Loyalty points: ${actor.loyaltyPoints}</p>
    <p>Bonuses: ${actor.bonuses}</p>
    <p><form action="${pageContext.request.contextPath}/sign_out" method="post"><input class="signout" type="submit" value="Sign Out"></form></p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>