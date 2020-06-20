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

<html>
<head>
    <title>Main</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="main.label"/></h2>
    <hr>
    <p align="center"><fmt:message key="main.description"/></p>
</div>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><fmt:message key="main.contacts"/></h2>
    <hr>
    <div class="smallbox">
    <form action="${pageContext.request.contextPath}/leave_comment" method="post">
        <p><label>
            <fmt:message key="main.name"/>: <input type="text" required value="${actor.name}" placeholder="Author name" name="authorName">
        </label></p>
        <p><label>
            <fmt:message key="main.phone"/>: <input type="text" required value="${actor.user.phone}" placeholder="Author phone" name="authorPhone">
        </label></p>
        <p><label>
            <textarea placeholder="Message" cols="164" rows="12" required name="message"><fmt:message key="main.msg"/></textarea>
        </label></p>
        <p><label>
            <input type="submit" value="<fmt:message key="main.leavemsg"/>">
        </label></p>
    </form>
    </div>
    <div class="smallbox" align="center">
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
    <div class="popup-window p-w-p msgbox">
        <p class="close">x</p>
        <div class="popup-inner">
            <p>
                <fmt:message key="main.success"/>
            </p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>