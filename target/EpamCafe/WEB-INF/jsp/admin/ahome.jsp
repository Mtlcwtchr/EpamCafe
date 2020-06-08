<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Активных заказов в настоящий момент: <strong>${activesNumber}</strong></h2>
    <hr>
    <p align="center"><a class="invis-ref" href="${pageContext.request.contextPath}/active_orders">Перейти</a> </p>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>