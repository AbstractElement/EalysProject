<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=utf-8" %>

<spring:message code="main.mes.details" var="mainMesDetails"/>
<spring:message code="err.mes.expt" var="errMesExpt"/>
<spring:message code="header.mes.home" var="headerMesHome"/>
<spring:message code="welcome.mes" var="welcomeMes"/>

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
                    <li class="breadcrumb-item active">${headerMesHome}</li>
                </ol>
            </nav>
            <!-- Jumbotron Header -->
            <div class="jumbotron my-4">
                <h1 class="display-3">${welcomeMes}, ${user.firstName}!</h1>
            </div>
            <!-- Page Features -->
            <div class="row">
                <c:forEach items="${projects}" var="project">
                    <div class="col-lg-3 col-md-6 mb-4">
                        <div class="card">
                            <c:choose>
                                <c:when test="${project.images.size() != 0}">
                                    <img class="card-img-top" style="width: 253px; height: 164px"
                                         src="${pageContext.request.contextPath}/resources/images/projectsPhotos/${project.images.get(0).imageName}" alt="">
                                </c:when>
                                <c:otherwise>
                                    <img class="card-img-top" style="width: 253px; height: 164px"
                                         src="${pageContext.request.contextPath}/resources/images/no-image.png" alt="">
                                </c:otherwise>
                            </c:choose>
                        <div class="card-body">
                            <h4 class="card-title text-center">${project.name}</h4>
                            <p class="card-text">${project.target}</p>
                        </div>
                        <div class="card-footer">
                            <a href="/project/${project.projectId}?category=project" class="btn btn-primary">
                                ${mainMesDetails} <span class="oi oi-chevron-right small"></span>
                            </a>
                        </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>
</html>