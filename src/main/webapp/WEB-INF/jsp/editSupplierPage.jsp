<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.06.2021
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ShoeStore - edit supplier</title>
        <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
        <script>
            <%@include file='../js/ajaxRequests.js'%>
            <%@include file='../js/editSupplierPage.js'%>
            <%@include file='../js/validationData.js'%>
        </script>
        <style>
            h2{text-align: center}
            #main-part{
                margin-left: 10%;
            }
            table tr{
                height: 25px;
            }
            #edit{
                margin: 30px;
                width: 200px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="headerForAdminPanel.jsp" />
        <div id="main-part">
            <h2>Изменение данных поставщика</h2>
            <p><a href="/shoestore/admin/suppliers">Назад</a></p>
            <input type="hidden" id="supplier-id" value="${supplier.id}">
            <table>
                <tr>
                    <td>Компания:</td>
                    <td>
                        <input id="company" value="${supplier.company}">
                    </td>
                </tr>
                <tr>
                    <td>Город:</td>
                    <td>
                        <input id="city" value="${supplier.city}">
                    </td>
                </tr>
                <tr>
                    <td>Страна:</td>
                    <td>
                        <input id="country" value="${supplier.country}">
                    </td>
                </tr>
                <tr>
                    <td>Адрес:</td>
                    <td>
                        <input id="address" value="${supplier.address}">
                    </td>
                </tr>
                <tr>
                    <td>Телефон:</td>
                    <td>
                        <input id="phone" value="${supplier.phone}">
                    </td>
                </tr>
                <tr>
                    <td>Почтовый индекс:</td>
                    <td>
                        <input id="postalCode" value="${supplier.postalCode}">
                    </td>
                </tr>
            </table>
            <button id="edit">Изменить</button>
        </div>
    </body>
</html>
