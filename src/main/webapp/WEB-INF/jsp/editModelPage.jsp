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
            <%@include file='../js/editModelPageUI.js'%>
            <%@include file="../js/editModePagelAjax.js"%>
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <p><a href="/admin/allModels">Назад</a></p>
        <h2>${model.brand} ${model.name}</h2>
        <div>
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
                    <td>Цена: </td>
                    <td>${model.price}</td>
                </tr>
            </table>
        </div>
        <div id="model-id"hidden>${model.id}</div>
        <input id="new-price" type="text" placeholder="Новая цена">
        <button id="update-price">Изменить цену</button>
        <div id="new-pair-form">
            <input id="new-pair-size" type="number" placeholder="Размер">
            <select id="new-pair-size-selector">
                <c:forEach var="status" items="${statuses}">
                    <option>${status}</option>
                </c:forEach>
            </select>
        </div>
        <button id="add-shoe">Добавить пару</button>

        <div id="shoes">
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
                            <td id="shoe-id">${shoe.id}</td>
                            <td>${shoe.size}</td>
                            <td>
                                <select id="status-selector">
                                    <c:forEach var="status" items="statuses">
                                        <c:when test="${status.equals(shoe.status)}">
                                            <option selected>${status}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option>${status}</option>
                                        </c:otherwise>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
