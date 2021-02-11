//Prikazi sve lekove
$(document).ready(function () {

    var medicineAdminPharmacy = $(".medicineAdminPharmacy")
    var medicineShowAdminPharmacy = $(".medicineShowAdminPharmacy")
    var medicineSearchAdminPharmacy = $(".medicineSearchAdminPharmacy")
    var addNewMedicineAdminPharmacy = $(".addNewMedicineAdminPharmacy")
    var successAddMedicine = $(".successAddMedicine")
    var errorAddMedicine = $(".errorAddMedicine")
    var modalDelete = $(".modalDelete")
    var errorDeleteMedicine = $(".errorDeleteMedicine")

    medicineAdminPharmacy.show();
    medicineShowAdminPharmacy.show();
    medicineSearchAdminPharmacy.hide();
    addNewMedicineAdminPharmacy.hide();
    successAddMedicine.hide();
    errorAddMedicine.hide();
    modalDelete.hide();
    errorDeleteMedicine.hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/medications/adminmedications",
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
                row += "<td>" + data[i]['code'] + "</td>";
                row += "<td>" + data[i]['name'] + "</td>";
                row += "<td>" + data[i]['producer'] + "</td>";
                row += "<td>" + data[i]['quantity'] + "</td>";
                row += "<td>" + data[i]['price'] + "</td>";

                var btnChange = "<button class='btnChange' id = " + data[i]['id'] + "> Izmeni lek </button>";
                row += "<td>" + btnChange + "</td>";

                var btnRemove = "<button class='btnRemove' id = " + data[i]['id'] + "> Ukloni lek </button>";
                row += "<td>" + btnRemove + "</td>";

                $('#tableMedicineAdminPharmacy').append(row);
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

//Pretraga
$(document).on('click', '.btnSearchMedicineAdminPharmacy', function () {


    var medicineAdminPharmacy = $(".medicineAdminPharmacy")
    var medicineShowAdminPharmacy = $(".medicineShowAdminPharmacy")
    var medicineSearchAdminPharmacy = $(".medicineSearchAdminPharmacy")
    var addNewMedicineAdminPharmacy = $(".addNewMedicineAdminPharmacy")
    var successAddMedicine = $(".successAddMedicine")
    var errorAddMedicine = $(".errorAddMedicine")
    var modalDelete = $(".modalDelete")
    var errorDeleteMedicine = $(".errorDeleteMedicine")

    medicineAdminPharmacy.show();
    medicineShowAdminPharmacy.hide();
    medicineSearchAdminPharmacy.show();
    addNewMedicineAdminPharmacy.hide();
    successAddMedicine.hide();
    errorAddMedicine.hide();
    modalDelete.hide();
    errorDeleteMedicine.hide();

    var searchParam = $(".searchMedicineAdminPharmacy").val();

    var myJSON = JSON.stringify({"name" :searchParam});

    $('.searchMedicineAdminPharmacy').val("");
    $('#tableMedicineSearchAdminPharmacy tbody').empty();

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/medications/adminMedicationSearch",
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
            $('#searchPharmacists').append(searchParam);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['code'] + "</td>";
                row += "<td>" + data[i]['name'] + "</td>";
                row += "<td>" + data[i]['producer'] + "</td>";
                row += "<td>" + data[i]['quantity'] + "</td>";
                row += "<td>" + data[i]['price'] + "</td>";

                var btnChange = "<button class='btnChange' id = " + data[i]['id'] + "> Izmeni lek </button>";
                row += "<td>" + btnChange + "</td>";

                var btnRemove = "<button class='btnRemove' id = " + data[i]['id'] + "> Ukloni lek </button>";
                row += "<td>" + btnRemove + "</td>";

                $('#tableMedicineSearchAdminPharmacy').append(row);
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

//Dodavanje
//Registracija dermatologa
$(document).on('click', '.btnAddMedicineAdminPharmacy', function () {
    var medicineAdminPharmacy = $(".medicineAdminPharmacy")
    var medicineShowAdminPharmacy = $(".medicineShowAdminPharmacy")
    var medicineSearchAdminPharmacy = $(".medicineSearchAdminPharmacy")
    var addNewMedicineAdminPharmacy = $(".addNewMedicineAdminPharmacy")
    var successAddMedicine = $(".successAddMedicine")
    var errorAddMedicine = $(".errorAddMedicine")
    var modalDelete = $(".modalDelete")
    var errorDeleteMedicine = $(".errorDeleteMedicine")

    medicineAdminPharmacy.hide();
    medicineShowAdminPharmacy.hide();
    medicineSearchAdminPharmacy.hide();
    addNewMedicineAdminPharmacy.show();
    successAddMedicine.hide();
    errorAddMedicine.hide();
    modalDelete.hide();
    errorDeleteMedicine.hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/medications/adminmedicationsNot",
        dataType: "json",
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS", data);
            for (i = 0; i < data.length; i++) {
                var option = "<option>" + data[i]['name'] + "</option>";
                $('#medicineName').append(option);
                console.log(option);
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

$(document).on('click', '#btnAddSaveMedicineAdminPharmacy', function () {

    var medicineName = $("#medicineName").val();
    var price = $("#price").val();
    var beginPriceValidity = $("#beginPriceValidity").val();
    var endPriceValidity = $("#endPriceValidity").val();

    var myJSON = formToJSON1(medicineName, price, beginPriceValidity, endPriceValidity);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/medications/addMedicineToPharmacy",
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
            $("#medicineName").val("");
            $("#price").val("");
            $("#beginPriceValidity").val("");
            $("#endPriceValidity").val("");

            window.location.href="adminPharmacyMedicine.html";
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
            if(jqXHR.status === 500)
            {
                var response = JSON.parse(jqXHR.responseText);
                document.getElementById('errorAddMeds').innerHTML = response['message'];
                var addNewMedicineAdminPharmacy = $(".addNewMedicineAdminPharmacy")
                addNewMedicineAdminPharmacy.hide();
                var errorAddMedicine = $(".errorAddMedicine");
                errorAddMedicine.show();
            }
        }
    });

});

function formToJSON1(medicineName, price, beginPriceValidity, endPriceValidity) {
    return JSON.stringify(
        {
            "name" : medicineName,
            "price" : price,
            "beginPriceValidity" : beginPriceValidity,
            "endPriceValidity" : endPriceValidity
        }
    );
}

$(document).on('click', '#returnToMedicine', function () {

    var successAddDermatologist = $(".successAddDermatologist");
    successAddDermatologist.hide();

    var dermatologistsShowAdminPharmacy = $(".dermatologistsShowAdminPharmacy")
    dermatologistsShowAdminPharmacy.show();

    var dermatologistsFilterAdminPharmacy = $(".dermatologistsFilterAdminPharmacy");
    dermatologistsFilterAdminPharmacy.hide();
});

$(document).on('click', '#errorAddMedicine', function () {

    var errorAddDermatologist = $(".errorAddDermatologist");
    errorAddDermatologist.hide();

    var dermatologistsShowAdminPharmacy = $(".dermatologistsShowAdminPharmacy")
    dermatologistsShowAdminPharmacy.show();

    var dermatologistsFilterAdminPharmacy = $(".dermatologistsFilterAdminPharmacy");
    dermatologistsFilterAdminPharmacy.hide();
});