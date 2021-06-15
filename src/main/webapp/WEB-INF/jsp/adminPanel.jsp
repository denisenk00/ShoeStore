<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.06.2021
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ShoeStore - admin panel</title>
    </head>
    <body>
        <jsp:include page="headerForAdminPanel.jsp" />
        <h2>Админ панель</h2>
        <div id="options">
            <ul>
                <li><a href="/shoestore/admin/allModels">Управление продукцией</a></li>
                <li><a href="/shoestore/admin/allSuppliers">Управление поставщиками</a></li>
                <li><a href="/shoestore/admin/allUsers">Управление пользователями</a></li>
            </ul>
        </div>
    </body>
</html>
