<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: denisenk00
  Date: 09.05.2021
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ShoeStore - basket</title>
    </head>
    <style>
        <%@include file='../css/basketPage.css' %>
    </style>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script>
        $(function (){
            $("#create-order").click(function(){
                alert("Товар забронирован");
            });
        })
    </script>
    <body>
        <jsp:include page="header.jsp" />
        <div id="main-part">
            <h2>Корзина</h2>
            <c:choose>
                <c:when test="${wishedShoes.isEmpty()}">
                    <p>Упс... Товаров в корзине пока что нет(</p>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                        <tr>
                            <th>Бренд</th>
                            <th>Название</th>
                            <th>Стать</th>
                            <th>Сезон</th>
                            <th>Тип</th>
                            <th>Цвет</th>
                            <th>Размер</th>
                            <th>Цена</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="shoe" items="${wishedShoes}">
                            <c:forEach var="model" items="${models}">
                                <c:if test="${shoe.key == model.id}">
                                    <tr>
                                        <td>${model.brand}</td>
                                        <td>
                                            <a href="/shoestore/store/models/model?id=${model.id}">${model.name}</a>
                                        </td>
                                        <td>${model.gender}</td>
                                        <td>${model.season}</td>
                                        <td>${model.type}</td>
                                        <td>${model.color}</td>
                                        <td>${shoe.value}</td>
                                        <td>${model.price}</td>
                                        <td>
                                            <form action="/shoestore/store/users/removeFromPCart" method="post">
                                                <input type="hidden" name="modelId" value="${model.id}">
                                                <input type="hidden" name="size" value="${shoe.value}">
                                                <button id="remove-button">Удалить</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                    <form action="/shoestore/store/orders/create" method="post">
                        <button id="create-order">Заказать</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
