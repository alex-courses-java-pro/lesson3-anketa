<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <title>Statistic</title>
</head>
<body>
<c:forEach items="${requestScope.stats}" var="answerData">
    <br/> <c:out value="question: ${answerData.question}"/>
    <c:forEach items="${answerData.answerStat}" var="stat">
        <br/> <c:out value="answered \"${stat.key}\" ${stat.value} times"/>
    </c:forEach>
    <br/>
</c:forEach>
</body>
</html>
