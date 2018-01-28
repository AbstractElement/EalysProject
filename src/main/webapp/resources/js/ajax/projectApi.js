
function addNewProject(){
    $('#nameError').text('');
    $('#targetError').text('');
    $('#descriptionError').text('');

    var formData = {
        name :  $("#name").val().trim(),
        description : $("#description").val().trim(),
        target : $("#target").val().trim()
    };

    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        url: '/api/project',
        data: JSON.stringify(formData),
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(data) {
            if (data.errors.length === 0) {
                $("#name").val('');
                $("#description").val('');
                $("#target").val('');
                $("#newProjectModal").modal('hide');
                moveToProjectPage(1);
            }
            else{
                data.errors.forEach(function (error) {
                    if(error.field === 'name')
                        $("#nameError").text(error.defaultMessage);
                    if(error.field === 'target')
                        $("#targetError").text(error.defaultMessage);
                    if(error.field === 'description')
                        $("#descriptionError").text(error.defaultMessage);
                });
            }
        }
    });
}

function saveUpdatedProject() {
    $('#nameError').text('');
    $('#targetError').text('');
    $('#descriptionError').text('');

    var formData = {
        projectId : $("#updateForm").attr('data-id'),
        name :  $("#name").val().trim(),
        description : $("#description").val().trim(),
        target : $("#target").val().trim(),
        date : $("#updateForm").attr('data-date')
    };

    $.ajax({
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        url: '/api/project',
        data: JSON.stringify(formData),
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(data){
            if (data.errors.length === 0) {
                $('#nameError').text('');
                $('#targetError').text('');
                $('#descriptionError').text('');
                $('#successMes').attr('class', ' alert alert-success alert-dismissible fade show');
            }
            else{
                data.errors.forEach(function (error) {
                    if(error.field === 'name')
                        $("#nameError").text(error.defaultMessage);
                    if(error.field === 'target')
                        $("#targetError").text(error.defaultMessage);
                    if(error.field === 'description')
                        $("#descriptionError").text(error.defaultMessage);
                });
            }
        }
    });
}

function deleteImage(imageId){
    var projectId = $('#image-' + imageId).attr('data-projectId');

    var data = {
        imageId : imageId,
        imageName : $('#image-' + imageId).attr('data-imageName')
    };

    $.ajax({
        type: 'DELETE',
        url: '/api/project/' + projectId + '/image',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(){
            $('#image-' + imageId).remove();
        }
    });
}
