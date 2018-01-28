<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:message code="header.mes.home" var="headerMesHome"/>
<spring:message code="header.mes.people" var="headerMesPeople"/>
<spring:message code="header.mes.profile" var="headerMesProfile"/>
<spring:message code="header.mes.projects" var="headerMesProjects"/>
<spring:message code="header.mes.risks" var="headerMesRisks"/>
<spring:message code="header.mes.logout" var="headerMesLogout"/>
<spring:message code="header.mes.keyActivate" var="headerMesKeyActivate"/>
<spring:message code="modal.mes.activate" var="modalMesActivate"/>
<spring:message code="modal.mes.close" var="modalMesClose"/>
<spring:message code="modal.mes.input" var="modalMesInput"/>
<spring:message code="modal.mes.keyActivate" var="modalMesKeyActivate"/>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/home">Ealys</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto" id="headerNav">
                <li class="nav-item">
                    <a class="nav-link" href="/home" id="home">
                        <span class="oi oi-home small"></span>
                        ${headerMesHome}
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/project?pageNumber=1" id="project">
                        <span class="oi oi-spreadsheet small"></span>
                        ${headerMesProjects}
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user?pageNumber=1" id="user">
                        <span class="oi oi-people small"></span>
                        ${headerMesPeople}
                    </a>
                </li>
                <li class="nav-item">
                    <div class="dropdown show">
                        <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${pageContext.request.userPrincipal.name}
                        </a>
                        <div class="dropdown-menu text-center" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="/user/0">
                                <span class="oi oi-person small"></span>
                                ${headerMesProfile}
                            </a>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>?lang=en">
                                <img src="${pageContext.request.contextPath}/resources/images/flags/en.png">
                                English
                            </a>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>?lang=ru">
                                <img src="${pageContext.request.contextPath}/resources/images/flags/ru.png">
                                Русский
                            </a>
                            <form class="dropdown-item" action="<c:url value='/logout'/>" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <input class="dropdown-item" type="submit" value="${headerMesLogout}">
                            </form>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>