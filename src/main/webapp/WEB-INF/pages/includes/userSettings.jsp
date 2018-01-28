<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<spring:message code="header.mes.profile" var="mesProfile"/>
<spring:message code="user.mes.login" var="userMesLogin"/>
<spring:message code="user.mes.email" var="userMesEmail"/>
<spring:message code="user.mes.firstName" var="userMesFirstName"/>
<spring:message code="user.name.lastName" var="userMesLastName"/>
<spring:message code="user.mes.position" var="userMesPosition"/>
<spring:message code="user.mes.phone" var="userMesPhone"/>
<spring:message code="user.mes.country" var="userMesCountry"/>
<spring:message code="user.mes.organizationName" var="userMesOrganization"/>
<spring:message code="user.mes.age" var="userMesAge"/>
<spring:message code="user.mes.about" var="userMesAbout"/>
<spring:message code="photo.mes.upload" var="photoUploadPhoto"/>
<spring:message code="user.mes.save" var="userMesSave"/>
<spring:message code="skill.mes.name" var="skillMesName"/>
<spring:message code="skill.mes.level" var="skillMesLevel"/>
<spring:message code="skill.mes.add" var="skillMesAdd"/>
<spring:message code="skill.mes.title" var="skillMesTitle"/>
<spring:message code="header.mes.home" var="headerMesHome"/>
<spring:message code="header.mes.settings" var="headerMesSettings"/>
<spring:message code="user.mes.sex.male" var="userMesMale"/>
<spring:message code="user.mes.sex.female" var="userMesFemale"/>
<spring:message code="user.mes.sex.other" var="userMesOther"/>
<spring:message code="changePass.mes.oldPass" var="changePassMesOldPass"/>
<spring:message code="changePass.mes.newPass" var="changePassMesNewPass"/>
<spring:message code="changePass.mes.confirmNewPass" var="changePassMesConfirmNewPass"/>
<spring:message code="changePass.mes.submit" var="changePassMesSubmit"/>
<spring:message code="changePass.err.mes" var="changePassErrMes"/>
<spring:message code="changePass.success.mes" var="changePassSuccessMes"/>
<spring:message code="changePass.mes" var="changePassMes"/>
<spring:message code="setting.mes.photo" var="settingMesPhoto"/>
<spring:message code="setting.mes.security" var="settingMesSecurity"/>
<spring:message code="setting.mes.skills" var="settingMesSkills"/>


