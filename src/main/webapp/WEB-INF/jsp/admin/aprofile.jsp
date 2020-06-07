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
    <title>Личный кабинет</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Личный <strong>кабинет</strong></h2>
    <hr>
    <form action="${pageContext.request.contextPath}/change_admin_profile" method="post">
        <p>Имя аккаунта: <label for="fieldUsername"><input type="text" id="fieldUsername" name="username" value="${actor.user.username}" placeholder="username"></label></p>
        <p>Пароль: <label for="fieldPassword"><input type="password" id="fieldPassword" name="password" value="${actor.user.password}" placeholder="password"></label></p>
        <p><input class="sign-butt" type="submit" value="Сохранить изменения"></p>
    </form >
    <p><form action="${pageContext.request.contextPath}/sign_out" method="post"><input class="sign-butt sign-out-butt" type="submit" value="Выйти"></form></p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>