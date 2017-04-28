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
    <title>Create User</title>
</head>
<body>
<h3>Create/Update User</h3>
<hr/>
<form method="post" action="save">
    <input type="text" name="tweetId" value="${user.tweetId}">
    Name <input type="text" name="name" value="${user.name}">
    <input type="submit" value="Save">
</form>
</body>
</html>
