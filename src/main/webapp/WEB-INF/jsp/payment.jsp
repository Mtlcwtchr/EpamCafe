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
    <title>Оплата заказа</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/commonpopup.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Оплата заказа</strong></h2>
    <hr>
    <div>
        <iframe src="https://money.yandex.ru/quickpay/button-widget?targets=Order%20payment&default-sum=${totalPrice}&button-text=12&any-card-payment-type=on&button-size=m&button-color=black&successURL=localhost:8080${pageContext.servletContext.contextPath}/payment_success&quickpay=small&account=4100115365944689&" width="184" height="36" frameborder="0" allowtransparency="true" scrolling="no"></iframe>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
