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
        <br/> ${question.questionText}
        <input type="hidden" name="questionText${questionIndex}" value="${question.questionText}"/>
        <c:forEach items="${question.answersTextList}" var="answer">
            <br/> <input type="radio" name="question${questionIndex}rb"
                         value="${answer}" checked/>${answer}
        </c:forEach>
        <c:set var="questionIndex" value="${questionIndex + 1}"/>
    </c:forEach>
    <br/><input type="submit"/>
</form>
</body>
</html>
