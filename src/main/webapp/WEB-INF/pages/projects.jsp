<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>

<spring:message code="main.mes.createProj" var="mainMesCreateProj"/>
<spring:message code="main.mes.create" var="mainMesCreate"/>
<spring:message code="project.mes.projectName" var="projectMesProjectName"/>
<spring:message code="project.mes.description" var="projectMesDescription"/>
<spring:message code="main.mes.details" var="mainMesDetails"/>
<spring:message code="project.mes.projects" var="projectMesProjects"/>
<spring:message code="project.mes.target" var="projectMesTarget"/>
<spring:message code="nav-bar.mes.leaveProject" var="navBarMesLeavePeoject"/>
<spring:message code="err.mes.expt" var="errMesExpt"/>
<spring:message code="header.mes.home" var="headerMesHome"/>

<html>
    <jsp:include page="includes/header.jsp"/>
    <body>
        <jsp:include page="includes/navigation.jsp"/>
        <div class="container mt-5">
            <c:if test="${exception == true}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${errMesExpt}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <nav aria-label="breadcrumb" role="navigation" class="mt-2">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="/home">${headerMesHome}</a>
                    </li>
                    <li class="breadcrumb-item active">${projectMesProjects}</li>
                </ol>
            </nav>
            <div class="row mt-4 align-items-center">
                <div class="col">
                    <h1>${projectMesProjects}</h1>
                </div>
                <div class="col">
                    <button type="button" class="btn btn-primary float-right" data-toggle="modal" data-target="#newProjectModal">
                        ${mainMesCreateProj}
                    </button>
                </div>
            </div>
            <hr>
            <div id="projects">
                <div id="newProject"></div>
                <div id="oldProjects">
                    <c:if test="${not empty projects}">
                        <c:forEach items="${projects}" var="project">
                            <div class="row">
                                <div class="col-md-9">
                                    <span>
                                        <a class="btn float-right" href="/project/${project.projectId}/leave">
                                            <span class="oi oi-trash"></span>
                                        </a>
                                        <a href="/project/${project.projectId}?category=userProject">
                                            <h3>${project.name}</h3>
                                        </a>
                                    </span>

                                    <p>${project.target}</p>

                                </div>
                                <div class="col-md-3" id="projectImage">
                                    <c:choose>
                                        <c:when test="${project.images.size() != 0}">
                                            <img class="img-thumbnail mb-3 mb-md-0"
                                                 src="${pageContext.request.contextPath}/resources/images/projectsPhotos/${project.images.get(0).imageName}"
                                                 style="width: 300px">
                                        </c:when>
                                        <c:otherwise>
                                            <img class="img-thumbnail mb-3 mb-md-0" style="width: 300px;"
                                                 src="${pageContext.request.contextPath}/resources/images/no-image.png">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <nav aria-label="Page navigation" >
                        <ul class="pagination" id="project-pagination"></ul>
                    </nav>
                </div>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>

    <!-- Project Modal -->
    <div class="modal fade" id="newProjectModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="newProjectModalLabel">${mainMesCreateProj}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form:form commandName="newProject">
                        <sec:csrfMetaTags/>
                        <fieldset>
                            <div class="form-group col-lg-12">
                                <label>${projectMesProjectName}</label>
                                <form:input path="name" type="text" class="form-control mr-sm-2"/>
                                <span id="nameError" class="small alert-danger"></span>
                            </div>
                            <div class="form-group col-lg-12">
                                <label>${projectMesTarget}</label>
                                <form:input path="target" type="text" class="form-control mr-sm-2"/>
                                <span id="targetError" class="small alert-danger"></span>
                            </div>
                            <div class="form-group col-lg-12">
                                <label>${projectMesDescription}</label>
                                <form:textarea path="description" class="form-control mr-sm-2"/>
                                <span id="descriptionError" class="small alert-danger"></span>
                            </div>
                            <div class="form-group col-lg-12">
                                <a onclick="addNewProject()" class="btn btn-primary btn-block">
                                    ${mainMesCreate}
                                </a>
                            </div>
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</html>


<script>
    $(document).ready(function () {
        amountProjectsPages(1);
    });
</script>