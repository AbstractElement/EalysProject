<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="page404.mes" var="notFoundMes"/>
<spring:message code="takeMeHome.mes" var="takeMeHome"/>

<html>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-12">
                    <div class="error-template text-center">
                        <h1>Oops!</h1>
                        <h2>404 Not Found</h2>
                        <div class="error-details">
                            ${notFoundMes}
                        </div>
                        <div class="error-actions mt-5">
                            <a href="/home" class="btn btn-primary btn-lg">
                                <span class="glyphicon glyphicon-home"></span>
                                ${takeMeHome}
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <img src="${pageContext.request.contextPath}/resources/images/errors/404_pic.jpg">
    <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
