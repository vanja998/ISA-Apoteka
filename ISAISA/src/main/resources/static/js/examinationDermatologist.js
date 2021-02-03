$(document).ready(function () {
    var examination = $(".examination")
    var writeReport = $(".writeReport")
    var prescriptMedication = $(".prescriptMedication")
    var examination = $(".scheduleNewAppointment")
    var examinationId = -1;

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/examinations/getAppointmentId",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            examinationId = data['id'];
            console.log(examinationId);
        },
        error: function () {
            window.location.href="error.html";
        }
    });

    
















});
