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
    <title>Main</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">
<div class="row">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="main.label"/></h2>
    <hr>
    <p class="text-center"><fmt:message key="main.description"/></p>
</div>

<div class="row">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="main.contacts"/></h2>
    <hr>
        <div class="col-md-6 col-md-push-6 col-sm-6 col-sm-push-6"  id="form-wrap">
            <form action="${pageContext.request.contextPath}/leave_comment" method="post">
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="name"><fmt:message key="main.name"/></label>
                        <p>
                        <input type="text" id="name" class="form-control" required value="${actor.name}" placeholder="" name="authorName">
                        </p>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="phone"><fmt:message key="main.phone"/></label>
                        <p>
                        <input type="text" id="phone" class="form-control" required value="${actor.user.phone}" placeholder="" name="authorPhone">
                        </p>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="message"><fmt:message key="main.msg"/></label>
                        <p>
                        <textarea name="message" id="message" cols="25" rows="10" class="form-control"></textarea>
                        </p>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="main.leavemsg"/>">
                    </div>
                </div>
            </form>
        </div>
    <div class="col-md-12 row-l">
    <table>
        <tr>
            <td>
                <fmt:message key="main.contactPhone"/>:
                <ul>
                    <li>
                        +(29) 666-31-31
                    </li>
                </ul>
            </td>
            <td>
                <fmt:message key="main.contactMail"/>:
                <ul>
                    <li>
                        cafecontactemail@gmail.com
                    </li>
                </ul>
            </td>
        </tr>
    </table>
    </div>
    <div class="popup-window-small white-wrap p-w-success">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text">
                <fmt:message key="main.success"/>
            </p>
        </div>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>