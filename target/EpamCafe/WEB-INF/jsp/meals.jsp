<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Meals</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="backpopup"></div>
<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Meals</strong></h2>
    <hr>
    <ul>
    <c:forEach var="meal" items="${meals}">
        <li>
            <div class="popup-window p-w-${meal.id}">
                <p class="close">x</p>
                <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}" method="post">
                <div class="popup-inner">
                    <p>Meal: ${meal.name}</p>
                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                    <p>Category: <a href="${pageContext.request.contextPath}/categories">${meal.category.name}</a></p>
                    <p>Price: ${meal.price}</p>
                    <p class="popup-inner-ingredients">Ingredients: ${meal.ingredients}</p>
                    <input type="submit" value="Add meal to My order">
                </div>
                </form>
            </div>
            <p class="popup-open" about="${meal.id}">${meal.name}</p>
        </li>
    </c:forEach>
 </ul>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
