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

    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy")
    pharmacistsShowAdminPharmacy.show();

    var pharmacistsSearchAdminPharmacy = $(".pharmacistsSearchAdminPharmacy")
    pharmacistsSearchAdminPharmacy.hide();

    var addNewPharmacistAdminPharmacy= $(".addNewPharmacistAdminPharmacy")
    addNewPharmacistAdminPharmacy.hide();

    var successAddPharmacist = $(".successAddPharmacist");
    successAddPharmacist.hide();

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

                $('#tablePharmacistsAdminPharmacy').append(row);
            }

        },
        error: function (data) {
            console.log("ERROR", data);
            window.location.href = "error.html";
        }
    });
});

$(document).on('click', '#btnSearchPharmacistsAdminPharmacy', function () {

    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy")
    pharmacistsShowAdminPharmacy.hide();

    var addNewPharmacistAdminPharmacy= $(".addNewPharmacistAdminPharmacy")
    addNewPharmacistAdminPharmacy.hide();

    var pharmacistsSearchAdminPharmacy= $(".pharmacistsSearchAdminPharmacy")
    pharmacistsSearchAdminPharmacy.show();

    var searchParam = $("#searchPharmacistsAdminPharmacy").val();
    searchParam = searchParam.split(" ");

    var myJSON = formToJSON(searchParam[0], searchParam[1])

    $('#searchPharmacists').empty();
    $('#tablePharmacistsSearchAdminPharmacy tbody').empty();

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pharmacyadmins/adminPharmacistsSearch",
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
            $('#searchPharmacists').append(searchParam[0], " ", searchParam[1]);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['firstName'] + "</td>";
                row += "<td>" + data[i]['lastName'] + "</td>";
                row += "<td>" + data[i]['rating'] + "</td>";
                row += "<td>" + data[i]['pharmacy']['name'] + "</td>";
                row += "<td>" + data[i]['pharmacy']['address'] + "</td>";
            }
            $('#tablePharmacistsSearchAdminPharmacy').append(row);
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

$(document).on('click', '#btnAddPharmacistAdminPharmacy', function () {

    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy")
    pharmacistsShowAdminPharmacy.hide();

    var pharmacistsSearchAdminPharmacy = $(".pharmacistsSearchAdminPharmacy")
    pharmacistsSearchAdminPharmacy.hide();

    var addNewPharmacistAdminPharmacy = $(".addNewPharmacistAdminPharmacy")
    addNewPharmacistAdminPharmacy.show();

});

$(document).on('click', '#btnAddSavePharmacistAdminPharmacy', function () {

    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var email = $("#email").val();
    var address = $("#address").val();
    var phone = $("#phone").val();
    var city = $("#city").val();
    var country = $("#country").val();

    var myJSON1 = formToJSON1(firstName, lastName, email, address, phone, city, country);

    console.log(myJSON1);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pharmacyadmins/adminPharmacistsAdd",
        dataType: "json",
        contentType: "application/json",
        data: myJSON1,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS: ", data);
            var addNewPharmacistAdminPharmacy = $(".addNewPharmacistAdminPharmacy")
            addNewPharmacistAdminPharmacy.hide();
            var successAddPharmacist = $(".successAddPharmacist");
            successAddPharmacist.show();
        },
        error: function (data) {
            console.log("ERROR: ", data);
            window.location.href="error.html";
        }
    });

});

function formToJSON1(firstName, lastName, email, address, phone, city, country) {
    return JSON.stringify(
        {
            "firstName" : firstName,
            "lastName" : lastName,
            "email" : email,
            "password" : "default",
            "address" : address,
            "phone" : phone,
            "city" : city,
            "country" : country
        }
    );
}

$(document).on('click', '#returnToPharmacists', function () {

    var successAddPharmacist = $(".successAddPharmacist");
    successAddPharmacist.hide();

    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy")
    pharmacistsShowAdminPharmacy.show();
});
