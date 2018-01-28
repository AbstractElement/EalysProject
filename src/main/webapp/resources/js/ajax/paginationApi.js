
function moveToProjectPage(pageNumber) {
    $.ajax({
        type: 'GET',
        url: '/api/pageProjects?page=' + pageNumber,
        success: function(data){
            amountProjectsPages(pageNumber);
            if (data !== null){
                $('#oldProjects').remove();
                window.history.pushState("Page", "Page updated", "/project?pageNumber=" + pageNumber);
                $('#projects').append('<div id="oldProjects"></div>');
                data.forEach(function (project, i) {
                    $('#oldProjects').append(
                        '<div class="row">' +
                        '<div class="col-md-9">' +
                        '<span>' +
                        '<a class="btn float-right" href="/project/${project.projectId}/leave">' +
                        '<span class="oi oi-trash"></span>' +
                        '</a>'+
                        '<a href="/project/' + project.projectId + '?category=project">' +
                        '<h3>' + project.name + '</h3>' +
                        '</a>' +
                        '</span>' +
                        '<p>' + project.target + '</p>' +
                        '</div>' +
                        '<div class="col-md-3" id="projectImage' + i + '">' +
                        '</div>' +
                        '</div>' +
                        '<hr>'
                    );
                    if (project.images.length !== 0) {
                        $('#projectImage' + i).append(
                            '<img class="img-thumbnail mb-3 mb-md-0" src='+ $('#contextPathHolder').attr('data-contextPath') +'"/resources/images/projectsPhotos/' +
                            project.images[0].imageName + '" style="width: 300px" alt="">'
                        )
                    }
                    else{
                        $('#projectImage' + i).append(
                            '<img class="img-thumbnail mb-3 mb-md-0" style="width: 300px;" src='+ $('#contextPathHolder').attr('data-contextPath') +'"/resources/images/no-image.png">'
                        )
                    }
                });
            }
        }
    });
}

function moveToRisksPage(pageNumber) {
    var projectId = $('#risksAccordion').attr('data-id');

    $.ajax({
        type: 'GET',
        url: '/api/project/' + projectId + '/pageRisks?page=' + pageNumber,
        success: function(data){
            if (data !== null){
                amountRisksPages(pageNumber);
                window.history.pushState("New page", "Page updated", "/project/" + projectId + "/risks?pageNumber=" + pageNumber);
                $('#risksAccordion').text('');
                data.forEach(function (risk) {
                    $('#risksAccordion').append(
                        '<div id="remove-' + risk.riskId +'" class="card mb-2">' +
                        '<div class="card-header" role="tab">' +
                        '<h5 class="mb-0">' +
                        '<a data-toggle="collapse" href="#' + risk.riskId + '" aria-expanded="true" aria-controls="collapseOne">' +
                        risk.name + " " +
                        '<span class="badge badge-pill badge-danger">' +
                        risk.dangerLevel +
                        '</span>' +
                        '</a>' +
                        '<button type="button" onclick="removeRisk(' + risk.riskId + ')"' +
                        ' class="close">&times;</button>' +
                        '</h5>' +
                        '</div>' +
                        '<div id="' + risk.riskId + '" class="collapse" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">' +
                        '<div class="card-body">' +
                        '<div class="media">' +
                        '<div class="media-body">' +
                        '<h5 class="mt-0">' + risk.author.firstName + " " + risk.author.lastName +
                        '<a id="saveChangeRisk-' + risk.riskId + '" class="invisible" ' +
                        'onclick="saveChangeRisk(' + risk.riskId + ')">' +
                        '<span class="oi oi-check small text-success"></span>' +
                        '</a>' +
                        '<a id="changeRisk-' + risk.riskId + '" class="float-right" ' +
                        'onclick="changeRisk(' + risk.riskId + ')">' +
                        '<span class="oi oi-pencil small text-primary"></span>' +
                        '</a>' +
                        '</h5>' +
                        '<sec:csrfMetaTags/>' +
                        '<textarea id="riskDescription-' + risk.riskId + '" type="text" disabled' +
                        ' class="form-control">' + risk.description + '</textarea>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>'
                    )
                });
            }
        }
    });
}

function moveToUsersPage(pageNumber) {
    var skillName = $('#searchSkill').val();
    var url = '';

    if (skillName !== '')
        url = '/api/user/skill?name=' + skillName + "&page=" + pageNumber;
    else
        url = '/api/user?page=' + pageNumber;

    $.ajax({
        type: 'GET',
        url: url,
        contentType: "application/json; charset=utf-8",
        success: function(data){
            amountUsersPages(pageNumber);
            window.history.pushState("New page", "Page updated", "/user?pageNumber=" + pageNumber);
            if (data !== null){
                $('#users').text('');
                data.forEach(function (user) {
                    $('#users').append(
                        '<div class="row text-left mb-4">' +
                        '<div class="col-lg-2">' +
                        '<img class="rounded-circle img-fluid d-block mx-auto"' +
                        ' src='+ $('#contextPathHolder').attr('data-contextPath') +'"/resources/images/usersPhotos/' + user.photo + '" style="width:100px;">' +
                        '</div>' +
                        '<div class="col-lg-10">' +
                        '<h3>' +
                        '<a href="/user/' + user.userId + '">' + user.firstName + " " + user.lastName + '</a>' +
                        '<small>' + " " + user.position + '</small>' +
                        '</h3>' + user.email +
                        '</div>' +
                        '</div>'
                    )
                });
            }
        }
    });
}


