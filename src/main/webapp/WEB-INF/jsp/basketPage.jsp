<%@ page import="ua.edu.j2ee.shoestore.model.Shoe" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.edu.j2ee.shoestore.model.ShoeModel" %>
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
<body>
${fdsf}
    <div id="block-body">
        <header>
            <h1 id="logo">ShoeStore</h1>
            <nav class="navpanel">
                <ul>
                    <li><a href="">logIn/logOut</a></li>
                    <li><a href="#">Корзина</a></li>
                </ul>
            </nav>
        </header>
        <div id="mainpart">
            <h4>Список товаров</h4>
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
                    <%
                        List<Shoe> wishedShoes = (List<Shoe>) request.getSession().getAttribute("wishedShoes");
                        List<ShoeModel> shoeModels = (List<ShoeModel>) request.getSession().getAttribute("models");
                        for (Shoe shoe: wishedShoes) {
                            for (ShoeModel model: shoeModels) {
                                if(shoe.getModelId() == model.getId()){ %>
                                    <tr>
                                        <td><%= model.getBrand() %></td>
                                        <td>
                                            <a href="#"><%= model.getName() %></a>
                                        </td>
                                        <td><%= model.getGender() %></td>
                                        <td><%= model.getSeason() %></td>
                                        <td><%= model.getType() %></td>
                                        <td><%= model.getColor() %></td>
                                        <td><%= shoe.getSize() %></td>
                                        <td><%= model.getPrice() %></td>
                                        <td>
                                            <a href="">Удалить</a>
                                        </td>
                                    </tr>

                    <%            }
                            }

                        }
                    %>

                </tbody>
            </table>
            <button id="book">Заказать</button>
        </div>
    </div>

</body>
</html>
