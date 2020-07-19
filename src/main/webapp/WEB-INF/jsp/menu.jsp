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
    <title>Menu</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.c-navbar-item').css('width', (100 / document.querySelectorAll('.c-navbar-item').length) + '%')

            if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?key') {
                    sessionStorage.removeItem('key');
                    sessionStorage.setItem('key', document.location.search.substring(document.location.search.lastIndexOf('=')+1, document.location.search.length));
            }

            $('.c-navbar-item-'+sessionStorage.getItem('key')).addClass('c-navbar-item-active');

        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><strong><fmt:message key="header.menu"/></strong></h2>
        <hr>
    </div>

    <div class="row">
        <table class="c-navbar">
            <tr>
                <c:forEach var="category" items="${categories}">
                    <c:if test="${category.id!=1}">
                        <td class="c-navbar-item c-navbar-item-${category.id}">
                            <a href="${pageContext.request.contextPath}/menu?key=${category.id}" class="invis-ref" style="display: block">
                                ${category.name}
                            </a>
                        </td>
                    </c:if>
                </c:forEach>
            </tr>
        </table>
    </div>

    <c:if test="${actor==null}">
        <p class="centered"><fmt:message key="menu.guestmsg"/></p>
    </c:if>

    <div class="row">
        <table class="menu-table-1">
            <tr>
                <c:forEach var="meal" items="${meals}">
                <c:set var="count" value="${count+1}"/>
                    <td class="menu-table-item centered">
                        <div class="form-group">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" class="centered text-center" alt="${meal.name} image" width="320" height="320"/>
                            <div class="intro-text text-center">
                                    ${meal.name} <a class="info-sign" href="${pageContext.request.contextPath}/meal?key=${meal.id}">i</a>
                                        <br>
                                    ${meal.price}$
                            </div>
                            <c:if test="${actor!=null}">
                                <form action="${pageContext.request.contextPath}/add_meal_to_order?key=${meal.id}" method="post">
                                    <div class="row form-group">
                                        <div class="col-md-12 centered">
                                            <label for="fieldSubmit"></label>
                                            <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="faq.addMeal"/>">
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </td>
                <c:if test="${count%3==0}">
                    </tr>
                    <tr>
                </c:if>
                </c:forEach>
            </tr>
        </table>
    </div>

    <div class="row">
        <p class="text-center"><fmt:message key="menu.description"/></p>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>