<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/authorizationheader.jsp"/>
<div class="box">
    <hr>
    <h2 class="intro-text text-center">Регистрация</h2>
    <hr>
    <br>
    <form class="intro-text text-center" action="${pageContext.request.contextPath}/sign_up" method="post">
        <label for="fieldUser">Логин: </label><input type="text" id="fieldUser" name="username">
        <label for="fieldPassword">Пароль: </label><input type="password" id="fieldPassword" name="password">
        <label for="fieldEmail">Email: </label><input type="text" id="fieldEmail" name="email">
        <label for="fieldPhone">Телефон: </label><input type="text" id="fieldPhone" name="phone">
        <label for="fieldName">Имя: </label><input type="text" id="fieldName" name="name">
        <input class="signbutt" type="submit" value="Зарегестрироваться">
    </form>
    <c:if test="${error!=null}">
        <p class="error-msg text-center">${error}</p>
    </c:if>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
