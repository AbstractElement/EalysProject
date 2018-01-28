<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="registr.help.mes.pass" var="helpMesPass"/>
<spring:message code="registr.help.mes.confirmPass" var="helpMesConfirmPass"/>
<spring:message code="err.mes.resetUserPass" var="errMesResetUserPass"/>
<spring:message code="resetPass.mes.newPass" var="resetPassMesNewPass"/>
<spring:message code="resetPass.mes.confirm" var="resetPassMesConfirm"/>
<spring:message code="err.mes.resetUserPassNotEquals" var="errMesResetUserPassNotEquals"/>


<!DOCTYPE html>
<html>
    <jsp:include page="includes/header.jsp"/>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-4"></div>
                <c:if test="${not empty resetUserPass}">
                    <form:form class="form-signin mt-4 col-lg-4" commandName="resetUserPass" action="/resetPass" method='POST'>
                        <c:if test="${resetUserPassError == true}">
                            <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                                ${errMesResetUserPass}
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${resetUserPassErrorNotEquals == true}">
                            <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                                ${errMesResetUserPassNotEquals}
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>
                        <h2 class="form-signin-heading">${resetPassMesNewPass}</h2>
                        <form:input path="email" type="hidden"/>
                        <label for="password" class="sr-only">${helpMesPass}</label>
                        <form:input type="password" path="password"
                                    placeholder="Password" class="form-control mr-sm-2 mb-2" required="true"
                                    data-toggle="tooltip" data-placement="right" title="${helpMesPass}"/>
                        <label for="repeatedPassword" class="sr-only">${helpMesConfirmPass}</label>
                        <form:input type="password" path="repeatedPassword" placeholder="Confirm password"
                                    class="form-control mr-sm-2 mb-2" required="true"
                                    data-toggle="tooltip" data-placement="right" title="${helpMesConfirmPass}"/>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">${resetPassMesConfirm}</button>
                    </form:form>
                </c:if>
                <div class="col-lg-4"></div>
            </div>
        </div>
    </body>
</html>
