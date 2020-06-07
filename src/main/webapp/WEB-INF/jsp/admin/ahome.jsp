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
    <title>Главная</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>
    <hr>
    <h2 class="intro-text text-center">Активных заказов в настоящий момент: <strong>${activesNumber}</strong></h2>
    <hr>
    <p><a class="invis-ref intro-text text-center" href="${pageContext.request.contextPath}/active_orders">Перейти</a> </p>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>