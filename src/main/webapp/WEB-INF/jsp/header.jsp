<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/25/2020
  Time: 7:55 PM
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Header</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <script>
        /*$(document).ready(function () {

            const header = document.getElementById('fh5co-header');
            const sticky = header.offsetTop;

            window.onscroll = function ()
            {myFunction(header, sticky)};

        });

        function myFunction(header, sticky) {
            if (window.pageYOffset > sticky){
                header.classList.add("sticky");
            } else {
                header.classList.remove("sticky");
            }
        }*/
    </script>
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
                    <li><a href="${pageContext.request.contextPath}/home"><fmt:message key="header.home"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/menu"><fmt:message key="header.menu"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/about_cafe"><fmt:message key="header.aboutus"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/manual"><fmt:message key="header.howtoorder"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/halls"><fmt:message key="header.reservation"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/profile"><fmt:message key="header.profile"/></a></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- </div> -->
</nav>

<c:if test="${actor!=null}">
    <div class="container">
        <div class="row">
            <div class="floating-cart">
                <a href="${pageContext.request.contextPath}/client_order">
                    <img src="${pageContext.request.contextPath}/get_local_image?key=cart" alt="#" width="64" height="64">
                </a>
            </div>
        </div>
    </div>
</c:if>

</body>
</html>
