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
    <title>Проблемка</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<div class="project-bar"><a href="${pageContext.request.contextPath}/home">Кафе <strong>КриссКросс</strong></a></div>
<div class="address-bar">localhost:8080</div>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Ничего серьезного, <strong>просто вас забанили ¯\_(ツ)_/¯</strong></h2>
    <hr>
    <p class="text-center">Свяжитесь с администратором по номеру +(029) 205-76-73 для решения вопроса</p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>