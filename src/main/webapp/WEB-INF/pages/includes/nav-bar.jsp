<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="nav-bar.mes.project" var="navBarMesProject"/>
<spring:message code="nav-bar.mes.projectGroup" var="navBarMesProjectGroup"/>
<spring:message code="nav-bar.mes.risks" var="navBarMesRisks"/>
<spring:message code="nav-bar.mes.leaveProject" var="navBarMesLeavePeoject"/>
<spring:message code="nav-bar.mes.menu" var="navBarMesMenu"/>

<ul class="nav nav-tabs" id="projectNav">
    <li class="nav-item">
        <a class="nav-link" href="/project/${project.projectId}?category=userProject" id="userProject">
            <span class="oi oi-monitor small"></span>
            ${navBarMesProject}
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/project/${project.projectId}/users?category=projectGroup" id="projectGroup">
            <span class="oi oi-people small"></span>
            ${navBarMesProjectGroup}
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/project/${project.projectId}/risks?pageNumber=1&category=risks" id="risks">
            <span class="oi oi-bar-chart small"></span>
            ${navBarMesRisks}
        </a>
    </li>
</ul>