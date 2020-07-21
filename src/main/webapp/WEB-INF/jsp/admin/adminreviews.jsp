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
    <title>Reviews</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>


<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><strong><fmt:message key="orders.history"/></strong></h2>
        <hr>
    </div>
    <div class="row">
        <table class="table-w-10 table-border-collapsed">
            <c:forEach var="comment" items="${comments}">
                <tr class="list-element">
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                            ${comment.authorName}
                    </td>
                    <td style="width: 20%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                            ${comment.authorPhone}
                    </td>
                    <td style="width: 60%; color: rgba(245, 245, 245, 0.9)">
                            ${comment.message}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>