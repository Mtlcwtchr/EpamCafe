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
    <title>Categories</title>
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

        $(document).ready(function () {
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
        })
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>


<div class="container">
    <div class="row">
        <table class="menu-table-1">
            <tr>
                <c:forEach var="category" items="${categories}">
                <c:set var="count" value="${count+1}"/>
                <td class="menu-table-item centered">
                    <div class="form-group">
                        <a class="delete-link" href="${pageContext.request.contextPath}/delete_category?key=${category.id}">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${category.pictureUrl}" about="${pageContext.servletContext.contextPath}/get_remote_image?url=${category.pictureUrl}" class="centered text-center" alt="${category.name} image" width="320" height="320"/>
                        </a>
                        <div class="intro-text text-center">
                            <form action="${pageContext.request.contextPath}/update_category?key=${category.id}" method="post">
                                <table>
                                    <tr>
                                        <td style="margin: 0; padding: 0; width: 80%;">
                                            <div class="col-md-12" style="padding: 0;">
                                                <label for="name"></label>
                                                <input type="text" id="name" class="form-control" required value="${category.name}" placeholder="" name="categoryName">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label for="pictureUrl"></label>
                                        <input type="text" id="pictureUrl" class="form-control" required value="${category.pictureUrl}" placeholder="" name="categoryPictureUrl">
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12 centered">
                                        <label for="fieldSubmit"></label>
                                        <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.update"/>">
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
                                <form action="${pageContext.request.contextPath}/save_category?key=${category.id}" method="post">
                                    <table>
                                    <tr>
                                        <td style="margin: 0; padding: 0; width: 80%;">
                                            <div class="col-md-12" style="padding: 0;">
                                                <label for="newName"></label>
                                                <input type="text" id="newName" class="form-control" required value="${category.name}" placeholder="" name="categoryName">
                                            </div>
                                        </td>
                                    </tr>
                                    </table>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <label for="newPictureUrl"></label>
                                            <input type="text" id="newPictureUrl" class="form-control" required placeholder="" name="categoryPictureUrl">
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
    <div class="p-w-s">
        <a>Are you sure want to delete this category?</a>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
