<%--
  Created by IntelliJ IDEA.
  User: denisenk00
  Date: 25.05.2021
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<html>
    <head>
        <title>ShoeStore - profile</title>
        <style>
            <%@include file='../css/profilePage.css' %>
        </style>
        <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
        <script>
            <%@include file='../js/profilePage.js' %>
            <%@include file='../js/ajaxRequests.js' %>
            <%@include file='../js/validationData.js'%>
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div id="main-part">
            <h2>Профиль</h2>
            <table id="user-info">
                <tr>
                    <td>Имя: </td>
                    <td id="user-name">${user.name}</td>
                </tr>
                <tr>
                    <td>Фамилия: </td>
                    <td id="user-surname">${user.surname}</td>
                </tr>
                <tr>
                    <td>Телефон: </td>
                    <td id="user-phone">${user.phone}</td>
                </tr>
                <tr>
                    <td>Почта: </td>
                    <td id="user-email">${user.email}</td>
                </tr>
            </table>
            <br>
            <button id="edit-info">Изменить данные</button>
            <div id="edit-info-form"></div>
            <br>
            <security:authorize access="hasRole('ADMIN')">
                <form action="/shoestore/store/users/removeFilters" method="get" >
                    <button id="admin-panel">Перейти в админ-панель</button>
                    <input name="goTo" type="hidden" value="adminPanel">
                </form>
            </security:authorize>
            <form action="/shoestore/authorization/logout">
                <button id="logout">Выйти из аккаунта</button>
            </form>
            <c:choose>
                <c:when test="${orders.isEmpty()}">
                    <p>Вы еще не сделали ни одного заказа(</p>
                </c:when>
                <c:otherwise>
                    <div id="orders">
                        <h3>Архив заказов</h3>
                        <table>
                            <thead>
                            <tr>
                                <th>id</th>
                                <th>Дата заказа</th>
                                <th>Стоимость заказа</th>
                                <th>id Товара</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td>${order.id}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.totalPrice}</td>
                                    <td></td>
                                </tr>
                                <c:forEach var="shoeId" items="${order.shoeIdList}">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><a href="/shoestore/store/models/modelByShoe?shoeId=${shoeId}">${shoeId}</a></td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
