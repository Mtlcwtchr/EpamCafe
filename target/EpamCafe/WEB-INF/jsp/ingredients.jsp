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
    <title>Ingredients</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Ingredients</strong></h2>
    <hr>
    <p>
        <a>
            <ul>
                <c:forEach var="ingredient" items="${ingredients}">
                    <li class = "list-part">${ingredient}</li>
                </c:forEach>
            </ul>
        </a>
    </p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>