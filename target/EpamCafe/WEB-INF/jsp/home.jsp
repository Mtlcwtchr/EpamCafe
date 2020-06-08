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
    <title>Главная</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Главная</h2>
    <hr>
    <p align="center">КриссКросс – это любимое гостями кафе с разнообразной популярной кухней и напитками. Также наше кафе предлагает возможность заказал блюд онлайн для сокращения время ожидания.</p>
        <ul>
            Что значит наше кафе?
            <li>
                Это   комфортные   залы,   располагающие   к   уютным   посиделкам   и душевным разговорам
            </li>
            <li>
                Это разнообразное, сбалансированное меню и демократичные цены, позволяющие каждому выбрать и заказать блюдо по душе
            </li>
            <li>
                Это возможность забрать любимые блюда с собой  при оформление заказ онлайн, или же насладится самой едой в нашем кафе.
            </li>
        </ul>
</div>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Контакты</h2>
    <hr>
    <div class="smallbox">
    <form action="${pageContext.request.contextPath}/leave_comment" method="post">
        <p><label>
            Имя отправителя: <input type="text" value="${actor.name}" placeholder="Ваше имя" name="authorName">
        </label></p>
        <p><label>
            Телефон отправителя: <input type="text" value="${actor.user.phone}" placeholder="Ваш телефон" name="authorPhone">
        </label></p>
        <p><label>
            <textarea placeholder="Ваше сообщение" cols="164" rows="12" name="message">Сообщение отправителя</textarea>
        </label></p>
        <p><label>
            <input type="submit" value="Оставить сообщение">
        </label></p>
    </form>
    </div>
    <div class="smallbox" align="center">
    <table>
        <tr>
            <td>
                Контактный телефон:
                <ul>
                    <li>
                        +(029) 205-76-73
                    </li>
                </ul>
            </td>
            <td>
                EMAIL:
                <ul>
                    <li>
                        nightstand.without.leg@gmail.com
                    </li>
                </ul>
            </td>
            <td>
                Адрес:
                <ul>
                    <li>
                        г.Ивацевичи, ул.Ленина, 51.
                    </li>
                </ul>
            </td>
        </tr>
    </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>