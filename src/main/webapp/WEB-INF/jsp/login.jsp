<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.06.2021
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ShoeStore - login</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
        <table>
            <tr>
                <td>Логин: </td>
                <td><input name="userName"/></td>
            </tr>

            <tr>
                <td>Пароль: </td>
                <td><input type="password" name="password" /></td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Login" />
                    <input type="reset" value="Reset" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
