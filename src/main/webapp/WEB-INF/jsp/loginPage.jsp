<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.06.2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShoeStore - login</title>
    <style>
        <%@include file='../css/registrationPage.css' %>
    </style>
</head>
<body>
    <header>
        <h1 id="logo">ShoeStore</h1>
    </header>
    <div id="main-part">
        <h2>Вход</h2>
        <br>
        <form method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
            <label>Электронная почта:</label>
            <input id="username" name="userName" required/>
            <br>
            <br>
            <label>Пароль:</label>
            <input id="password" type="password" name="password" required/>
            <br>
            <br>
            <button type="submit">Войти</button>
        </form>
        <div id="registration-container">
            <p>У вас нет аккаунта? <a href="/shoestore/authorization/registration">Зарегистрироваться</a>.</p>
        </div>
    </div>
</body>
</html>
