//Svi korisnici
$(document).ready(function () {    // Čeka se trenutak kada je DOM(Document Object Model) učitan da bi JS mogao sa njim da manipuliše.
    // ajax poziv
    $.ajax({
        type: "GET",                                                // HTTP metoda
        url: "http://localhost:8081/pharmacy/allpharmacies",                  // URL koji se gađa
        dataType: "json",                                           // tip povratne vrednosti
        success: function (data) {
            console.log("SUCCESS : ", data);                        // ispisujemo u konzoli povratnu vrednost
            for (i = 0; i < data.length; i++) {                     // prolazimo kroz listu svih zaposlenih
                var row = "<tr>";// kreiramo red za tabelu
                row += "<td>" + data[i]['name'] + "</td>";     // ubacujemo podatke jednog zaposlenog u polja
                row += "<td>" + data[i]['address'] + "</td>";
                row += "<td>" + data[i]['description'] + "</td>";
                row += "<td>" + data[i]['rating'] + "</td>";

                $('#pharmacy').append(row);                        // ubacujemo kreirani red u tabelu čiji je id = employees
            }
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});
