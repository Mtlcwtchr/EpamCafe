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
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Meals</strong></h2>
    <hr>

 <table class="table">
    <tr>
    <c:forEach var="meal" items="${meals}">
        <td>
            <div class="popup-window p-w-${meal.id}">
                <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}&category=${meal.category.name}" method="post">
                <div class="popup-inner">
                    <p>${meal.name}</p>
                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                    <p>Category: <a href="${pageContext.request.contextPath}/categories" class="invis-ref">${meal.category.name}</a></p>
                    <p>Price: ${meal.price}</p>
                    <p>Ingredients: </p>
                    <c:forEach var="ingredient" items="${meal.ingredients}">
                        <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ingredient: ${ingredient.name} | Mass: ${ingredient.mass}</p>
                    </c:forEach>
                    <input type="submit" value="Add meal to My order">
                </div>
                </form>
            </div>
        </td>
    </c:forEach>
 </tr>
</table>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
