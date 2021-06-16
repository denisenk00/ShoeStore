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
        <title>ShoeStore</title>
        <style>
            <%@include file='../css/shoeModelPage.css' %>
        </style>
        <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
        <script>
            <%@include file='../js/shoeModelPage.js' %>
            <%@include file='../js/ajaxRequests.js'%>
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div id="main-part">
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
            <input type="hidden" id="model-id" value="${model.id}">
            <button id="addToBasket">Добавить в корзину</button>
        </div>
    </body>
</html>
