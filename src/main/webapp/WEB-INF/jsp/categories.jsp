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
    <title>Categories</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Menu</strong></h2>
    <hr>

    <table class="table">
        <tr>
        <c:forEach var="category" items="${categories}">
                <td>
                <div class="popup-window p-w-${category.id}">
                    <div class="popup-inner">
                        <a href="${pageContext.request.contextPath}/meals?category=${category.name}" class="invis-ref">
                            <p class="intro-text text-center">${category.name}</p>
                            <img src="${pageContext.servletContext.contextPath}/load_image?url=${category.pictureUrl}" class="centered text-center" alt="${category.name} image" width="128" height="128"/>
                        </a>
                    </div>
                </div>
                </td>
        </c:forEach>
        </tr>
    </table>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
