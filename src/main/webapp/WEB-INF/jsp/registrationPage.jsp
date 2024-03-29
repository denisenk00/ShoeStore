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
            <%@include file='../js/validationData.js'%>
            <%@include file='../js/ajaxRequests.js' %>

        </script>
    </head>
    <body>
        <header>
            <h1 id="logo">ShoeStore</h1>
        </header>
        <div id="main-part">
            <h2>Регистрация</h2>
            <p>Если вы уже зарегистрированы, нажмите <a href="/shoestore/authorization/login">войти</a></p>
            <br>
            <form:form action="/shoestore/authorization/registration" method="post" modelAttribute="user">
                <form:label path="name">Имя:</form:label>
                <form:input path="name" id="firstname"/><br/>
                <br>
                <form:label path="surname">Фамилия:</form:label>
                <form:input path="surname" id="surname"/><br/>
                <br>
                <form:label path="phone">Телефон:</form:label>
                <form:input path="phone" id="phone"/><br/>
                <br>
                <form:label path="email">E-mail:</form:label>
                <form:input path="email" id="email"/><br/>
                <br>
                <form:label path="password">Пароль:</form:label>
                <form:input path="password" id="password"/><br/>
                <br>
                <form:button id="register">Зарегистрироваться</form:button>
            </form:form>
        </div>
    </body>
</html>
