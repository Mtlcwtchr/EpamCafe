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
    <title>Our halls</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Our halls</strong></h2>
    <hr>
    <table>
        <c:forEach var="hall" items="${halls}">
            <tr>
                <td>
                    <div class="smallbox">
                        <form action="${pageContext.request.contextPath}/update_hall?chosenHallId=${hall.id}" method="post">
                            <p class="intro-text text-center">
                                <label>Hall <input type="text" value="${hall.id}" name="hallNumber" placeholder="hall number"></label>
                            </p>
                            <p class="intro-text text-center">
                                <label>Guests number: <input type="text" value="${hall.guestsNumber}" name="hallGuestsNumber" placeholder="hall guests number"></label>
                            </p>
                            <p>
                                <label><textarea cols="128" rows="22" name="hallDescription" placeholder="hall description">${hall.hallDescription}</textarea></label>
                            </p>
                            <input type="submit" value="Apply changes">
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>

    <div class="popup-window p-w-0">
        <p class="close">x</p>
        <form action="${pageContext.request.contextPath}/save_hall" method="post">
            <div class="popup-inner">
                <p><label>
                    <input type="text" placeholder="hall number" name="hallNumber">
                </label></p>
                <p><label>
                    <input type="text" placeholder="hall guests number" name="hallGuestsNumber">
                </label></p>
                <p><label>
                    <textarea cols="128" rows="22" placeholder="hall description" name="hallDescription"></textarea>
                </label></p>
                <input type="submit" value="Save hall">
            </div>
        </form>
    </div>
    <p class="popup-open" about="0">Save new hall</p>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>