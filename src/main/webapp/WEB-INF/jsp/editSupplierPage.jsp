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
    </head>
    <body>
        <jsp:include page="headerForAdminPanel.jsp" />
        <p><a href="/shoestore/admin/allSuppliers">Назад</a></p>

        <form:form action="updateSupplier" method="post" modelAttribute="supplier">
            <form:hidden path="id"></form:hidden>
            <form:label path="company">Компания:</form:label>
            <form:input path="company" id="company"/><br/>

            <form:label path="city">Город:</form:label>
            <form:input path="city" id="city"/><br/>

            <form:label path="country">Страна:</form:label>
            <form:input path="country" id="country"/><br/>

            <form:label path="address">Адрес:</form:label>
            <form:input path="address" id="address"/><br/>

            <form:label path="phone">Телефон:</form:label>
            <form:input path="phone" id="phone"/><br/>

            <form:label path="postalCode">Почтовый индекс:</form:label>
            <form:input path="postalCode" id="postal-code"/><br/>

            <form:button id="submit">Изменить</form:button>
        </form:form>
    </body>
</html>
