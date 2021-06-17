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
        <style>
            #main-part{
                margin-left: 10%;
            }
            h2{
                margin-bottom: 30px;
            }
            li {
                list-style-type: none;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="headerForAdminPanel.jsp" />
        <div id="main-part">
            <h2>Админ панель</h2>
            <div id="options">
                <ul>
                    <li><form method="get" action="/shoestore/admin/models"> <button>Управление продукцией</button></form></li>
                    <li><form method="get" action="/shoestore/admin/suppliers"> <button>Управление поставщиками</button></form></li>
                    <li><form method="get" action="/shoestore/admin/users"> <button>Управление пользователями</button></form></li>
                </ul>
            </div>
        </div>
    </body>
</html>
