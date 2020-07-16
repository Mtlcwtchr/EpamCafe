<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="thandler" uri="https://github.com/Mtlcwtchr/EpamCafe" %>
<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/25/2020
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Footer</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

    <div class="container">
    <div class="row copyright">
        <div class="col-md-12 text-center">
            <hr>
            <p class="intro-text text-center"> Copyright &copy; ECafe | <thandler:timeCustomTag/></p>
            <hr>
        </div>
    </div>
    </div>

</body>
</html>
