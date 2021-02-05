$(document).ready(function () {

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

    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    pharmacistsFilterAdminPharmacy.hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/pharmacists/adminPharmacists",
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

//Pretraga
$(document).on('click', '.btnSearchPharmacistsAdminPharmacy', function () {

    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy")
    pharmacistsShowAdminPharmacy.hide();

    var addNewPharmacistAdminPharmacy= $(".addNewPharmacistAdminPharmacy")
    addNewPharmacistAdminPharmacy.hide();

    var pharmacistsSearchAdminPharmacy= $(".pharmacistsSearchAdminPharmacy")
    pharmacistsSearchAdminPharmacy.show();

    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    pharmacistsFilterAdminPharmacy.hide();

    var searchParam = $(".searchPharmacistsAdminPharmacy").val();
    searchParam = searchParam.split(" ");

    var myJSON = formToJSON(searchParam[0], searchParam[1])

    $('.searchPharmacists').empty();
    $('#tablePharmacistsSearchAdminPharmacy tbody').empty();

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pharmacists/adminPharmacistsSearch",
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
            $('.searchPharmacists').append(searchParam[0], " ", searchParam[1]);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['firstName'] + "</td>";
                row += "<td>" + data[i]['lastName'] + "</td>";
                row += "<td>" + data[i]['rating'] + "</td>";
                row += "<td>" + data[i]['pharmacy']['name'] + "</td>";
                row += "<td>" + data[i]['pharmacy']['address'] + "</td>";

                $('#tablePharmacistsSearchAdminPharmacy').append(row);
            }
        },
        error: function (jqXHR) {
            if(jqXHR.status === 403)
            {
                window.location.href="error.html";
            }
            if(jqXHR.status === 401)
            {
                window.location.href="error.html";
            }
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

//Registracija farmaceuta
$(document).on('click', '.btnAddPharmacistAdminPharmacy', function () {

    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy")
    pharmacistsShowAdminPharmacy.hide();

    var pharmacistsSearchAdminPharmacy = $(".pharmacistsSearchAdminPharmacy")
    pharmacistsSearchAdminPharmacy.hide();

    var addNewPharmacistAdminPharmacy = $(".addNewPharmacistAdminPharmacy")
    addNewPharmacistAdminPharmacy.show();

    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    pharmacistsFilterAdminPharmacy.hide();

});

$(document).on('click', '#btnAddSavePharmacistAdminPharmacy', function () {

    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var email = $("#email").val();
    var address = $("#address").val();
    var phone = $("#phone").val();
    var city = $("#city").val();
    var country = $("#country").val();

    var myJSON = formToJSON1(firstName, lastName, email, address, phone, city, country);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pharmacists/adminPharmacistsAdd",
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
            var addNewPharmacistAdminPharmacy = $(".addNewPharmacistAdminPharmacy")
            addNewPharmacistAdminPharmacy.hide();
            var successAddPharmacist = $(".successAddPharmacist");
            successAddPharmacist.show();
        },
        error: function (jqXHR) {
            if(jqXHR.status === 403)
            {
                window.location.href="error.html";
            }
            if(jqXHR.status === 401)
            {
                window.location.href="error.html";
            }
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

    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    pharmacistsFilterAdminPharmacy.hide();
});

$(document).on('click', '.btnFilterPharmacistAdminPharmacy', function () {

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

    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    pharmacistsFilterAdminPharmacy.show();
});

$(document).on('click', '#btnSubmitFilterPharmacists', function () {

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

    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    pharmacistsFilterAdminPharmacy.hide();

    var ratingOver = $("#ratingOver").val();
    var ratingUnder = $("#ratingUnder").val();

    var myJSON = formToJSON2(ratingOver, ratingUnder);

    $('#tablePharmacistsAdminPharmacy tbody').empty();

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pharmacists/adminPharmacistsFilter",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
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
        error: function (jqXHR) {
            if(jqXHR.status === 403)
            {
                window.location.href="error.html";
            }
            if(jqXHR.status === 401)
            {
                window.location.href="error.html";
            }
        }
    });
});

function formToJSON2(ratingOver, ratingUnder) {
    return JSON.stringify(
        {
            "ratingOver" : ratingOver,
            "ratingUnder" : ratingUnder
        }
    );
}

$(document).on('click', '#btnCancelFilterPharmacists', function () {
    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    pharmacistsFilterAdminPharmacy.hide();
});