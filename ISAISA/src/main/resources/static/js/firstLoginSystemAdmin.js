$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/systemadmins/admin",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            if(data['flag']===0){
                window.location.href="changePassSystemAdmin.html";
            }
        },
        error: function (jqXHR) {
            if(jqXHR.status == 403)
            {
                window.location.href="error.html";
            }
            if(jqXHR.status == 401)
            {
                window.location.href="error.html";
            }
        }
    });
});