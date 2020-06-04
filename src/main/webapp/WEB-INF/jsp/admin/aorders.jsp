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
    <title>Orders history</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Orders history</strong></h2>
    <hr>
<table class="bordered-t">
    <tr>
        <c:forEach var="order" items="${orders}">
            <td>
                <p>Ordered for: ${order.orderDate}</p>
            <ul>
                <c:forEach var="meal" items="${order.meals}">
                    <li>
                        <div class="popup-window p-w-${meal.id}">
                            <p class="close">x</p>
                                <div class="popup-inner">
                                    <p>Meal: ${meal.name}</p>
                                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                                    <p>Category: <a href="${pageContext.request.contextPath}/categories">${meal.category.name}</a></p>
                                    <p>Price: ${meal.price}</p>
                                    <p>Ingredients: </p>
                                    <c:forEach var="ingredient" items="${meal.ingredients}">
                                        <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ingredient: ${ingredient.name} | Mass: ${ingredient.mass}</p>
                                    </c:forEach>
                                    <input type="submit" value="Remove meal from order">
                                </div>
                        </div>
                        <p class="popup-open" about="${meal.id}">${meal.name}</p>
                        <form action="${pageContext.request.contextPath}/update_order?chosenOrderId=${order.id}" method="post">
                            <label>
                              <a>Is paid: </a><input type="checkbox" name="isPaid" value="${order.isPaid}">
                            </label>
                            <label>
                                <a>Is prepared: </a><input type="checkbox" name="isPrepared" value="${order.isPrepared}">
                            </label>
                            <label>
                                <a>Is taken: </a><input type="checkbox" name="isTaken" value="${order.isTaken}">
                            </label>
                            <input type="submit" value="Apply changes">
                        </form>
                    </li>
                </c:forEach>
            </ul>
            </td>
        </c:forEach>
    </tr>
</table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
