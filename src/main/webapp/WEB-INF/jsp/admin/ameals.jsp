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
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
    <script>
        $(document).ready(function(){

        document.querySelectorAll('.set-old').forEach((element)=>{element.addEventListener('click',
            ()=>{document
                .getElementById('mass-'+element.getAttribute('about')
                    .substring(0, element.getAttribute('about').lastIndexOf('?')))
                    .value = element
                                .getAttribute('about')
                                .substring(element.getAttribute('about').lastIndexOf('?')+1, element.getAttribute('about').length);})
        });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Блюда</strong></h2>
    <hr>

    <ul>
        <c:forEach var="meal" items="${meals}">
            <li>
                <div>
                    <div class="popup-window p-w-${meal.id}">
                        <p class="close">x</p>
                            <div class="popup-inner">
                            <form action="${pageContext.request.contextPath}/update_meal?chosenMealId=${meal.id}" method="post">
                            <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                            <label>
                                <input type="text" value="${meal.pictureUrl}" placeholder="meal picture url" name="mealPicUrl">
                            </label>
                            <label>
                                Имя: <input type="text" value="${meal.name}" placeholder="meal name" name="mealName">
                            </label>
                            <label>
                                <select name="category">
                                    <c:forEach var="category" items="${categories}">
                                        <c:if test="${category.name==meal.category.name}">
                                            <option selected value="${category.name}" name="mealCategory">${category.name}</option>
                                        </c:if>
                                        <c:if test="${category.name!=meal.category.name}">
                                            <option value="${category.name}" name="mealCategory">${category.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </label>
                            <label>
                                Ценой: <input type="text" value="${meal.price}" placeholder="price" name="mealPrice"> рос. руб.
                            </label>
                                <div>Состав: </div>
                                <c:forEach var="ingredient" items="${ingredients}">
                                    <div>
                                        <label>
                                        <input type="text" value="${ingredient.name}" name="ingredient" readonly>
                                        </label>
                                        <label>
                                            <input id="mass-${meal.id}-${ingredient.id}" type="text" value="0" name="${ingredient.name}NewMass" placeholder="ingredient mass">
                                        </label>
                                        <c:forEach var="ingredientOfMeal" items="${meal.ingredients}">
                                            <c:if test="${ingredient.name == ingredientOfMeal.name}">
                                                <a class="set-old invis-ref" about="${meal.id}-${ingredient.id}?${ingredientOfMeal.mass}">Old value: ${ingredientOfMeal.mass}</a>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                                <input type="submit" value="Сохранить изменения">
                                </form>
                                <form action="${pageContext.request.contextPath}/delete_meal?chosenMealId=${meal.id}" method="post">
                                    <input type="submit" value="Удалить блюдо">
                                </form>
                            </div>
                    </div>
                </div>
                <hr>
                    <p class="popup-open" about="${meal.id}">Id:${meal.id} | Блюдо: ${meal.name}</p>
                <hr>
            </li>
        </c:forEach>
    </ul>

    <div class="popup-window p-w-0">
        <p class="close">x</p>
        <form action="${pageContext.request.contextPath}/save_meal" method="post">
            <div class="popup-inner">
                <label>
                    <input type="text" placeholder="meal name" name="mealName">
                </label>
                <label>
                    <input type="text" placeholder="meal picture url" name="mealPicUrl">
                </label>
                <label>
                    <select name="category">
                        <c:forEach var="category" items="${categories}">
                            <option  value="${category.name}" name="mealCategory">${category.name}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    <input type="text" placeholder="price" name="mealPrice">
                </label>
                <div>Состав: </div>
                <c:forEach var="ingredient" items="${ingredients}">
                    <div>
                        <label>
                            <input type="text" value="${ingredient.name}" name="ingredient" readonly>
                        </label>
                        <label>
                            <input type="text" value="0" name="${ingredient.name}NewMass" placeholder="ingredient mass">
                        </label>
                    </div>
                </c:forEach>
                <input type="submit" value="Добавить блюдо">
            </div>
        </form>
    </div>
    <p class="popup-open" about="0">Добавить новое блюдо</p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
