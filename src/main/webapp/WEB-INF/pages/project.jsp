<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>

<spring:message code="project.mes.projectName" var="projectMesProjectName"/>
<spring:message code="project.mes.description" var="projectMesDescription"/>
<spring:message code="project.mes.save" var="projectMesSave"/>
<spring:message code="project.mes.target" var="projectMesTarget"/>
<spring:message code="photo.mes.upload" var="photoMesPhoto"/>
<spring:message code="err.mes.access" var="errMesAccess"/>
<spring:message code="inf.mes.successUpdatedProject" var="infMesUpdatedProject"/>
<spring:message code="btn.mes.Back" var="btnMesBack"/>
<spring:message code="err.mes.expt" var="errMesExpt"/>
<spring:message code="nav-bar.mes.download" var="navBarMesDownload"/>
<spring:message code="nav-bar.mes.edit" var="navBarMesEdit"/>
<spring:message code="project.mes.projects" var="projectMesProjects"/>
<spring:message code="err.mes.inviteActivating" var="errMesInviteActivating"/>
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
            <c:if test="${not empty accessError}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${errMesAccess}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:if test="${inviteActivatingError == true}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${errMesInviteActivating}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:if test="${not empty project}">
                <nav aria-label="breadcrumb" role="navigation" class="mt-2">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="/home">${headerMesHome}</a>
                        </li>
                        <li class="breadcrumb-item">
                            <a href="/project?pageNumber=1">${projectMesProjects}</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">${project.name}</li>
                    </ol>
                </nav>
                <jsp:include page="includes/nav-bar.jsp"/>
                <div class="row">
                    <div class="col-lg-10">
                        <h1 class="mt-4">${projectMesProjectName}: ${project.name} </h1>
                        <span class="font-weight-light font-italic">${project.date}</span>
                        <hr>
                        <h4>${projectMesTarget}</h4>
                        <span class="font-italic font-weight-light">${project.target}</span>
                        <hr>
                        <h4>
                            ${projectMesDescription}
                        </h4>
                        <p>${project.description}</p>
                        <hr>
                        <c:if test="${project.images.size() != 0}">
                            <div class="row">
                                <c:forEach items="${project.images}" var="image">
                                    <div class="col-lg-4 mb-4">
                                        <a href="${pageContext.request.contextPath}/resources/images/projectsPhotos/${image.imageName}"
                                         data-lightbox="roadtrip">
                                            <img class="img-fluid rounded" style="width: 300px; height: 300px"
                                                 src="${pageContext.request.contextPath}/resources/images/projectsPhotos/${image.imageName}">
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>

                    <div class="col-md-2 mt-3">
                        <c:if test="${userRole.equals('OWNER')}">
                            <div class="mb-2">
                                <a href="/project/update/${project.projectId}" class="btn btn-outline-success btn-block">
                                    <span class="oi oi-pencil small"></span>
                                        ${navBarMesEdit}
                                </a>
                            </div>
                        </c:if>
                        <div>
                            <a href="/project/${project.projectId}/download" class="btn btn-outline-success btn-block">
                                <span class="oi oi-data-transfer-download small"></span>
                                ${navBarMesDownload}
                            </a>
                        </div>
                    </div>
                </div>
            </c:if>


            <c:if test="${not empty updateProject}">
                <nav aria-label="breadcrumb" role="navigation" class="mt-2">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="/project?pageNumber=1">${projectMesProjects}</a>
                        </li>
                        <li class="breadcrumb-item">
                            <a href="/project/${updateProject.projectId}">${updateProject.name}</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">${navBarMesEdit}</li>
                    </ol>
                </nav>
                <div id="successMes" class="invisible" role="alert">
                    ${infMesUpdatedProject}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="form-group mb-2">
                    <a class="btn text-primary" onclick="window.history.back()">&larr; ${btnMesBack}</a>
                </div>
                <form:form commandName="updateProject" id="updateForm" data-id="${updateProject.projectId}" data-date="${updateProject.date}">
                    <div class="form-group row">
                        <sec:csrfMetaTags/>
                        <form:input path="projectId" type="hidden"/>
                        <form:input path="date" type="hidden"/>
                        <div class="form-group col-lg-4">
                            ${projectMesProjectName}:
                            <form:input path="name" type="text" cssClass="form-control"/>
                            <span id="nameError" class="small alert-danger"></span>
                        </div>
                        <div class="form-group col-lg-8">
                            ${projectMesTarget}:
                            <form:input path="target" type="text" cssClass="form-control"/>
                            <span id="targetError" class="small alert-danger"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="form-group col-lg-12">
                            ${projectMesDescription}:
                            <form:textarea path="description" cols="40" rows="7" cssClass="form-control"/>
                            <span id="descriptionError" class="small alert-danger"></span>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="form-group col-lg-12">
                            <input onclick="saveUpdatedProject()" type="button" value="${projectMesSave}" class="btn btn-primary btn-block"/>
                        </div>
                    </div>
                </form:form>
                <form enctype="multipart/form-data">
                    <div class="form-group row">
                        <div class="form-group col-lg-12">
                            <input type="file" accept="image/*" name="images" multiple required="true"/>
                            <input formaction="/photo/upload?${_csrf.parameterName}=${_csrf.token}&target=project&projectId=${updateProject.projectId}"
                                   formmethod="post" type="submit" name="loadPhoto" value="${photoMesPhoto}"/>
                        </div>
                    </div>
                </form>
                <div class="row">
                    <c:if test="${not empty updateProject.images}">
                        <c:forEach items="${updateProject.images}" var="image">
                            <div class="col-lg-3 mr-3 mb-2" id="image-${image.imageId}" data-projectId="${updateProject.projectId}"
                                 data-imageName="${image.imageName}">
                                <img class="rounded float-left" style="width: 200px"
                                     src="${pageContext.request.contextPath}/resources/images/projectsPhotos/${image.imageName}">
                                <a class="float-left" onclick="deleteImage(${image.imageId});">
                                    <sec:csrfMetaTags/>
                                    <span class="close">&times;</span>
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </c:if>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>
</html>
