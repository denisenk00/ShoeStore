<%--
  Created by IntelliJ IDEA.
  User: denisenk00
  Date: 30.05.2021
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShoeStore - login</title>
    <style>
        <%@include file='../css/registrationPage.css' %>
    </style>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script>
        <%@include file='../js/registrationPage.js' %>
    </script>
</head>
<body>
<header style="height: 80px; background-color:black; width: auto;">
    <h1 id="logo">ShoeStore</h1>
</header>
<div id="mainpart">
    <h2>Регистрация</h2>
    <p>Если вы уже зарегистрированы, нажмите <a a="/">войти</a></p>
    <form action="/register">
        <label>Имя</label>
        <input type="text" id="firstname" placeholder= "Имя" required />
        <label>Фамилия</label>
        <input type="text" id="surname" placeholder= "Фамилия" required />
        <label>Телефон</label>
        <input type="text" id="phone" placeholder= "Телефон" required />
        <label>Електронная почта</label>
        <input type="text" id="email" placeholder= "Почта" required />
        <label>Пароль</label>
        <input type="text" id="password" placeholder= "Пароль" required />
        <br>
        <input type="submit" id="register" value="Зарегистрироваться">
    </form>
</div>
</body>
</html>
