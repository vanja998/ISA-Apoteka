//Id apoteke na koju je kliknuto
var id
//Prikaz info o apoteci
$(document).on('click', '.btnPharmacy', function () {
    var allPharmacies = $(".allPharmacies");
    var onePharmacy = $(".onePharmacy");
    var card = $(".card")
    var pharmacistsAdminPharmacy = $(".pharmacistsAdminPharmacy")
    var pharmacistsSearchAdminPharmacy = $(".pharmacistsSearchAdminPharmacy")
    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy");

    allPharmacies.hide();
    onePharmacy.show();
    pharmacistsShowAdminPharmacy.hide();
    card.show();
    pharmacistsFilterAdminPharmacy.hide();
    pharmacistsSearchAdminPharmacy.hide();
    pharmacistsAdminPharmacy.hide();

    id = this.id;
    console.log(id);
    var id1 = JSON.stringify({"id" : id});

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pharmacy",
        dataType: "json",
        contentType: "application/json",
        data: id1,
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS: ", data);
            $('#pharmacyName').append(data['name']);
            $('#address1').append(data['address']);
            $('#description1').append(data['description']);
            $('#rating1').append(data['rating']);
        },
        error: function (data) {
            window.location.href = "error.html"
        }
    });

});

//*************************Farmaceuti
//Prikaz farmaceuta
$(document).on('click', '#patientAllPharmacists', function () {
    var card = $(".card");
    var pharmacistsShowAdminPharmacy = $(".pharmacistsShowAdminPharmacy");
    var pharmacistsSearchAdminPharmacy = $(".pharmacistsSearchAdminPharmacy");
    var pharmacistsFilterAdminPharmacy = $(".pharmacistsFilterAdminPharmacy");
    var pharmacistsAdminPharmacy = $(".pharmacistsAdminPharmacy");

    card.hide();
    pharmacistsShowAdminPharmacy.show();
    pharmacistsSearchAdminPharmacy.hide();
    pharmacistsFilterAdminPharmacy.hide();
    pharmacistsAdminPharmacy.show();

    var id1 = JSON.stringify({"id" : id});

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/pharmacists/allPharmacistsFromPharmacy",
        dataType: "json",
        contentType: "application/json",
        data: id1,
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

                var btnMakeAppointment = "<button class='btnMakeAppointment' id = " + data[i]['id'] + "> Zakazi savetovanje </button>";
                row += "<td>" + btnMakeAppointment + "</td>";

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

//Zakazivanje
$(document).on('click', '.btnMakeAppointment', function (){


});

//************************Dermatolozi

