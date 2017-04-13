<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <title>Statistic</title>
</head>
<body>
<c:forEach items="${requestScope.questions}" var="question">
    <br/> <c:out value="question: ${question}"/>
    <c:forEach items="${requestScope.answerStat}" var="stat">
        <br/> <c:out value="answered \"${stat.key}\" ${stat.value} times"/>
    </c:forEach>
</c:forEach>
</body>
</html>
