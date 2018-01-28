<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>

<spring:message code="main.mes.create" var="mesCreate"/>
<spring:message code="risk.mes.name" var="riskMesName"/>
<spring:message code="risk.mes.dangerLevel" var="riskMesDangerLevel"/>
<spring:message code="risk.mes.description" var="riskMesDescription"/>
<spring:message code="risk.mes.author" var="riskMesAuthor"/>
<spring:message code="err.mes.access" var="errMesAccess"/>
<spring:message code="risk.mes.newRisk" var="riskMesNewRisk"/>
<spring:message code="err.mes.expt" var="errMesExpt"/>
<spring:message code="nav-bar.mes.risks" var="navBarMesRisks"/>
<spring:message code="project.mes.projects" var="projectMesProjects"/>
<spring:message code="header.mes.home" var="headerMesHome"/>
<spring:message code="risk.mes.add" var="riskMesAdd"/>

<style>
    canvas{
        width: 900px !important;
        height: 600px !important;
    }
</style>

<html>
    <jsp:include page="includes/header.jsp"/>
    <body>
        <jsp:include page="includes/navigation.jsp"/>
        <div class="container mt-5" id="container">
            <c:if test="${exception == true}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${errMesExpt}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:if test="${not empty accessError}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${errMesAccess}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:if test="${empty accessError}">
                <nav aria-label="breadcrumb" role="navigation" class="mt-2">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="/home">${headerMesHome}</a>
                        </li>
                        <li class="breadcrumb-item">
                            <a href="/project?pageNumber=1">${projectMesProjects}</a>
                        </li>
                        <li class="breadcrumb-item">
                            <a href="/project/${project.projectId}">${project.name}</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">${navBarMesRisks}</li>
                    </ol>
                </nav>
                <jsp:include page="includes/nav-bar.jsp"/>
                <div class="row">
                    <div class="col-lg-10">
                        <canvas id="riskChart" data-id="${project.projectId}"></canvas>
                        <div id="risksAccordion" data-id="${project.projectId}" role="tablist" class="mb-4">
                            <c:if test="${not empty projectRisks}">
                                <c:forEach items="${projectRisks}" var="risk">
                                    <div id="remove-${risk.riskId}" class="card mb-2">
                                        <div class="card-header" role="tab">
                                            <h5 class="mb-0">
                                                <a data-toggle="collapse" href="#${risk.riskId}" aria-expanded="true" aria-controls="collapseOne">
                                                    ${risk.name}
                                                    <span class="badge badge-pill badge-danger">
                                                        ${risk.dangerLevel}
                                                    </span>
                                                </a>
                                                <button type="button" onclick="removeRisk(${risk.riskId})"
                                                        class="close">&times;</button>
                                            </h5>
                                        </div>
                                        <div id="${risk.riskId}" class="collapse" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                                            <div class="card-body">
                                                <div class="media">
                                                    <div class="media-body">
                                                        <h5 class="mt-0">
                                                            ${risk.author.firstName} ${risk.author.lastName}
                                                            <a id="saveChangeRisk-${risk.riskId}" class="invisible" onclick="saveChangeRisk(${risk.riskId})">
                                                                <span class="oi oi-check small text-success"></span>
                                                            </a>
                                                            <a id="changeRisk-${risk.riskId}" class="float-right" onclick="changeRisk(${risk.riskId})">
                                                                <span class="oi oi-pencil small text-primary"></span>
                                                            </a>
                                                        </h5>
                                                        <sec:csrfMetaTags/>
                                                        <textarea id="riskDescription-${risk.riskId}" type="text" disabled
                                                                  class="form-control">${risk.description}</textarea>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <nav aria-label="Page navigation">
                            <ul class="pagination" id="risk-pagination"></ul>
                        </nav>
                    </div>
                    <div class="col-md-2 mt-3">
                        <a href="" class=" btn btn-outline-success btn-block" data-toggle="modal" data-target="#riskModal">
                            <span class="oi oi-plus small"></span>
                            ${riskMesNewRisk}
                        </a>
                    </div>
                </div>
            </c:if>
        </div>

        <%--Risk Modal--%>
        <div class="modal fade" id="riskModal" tabindex="-1" role="dialog" aria-labelledby="riskModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="riskModalLabel">${riskMesNewRisk}</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <c:if test="${not empty newRisk}">
                            <form:form commandName="newRisk">
                                <sec:csrfMetaTags/>
                                <div class="col-lg-12">
                                    <div class="row mb-3">
                                        <div class="col-lg-9">
                                            ${riskMesName}: <form:input class="form-control" path="name" type="text" required="true"/>
                                            <span id="nameError" class="small alert-danger"></span>
                                        </div>
                                        <div class="col-lg-3">
                                            ${riskMesDangerLevel}: <form:input class="form-control"
                                                   path="dangerLevel" type="number" value="1" min="1" max="10"/>
                                            <span id="levelError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-lg-12">
                                            <form:textarea cols="50" rows="5" class="form-control"
                                               path="description" placeholder="${riskMesDescription}"/>
                                            <span id="descriptionError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <a onclick="addNewRisk(${project.projectId})" type="button"
                                                   class="btn btn-primary btn-block">
                                                ${riskMesAdd}
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chartjs/Chart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chartjs/drawChart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
</html>

<script>
    $(document).ready(function () {
        amountRisksPages(1);
    });
</script>
