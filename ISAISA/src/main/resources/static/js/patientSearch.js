$(document).ready(function () {

    var patientsShowAll = $(".patientsShowAll")
    patientsShowAll.show();
    var patientsShowSearch = $(".patientsShowSearch")
    patientsShowSearch.hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/patients/allSearchPatientsDerma",
        dataType: "json",
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['firstName'] + "</td>";
                row += "<td>" + data[i]['lastName'] + "</td>";
                row += "<td>" + data[i]['address'] + "</td>";
                row += "<td>" + data[i]['city'] + "</td>";
                row += "<td>" + data[i]['phone'] + "</td>";
                $('#tablePatientsShow').append(row);

            }
        },
        error: function (data) {
            console.log("ERROR", data);
            //window.location.href = "error.html";
        }
    });
});

$(document).on('click', '#btnSearchPatient', function () {

    var patientsShowAll = $(".patientsShowAll")
    patientsShowAll.hide();
    var patientsShowSearch = $(".patientsShowSearch")
    patientsShowSearch.show();

    var searchParam = $("#patientSearch").val();
    searchParam = searchParam.split(" ");

    var myJSON = formToJSON(searchParam[0], searchParam[1])

    $('#searchPatient').empty();
    $('#tablepatientsShowSearch tbody').empty();

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/patients/searchPatientsDerma",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS: ", data);
            $('#searchPatient').append(searchParam[0], " ", searchParam[1]);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['firstName'] + "</td>";
                row += "<td>" + data[i]['lastName'] + "</td>";
                row += "<td>" + data[i]['address'] + "</td>";
                row += "<td>" + data[i]['city'] + "</td>";
                row += "<td>" + data[i]['phone'] + "</td>";
                $('#tablepatientsShowSearch').append(row);
            }

        },
        error: function (data) {
            console.log("ERROR: ", data);
            window.location.href="error.html";
        }
    });

});

function formToJSON(firstName, lastName) {
    return JSON.stringify(
        {
            "firstName": firstName,
            "lastName": lastName
        }
    );
}