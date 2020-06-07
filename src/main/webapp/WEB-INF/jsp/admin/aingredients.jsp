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
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Ingredients</strong></h2>
    <hr>

    <table class="table">
        <c:forEach var="ingredient" items="${ingredients}">
            <c:set var="count" value="${count+1}"/>
            <c:if test="${count%5!=0}">
                <td>
                    <div class="smallbox">
                    <form action="${pageContext.request.contextPath}/update_ingredient?chosenIngredientId=${ingredient.id}" method="post">
                        <img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" class="centered text-center" alt="${ingredient.name} image" width="128" height="128"/>
                        <label>
                            <input type="text" value="${ingredient.pictureUrl}" placeholder="ingredient picture url" name="ingredientPicUrl">
                        </label>
                        <label>
                            <input type="text" value="${ingredient.name}" placeholder="ingredient name" name="ingredientName">
                        </label>
                        <input type="submit" value="Apply changes">
                    </form>
                        <form action="${pageContext.request.contextPath}/delete_ingredient?chosenIngredientId=${ingredient.id}" method="post">
                            <input type="submit" value="Delete ingredient">
                        </form>
                    </div>
                </td>
            </c:if>
            <c:if test="${count%5==0}">
                </tr>
                <tr>
                <td>
                    <div class="smallbox">
                        <form action="${pageContext.request.contextPath}/update_ingredient?chosenIngredientId=${ingredient.id}" method="post">
                            <img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" class="centered text-center" alt="${ingredient.name} image" width="128" height="128"/>
                            <label>
                                <input type="text" value="${ingredient.pictureUrl}" placeholder="ingredient picture url" name="ingredientPicUrl">
                            </label>
                            <label>
                                <input type="text" value="${ingredient.name}" placeholder="ingredient name" name="ingredientName">
                            </label>
                            <input type="submit" value="Apply changes">
                        </form>
                            <form action="${pageContext.request.contextPath}/delete_ingredient?chosenIngredientId=${ingredient.id}" method="post">
                              <input type="submit" value="Delete ingredient">
                            </form>
                    </div>
                </td>
            </c:if>
        </c:forEach>
    </table>

    <div class="popup-window p-w-0">
        <form action="${pageContext.request.contextPath}/save_ingredient" method="post">
            <div class="close">x</div>
            <div class="popup-inner">
                <label>
                    <input type="text" placeholder="ingredient name" name="ingredientName">
                </label>
                <label>
                    <input type="text" placeholder="ingredient picture url" name="ingredientPicUrl">
                </label>
                <input type="submit" value="Save ingredient">
            </div>
        </form>
    </div>
    <p class="popup-open" about="0">Save new ingredient</p>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
