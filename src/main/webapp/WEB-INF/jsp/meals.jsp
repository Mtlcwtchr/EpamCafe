<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Meals</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong><fmt:message key="meal.label"/></strong></h2>
    <hr>

 <table class="table">
    <c:forEach var="meal" items="${meals}">
    <c:set var="count" value="${count+1}"/>
    <c:if test="${count%5==0}">
        </tr>
        <tr>
        <td>
            <div class="smallbox">
                <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}&category=${meal.category.name}" method="post">
                    <p>${meal.name}</p>
                    <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                    <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/categories" class="invis-ref">${meal.category.name}</a></p>
                    <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
                    <details>
                        <summary><fmt:message key="meal.composition"/></summary>
                    <c:forEach var="ingredient" items="${meal.ingredients}">
                        <c:if test="${ingredient.mass!=0}">
                        <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | ${ingredient.name} | <fmt:message key="ingredient.mass"/>: ${ingredient.mass}</p>
                        </c:if>
                    </c:forEach>
                    </details>
                    <c:if test="${actor!=null}">
                        <p>
                            <input type="submit" value="<fmt:message key="meal.add"/>">
                        </p>
                    </c:if>
                </form>
            </div>
        </td>
     </c:if>
     <c:if test="${count%5!=0}">
         <td>
             <div class="smallbox">
                 <form action="${pageContext.request.contextPath}/add_meal_to_order?chosenMealId=${meal.id}&category=${meal.category.name}" method="post">
                     <p>${meal.name}</p>
                     <img src="${pageContext.servletContext.contextPath}/load_image?url=${meal.pictureUrl}" alt="${meal.name} image" width="128" height="128"/>
                     <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/categories" class="invis-ref">${meal.category.name}</a></p>
                     <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
                     <details>
                         <summary><fmt:message key="meal.composition"/></summary>
                         <c:forEach var="ingredient" items="${meal.ingredients}">
                             <c:if test="${ingredient.mass!=0}">
                                 <p class="popup-inner-ingredient"><img src="${pageContext.servletContext.contextPath}/load_image?url=${ingredient.pictureUrl}" alt="${ingredient.name} image" width="32" height="32"/> | ${ingredient.name} | <fmt:message key="ingredient.mass"/>: ${ingredient.mass}</p>
                             </c:if>
                         </c:forEach>
                     </details>
                     <c:if test="${actor!=null}">
                         <p>
                             <input type="submit" value="<fmt:message key="meal.add"/>">
                         </p>
                     </c:if>
                 </form>
             </div>
         </td>
     </c:if>
    </c:forEach>
</table>
    <div class="popup-window p-w-p smallbox">
        <p class="close">x</p>
        <div class="popup-inner">
            <p>
                <fmt:message key="meal.success"/>
            </p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
