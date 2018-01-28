<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>

<spring:message code="header.mes.profile" var="mesProfile"/>
<spring:message code="user.mes.phone" var="userMesPhone"/>
<spring:message code="user.mes.organizationName" var="userMesOrganization"/>
<spring:message code="user.mes.about" var="userMesAbout"/>
<spring:message code="photo.mes.upload" var="photoUploadPhoto"/>
<spring:message code="err.mes.expt" var="errMesExpt"/>
<spring:message code="err.mes.access" var="errMesAccess"/>
<spring:message code="invite.mes.inviteProject" var="mesInviteProject"/>
<spring:message code="invite.mes.send" var="mesSendInvite"/>
<spring:message code="nav-bar.mes.projectGroup" var="navBarMesProjectGroup"/>
<spring:message code="project.mes.userRoleGuest" var="projectMesGuest"/>
<spring:message code="project.mes.userRoleOwner" var="projectMesOwner"/>
<spring:message code="header.mes.people" var="headerMesPeople"/>
<spring:message code="header.mes.home" var="headerMesHome"/>
<spring:message code="project.mes.projects" var="projectMesProjects"/>
<spring:message code="header.mes.settings" var="headerMesSettings"/>
<spring:message code="header.mes.settings" var="headerMesSettings"/>

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
            <c:if test="${not empty users}">
                <nav aria-label="breadcrumb" role="navigation" class="mt-2">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="/home">${headerMesHome}</a>
                        </li>
                        <li class="breadcrumb-item active">${headerMesPeople}</li>
                    </ol>
                </nav>
                <div class="row mb-2 mt-4 align-items-center">
                    <div class="col-lg-9">
                        <h1>${headerMesPeople}</h1>
                    </div>
                    <div class="col-lg-3">
                        <div class="input-group">
                            <span class="input-group-btn" id="basic-addon1">
                                <button class="btn btn-primary" onclick="moveToUsersPage(1)">
                                    <span class="oi oi-magnifying-glass"></span>
                                </button>
                            </span>
                            <input type="text" list="skillsList" class="form-control" placeholder="Skill" id="searchSkill" aria-describedby="basic-addon1">
                        </div>
                        <datalist id="skillsList">
                            <c:forEach items="${skills}" var="skill">
                            <option value="${skill.name}">
                                </c:forEach>
                        </datalist>
                    </div>
                </div>
                <hr>
                <div class="row mt-2">
                    <div class="col-lg-12">
                        <div id="users">
                            <c:forEach items="${users}" var="user">
                                <div class="row text-left mb-4">
                                    <div class="col-lg-2">
                                        <img class="rounded-circle img-fluid d-block mx-auto"
                                             src="${pageContext.request.contextPath}/resources/images/usersPhotos/${user.photo}" style="width:100px;">
                                    </div>
                                    <div class="col-lg-10">
                                        <h3>
                                            <a href="/user/${user.userId}">${user.firstName} ${user.lastName}</a>
                                            <small>${user.position}</small>
                                        </h3>
                                            ${user.email}
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <nav aria-label="Page navigation" >
                            <ul class="pagination" id="user-pagination"></ul>
                        </nav>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty user}">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == user.username}">
                        <nav aria-label="breadcrumb" role="navigation" class="mt-2">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="/home">${headerMesHome}</a>
                                </li>
                                <li class="breadcrumb-item active">${mesProfile}</li>
                            </ol>
                        </nav>
                    </c:when>
                    <c:otherwise>
                        <nav aria-label="breadcrumb" role="navigation" class="mt-2">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="/home">${headerMesHome}</a>
                                </li>
                                <li class="breadcrumb-item">
                                    <a href="/user?pageNumber=1">${headerMesPeople}</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">${user.firstName} ${user.lastName}</li>
                            </ol>
                        </nav>
                    </c:otherwise>
                </c:choose>
                <div class="row mt-4">
                    <div class="col-lg-3">
                        <img class="rounded float-left" style="width: 200px"
                             src="${pageContext.request.contextPath}/resources/images/usersPhotos/${user.photo}"/>
                        <c:if test="${pageContext.request.userPrincipal.name == user.username}">
                            <a class="btn btn-primary mt-2" style="width:200px;" href="/user/settings">
                                <span class="oi oi-pencil small"></span>
                                    ${headerMesSettings}
                            </a>
                        </c:if>
                    </div>
                    <div class="col-lg-9">
                        <div class="row mb-0">
                            <div class="col-lg-12">
                                <h4 class="font-weight-bold">${user.firstName} ${user.lastName}, ${user.age}
                                    (<span class="text-primary font-italic">${user.email}</span>)
                                </h4>
                            </div>
                        </div>
                        <div class="row mb-0">
                            <div class="col-lg-12">
                                <p class="font-weight-light">
                                    ${userMesOrganization}: ${user.organizationName}, ${user.position}
                                </p>

                            </div>
                        </div>
                        <div class="row mb-0">
                            <div class="col-lg-4">
                                <p class="font-weight-light">
                                    ${userMesPhone}: ${user.phoneNumber}
                                </p>
                            </div>
                        </div>
                        <div class="row mb-0">
                            <div class="col-lg-12">
                                <p class="font-weight-light">
                                    ${userMesAbout}:
                                    <span class="font-italic">${user.about}</span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-lg-10">
                            <c:if test="${not empty user.skills}">
                                <sec:csrfMetaTags/>
                                <c:forEach items="${user.skills}" var="skill">
                                    <div class="btn-group mt-2 mr-2" id="skill-${skill.skillId}">
                                        <a class="btn btn-secondary font-weight-bold" href="/user/skill?skill=${skill.name}">${skill.name}</a>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <jsp:include page="includes/userSettings.jsp"/>
            <c:if test="${not empty projectGroup}">
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
                        <li class="breadcrumb-item active" aria-current="page">${navBarMesProjectGroup}</li>
                    </ol>
                </nav>
                <jsp:include page="includes/nav-bar.jsp"/>
                <div class="row">
                    <div class="col-lg-12">
                        <c:if test="${userRole.equals('OWNER')}">
                            <div class="card my-4">
                                <h5 class="card-header">${mesInviteProject}</h5>
                                <div class="card-body">
                                    <div class="input-group mb-3">
                                        <input type="email" id="employeeEmail" class="form-control" placeholder="Email"/>
                                        <span class="input-group-btn">
                                      <button class="btn btn-outline-success" onclick="sendInvite()" type="button">&#10004;</button>
                                    </span>
                                    </div>
                                    <div id="inviteUsers" data-id="${project.projectId}">
                                        <c:forEach items="${inviteUsers}" var="user">
                                            <div class="col-lg-12">
                                                ${user.email}
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <h2 class="my-4">${navBarMesProjectGroup}</h2>
                        <div id="projectGroup"></div>
                        <c:forEach items="${projectGroup}" var="project">
                            <div class="row text-left mb-4">
                               <div class="col-lg-2">
                                    <img class="rounded-circle img-fluid d-block mx-auto"
                                         src="${pageContext.request.contextPath}/resources/images/usersPhotos/${project.user.photo}" style="width:100px; height: 100px" >
                               </div>
                                <div class="col-lg-10">
                                    <h3>
                                        <a href="/user/${project.user.userId}">
                                            ${project.user.firstName} ${project.user.lastName}
                                        </a>
                                        <small>${project.user.position}</small>
                                    </h3>
                                    ${project.user.email}
                                    <c:choose>
                                        <c:when test="${project.roleOnProject.equals('OWNER')}">
                                            <span class="badge badge-pill badge-success">${projectMesOwner}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-pill badge-info">${projectMesGuest}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>
</html>

<script>
    $(document).ready(function () {
        amountUsersPages(1);
    });
</script>