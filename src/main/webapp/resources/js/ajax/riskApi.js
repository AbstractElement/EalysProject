function addNewRisk(projectId){
    $("#nameError").text('');
    $("#levelError").text('');
    $("#descriptionError").text('');

    var formData = {
        name :  $("#name").val().trim(),
        description : $("#description").val().trim(),
        dangerLevel : $("#dangerLevel").val().trim()
    };

    $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        url: '/api/risk/' + projectId,
        data: JSON.stringify(formData),
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(data){
            if (data.errors.length === 0){
                getChart(projectId);
                $("#name").val('');
                $("#description").val('');
                $("#dangerLevel").val('1');
                $('#riskModal').modal('hide');
                moveToRisksPage(1);
            }
            else{
                data.errors.forEach(function (error) {
                    if(error.field === 'name')
                        $("#nameError").text(error.defaultMessage);
                    if(error.field === 'dangerLevel')
                        $("#levelError").text(error.defaultMessage);
                    if(error.field === 'description')
                        $("#descriptionError").text(error.defaultMessage);
                });
            }
        }
    });
}

function removeRisk(riskId) {
    var projectId = $('#risksAccordion').attr('data-id');

    $.ajax({
        type: 'DELETE',
        url: '/api/risk/' + riskId,
        contentType: "application/json; charset=utf-8",
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(){
            $('#remove-' + riskId).remove();
            getChart(projectId);
            moveToRisksPage(1)
        }
    });
}

function changeRisk(riskId) {
    $('#riskDescription-' + riskId).removeAttr('disabled');
    $('#saveChangeRisk-' + riskId).attr('class', 'visible close float-right')
}

function saveChangeRisk(riskId) {
    var description = $('#riskDescription-' + riskId).val().trim();
    var projectId = $('#risksAccordion').attr('data-id');

    $.ajax({
        type: 'PUT',
        url: '/api/project/' + projectId + '/risk/' + riskId,
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: description,
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(data){
            if (data !== null) {
                $('#saveChangeRisk-'+ riskId).attr('class', 'invisible');
                $('#riskDescription-' + riskId).attr('disabled', 'true');
            }
        }
    });
}
