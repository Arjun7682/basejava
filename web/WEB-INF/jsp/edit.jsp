<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <h2>Имя:</h2>
        <input type="text" name="fullName" size=50 value="${resume.fullName}">

        <h3>Контакты:</h3>

        <table border="0">
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <tr>
                    <td width="130">${type.title}</td>
                    <td><input type="text" name="${type.name()}" size=30 value="${resume.getContacts(type)}"></td>
                </tr>
            </c:forEach>
        </table>

        <h3>Секции:</h3>

        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSections(type)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.Section"/>
            <h2>${type.title}</h2>
            <c:choose>
                <c:when test="${type == 'OBJECTIVE' || type == 'PERSONAL'}">
                    <textarea name="${type}" cols=100
                              rows=5><%=((TextSection) section).getContent()%></textarea>
                </c:when>
                <c:when test="${type == 'ACHIEVEMENT' || type == 'QUALIFICATIONS'}">
                    <textarea name="${type}" cols=100
                              rows=5><%=String.join("\n", ((ListSection) section).getContent())%></textarea>
                </c:when>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <table border="0">
                        <c:forEach var="orgEntry" items="<%=((OrganizationSection) section).getContent()%>"
                                   varStatus="counter">
                            <jsp:useBean id="orgEntry" type="ru.javawebinar.basejava.model.Organization"/>

                            <tr>
                                <td>Компания:</td>
                                <td><input type="text" name="${type.name()}" size="80" value="${orgEntry.company.text}">
                            </tr>
                            <tr>
                                <td>URL:</td>
                                <td><input type="text" name="${type.name()}url" size="80"
                                           value="${orgEntry.company.url}"></td>
                            </tr>
                            <c:forEach var="position" items="${orgEntry.orgEntries}">
                                <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                                <tr>
                                    <td>Начальная дата:</td>
                                    <td><input type="text" name="${type.name()}${counter.index}startDate" size="10"
                                               value="${DateUtil.format(position.startDate)}"></td>
                                </tr>
                                <tr>
                                    <td>Конечная дата:</td>
                                    <td><input type="text" name="${type.name()}${counter.index}endDate" size="10"
                                               value="${DateUtil.format(position.endDate)}"></td>
                                </tr>
                                <tr>
                                    <td>Должность:</td>
                                    <td><input type="text" name="${type.name()}${counter.index}title" size="80"
                                               value="${position.title}"></td>
                                </tr>
                                <tr>
                                    <td style="vertical-align: top">Описание:</td>
                                    <td><textarea name="${type.name()}${counter.index}description" cols=80
                                                  rows=3>${position.description}</textarea>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td><br></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
