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
    <title>Блюда</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Блюда</strong></h2>
    <hr>

 <table class="table">
    <c:forEach var="meal" items="${meals}">
    <c:set var="count" value="${count+1}"/>
    <c:if test="${count%5!=0}">
        <td>
            <div class="smallbox">
                <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}&category=${meal.category.name}" method="post">
                    <p>${meal.name}</p>
                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                    <p>Категория: <a href="${pageContext.request.contextPath}/categories" class="invis-ref">${meal.category.name}</a></p>
                    <p>Цена: ${meal.price} рос. руб.</p>
                    <p>Состав: </p>
                    <c:forEach var="ingredient" items="${meal.ingredients}">
                        <c:if test="${ingredient.mass!=0}">
                        <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ингредиент: ${ingredient.name} | Массой: ${ingredient.mass}</p>
                        </c:if>
                    </c:forEach>
                    <c:if test="${actor!=null}">
                        <input type="submit" value="Добавить блюдо в заказ">
                    </c:if>
                </form>
            </div>
        </td>
     </c:if>
     <c:if test="${count%5==0}">
         </tr>
         <tr>
         <td>
             <div class="smallbox">
                 <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}&category=${meal.category.name}" method="post">
                     <p>${meal.name}</p>
                     <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                     <p>Категория: <a href="${pageContext.request.contextPath}/categories" class="invis-ref">${meal.category.name}</a></p>
                     <p>Цена: ${meal.price} рос. руб.</p>
                     <p>Состав: </p>
                     <c:forEach var="ingredient" items="${meal.ingredients}">
                         <c:if test="${ingredient.mass!=0}">
                             <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ингредиент: ${ingredient.name} | Массой: ${ingredient.mass}</p>
                         </c:if>
                     </c:forEach>
                     <c:if test="${actor!=null}">
                         <input type="submit" value="Добавить блюдо в заказ">
                     </c:if>
                 </form>
             </div>
         </td>
     </c:if>
    </c:forEach>
</table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
