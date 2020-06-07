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
    <title>Что-то пошло не так</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<div class="project-bar"><a href="${pageContext.request.contextPath}/home">Кафе <strong>КриссКросс</strong></a></div>
<div class="address-bar">localhost:8080</div>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Ой, <strong>что-то пошло не так ¯\_(ツ)_/¯</strong></h2>
    <hr>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>