function amountUsersPages(pageNumber) {
    $.ajax({
        type: 'GET',
        url: '/api/amountUsersPages',
        contentType: "application/json; charset=utf-8",
        success: function(data){
            if (data !== null){
                $('#user-pagination').text('');
                if (data > 1) {
                    $('#user-pagination').append(
                        '<li class="page-item" id="previousPage">' +
                        '<span class="page-link" onclick="moveToUsersPage(' + (pageNumber - 1) + ')">&laquo;</span>' +
                        '</li>'
                    );
                    if (pageNumber === 1)
                        $('#previousPage').attr('class', 'page-item disabled');
                }
                for (var i = 1; i <= data; i++){
                    if (i === pageNumber)
                        $('#user-pagination').append(
                            '<li class="page-item active">' +
                            '<span class="page-link" onclick="moveToUsersPage(' + i + ')">' + i + '</span>' +
                            '<span class="sr-only">(current)</span>' +
                            '</li>'
                        );
                    else
                        $('#user-pagination').append(
                            '<li class="page-item">' +
                            '<span class="page-link" onclick="moveToUsersPage(' + i + ')">' + i + '</span>' +
                            '</li>'
                        )
                }
                if (data > 10){
                    $('#user-pagination').append(
                        '<li class="page-item" id="nextPage">' +
                        '<span class="page-link" onclick="moveToUsersPage('+ (pageNumber + 1) +')">&raquo;</span>' +
                        '</li>'
                    );
                    if (pageNumber === data)
                        $('#nextPage').attr('class', 'page-item disabled');
                }
            }
        }
    });
}

function amountProjectsPages(pageNumber) {
    $.ajax({
        type: 'GET',
        url: '/api/amountProjectsPages',
        contentType: "application/json; charset=utf-8",
        success: function(data){
            if (data !== null){
                $('#project-pagination').text('');
                if (data > 1) {
                    $('#project-pagination').append(
                        '<li class="page-item" id="previousPage">' +
                        '<span class="page-link" onclick="moveToProjectPage(' + (pageNumber - 1) + ')">&laquo;</span>' +
                        '</li>'
                    );
                    if (pageNumber === 1)
                        $('#previousPage').attr('class', 'page-item disabled');
                }
                for (var i = 1; i <= data; i++){
                    if (i === pageNumber)
                        $('#project-pagination').append(
                            '<li class="page-item active">' +
                            '<span class="page-link" onclick="moveToProjectPage(' + i + ')">' + i + '</span>' +
                            '<span class="sr-only">(current)</span>' +
                            '</li>'
                        );
                    else
                        $('#project-pagination').append(
                            '<li class="page-item">' +
                            '<span class="page-link" onclick="moveToProjectPage(' + i + ')">' + i + '</span>' +
                            '</li>'
                        )
                }
                if (data > 1) {
                    $('#project-pagination').append(
                        '<li class="page-item" id="nextPage">' +
                        '<span class="page-link" onclick="moveToProjectPage(' + (pageNumber + 1) + ')">&raquo;</span>' +
                        '</li>'
                    );
                    if (pageNumber === data)
                        $('#nextPage').attr('class', 'page-item disabled');
                }
            }
        }
    });
}

function amountRisksPages(pageNumber) {
    var projectId = $('#risksAccordion').attr('data-id');

    $.ajax({
        type: 'GET',
        url: '/api/amountRisksPages?project=' + projectId,
        contentType: "application/json; charset=utf-8",
        success: function(data){
            if (data !== null){
                $('#risk-pagination').text('');
                if (data > 1) {
                    $('#risk-pagination').append(
                        '<li class="page-item" id="previousPage">' +
                        '<span class="page-link" onclick="moveToRisksPage(' + (pageNumber - 1) + ')">&laquo;</span>' +
                        '</li>'
                    );
                    if (pageNumber === 1)
                        $('#previousPage').attr('class', 'page-item disabled');
                }
                for (var i = 1; i <= data; i++){
                    if (i === pageNumber)
                        $('#risk-pagination').append(
                            '<li class="page-item active">' +
                            '<span class="page-link" onclick="moveToRisksPage(' + i + ')">' + i + '</span>' +
                            '<span class="sr-only">(current)</span>' +
                            '</li>'
                        );
                    else
                        $('#risk-pagination').append(
                            '<li class="page-item">' +
                            '<span class="page-link" onclick="moveToRisksPage(' + i + ')">' + i + '</span>' +
                            '</li>'
                        );
                }
                if (data > 1){
                    $('#risk-pagination').append(
                        '<li class="page-item" id="nextPage">' +
                        '<span class="page-link" onclick="moveToRisksPage(' + (pageNumber + 1) +')">&raquo;</span>' +
                        '</li>'
                    );
                    if (pageNumber === data)
                        $('#nextPage').attr('class', 'page-item disabled');
                }

            }
        }
    });
}