$(document).ready(function () {

    var dermatologistsAdminPharmacy = $(".dermatologistsAdminPharmacy")
    dermatologistsAdminPharmacy.hide();

    var pharmacistsAdminPharmacy = $(".pharmacistsAdminPharmacy")
    pharmacistsAdminPharmacy.hide();

    var employeesAdminPharmacy = $(".employeesAdminPharmacy")
    employeesAdminPharmacy.show();
});

$(document).on('click', '#btnPharmacistsAdminPharmacy', function () {

    var dermatologistsAdminPharmacy = $(".dermatologistsAdminPharmacy")
    dermatologistsAdminPharmacy.hide();

    var employeesAdminPharmacy = $(".employeesAdminPharmacy")
    employeesAdminPharmacy.hide();

    var pharmacistsAdminPharmacy = $(".pharmacistsAdminPharmacy")
    pharmacistsAdminPharmacy.show();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/pharmacyadmins/adminPharmacists",
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
                row += "<td>" + data[i]['rating'] + "</td>";
                row += "<td>" + data[i]['pharmacy']['name'] + "</td>";
                row += "<td>" + data[i]['pharmacy']['address'] + "</td>";
            }
            $('#tablePharmacistsAdminPharmacy').append(row);
        },
        error: function (data) {
            console.log("ERROR", data);
            //window.location.href = "error.html";
        }
    });
});