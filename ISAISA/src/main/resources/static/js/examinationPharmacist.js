var examinationId = -1;

$(document).ready(function () {
    var examination = $(".examination")
    examination.show();
    var writeReport = $(".writeReport")
    writeReport.hide();
    var prescriptMedication = $(".prescriptMedication")
    prescriptMedication.hide();
    /*var scheduleNewAppointment = $(".scheduleNewAppointment")
    scheduleNewAppointment.hide();*/
    var medInPharmacy = $(".medInPharmacy");
    medInPharmacy.hide();
    var medNotInPharmacy = $(".medNotInPharmacy");
    medNotInPharmacy.hide();
    /*var alternativeMedication = $(".alternativeMedication");
    alternativeMedication.hide();
    var howToScheduleAppointment = $(".howToScheduleAppointment")
    howToScheduleAppointment.hide();
    var existingAppointments = $(".existingAppointments")
    existingAppointments.hide();
    var createAppointment = $(".createAppointment")
    createAppointment.hide();*/

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/examinations/getCounselingId",
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
    /*var scheduleNewAppointment = $(".scheduleNewAppointment")
    scheduleNewAppointment.hide();*/
    var medInPharmacy = $(".medInPharmacy");
    medInPharmacy.hide();
    var medNotInPharmacy = $(".medNotInPharmacy");
    medNotInPharmacy.hide();
    /*var alternativeMedication = $(".alternativeMedication");
    alternativeMedication.hide();
    var howToScheduleAppointment = $(".howToScheduleAppointment")
    howToScheduleAppointment.hide();
    var existingAppointments = $(".existingAppointments")
    existingAppointments.hide();
    var createAppointment = $(".createAppointment")
    createAppointment.hide();*/

    $(document).on('click', '#btnSubmitReport', function () {
        var report = $("#chReport").val();
        var myJSON = JSON.stringify({"id":examinationId, "report":report});

        $.ajax({
            type: "POST",
            url: "http://localhost:8081/examinations/writeReportPharmacist",
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

$(document).on('click', '#btnPrescript', function () {
    var examination = $(".examination");
    examination.hide();
    var writeReport = $(".writeReport");
    writeReport.hide();
    var prescriptMedication = $(".prescriptMedication");
    prescriptMedication.show();
    /*var scheduleNewAppointment = $(".scheduleNewAppointment");
    scheduleNewAppointment.hide();*/
    var medInPharmacy = $(".medInPharmacy");
    medInPharmacy.hide();
    var medNotInPharmacy = $(".medNotInPharmacy");
    medNotInPharmacy.hide();
    /*var alternativeMedication = $(".alternativeMedication");
    alternativeMedication.hide();
    var howToScheduleAppointment = $(".howToScheduleAppointment")
    howToScheduleAppointment.hide();
    var existingAppointments = $(".existingAppointments")
    existingAppointments.hide();
    var createAppointment = $(".createAppointment")
    createAppointment.hide();*/

    $("#idDropDown").empty();

    var myJSON = JSON.stringify({"id":examinationId});
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/examinations/getMedicationsForPrescriptionPharmacist",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log('Success', data);
            $("#idDropDown").append("<option> </option>");
            for (i = 0; i < data.length; i++) {
                /*var row = "<tr>";
                row += "<td>" + data[i]['name'] + "</td>";
                var btn = "<button class='btnButton' id = " + data[i]['name'] + ">Proveri</button>";
                row += "<td>" + btn + "</td>";
                $('#tableMeds').append(row);*/
                var name = data[i]['name'];
                $("#idDropDown").append("<option value='"+name+"'>"+name+"</option>");
            }
        },
        error: function (data) {
            console.log('Error', data);
        }
    });
});

var imeIzabranogLeka;

$(document).on('change', '#idDropDown', function () {
    //console.log('Something');
    var name = $("#idDropDown :selected").val();
    var myJSON = JSON.stringify({"id":examinationId, "name":name});
    imeIzabranogLeka = name;
    /*console.log(name, examinationId);
    var examination = $(".examination");
    examination.hide();
    var writeReport = $(".writeReport");
    writeReport.hide();
    var prescriptMedication = $(".prescriptMedication");
    prescriptMedication.hide();
    var scheduleNewAppointment = $(".scheduleNewAppointment");
    scheduleNewAppointment.hide();*/
    var medInPharmacy = $(".medInPharmacy");
    medInPharmacy.hide();
    var medNotInPharmacy = $(".medNotInPharmacy");
    medNotInPharmacy.hide();



    $.ajax({
        type: "POST",
        url: "http://localhost:8081/examinations/checkIfMedicationIsAvailablePharma",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log('Success', data);

            var d = data['value'];
            console.log('d',d);
            if(d === true){
                medInPharmacy.show();
                /*var row = "<tr>";
                row += "<td>Lek je dostupan u apoteci.</td>";
                var btn = "<button class='btnSubmitPrescription' id = " + name + ">Potvrdi</button>";
                row += "<td>" + btn + "</td>";
                $('#tableMedInPharmacy').append(row);*/
            }
            else {
                medNotInPharmacy.show();
            }

        },
        error: function (data) {
            console.log('Error', data);
        }
    });
});












