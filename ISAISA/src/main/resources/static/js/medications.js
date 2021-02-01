$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8081/medications/allmedications",                  // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (data) {
            console.log("SUCCESS : ", data);                        // ispisujemo u konzoli povratnu vrednost
            for (i = 0; i < data.length; i++) {                     // prolazimo kroz listu svih zaposlenih
                var row = "<tr>";// kreiramo red za tabelu
                row += "<td>" + data[i]['code'] + "</td>";     // ubacujemo podatke jednog zaposlenog u polja
                row += "<td>" + data[i]['name'] + "</td>";
                row += "<td>" + data[i]['type_med'] + "</td>";
                row += "<td>" + data[i]['shape_med'] + "</td>";
                row += "<td>" + data[i]['ingredients'] + "</td>";
                row += "<td>" + data[i]['producer'] + "</td>";
                row += "<td>" + data[i]['prescription'] + "</td>";
                row += "<td>" + data[i]['notes'] + "</td>";

                $('#medication').append(row);                        // ubacujemo kreirani red u tabelu čiji je id = employees
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});
$(document).on('click', '#btnSearchMedication', function () {

    var searchParam = $("#searchMedication").val();


    var myJSON = formToJSON(searchParam)

    $('#tableMedicationsSearch').empty();
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/medications/MedicationsSearch",
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