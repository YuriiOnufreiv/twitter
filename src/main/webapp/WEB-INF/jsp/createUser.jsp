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
<form method="post" action="create">
    Name <input type="text" name="name">
    <input type="submit" value="Create">
</form>
<c:forEach items="${tweets}" var="tweet">
    <ul>
        <li>Author: ${tweet.user.name}</li>
        <li>Text: ${tweet.text}</li>
        <hr/>
        <%--<li>Likes:--%>
                <%--${tweet.likesCount}--%>
            <%--<input type="button" name="${tweet.id}" class="like" value="Like"/>--%>
        <%--</li>--%>
        <%--<li>Retweets:--%>
                <%--${tweet.retweetCount}--%>
            <%--<input type="button" name="${tweet.id}" class="retweet" value="Retweet"/>--%>
        <%--</li>--%>
    </ul>
</c:forEach>
</body>
</html>
