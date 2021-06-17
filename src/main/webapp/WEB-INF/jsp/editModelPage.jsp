<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.06.2021
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>ShoeStore - edit model</title>
        <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
        <script>
            <%@include file='../js/editModelPage.js'%>
            <%@include file="../js/ajaxRequests.js"%>
        </script>
        <style>
            <%@include file='../css/editModelPage.css' %>
        </style>
    </head>
    <body>
        <jsp:include page="headerForAdminPanel.jsp" />
        <h2>${model.brand} ${model.name}</h2>
        <div id="main-part">
            <p><a href="/shoestore/admin/models/">Назад</a></p>
            <div id="description">
                <table>
                    <th>
                        <h3>Описание</h3>
                    </th>
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
                        <td>Цена: </td>
                        <td id="price">${model.price}</td>
                    </tr>
                </table>
            </div>
            <input id="model-id" type="hidden" value="${model.id}">
            <br>
            <h3>Изменить цену</h3>
            <input id="new-price" type="text" placeholder="Новая цена">
            <button id="update-price">Изменить</button>
            <br>
            <div id="new-pair-form">
                <h3>Добавить пару обуви этой модели</h3>
                <input id="new-pair-size" type="number" placeholder="Размер">
                <select id="new-pair-size-selector">
                    <c:forEach var="status" items="${statuses}">
                        <option>${status}</option>
                    </c:forEach>
                </select>
                <br><br>
                <button id="add-shoe">Добавить пару</button>
            </div>
            <div id="shoes">
                <h3>Изменить статус пары обуви этой модели</h3>
                <table>
                    <thead>
                    <tr>
                        <td>id</td>
                        <td>size</td>
                        <td>status</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="shoe" items="${shoes}">
                        <tr>
                            <td class="shoe-id">${shoe.id}</td>
                            <td>${shoe.size}</td>
                            <td>
                                <select class="status-selector" name="${shoe.id}">
                                    <c:forEach var="status" items="${statuses}">
                                        <c:choose>
                                            <c:when test="${status.equals(shoe.status)}">
                                                <option selected>${status}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option>${status}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
