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
    <title>Home</title>
    <script>
        $.fn.popup = function() { 	//функция для открытия всплывающего окна:
            this.css('position', 'absolute').fadeIn();	//задаем абсолютное позиционирование и эффект открытия
            //махинации с положением сверху:учитывается высота самого блока, экрана и позиции на странице:
            this.css('top', ($(window).height() - this.height()) / 2 + $(window).scrollTop() + 'px');
            //слева задается отступ, учитывается ширина самого блока и половина ширины экрана
            this.css('left', ($(window).width() - this.width()) / 2  + 'px');
        }
        $.fn.popout = function() {
            this.css().fadeOut();
        }
        $(document).ready(function(){

            $('.datebox').popout();

            document.querySelectorAll('.table-d-op').forEach((link)=>{link.addEventListener('click',
                ()=>{
                $('.datebox').popout();
                $('.db-'+link.getAttribute('about')).popup()})})

        });
    </script>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong>Resrvation</strong></h2>
    <hr>
    <form action="${pageContext.request.contextPath}/place_reservation" method="post">
        <label>
            <select>
            <c:forEach var="tableNo" begin="1" end="${tablesQuantity}">
                <option class="$table-d-op" value="${tableNo}" name="tablesNo" about="${tableNo}">Table ${tableNo}</option>
                <div class="datebox db-${tableNo} smallbox">
                    <hr>
                    <label>
                        Reserve from: <input type="datetime-local" required name="dateFrom" min="${minOrderFromDate}" max="${maxOrderFromDate}">
                    </label>
                    <hr>
                    <label>
                        Reserve from: <input type="datetime-local" required name="dateTo" min="${minOrderToDate}" max="${maxOrderToDate}">
                    </label>
                    <hr>
                    <label>
                        <input type="submit" value="Ask for reservation" align="center">
                    </label>
                </div>
            </c:forEach>
            </select>
        </label>
    </form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>