<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Authorization</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<br>
<br>
<br>
<br>

<nav class="fh5co-nav scrolled" role="navigation">
    <!-- <div class="top-menu"> -->
    <div class="container">
        <div class="row">
            <div><details class="invis-ref " style="padding: 10px 15px; top: 0; position: fixed; z-index: 1091">
                <summary> <img src="${pageContext.request.contextPath}/get_local_image?key=lang" alt="lang image" height="16" width="16"/> <fmt:message key="lang"/></summary>
                <a class="invis-ref" href="?locale=en">
                    EN
                </a>
                <a class="invis-ref" href="?locale=by">
                    BY
                </a>
                <a class="invis-ref" href="?locale=ru">
                    RU
                </a>
            </details></div>
            <br>
            <br>
            <div class="col-xs-12 intro-text text-center logo-wrap">
                <div id="fh5co-logo"><a href="${pageContext.request.contextPath}/home">Epam <span>Cafe</span></a></div>
            </div>
            <div class="col-xs-12 text-center menu-1 menu-wrap">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/sign_in"><fmt:message key="sign.in"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/sign_up"><fmt:message key="sign.up"/></a></li>
                </ul>
            </div>
        </div>

    </div>
    <!-- </div> -->
</nav>

</body>
</html>
