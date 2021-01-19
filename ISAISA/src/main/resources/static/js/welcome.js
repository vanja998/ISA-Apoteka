$(document).ready(function () {

    var email = localStorage.getItem("email");

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/users/u",
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
            $('#id').append(data['id']);
            $('#email').append(data['email']);
            $('#password').append(data['password']);
            $('#firstName').append(data['firstName']);
            $('#lastName').append(data['lastName']);
            $('#address').append(data['address']);
            $('#phone').append(data['phone']);
            $('#city').append(data['city']);
            $('#country').append(data['country']);

            //var korisniciDiv = $("#jedanKorisnik");              // dobavi element čiji je id = oneEmployee
            //korisniciDiv.show();                               // prikaži taj element

            //var btn = "<button class='btnPromenaUloge' id = " + data['id'] + ">Promeni ulogu </button>";
            //var row = "<tr>";
            //row += "<td>" + btn + "</td>";
            //$('#promenaUloge').append(row);
        },
        error: function (data) {
            console.log("ERROR : ", data);
        }
    });

});