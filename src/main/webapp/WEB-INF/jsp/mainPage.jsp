<%-- Created by IntelliJ IDEA.
  User: denisenk00
  Date: 09.05.2021
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ShoeStore</title>
    <style>
        <%@include file='../css/mainPage.css' %>
    </style>
    <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
    <script>
        <%@include file='../js/mainPageUI.js' %>
        <%@include file='../js/mainPageAjax.js'%>
    </script>
</head>
<body>
    <div id="block-body">
        <header>
            <h1 id="logo">ShoeStore</h1>
            <nav class="navpanel">
                <ul>
                    <li><a href="">logIn/logOut</a></li>
                    <li><a href="basketPage.jsp">Корзина</a></li>
                </ul>
            </nav>
        </header>

        <div class="filters">
            <h3>Сезон</h3>
            <div class="checkselect">
                <label><input class="season" type="checkbox" value="Зима">Зима</label>
                <label><input class="season" type="checkbox" value="Весна/Осень">Весна/Осень</label>
                <label><input class="season" type="checkbox" value="Лето">Лето</label>
            </div>
            <h3>Тип</h3>
            <div class="checkselect">
                <c:forEach var="type" items="${allTypes}">
                    <label><input class="type" type="checkbox" value="${type}">${type}</label>
                </c:forEach>
            </div>
            <h3>Бренд</h3>
            <div class="checkselect">
                <c:forEach var="brand" items="${allBrands}">
                    <label><input class="brand" type="checkbox" value="${brand}">${brand}</label>
                </c:forEach>
            </div>
            <h3>Размер</h3>
            <div class="checkselect">
                <c:forEach var="size" items="${allSizes}">
                    <label><input class="size" type="checkbox" value="${size}">${size}</label>
                </c:forEach>
            </div>
            <h3>Цвет</h3>
            <div class="checkselect">
                <c:forEach var="color" items="${allColors}">
                    <label><input class="color" type="checkbox" value="${color}">${color}</label>
                </c:forEach>
            </div>
            <h3>Стать</h3>
            <div class="checkselect">
                <label><input class="gender" type="checkbox" value="Male">Male</label>
                <label><input class="gender" type="checkbox"  value="Female">Female</label>
            </div>
            <h3>Цена</h3>
            <div class="price-area">
                <input id="minprice" value="${minPrice}" type="number" name="minPrice" min="0" placeholder="от">
                <input id="maxprice" value="${maxPrice}" type="number" name="maxPrice" min="0" placeholder="до">
            </div>
            <button id="filterbutton">Фильтровать</button>
        </div>
        <div id="mainpart">
            <h2>Shoes</h2>
            <br>
            <table>
                <thead>
                    <tr>
                      <th>Бренд</th>
                      <th>Название</th>
                      <th>Стать</th>
                      <th>Сезон</th>
                      <th>Тип</th>
                      <th>Цвет</th>
                      <th>Цена</th>
                    </tr>
                </thead>
                <tbody id="models">


                    <c:forEach var="model" items="${allShoeModel}">
                       <tr>
                          <td>${model.brand}</td>
                           <td>
                               <a href="shoeModel/${model.id}">${model.name}</a>
                           </td>
                           <td>${model.gender}</td>
                           <td>${model.season}</td>
                           <td>${model.type}</td>
                           <td>${model.color}</td>
                           <td>${model.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="pagination">${pagination}</div>
    </div>
</body>
</html>
