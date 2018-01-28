<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="errorPage.mes" var="errPageMes"/>
<spring:message code="takeMeHome.mes" var="takeMeHome"/>

<html>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <link href="${pageContext.request.contextPath}/resources/css/fullBackground.css" type="text/css" rel="stylesheet">

        <div class="container mt-5">
            <div class="row">
                <div class="col-lg-12">
                    <div class="error-template text-center">
                        <h1>Oops!</h1>
                        <div class="error-details">
                            ${errPageMes}
                        </div>
                        <div class="error-actions mt-5">
                            <a href="/home" class="btn btn-primary btn-lg">${takeMeHome}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>