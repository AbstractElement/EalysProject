function setRequestHeader(xhr){
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    xhr.setRequestHeader(header, token);
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});

$(document).ready(function () {
    var category = window.location.href.split('category=')[1];
    $('#projectNav').find('a').attr('class', 'nav-link');
    $('#' + category).attr('class', 'nav-link active');
    var path = window.location.pathname.split('/')[1];
    $('#user').attr('class', 'nav-link');
    $('#home').attr('class', 'nav-link');
    $('#project').attr('class', 'nav-link');
    $('#' + path).attr('class', 'nav-link active');
});

function registration() {
    $('#usernameError').text('');
    $('#emailError').text('');
    $('#firstNameError').text('');
    $('#lastNameError').text('');
    $('#passwordError').text('');
    $('#repeatedPasswordError').text('');
    $('#sexError').text('');


    var radios = document.getElementsByName('sex');
    var sex = '';

    for (var i = 0, length = radios.length; i < length; i++)
        if (radios[i].checked) {
            sex = radios[i].value;
            break;
        }

    var formData = {
        username :  $("#usernameReg").val().trim(),
        email : $("#email").val().trim(),
        firstName : $("#firstName").val().trim(),
        lastName :  $("#lastName").val().trim(),
        password : $("#passwordReg").val().trim(),
        repeatedPassword : $("#repeatedPassword").val().trim(),
        sex : sex
    };

    $('body').append('<div id="page-preloader"><span class="spinner"></span></div>');

    $.ajax({
        type: 'POST',
        dataType : 'json',
        contentType: "application/json; charset=utf-8",
        url: '/api/registration',
        data: JSON.stringify(formData),
        beforeSend: function (xhr) {
            setRequestHeader(xhr)
        },
        success: function (data) {
            $('#page-preloader').remove();
            if (data !== null && data.errors.length === 0){
                $("#usernameReg").val('');
                $("#email").val('');
                $("#firstName").val('');
                $("#lastName").val('');
                $("#passwordReg").val('');
                $("#repeatedPassword").val('');
                $("#sex").val('');
                $('#registrationModal').modal('hide');
                $('#successReg').attr('class', ' alert alert-success alert-dismissible fade show col-lg')
            }
            else if (data.errors.length !== 0){
                data.errors.forEach(function (error) {
                    if(error.field === 'username')
                        $("#usernameError").text(error.defaultMessage);
                    if(error.field === 'email')
                        $("#emailError").text(error.defaultMessage);
                    if(error.field === 'firstName')
                        $("#firstNameError").text(error.defaultMessage);
                    if(error.field === 'lastName')
                        $("#lastNameError").text(error.defaultMessage);
                    if(error.field === 'password')
                        $("#passwordError").text(error.defaultMessage);
                    if(error.field === 'repeatedPassword')
                        $("#repeatedPasswordError").text(error.defaultMessage);
                    if(error.field === 'sex')
                        $("#sexError").text(error.defaultMessage);
                });
            }
        },
        error: function () {
            $('#page-preloader').remove();
        }
    });
}

function getChart(projectId) {
    $.ajax({
        type: 'GET',
        url: '/api/chart/' + projectId,
        success: function(data){
            drawChart(data);
        }
    });
}

function sendInvite() {
    var email = $('#employeeEmail').val().trim();
    var projectId = $("#inviteUsers").attr('data-id');

    $('body').append('<div id="page-preloader"><span class="spinner"></span></div>');

    $.ajax({
        type: 'GET',
        url: '/api/project/invite?projectId=' + projectId + '&email=' + email,
        success: function(data){
            $('#page-preloader').remove();
            if (data !== "") {
                $('#employeeEmail').val('');
                $('#inviteUsers').append('<div class="col-lg-12">' + data.email + '</div>');
            }
        }
    });
}



function submitNewPassword() {
    var passArr = [
        $('#oldPass').val(),
        $('#newPass').val(),
        $('#confirmNewPass').val()
    ];

    $.ajax({
        type: 'POST',
        url: '/api/newPass',
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(passArr),
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(data){
            if (data === true)
                $('#changePassMesSuccess').attr('class', 'alert alert-success');
            else
                $('#changePassMesError').attr('class', 'alert alert-danger');
        }
    });
}
