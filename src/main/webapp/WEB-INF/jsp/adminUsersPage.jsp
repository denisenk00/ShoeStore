<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 15.06.2021
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>ShoeStore - user management</title>
        <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
        <script>
            <%@include file='../js/ajaxRequests.js'%>
            <%@include file="../js/adminUsersPage.js"%>
        </script>
        <style>
            <%@include file='../css/adminUsersPage.css'%>
        </style>
    </head>
    <body>
        <jsp:include page="headerForAdminPanel.jsp" />
        <div id="main-part">
            <h2>Панель управления пользователями</h2>
            <p><a href="/shoestore/admin/">Назад</a></p>
            <table>
                <thead>
                <tr>
                    <td>Имя</td>
                    <td>Фамилия</td>
                    <td>Телефон</td>
                    <td>E-mail</td>
                    <td>Роль</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>
                            <select class="roles-selector" name="${user.id}">
                                <c:choose>
                                    <c:when test="${user.role.equals('USER')}">
                                        <option selected>USER</option>
                                        <option>ADMIN</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>USER</option>
                                        <option selected>ADMIN</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
