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
    <title>ShoeStore - Profile</title>
    <style>
        <%@include file='../css/profilePage.css' %>
    </style>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script>
        <%@include file='../js/profilePageUI.js' %>
        <%@include file='../js/profilePageAjax.js' %>
    </script>
</head>
<body>
    <div id="block-body">
         <header>
             <h1 id="logo"><a href="/store/">ShoeStore</a></h1>
             <nav class="navpanel">
                <ul>
                    <li><a href="/store/profile">Профиль</a></li>
                    <li><a href="/store/basket">Корзина</a></li>
                </ul>
            </nav>
        </header>
        <div id="mainpart">
            <h2>Профиль</h2>
            <table id="customUser-info">
                <tr>
                    <td>Имя: </td>
                    <td id="customUser-name">${customUser.name}</td>
                </tr>
                <tr>
                    <td>Фамилия: </td>
                    <td id="customUser-surname">${customUser.surname}</td>
                </tr>
                <tr>
                    <td>Телефон: </td>
                    <td id="customUser-phone">${customUser.phone}</td>
                </tr>
                <tr>
                    <td>Почта: </td>
                    <td id="customUser-email">${customUser.email}</td>
                </tr>
            </table>
            <br>
            <button id="edit-info">Изменить данные</button>
            <security:authorize access="hasRole('ADMIN')">
                <button id="admin-panel">Перейти в админ-панель</button>
            </security:authorize>
            <button id="logout">Выйти из аккаунта</button>
            <div id="edit-info-form"></div>
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
                                     <td><a href="/modelByShoe?shoeId=${shoeId}">${shoeId}</a></td>
                                 </tr>
                             </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
