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
    <title>История заказов</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>История заказов</strong></h2>
    <hr>

    <table>
        <c:forEach var="order" items="${actor.orders}">
            <c:set var="count" value="${count+1}"/>
            <c:if test="${count%5!=0}">
                <td>
                <div class="smallbox text-center">
                    <p>Order ${order.id} | Ordered for: ${order.orderDate}</p>
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
                                        <c:if test="${!order.paid}">
                                        <input type="submit" value="Убрать блюдо из заказа">
                                        </c:if>
                                    </div>
                                </div>
                                <p class="popup-open" about="${meal.id}">${meal.name}</p>
                            </li>
                        </c:forEach>
                        <c:if test="${order.clientMark==0}">
                        <form action="${pageContext.request.contextPath}/rate_order?chosenOrderId=${order.id}" method="post">
                        <div class="rating_block">
                            <input name="rating" value="5" id="rating_5" type="radio" />
                            <label for="rating_5" class="label_rating"></label>

                            <input name="rating" value="4" id="rating_4" type="radio" />
                            <label for="rating_4" class="label_rating"></label>

                            <input name="rating" value="3" id="rating_3" type="radio" />
                            <label for="rating_3" class="label_rating"></label>

                            <input name="rating" value="2" id="rating_2" type="radio" />
                            <label for="rating_2" class="label_rating"></label>

                            <input name="rating" value="1" id="rating_1" type="radio" />
                            <label for="rating_1" class="label_rating"></label>
                        </div>
                        <label>
                            <textarea cols="24" rows="4" placeholder="Оставьте комментарий" name="clientComment"></textarea>
                        </label>
                            <p><input type="submit" value="Оставить отзыв"></p>
                        </form>
                        </c:if>
                        <c:if test="${!order.paid}">
                            <form action="${pageContext.request.contextPath}/cancel_order?chosenOrderId=${order.id}" method="post">
                                <input type="submit" value="Отменить заказ">
                            </form>
                        </c:if>
                    </ul>
                </div>
                </td>
            </c:if>
            <c:if test="${count%5==0}">
                </tr>
                <tr>
                <td>
                <div class="smallbox text-center">
                    <p>Заказ ${order.id} | На дату: ${order.orderDate}</p>
                    <ul>
                        <c:forEach var="meal" items="${order.meals}">
                            <li>
                                <div class="popup-window p-w-${meal.id}">
                                    <p class="close">x</p>
                                    <div class="popup-inner">
                                        <p>Блюдо: ${meal.name}</p>
                                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                                        <p>Категория: <a href="${pageContext.request.contextPath}/categories">${meal.category.name}</a></p>
                                        <p>Цена: ${meal.price} рос. руб.</p>
                                        <p>Состав: </p>
                                        <c:forEach var="ingredient" items="${meal.ingredients}">
                                            <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | Ингредиент: ${ingredient.name} | Массой: ${ingredient.mass} г.</p>
                                        </c:forEach>
                                        <c:if test="${!order.paid}">
                                            <input type="submit" value="Убрать блюдо из заказа">
                                        </c:if>
                                    </div>
                                </div>
                                <p class="popup-open" about="${meal.id}">${meal.name}</p>
                            </li>
                        </c:forEach>
                            <form action="${pageContext.request.contextPath}/rate_order?chosenOrderId=${order.id}" method="post">
                            <c:if test="${order.clientMark==0}">
                            <div class="rating_block">
                                <input name="rating" value="5" id="rating_5-1" type="radio" />
                                <label for="rating_5-1" class="label_rating"></label>

                                <input name="rating" value="4" id="rating_4-1" type="radio" />
                                <label for="rating_4-1" class="label_rating"></label>

                                <input name="rating" value="3" id="rating_3-1" type="radio" />
                                <label for="rating_3-1" class="label_rating"></label>

                                <input name="rating" value="2" id="rating_2-1" type="radio" />
                                <label for="rating_2-1" class="label_rating"></label>

                                <input name="rating" value="1" id="rating_1-1" type="radio" />
                                <label for="rating_1-1" class="label_rating"></label>
                            </div>
                                <label>
                                    <textarea cols="24" rows="4" placeholder="Оставьте комментарий" name="clientComment"></textarea>
                                </label>
                                <p><input type="submit" value="Оставить отзыв"></p>
                            </form>
                            </c:if>
                        <c:if test="${!order.paid}">
                        <form action="${pageContext.request.contextPath}/cancel_order?chosenOrderId=${order.id}" method="post">
                            <input type="submit" value="Отменить заказ">
                        </form>
                        </c:if>
                    </ul>
                </div>
                </td>
             </c:if>
        </c:forEach>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
