var examinationId = -1;

$(document).ready(function () {
    var examination = $(".examination")
    examination.show();
    var writeReport = $(".writeReport")
    writeReport.hide();
    var prescriptMedication = $(".prescriptMedication")
    prescriptMedication.hide();
    var scheduleNewAppointment = $(".scheduleNewAppointment")
    scheduleNewAppointment.hide();

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

$(document).on('click', '#btnReport', function () {
    var examination = $(".examination")
    examination.hide();
    var writeReport = $(".writeReport")
    writeReport.show();
    var prescriptMedication = $(".prescriptMedication")
    prescriptMedication.hide();
    var scheduleNewAppointment = $(".scheduleNewAppointment")
    scheduleNewAppointment.hide();


    $(document).on('click', '#btnSubmitReport', function () {
        var report = $("#chReport").val();
        var myJSON = JSON.stringify({"id":examinationId, "report":report});

        $.ajax({
            type: "POST",
            url: "http://localhost:8081/examinations/writeReport",
            dataType: "json",
            contentType: "application/json",
            data: myJSON,
            beforeSend: function(xhr) {
                if (localStorage.token) {
                    xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
                }
            },
            success: function (data) {
                var writeReport = $(".writeReport")
                writeReport.hide();
                var examination = $(".examination")
                examination.show();
                alert("Izmene su sacuvane");
            },
            error: function (data) {
                window.location.href="error.html";
            }
        });
    });










});