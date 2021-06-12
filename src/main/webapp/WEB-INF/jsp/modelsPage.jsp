<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.06.2021
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>ShoeStore - product management</title>
        <style>
            <%@include file='../css/mainPage.css' %>
        </style>
        <script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
        <script>
            <%@include file='../js/checkBoxSelectList.js'%>
            <%@include file='../js/modelsPageUI.js' %>
            <%@include file='../js/mainPageUI.js'%>
            <%@include file='../js/ajaxRequests.js'%>
        </script>
    </head>
    <body>
    <div id="page-location"hidden>modelsPage</div>
        <jsp:include page="headerForAdminPanel.jsp" />
        <div class="filters">
            <h3>Сезон</h3>
            <div class="checkselect">
                <c:choose>
                    <c:when test="${wishedSeasons.contains('Зима')}">
                        <label><input class="season" type="checkbox" value="Зима" checked>Зима</label>
                    </c:when>
                    <c:otherwise>
                        <label><input class="season" type="checkbox" value="Зима">Зима</label>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${wishedSeasons.contains('Весна/Осень')}">
                        <label><input class="season" type="checkbox" value="Весна/Осень" checked>Весна/Осень</label>
                    </c:when>
                    <c:otherwise>
                        <label><input class="season" type="checkbox" value="Весна/Осень">Весна/Осень</label>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${wishedSeasons.contains('Лето')}">
                        <label><input class="season" type="checkbox" value="Лето" checked>Лето</label>
                    </c:when>
                    <c:otherwise>
                        <label><input class="season" type="checkbox" value="Лето">Лето</label>
                    </c:otherwise>
                </c:choose>
            </div>
            <h3>Тип</h3>
            <div class="checkselect">
                <c:forEach var="type" items="${allTypes}">
                    <c:choose>
                        <c:when test="${wishedTypes.contains(type)}">
                            <label><input class="type" type="checkbox" value="${type}" checked>${type}</label>
                        </c:when>
                        <c:otherwise>
                            <label><input class="type" type="checkbox" value="${type}">${type}</label>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <h3>Бренд</h3>
            <div class="checkselect">
                <c:forEach var="brand" items="${allBrands}">
                    <c:choose>
                        <c:when test="${wishedBrands.contains(brand)}">
                            <label><input class="brand" type="checkbox" value="${brand}" checked>${brand}</label>
                        </c:when>
                        <c:otherwise>
                            <label><input class="brand" type="checkbox" value="${brand}">${brand}</label>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <h3>Размер</h3>
            <div class="checkselect">
                <c:forEach var="size" items="${allSizes}">
                    <c:choose>
                        <c:when test="${wishedSizes.contains(size)}">
                            <label><input class="size" type="checkbox" value="${size}" checked>${size}</label>
                        </c:when>
                        <c:otherwise>
                            <label><input class="size" type="checkbox" value="${size}">${size}</label>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <h3>Цвет</h3>
            <div class="checkselect">
                <c:forEach var="color" items="${allColors}">
                    <c:choose>
                        <c:when test="${wishedColors.contains(color)}">
                            <label><input class="color" type="checkbox" value="${color}" checked>${color}</label>
                        </c:when>
                        <c:otherwise>
                            <label><input class="color" type="checkbox" value="${color}">${color}</label>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <h3>Стать</h3>
            <div class="checkselect">
                <c:choose>
                    <c:when test="${wishedGenders.contains('Мужская')}">
                        <label><input class="gender" type="checkbox" value="Мужская" checked>Мужская</label>
                    </c:when>
                    <c:otherwise>
                        <label><input class="gender" type="checkbox" value="Мужская">Мужская</label>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${wishedGenders.contains('Женская')}">
                        <label><input class="gender" type="checkbox"  value="Женская" checked>Женская</label>
                    </c:when>
                    <c:otherwise>
                        <label><input class="gender" type="checkbox"  value="Женская">Женская</label>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${wishedGenders.contains('Унисекс')}">
                        <label><input class="gender" type="checkbox"  value="Унисекс" checked>Унисекс</label>
                    </c:when>
                    <c:otherwise>
                        <label><input class="gender" type="checkbox"  value="Унисекс">Унисекс</label>
                    </c:otherwise>
                </c:choose>
            </div>
            <h3>Цена</h3>
            <div class="price-area">
                <c:choose>
                    <c:when test="${wishedMinPrice != 0}">
                        <input id="minprice" value="${wishedMinPrice}" type="number" name="minPrice" min="0" placeholder="от">
                    </c:when>
                    <c:otherwise>
                        <input id="minprice" value="${minPrice}" type="number" name="minPrice" min="0" placeholder="от">
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${wishedMaxPrice != 0}">
                        <input id="maxprice" value="${wishedMaxPrice}" type="number" name="maxPrice" min="0" placeholder="до">
                    </c:when>
                    <c:otherwise>
                        <input id="maxprice" value="${maxPrice}" type="number" name="maxPrice" min="0" placeholder="до">
                    </c:otherwise>
                </c:choose>
            </div>
            <button id="filterbutton">Фильтровать</button>
        </div>
        <div id="main-part">
            <p><a href="/store/adminPanel">Назад</a></p>
            <h2>Панель управления товаром</h2>
            <button id="new-model">Новая модель</button>
            <div id="new-model-form"></div>
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
                                <a href="/admin/model?id=${model.id}">${model.name}</a>
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
            <div id="pagination">${pagination}</div>
        </div>
    </body>
</html>
