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
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="backpopup"></div>
<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Ingredients</strong></h2>
    <hr>
    <ul>
        <c:forEach var="category" items="${categories}">
            <li>
                <div class="popup-window p-w-${category.id}">
                    <p class="close">x</p>
                    <div class="popup-inner">
                        <p>Category: ${category.name}</p>
                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${category.pictureUrl}" alt="${category.name} image" width="128" height="128"/>
                        <p>Some text about category</p>
                    </div>
                </div>
                <p class="popup-open" about="${category.id}">${category.name}</p>
            </li>
        </c:forEach>
    </ul>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
