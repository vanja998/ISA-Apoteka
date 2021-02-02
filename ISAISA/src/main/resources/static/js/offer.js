$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/auth/allorders",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS : ", data);

            for (i = 0; i < data.length; i++) {
               
                var row = "<tr>";
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['adminEmail'] + "</td>";
                row += "<td>" + data[i]['pharmacyName'] + "</td>";
                row += "<td>" + data[i]['pharmacyAddress'] + "</td>";
                row += "<td>" + data[i]['dateDeadline'] + "</td>";
                row += "<td>" + data[i]['statusSupplier'] + "</td>";



                    var btn = "<button class='btnGiveOffer' id = " + data[i]['id'] + ">Kreiraj ponudu</button>";


                    row += "<td>" + btn + "</td>";

                $('#orders').append(row);

            }


        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });
});