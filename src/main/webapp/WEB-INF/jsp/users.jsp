<%--
  Created by IntelliJ IDEA.
  User: Yurii_Onufreiv
  Date: 14-Apr-17
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3>List of Users</h3>
<hr/>
<c:forEach items="${users}" var="user">
    <ul>
        <form method="get" action="edit">
            <li><input type="text" name="id" value="${user.id}"/></li>
            <li>Username: ${user.name}</li>
            <input type="submit" value="Edit"/>
        </form>
        <hr/>
    </ul>
</c:forEach>
<form method="get" action="create">
    <input type="submit" value="Add"/>
</form>
</body>
</html>
