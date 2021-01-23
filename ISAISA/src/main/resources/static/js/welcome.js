$(document).ready(function () {

    var email = localStorage.getItem("email");

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/users/user",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS : ", data);
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });

});