$(document).on('click', '#btnSearchMedication', function () {

    var name = $("#name").val();


    var myJSON = formToJSON(name)

    $('#tableMedicationsSearch').empty();
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/medications/MedicationsSearchforReservation",
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
            $('#searchMedication').append(searchParam);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['code'] + "</td>";     // ubacujemo podatke jednog zaposlenog u polja
                row += "<td>" + data[i]['name'] + "</td>";
                row += "<td>" + data[i]['type_med'] + "</td>";
                row += "<td>" + data[i]['shape_med'] + "</td>";
                row += "<td>" + data[i]['ingredients'] + "</td>";
                row += "<td>" + data[i]['producer'] + "</td>";
                row += "<td>" + data[i]['prescription'] + "</td>";
                row += "<td>" + data[i]['notes'] + "</td>";
                $('#tableMedicationsSearch').append(row);
            }

        },
        error: function (data) {
            console.log("ERROR: ", data);
            window.location.href="error.html";
        }
    });

});

function formToJSON(name) {
    return JSON.stringify(
        {
            "name": name

        }
    );
}