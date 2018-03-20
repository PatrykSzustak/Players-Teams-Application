<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="players" type="java.util.List"--%>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="Players"/></title>
</head>
<body>
<c:if test="${playerSession.counter > 0}">
    <p>
        <spring:message code="player.session.1"/> ${playerSession.counter}
        <spring:message code="player.session.2"/>${playerSession.mostRecentPlayer}
    </p>
</c:if>

<ul>
<c:forEach items="${players2}" var="player2">
    <li>${player2.name} ${player2.surname}</li>
</c:forEach>
</ul>

${player1.name} ${player1.surname}


<form:form method="post" modelAttribute="player">

    <ul>
        <c:forEach items="${players}" var="player">
            <li>
                <a href="/playersPage/edit/${player.id}">${player.name} ${player.surname} plays at ${player.teamName}</a>
                <button type="submit" name="removeId" value="${player.id}"><spring:message code="REMOVE"/></button>
            </li>
            <spring:message code="players.id"/>
        </c:forEach>
        <center>
            <label><spring:message code="players.name"/><form:input path="name" type="text"/></label>
            <label><spring:message code="players.surname"/><form:input path="surname" type="text"/></label>

            <button type="submit" name="action" value="ADD"><spring:message code="ADD"/></button>
        </center>
    </ul>
    <label><form:errors path="name"/></label>
    <label><form:errors path="surname"/></label>

</form:form>

</body>
</html>
