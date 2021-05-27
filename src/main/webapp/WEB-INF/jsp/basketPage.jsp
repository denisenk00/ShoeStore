<%@ page import="ua.edu.j2ee.shoestore.model.Shoe" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.edu.j2ee.shoestore.model.ShoeModel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denisenk00
  Date: 09.05.2021
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShoeStore - basket</title>
</head>
<style>
    <%@include file='../css/basketPage.css' %>
</style>
<body>
    <div id="block-body">
        <header>
            <h1 id="logo"><a href="index?page=1">ShoeStore</a></h1>
            <nav class="navpanel">
                <ul>
                    <li><a href="">logIn/logOut</a></li>
                    <li><a href="#">Корзина</a></li>
                </ul>
            </nav>
        </header>
        <div id="mainpart">
            <h2>Корзина</h2>
            <table>
                <thead>
                    <tr>
                        <th>Бренд</th>
                        <th>Название</th>
                        <th>Стать</th>
                        <th>Сезон</th>
                        <th>Тип</th>
                        <th>Цвет</th>
                        <th>Размер</th>
                        <th>Цена</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="shoe" items="${wishedShoes}">
                        <c:forEach var="model" items="${models}">
                            <c:if test="${model.id == shoe.modelId}">
                                <tr>
                                    <td>${model.brand}</td>
                                    <td>
                                        <a href="#">${model.name}</a>
                                    </td>
                                    <td>${model.gender}</td>
                                    <td>${model.season}</td>
                                    <td>${model.type}</td>
                                    <td>${model.color}</td>
                                    <td>${shoe.size}</td>
                                    <td>${model.price}</td>
                                    <td>
                                        <a href="#">Удалить</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
            <button id="book">Заказать</button>
        </div>
    </div>

</body>
</html>