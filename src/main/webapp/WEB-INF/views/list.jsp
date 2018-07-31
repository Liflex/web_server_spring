<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="ru.dmitartur.model.User" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  ru.arthur.webserver.model
  Date: 23.06.2018
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>Users list</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Users</h2>
        </div>
        <form method="Post">
            Name: <input type="text" name="name" required/>
            Login: <input type="text" name="login" required/>
            Password : <input type="text" name="pass" required/>
            <input type="submit" formaction="/admin/add" value="add new User">
        </form>
        <ul>
            <ul class="w3-ul">
                <c:forEach items="${Users}" var="users">
                    <form method="Post">
                        Name: <input type="text" name="name" value= ${users.name}>
                        Login: <input type="text" name="login" value= ${users.login}>
                        Password: <input type="text" name="pass" value= ${users.password}>
                        Role: <select name="role">
                        <c:if test="${users.role == 'adminControllers'}">
                        <option value="admin">admin</option>
                        <option value="user">user</option>
                        </c:if>
                        <c:if test="${users.role == 'userControllers'}">
                        <option value="user">user</option>
                        <option value="admin">admin</option>
                        </c:if>
                    </select>

                        <input type="hidden" name="id" value= ${users.id}>
                        <input type="submit" formaction="/admin/update" value="Change">
                    </form>
                    <form method="Post">
                        <input type="hidden" name="id" value= ${users.id}>
                        <input type="submit" formaction="/admin/delete" value="Delete">
                    </form>
                </c:forEach>
            </ul>
        </ul>
    </div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form method="Get">
        <button type="submit" formaction="/" class="w3-btn w3-round-large">Back to login menu</button>
    </form>

</div>
</body>
</html>
