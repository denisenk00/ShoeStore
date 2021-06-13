<%--
  Created by IntelliJ IDEA.
  User: denisenk00
  Date: 30.05.2021
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShoeStore - registration</title>
    <style>
        <%@include file='../css/registrationPage.css' %>
    </style>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script>
        <%@include file='../js/registrationPage.js' %>
    </script>
</head>
<body>
<header>
    <h1 id="logo">ShoeStore</h1>
</header>
<div id="mainpart">
    <h2>Регистрация</h2>
    <p>Если вы уже зарегистрированы, нажмите</p>
    <p><a href="/login">ВОЙТИ</a></p>
    <form:form action="registration" method="post" modelAttribute="customUser">
        <form:label path="name">Имя:</form:label>
        <form:input path="name" id="firstname"/><br/>

        <form:label path="surname">Фамилия:</form:label>
        <form:input path="surname" id="surname"/><br/>

        <form:label path="phone">Телефон:</form:label>
        <form:input path="phone" id="phone"/><br/>

        <form:label path="email">E-mail:</form:label>
        <form:input path="email" id="email"/><br/>

        <form:label path="password">Пароль:</form:label>
        <form:input path="password" id="password"/><br/>

        <form:button id="register">Зарегистрироваться</form:button>
    </form:form>
</div>
</body>
</html>
