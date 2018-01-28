<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<spring:message code="login.mes.login" var="loginMesLogin"/>
<spring:message code="login.mes.registration" var="loginMesRegistration"/>
<spring:message code="login.mes.forgotPassword" var="loginMesForgotPass"/>
<spring:message code="login.err.mes.wrongData" var="loginErrMesWrongData"/>
<spring:message code="inf.mes.successRegistration" var="mesSuccessRegistration"/>
<spring:message code="login.mes.registration" var="loginMesRegistration"/>
<spring:message code="registr.help.mes.username" var="helpMesUsername"/>
<spring:message code="registr.help.mes.email" var="helpMesEmail"/>
<spring:message code="registr.help.mes.pass" var="helpMesPass"/>
<spring:message code="registr.help.mes.confirmPass" var="helpMesConfirmPass"/>
<spring:message code="mes.successRecoveryPass" var="mesSuccessRecPass"/>
<spring:message code="btn.mes.Back" var="btnMesBack"/>
<spring:message code="user.mes.sex.male" var="userMesMale"/>
<spring:message code="user.mes.sex.female" var="userMesFemale"/>
<spring:message code="user.mes.sex.other" var="userMesOther"/>
<spring:message code="err.mes.expt" var="errMesExpt"/>
<spring:message code="resetPass.mes.confirm" var="resetPassMesConfirm"/>

<html>
    <jsp:include page="includes/header.jsp"/>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-4"></div>
                <form class="form-signin mt-4 col-lg-4" name="loginForm" action="<c:url value='/login' />" method='POST'>
                    <c:if test="${exception == true}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${errMesExpt}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${resetUserPassSuccessMes == true}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            ${mesSuccessRecPass}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:if>
                    <div id="successReg" class="invisible" role="alert">
                        ${mesSuccessRegistration}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <h2 class="form-signin-heading"><span class="oi oi-account-login" aria-hidden="true"></span> ${loginMesLogin}</h2>
                    <label for="username" class="sr-only">Login</label>
                    <input type="text" id="username" name="username" class="form-control mb-2" placeholder="Login" required autofocus>
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            ${loginErrMesWrongData}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:if>
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" id="password" name="password" class="form-control mb-3" placeholder="Password" required>
                    <button class="btn btn-primary btn-block" id="loginButton" type="submit">${loginMesLogin}</button>
                    <div class="login-input-bottom">
                        <small><a href="" data-target="#registrationModal" id="registration" data-toggle="modal">${loginMesRegistration} </a></small>
                        <small><a href="" data-target="#forgotPassModal" data-toggle="modal">${loginMesForgotPass}</a></small>
                        <small class="float-right">
                            <a href="<%=request.getContextPath()%>?lang=en">En</a>
                            <a href="<%=request.getContextPath()%>?lang=ru">Ru</a>
                        </small>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <div class="col-lg-4"></div>
            </div>

            <%--Registration Modal--%>
            <div class="modal fade" id="registrationModal" tabindex="-1" role="dialog" aria-labelledby="registrationModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="registrationModalLabel">${loginMesRegistration}</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form:form class="form-horizontal" commandName="registrationForm">
                                <sec:csrfMetaTags/>
                                <fieldset>
                                    <div class="control-group mb-3">
                                        <div class="controls ">
                                            <form:input path="username" id="usernameReg" type="text" name="username"
                                                        placeholder="Username" class="form-control mr-sm-2" required="true"
                                                        data-toggle="tooltip" data-placement="right" title="${helpMesUsername}"/>
                                            <span id="usernameError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="control-group mb-3">
                                        <div class="controls">
                                            <form:input path="email" type="text" name="email" placeholder="Email"
                                                        class="form-control mr-sm-2"
                                                        data-toggle="tooltip" data-placement="right" title="${helpMesEmail}"/>
                                            <span id="emailError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="control-group mb-3">
                                        <div class="controls">
                                            <form:input path="firstName" type="text" name="fistName" placeholder="First name"
                                                        class="form-control mr-sm-2" required="true"/>
                                            <span id="firstNameError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="control-group mb-3">
                                        <div class="controls">
                                            <form:input path="lastName" type="text" name="lastName" placeholder="Last name"
                                                        class="form-control mr-sm-2" required="true"/>
                                            <span id="lastNameError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="control-group mb-3">
                                        <div class="controls">
                                            <form:radiobutton path="sex" name="sex" value="male" required="true"/> ${userMesMale}
                                            <form:radiobutton path="sex" name="sex" value="female" required="true"/> ${userMesFemale}
                                            <form:radiobutton path="sex" name="sex" value="other" required="true"/> ${userMesOther}
                                            <span id="sexError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="control-group mb-3">
                                        <div class="controls">
                                            <form:input path="password" id="passwordReg" type="password" name="password"
                                                        placeholder="Password" class="form-control mr-sm-2" required="true"
                                                        data-toggle="tooltip" data-placement="right" title="${helpMesPass}"/>
                                            <span id="passwordError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="control-group mb-3">
                                        <div class="controls">
                                            <form:input path="repeatedPassword" type="password"
                                                        placeholder="Confirm password" class="form-control mr-sm-2" required="true"
                                                        data-toggle="tooltip" data-placement="right" title="${helpMesConfirmPass}"/>
                                            <span id="repeatedPasswordError" class="small alert-danger"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <button type="button" id="confirmRegistration" onclick="registration()" class="btn btn-primary btn-block">
                                                ${loginMesRegistration}</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>

            <%--Forgot Password--%>
            <div class="modal fade" id="forgotPassModal" tabindex="-1" role="dialog" aria-labelledby="forgotPassModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="forgotPassModalLabel">${loginMesForgotPass}</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="/forgot" method="get">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <input type="text" class="form-control" placeholder="Enter your email" name="email" id="emailForResetPass">
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-lg-12">
                                        <button type="submit" class="btn btn-primary btn-block">${resetPassMesConfirm}</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="includes/footer.jsp"/>
    </body>
</html>
