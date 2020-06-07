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
    <title>История заказов клиента</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>История заказов клиента</strong></h2>
    <hr>

    <ul>
        <c:forEach var="order" items="${orders}">
            <li>
            <div class="popup-window p-w-${order.id}">
                <p class="close">x</p>
                <div class="popup-inner">
                    <p>Заказчик: <a class="invis-ref" href="${pageContext.request.contextPath}/aclients?open=${order.customer.id}">Id: ${order.customer.id} | ${order.customer.name}</a></p>
                <hr><ul>
                    <c:forEach var="meal" items="${order.meals}">
                    <li>
                        <a class="invis-ref" href="${pageContext.request.contextPath}/meals?open=${meal.id}">${meal.name}</a>
                    </li>
                    </c:forEach>
                </ul><hr>

                    <form action="${pageContext.request.contextPath}/update_order?chosenOrderId=${order.id}" method="post">
                        <label>
                            <c:if test="${order.paid}">
                                <input type="checkbox" name="params" value="isPaid" checked/>
                            </c:if>
                            <c:if test="${!order.paid}">
                                <input type="checkbox" name="params" value="isPaid"/>
                            </c:if>
                            <a>Оплачен </a>
                        </label>
                        <a>|</a>
                        <label>
                            <c:if test="${order.prepared}">
                                <input type="checkbox" name="params" value="isPrepared" checked/>
                            </c:if>
                            <c:if test="${!order.prepared}">
                                <input type="checkbox" name="params" value="isPrepared"/>
                            </c:if>
                            <a>Готов </a>
                        </label>
                        <a>|</a>
                        <label>
                            <c:if test="${order.taken}">
                                <input type="checkbox" name="params" value="isTaken" checked/>
                            </c:if>
                            <c:if test="${!order.taken}">
                                <input type="checkbox" name="params" value="isTaken"/>
                            </c:if>
                            <a>Получен </a>
                        </label>
                        <input type="submit" value="Сохранить изменения">
                    </form>
                </div>
            </div>
                <p class="popup-open" about="${order.id}">Заказ на дату: ${order.orderDate}</p>
            </li>
        </c:forEach>
    </ul>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
