$(document).on("submit", "form", function (event) {           // kada je submitovana forma za kreiranje novog zaposlenog
    event.preventDefault();

    var namee = $("#namee").val();
    var lastName = $("#lastName").val();
    var password = $("#password").val();
    var adress = $("#adress").val();
    var email = $("#email").val();
    var city = $("#city").val();
    var country = $("#country").val();
    var phoneNumber = $("#phoneNumber").val();

    var newKupacJSON = formToJSON(namee,lastName, password,  adress, email, city, country, phoneNumber);  // pozivamo pomoćnu metodu da kreira JSON

    $.ajax({
        type: "POST",                                               // HTTP metoda je POST
        url: "http://localhost:8081/auth/signupPatient",                 // URL na koji se šalju podaci
        dataType: "json",                                           // tip povratne vrednosti
        contentType: "application/json",                            // tip podataka koje šaljemo
        data: newKupacJSON,                                      // Šaljemo novog zaposlenog
        success: function () {
            alert("success");
            window.location.href = "welcomeAdminPharmacy.html";
        },
        error: function (error) {
            alert(error);
        }
    });
});

function formToJSON(namee,lastName,  password, adress, email, city, country, phoneNumber) {
    return JSON.stringify(
        {
            "namee": namee,
            "lastName": lastName,

            "password": password,

            "adress": adress,
            "email": email,
            "city": city,
            "country": country,
            "phoneNumber": phoneNumber

        }
    );
};