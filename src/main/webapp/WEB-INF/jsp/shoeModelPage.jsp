<%--
  Created by IntelliJ IDEA.
  User: denisenk00
  Date: 25.05.2021
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Title</title>
        <style>
            <%@include file='../css/shoeModelPage.css' %>
        </style>
    </head>
    <body>
        <div id="block-body">
            <header>
                <h1 id="logo"><a href="index?page=1">ShoeStore</a></h1>
                <nav class="navpanel">
                    <ul>
                        <li><a href="#">logIn/logOut</a></li>
                        <li><a href="#">Корзина</a></li>
                    </ul>
                </nav>
            </header>
            <div id="mainpart">
                <h2>${model.brand} ${model.name}</h2>
                <h3>Описание</h3>
                <table id="description">
                    <tr>
                        <td>Название: </td>
                        <td>${model.name}</td>
                    </tr>
                    <tr>
                        <td>Бренд: </td>
                        <td>${model.brand}</td>
                    </tr>
                    <tr>
                        <td>Тип: </td>
                        <td>${model.type}</td>
                    </tr>
                    <tr>
                        <td>Сезон: </td>
                        <td>${model.season}</td>
                    </tr>
                    <tr>
                        <td>Цвет: </td>
                        <td>${model.color}</td>
                    </tr>
                    <tr>
                        <td>Стать: </td>
                        <td>${model.gender}</td>
                    </tr>
                    <tr>
                        <td><p>Доступные размеры:</p></td>
                        <td>
                            <select id="size-selector">
                                <c:forEach var="size" items="${modelSizes}">
                                    <option>${size}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <button id="addToBasket">Добавить в корзину</button>
            </div>
        </div>
    </body>
</html>
