<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Вход</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/authorizationheader.jsp"/>
<div class="box">
    <hr>
    <h2 class="intro-text text-center">Вход</h2>
    <hr>
    <br>
    <form class="intro-text text-center" action="${pageContext.request.contextPath}/sign_in" method="post">
        <label for="fieldUser">Логин: </label><input type="text" id="fieldUser" name="username">
        <label for="fieldPassword">Пароль: </label><input type="password" id="fieldPassword" name="password">
        <input class="signbutt" type="submit" value="Войти">
    </form>
    <c:if test="${error!=null}">
        <p class="error-msg text-center">${error}</p>
    </c:if>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
