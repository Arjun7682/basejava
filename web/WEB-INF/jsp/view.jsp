<%@ page import="ru.javawebinar.basejava.model.ListSection" %>
<%@ page import="ru.javawebinar.basejava.model.OrganizationSection" %>
<%@ page import="ru.javawebinar.basejava.model.TextSection" %>
<%@ page import="ru.javawebinar.basejava.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.uuid}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <hr>
    <p>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.Section>"/>
    <h2>${sectionEntry.key.title}</h2>
    <c:choose>
        <c:when test="${sectionEntry.key == 'OBJECTIVE' || sectionEntry.key == 'PERSONAL'}">
            <%=((TextSection) sectionEntry.getValue()).getContent()%>
        </c:when>
        <c:when test="${sectionEntry.key == 'ACHIEVEMENT' || sectionEntry.key == 'QUALIFICATIONS'}">
            <ul>
                <c:forEach var="listEntry" items="<%=((ListSection) sectionEntry.getValue()).getContent()%>">
                <li>${listEntry}
                    </c:forEach>
            </ul>
        </c:when>
        <c:when test="${sectionEntry.key == 'EXPERIENCE' || sectionEntry.key == 'EDUCATION'}">
            <c:forEach var="orgEntry" items="<%=((OrganizationSection) sectionEntry.getValue()).getContent()%>">
                <jsp:useBean id="orgEntry" type="ru.javawebinar.basejava.model.Organization"/>
                <a href="${orgEntry.company.url}"><h3>${orgEntry.company.text}</h3></a>
                <table border="0">
                    <c:forEach var="position" items="${orgEntry.orgEntries}">
                        <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Organization.Position"/>
                        <tr>
                            <td rowspan="2" width="20%" style="vertical-align: top">
                                    ${DateUtil.format(position.startDate)} - ${DateUtil.format(position.endDate)}
                            </td>
                            <td>
                                <b>${position.title}</b>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                    ${position.description}
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </c:forEach>
        </c:when>
    </c:choose>
    </c:forEach>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
