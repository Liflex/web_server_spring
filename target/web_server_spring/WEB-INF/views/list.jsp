<%--
  Created by IntelliJ IDEA.
  ru.arthur.webserver.model
  Date: 23.06.2018
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Users list</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href="${contextPath}/resources/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/common.css" rel="stylesheet">
</head>
<body>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Users</h2>
        </div>
        <div class="container">
            <%--@elvariable id="user" type="ru.dmitartur.model.User"--%>
            <form:form method="POST" action="${contextPath}/admin/add" modelAttribute="user" class="form-signin">
                <h2 class="form-signin-heading">Add new User</h2>
                <span>${message}</span>
                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="form-control" placeholder="Username" autofocus="true"></form:input>
                        <form:errors path="username"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="password" class="form-control" placeholder="Password"></form:input>
                        <form:errors path="password"></form:errors>
                    </div>
                </spring:bind>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
            </form:form>

        </div>
        <ul>
            <ul class="w3-ul">
                <c:forEach items="${Users}" var="user">
                    <form method="Post">
                        Login: <input type="text" name="username" value= ${user.username}>
                        Password: <input type="text" name="password" value= ${user.password}>
                        <c:forEach var="type" items="${roles}">
                                <label class="checkbox"><input value="${type}" type="checkbox" name="type" checked>${type}</label>
                        </c:forEach>
                        <input type="hidden" name="id" value= ${user.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" formaction="/admin/update" value="Change">
                    </form>
                    <form method="Post">
                        <input type="hidden" name="id" value= ${user.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" formaction="/admin/delete" value="Delete">
                    </form>
                </c:forEach>
            </ul>
        </ul>
    </div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form method="Get">
        <button type="submit" formaction="/logout" class="w3-btn w3-round-large">logout</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</div>
</body>
</html>