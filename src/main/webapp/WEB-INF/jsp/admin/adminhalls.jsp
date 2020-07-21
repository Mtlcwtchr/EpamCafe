<%--
  Created by IntelliJ IDEA.
  User: St.Anislav
  Date: 5/20/2020
  Time: 4:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-16" pageEncoding="UTF-16" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Halls</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><strong><fmt:message key="admin.halls"/></strong></h2>
        <hr>
    </div>
    <div class="row">
        <table class="menu-table-1">
            <tr>
                <c:forEach var="hall" items="${halls}">
                <c:set var="count" value="${count+1}"/>
                <td class="menu-table-item centered">
                    <div class="form-group"  id="form-wrap">
                        <div class="row form-group">
                            <div class="col-md-12 centered">
                                <label for="fieldSubmit"></label>
                                <a href="${pageContext.request.contextPath}/admin_reservation?hkey=${hall.id}" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg"><fmt:message key="admin.hallReservations"/></a>
                            </div>
                        </div>
                        <form action="${pageContext.request.contextPath}/update_hall?ukey=${hall.id}" method="post">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="hallId"><fmt:message key="halls.hall"/></label>
                                    <input type="text" id="hallId" class="form-control" required value="${hall.id}" placeholder="" name="hallId">
                                </div>
                            </div>
                            <table>
                                <tr>
                                    <td>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <label for="hallName"><fmt:message key="halls.name"/></label>
                                                <input type="text" id="hallName" class="form-control" required value="${hall.name}" placeholder="" name="hallName">
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <label for="hallGuestsNumber"><fmt:message key="halls.for"/></label>
                                                <input type="text" id="hallGuestsNumber" class="form-control" required value="${hall.guestsNumber}" placeholder="" name="hallGuestsNumber"> <fmt:message key="halls.ppl"/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="hallDescription"><fmt:message key="halls.description"/></label>
                                        <textarea name="hallDescription" id="hallDescription" required cols="25" rows="10" class="form-control">${hall.description}</textarea>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.update"/>">
                                </div>
                            </div>
                        </form>
                        <form action="${pageContext.request.contextPath}/delete_hall?dkey=${hall.id}" method="post">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.delete"/>">
                                </div>
                            </div>
                        </form>
                    </div>
                </td>
                <c:if test="${count%3==0}">
            </tr>
            <tr>
                </c:if>
                </c:forEach>
                    <td class="menu-table-item centered">
                        <div class="form-group white-wrap ">
                            <form action="${pageContext.request.contextPath}/save_hall" method="post">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label for="newHallId"><fmt:message key="halls.hall"/></label>
                                        <input type="text" id="newHallId" class="form-control" required  placeholder="" name="hallId">
                                    </div>
                                </div>
                                <table>
                                    <tr>
                                        <td>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <label for="newHallName"><fmt:message key="halls.name"/></label>
                                                    <input type="text" id="newHallName" class="form-control" required placeholder="" name="hallName">
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <label for="newHallGuestsNumber"><fmt:message key="halls.for"/></label>
                                                    <input type="text" id="newHallGuestsNumber" class="form-control" required placeholder="" name="hallGuestsNumber"> <fmt:message key="halls.ppl"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label for="newHallDescription"><fmt:message key="halls.description"/></label>
                                        <textarea name="hallDescription" id="newHallDescription" required cols="25" rows="10" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.addnew"/>">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>