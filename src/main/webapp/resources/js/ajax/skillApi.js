function addNewSkill() {
    var skillName = $('#inputNewSkill').val().trim();

    if (skillName.trim() !== ''){
        $.ajax({
            type: 'POST',
            url: '/api/skill',
            contentType: "application/json; charset=utf-8",
            data: skillName,
            beforeSend: function(xhr){
                setRequestHeader(xhr)
            },
            success: function(data) {
                if (data !== null) {
                    $('#inputNewSkill').val('');
                    $('#userSkills').append(
                        '<div class="btn-group mt-2 mr-2" id="skill-' + data.skillId + '">' +
                        '<a class="btn btn-secondary font-weight-bold" href="/user/skill?skill=' + data.name + '">' + data.name + '</a>' +
                        '<button type="button" onclick="removeSkill(' + data.skillId + ')" ' +
                        'class="btn btn-secondary font-weight-bold">&times;' +
                        '</button>' +
                        '</div>');
                }
            }
        });
    }
}

function removeSkill(skillId) {
    $.ajax({
        type: 'DELETE',
        url: '/api/skill/' + skillId,
        beforeSend: function(xhr){
            setRequestHeader(xhr)
        },
        success: function(){
            $('#skill-' + skillId).remove();
        }
    });
}