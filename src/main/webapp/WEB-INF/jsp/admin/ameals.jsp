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
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Meals</strong></h2>
    <hr>

 <table class="table">
    <tr>
    <c:forEach var="meal" items="${meals}">
        <td>
            <div>
                <form action="${pageContext.request.contextPath}/update_meal?chosenMealId=${meal.id}" method="post">
                <div>
                    <label>
                        <input type="text" value="${meal.name}" placeholder="meal name">
                    </label>
                    <label>
                        <input type="text" value="${meal.pictureUrl}" placeholder="meal picture url">
                    </label>
                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                    <label>
                        <select name="category">
                            <c:forEach var="category" items="${categories}">
                                <option>${category.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <label>
                        <input type="text" value="${meal.price}" placeholder="price" name="mealPrice">
                    </label>
                    <p>Ingredients: </p>
                    <c:forEach var="ingredient" items="${meal.ingredients}">
                        <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ingredient: ${ingredient.name} | Mass: ${ingredient.mass}</p>
                    </c:forEach>
                    <input type="submit" value="Apply changes">
                </div>
                </form>
            </div>
        </td>
    </c:forEach>
 </tr>
</table>

    <div class="popup-window p-w-0">
        <form action="${pageContext.request.contextPath}/save_meal" method="post">
            <div class="popup-inner">
                <label>
                    <input type="text" placeholder="meal name">
                </label>
                <label>
                    <input type="text" placeholder="meal picture url">
                </label>
                <label>
                    <select name="category">
                        <c:forEach var="category" items="${categories}">
                            <option>${category.name}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    <input type="text" placeholder="price" name="mealPrice">
                </label>
                <input type="submit" value="Save meal">
            </div>
        </form>
    </div>
    <p class="popup-open" about="0">Save new meal</p>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
