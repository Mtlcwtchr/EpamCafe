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

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Menu</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
    <script>
        $.fn.fun = function(clientX, clientY) {
            this.css('position', 'fixed').fadeIn();
            this.css('left', clientX);
            this.css('top', clientY);
            document.getSelection()
        }

        $(document).ready(function(){
            $('.p-w-s').fadeOut();

            document.querySelectorAll('.delete-link img').forEach( (item) => {
                item.addEventListener('mouseenter', (ev) => {
                    $('.p-w-s').fun($(ev).clientX, $(ev.clientY));
                    item.setAttribute('src',
                        '${pageContext.servletContext.contextPath}/get_local_image?key=deleting');
                });
                item.addEventListener('mouseleave', () => {
                    $('.p-w-s').fadeOut();
                    item.setAttribute('src',
                        item.getAttribute('about'))
                })
            })

            $('.c-navbar-item').css('width', (100 / document.querySelectorAll('.c-navbar-item').length) + '%')

            if (document.location.search.substring(document.location.search.lastIndexOf('?'), document.location.search.lastIndexOf('='))==='?key') {
                sessionStorage.removeItem('key');
                sessionStorage.setItem('key', document.location.search.substring(document.location.search.lastIndexOf('=')+1, document.location.search.length));
            }

            $('.c-navbar-item-'+sessionStorage.getItem('key')).addClass('c-navbar-item-active');

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

<div class="container">
    <div class="row">
        <table class="c-navbar">
            <tr>
                <c:forEach var="category" items="${categories}">
                    <td class="c-navbar-item c-navbar-item-${category.id}">
                        <a href="${pageContext.request.contextPath}/admin_menu?key=${category.id}" class="invis-ref" style="display: block">
                                ${category.name}
                        </a>
                    </td>
                </c:forEach>
                    <td class="c-navbar-item c-navbar-item-all">
                        <a href="${pageContext.request.contextPath}/admin_menu?key=all" class="invis-ref" style="display: block">
                            <fmt:message key="menu.all"/>
                        </a>
                    </td>
            </tr>
        </table>
    </div>
    <div class="row">
        <table class="menu-table-1">
            <tr>
                <c:forEach var="meal" items="${meals}">
                <c:set var="count" value="${count+1}"/>
                <td class="menu-table-item centered">
                    <div class="form-group">
                        <a class="delete-link" href="${pageContext.request.contextPath}/delete_meal?dkey=${meal.id}&key=${meal.category.id}">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" about="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" class="centered text-center" alt="${meal.name} image" width="320" height="320"/>
                        </a>
                        <div class="intro-text text-center">
                            <form action="${pageContext.request.contextPath}/update_meal?ukey=${meal.id}&key=${meal.category.id}" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="name"></label>
                                        <input type="text" id="name" class="form-control" required value="${meal.name}" placeholder="" name="mealName">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="pictureUrl"></label>
                                        <input type="text" id="pictureUrl" class="form-control" required value="${meal.pictureUrl}" placeholder="" name="mealPictureUrl">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="category"></label>
                                        <select class="form-control" id="category" name="mealCategoryName">
                                            <c:forEach var="category" items="${categories}">
                                                <c:if test="${category==meal.category}">
                                                    <option value="${category.name}" selected class="form-control">${category.name}</option>
                                                </c:if>
                                                <c:if test="${category!=meal.category}">
                                                    <option value="${category.name}" class="form-control">${category.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                        <div class="col-md-6">
                                            <table>
                                                <tr>
                                                    <td style="width: 80%; margin: 0; padding: 0;">
                                                        <label for="price"></label>
                                                        <input type="text" id="price" class="form-control" required value="${meal.price}" placeholder="" name="mealPrice">
                                                    </td>
                                                    <td style="width: 20%; margin: 0; padding: 0;">
                                                        $
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12 centered">
                                        <label for="fieldSubmit"></label>
                                        <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.update"/>"> <a class="info-sign" href="${pageContext.request.contextPath}/admin_meal_info?key=${meal.id}">i</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </td>
                <c:if test="${count%3==0}">
            </tr>
            <tr>
                </c:if>
                </c:forEach>
                    <td class="menu-table-item centered">
                        <div class="form-group">
                            <img src="${pageContext.servletContext.contextPath}/get_local_image?key=adding" class="centered text-center" alt="add image" width="320" height="320"/>
                            <div class="intro-text text-center">
                                <form action="${pageContext.request.contextPath}/save_meal" method="post">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label for="newName"></label>
                                            <input type="text" id="newName" class="form-control" required placeholder="" name="mealName">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label for="newPictureUrl"></label>
                                            <input type="text" id="newPictureUrl" class="form-control" required placeholder="" name="mealPictureUrl">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label for="newCategory"></label>
                                            <select class="form-control" id="newCategory" name="mealCategoryName">
                                                <c:forEach var="category" items="${categories}">
                                                    <c:if test="${category!=meal.category}">
                                                        <option value="${category.name}" class="form-control">${category.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <table>
                                                <tr>
                                                    <td style="width: 80%; margin: 0; padding: 0;">
                                                        <label for="newPrice"></label>
                                                        <input type="text" id="newPrice" class="form-control" required placeholder="" name="mealPrice">
                                                    </td>
                                                    <td style="width: 20%; margin: 0; padding: 0;">
                                                        $
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12 centered">
                                            <label for="fieldSubmitAdding"></label>
                                            <input type="submit" id="fieldSubmitAdding" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.addnew"/>">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
