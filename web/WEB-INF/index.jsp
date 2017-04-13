<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <title>Pointless Quiz</title>
</head>
<body>
<form action="/result" method="POST">
    Who are you?
    <br/><input type="text" name="namef" placeholder="Name">
    <c:set var="questionIndex" value="0"/>
    <c:forEach items="${requestScope.questions}" var="question">
        <br/>${question.question}
        <c:set var="answerIndex" value="0"/>
        <c:forEach items="${question.answers}" var="answer">
            <br/> <input type="radio" name="question${questionIndex}rb"
                         value="${answerIndex}"/>${answer}
            <c:set var="answerIndex" value="${answerIndex + 1}"/>
        </c:forEach>
        <c:set var="questionIndex" value="${questionIndex + 1}"/>
    </c:forEach>
    <br/><input type="submit"/>
</form>
</body>
</html>
