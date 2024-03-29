<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.06.2021
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ShoeStore - suppliers</title>
        <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
        <script>
            <%@include file='../js/adminSuppliersPage.js'%>
            <%@include file='../js/ajaxRequests.js'%>
            <%@include file='../js/validationData.js'%>
        </script>
        <style>
            <%@include file='../css/adminSuppliersPage.css'%>
        </style>
    </head>
    <body>
        <jsp:include page="headerForAdminPanel.jsp" />
        <div id="main-part">
            <h2>Панель управления поставщиками</h2>
            <p><a href="/shoestore/admin/">Назад</a></p>
            <button id="new-supplier">Новый поставщик</button>
            <div id="new-supplier-form"></div>
            <table id="suppliers-table">
                <thead>
                <tr>
                    <td>id</td>
                    <td>Компания</td>
                    <td>Город</td>
                    <td>Страна</td>
                    <td>Адрес</td>
                    <td>Телефон</td>
                    <td>Почтовый индекс</td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="supplier" items="${suppliers}">
                    <tr>
                        <td>${supplier.id}</td>
                        <td>${supplier.company}</td>
                        <td>${supplier.city}</td>
                        <td>${supplier.country}</td>
                        <td>${supplier.address}</td>
                        <td>${supplier.phone}</td>
                        <td>${supplier.postalCode}</td>
                        <td>
                            <p><a href="/shoestore/admin/suppliers/update?id=${supplier.id}">Изменить</a></p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div id="pagination">${pagination}</div>
        </div>
    </body>
</html>
