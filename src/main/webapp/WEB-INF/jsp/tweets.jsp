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
    <title>Tweets</title>
</head>
<body>
<h3>List of tweets</h3>
<hr/>
<c:forEach items="${tweets}" var="tweet">
    <ul>
        <li>Author: ${tweet.user.name}</li>
        <li>Text: ${tweet.text}</li>
        <hr/>
        <%--<li>Likes:--%>
                <%--${tweet.likesCount}--%>
            <%--<input type="button" name="${tweet.tweetId}" class="like" value="Like"/>--%>
        <%--</li>--%>
        <%--<li>Retweets:--%>
                <%--${tweet.retweetCount}--%>
            <%--<input type="button" name="${tweet.tweetId}" class="retweet" value="Retweet"/>--%>
        <%--</li>--%>
    </ul>
</c:forEach>
</body>
</html>