<c:if test="${not empty updateUser}">
    <nav aria-label="breadcrumb" role="navigation" class="mt-2">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/home">${headerMesHome}</a>
            </li>
            <li class="breadcrumb-item">
                <a href="/user/0">${mesProfile}</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">
                ${headerMesSettings}
            </li>
        </ol>
    </nav>
    <ul class="nav nav-tabs">
        <li class="nav-item"><a data-toggle="tab" href="#profileNav" class="nav-link active">${mesProfile}</a></li>
        <li class="nav-item"><a data-toggle="tab" href="#skillsNav" class="nav-link">${settingMesSkills}</a></li>
        <li class="nav-item"><a data-toggle="tab" href="#photoNav" class="nav-link">${settingMesPhoto}</a></li>
        <li class="nav-item"><a data-toggle="tab" href="#securityNav" class="nav-link">${settingMesSecurity}</a></li>
    </ul>
    <div class="tab-content">
        <div id="profileNav" class="tab-pane active">
            <form:form commandName="updateUser" action="/user/settings/submit" method="post">
                <form:input path="userId" type="hidden"/>
                <form:input path="password" type="hidden"/>
                <form:input path="photo" type="hidden"/>
                <div class="form-group row mt-4">
                    <div class="form-group col-lg-4">
                        ${userMesLogin}:
                        ${updateUser.username} <form:input path="username" type="hidden"/>
                    </div>
                    <div class="form-group col-lg-4">
                        ${userMesEmail}:
                        ${updateUser.email} <form:input path="email" class="form-control" type="hidden"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="form-group col-lg-4">
                        ${userMesFirstName}:
                        <form:input path="firstName" class="form-control" type="text"/>
                        <form:errors path="firstName" cssClass="text-danger"/>
                    </div>
                    <div class="form-group col-lg-4">
                        ${userMesLastName}:
                        <form:input path="lastName" class="form-control" type="text"/>
                        <form:errors path="lastName" cssClass="text-danger"/>
                    </div>
                    <div class="form-group col-lg-1">
                        ${userMesAge}:
                        <form:input path="age" class="form-control" type="number" min="18" max="65"/>
                        <form:errors path="age" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="form-group col-lg-4">
                        ${userMesOrganization}:
                        <form:input path="organizationName" class="form-control" type="text"/>
                        <form:errors path="organizationName" cssClass="text-danger"/>
                    </div>
                    <div class="form-group col-lg-4">
                        ${userMesPosition}:
                        <form:input path="position" class="form-control" type="text"/>
                        <form:errors path="position" cssClass="text-danger"/>
                    </div>
                    <div class="form-group col-lg-2">
                        ${userMesPhone}:
                        <form:input path="phoneNumber" class="form-control" type="tel"/>
                        <form:errors path="phoneNumber" cssClass="text-danger"/>
                    </div>
                </div>
                <div class="form-group row">
                    <div class=" form-group col-lg-4">
                        <form:radiobutton path="sex" name="sex" value="male" required="true"/> ${userMesMale}
                        <form:radiobutton path="sex" name="sex" value="female" required="true"/> ${userMesFemale}
                        <form:radiobutton path="sex" name="sex" value="other" required="true"/> ${userMesOther}
                    </div>
                </div>
                <div class="form-group row">
                    <div class="form-group col-lg-12">
                        ${userMesAbout}:
                        <form:textarea cssClass="form-control" rows="7" path="about"/>
                        <form:errors path="about" cssClass="text-danger"/>
                    </div>
                    <div class="form-group col-lg-12">
                        <input type="submit" class="btn btn-primary btn-block" value="${userMesSave}">
                    </div>
                </div>
            </form:form>
        </div>
        <div id="skillsNav" class="tab-pane fade mt-4">
            <div id="newSkill" class="input-group">
                <sec:csrfMetaTags/>
                <input type="text" class="form-control" id="inputNewSkill" placeholder="${skillMesTitle}">
                <span class="input-group-btn" onclick="addNewSkill()">
                    <button class="btn btn-primary" type="button">
                        <span class="oi oi-plus small"></span> ${skillMesAdd}
                    </button>
                </span>
            </div>
            <div class="mt-5">
                <div class="row">
                    <div id="userSkills" class="col-lg-10">
                        <c:if test="${not empty updateUser.skills}">
                            <sec:csrfMetaTags/>
                            <c:forEach items="${updateUser.skills}" var="skill">
                                <div class="btn-group mt-2 mr-2" id="skill-${skill.skillId}">
                                    <a class="btn btn-secondary font-weight-bold" href="/user/skill?skill=${skill.name}">${skill.name}</a>
                                    <c:if test="${pageContext.request.userPrincipal.name == updateUser.username}">
                                        <button type="button" onclick="removeSkill(${skill.skillId})"
                                                class="btn btn-secondary font-weight-bold">&times;</button>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div id="photoNav" class="tab-pane fade mt-4">
            <form enctype="multipart/form-data">
                <div class="form-group row">
                    <div class="col-lg-2">
                        <img class="rounded float-left" style="width: 150px; height: 150px"
                             src="${pageContext.request.contextPath}/resources/images/usersPhotos/${updateUser.photo}"> <br>
                    </div>
                    <div class="form-group row col-lg-10">
                        <div class="col-lg-4">
                            <input type="file" accept="image/jpeg" name="images" multiple required="true" class="mb-4"/>
                            <input formaction="/photo/upload?${_csrf.parameterName}=${_csrf.token}&target=user"
                                   formmethod="post" type="submit" value="${photoUploadPhoto}" class="btn btn-primary "/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div id="securityNav" class="tab-pane fade mt-4">
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <div class="invisible" id="changePassMesSuccess">
                        ${changePassSuccessMes}
                    </div>
                    <div class="invisible" id="changePassMesError">
                        ${changePassErrMes}
                    </div>
                    <form>
                        <sec:csrfMetaTags/>
                        <h4 class="text-center">${changePassMes}</h4>
                        <div class="form-group">
                            <label for="oldPass">${changePassMesOldPass}</label>
                            <input type="password" class="form-control" name="oldPass" id="oldPass" placeholder="${changePassMesOldPass}">
                        </div>
                        <div class="form-group">
                            <label for="newPass">${changePassMesNewPass}</label>
                            <input type="password" class="form-control" name="newPass" id="newPass" placeholder="${changePassMesNewPass}">
                        </div>
                        <div class="form-group">
                            <label for="confirmNewPass">${changePassMesConfirmNewPass}</label>
                            <input type="password" class="form-control" name="confirmNewPass" id="confirmNewPass" placeholder="${changePassMesConfirmNewPass}">
                        </div>
                        <button type="button" onclick="submitNewPassword()" class="btn btn-primary btn-block">${changePassMesSubmit}</button>
                    </form>
                </div>
                <div class="col-lg-4"></div>
            </div>
        </div>
    </div>
</c:if>