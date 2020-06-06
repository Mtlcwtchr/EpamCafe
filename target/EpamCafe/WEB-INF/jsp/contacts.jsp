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
    <title>Contacts</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center">Contact <strong>us</strong></h2>
    <hr>
    <form action="${pageContext.request.contextPath}/contact_us" method="post">
        <label>
            <textarea placeholder="your message" cols="128" rows="12" name="message">Your message</textarea>
        </label>
        <p><label>
            <input type="submit" value="Leave message">
        </label></p>
    </form>
    <p align="center">You can also contact us using mobile phone by calling: +375295013866</p>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>