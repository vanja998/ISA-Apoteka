$(document).ready(function () {

    var changeProfileDermatologist = $(".changeProfileDermatologist")
    changeProfileDermatologist.hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/dermatologists/dermatologist",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {

            $('#firstName').append(data['firstName']);
            $('#lastName').append(data['lastName']);
            $('#email').append(data['email']);
            $('#address').append(data['address']);
            $('#phone').append(data['phone']);
            $('#city').append(data['city']);
            $('#country').append(data['country']);
            $('#pharmacy').append(data['pharmacy']);
        },
        error: function (data) {
            window.location.href="error.html";
        }
    });
});

$(document).on('click', '#btnChangeProfileDermatologist', function () {

    var profileDermatologist = $(".profileDermatologist")
    profileDermatologist.hide();

    var changeProfileDermatologist = $(".changeProfileDermatologist")
    changeProfileDermatologist.show();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/dermatologists/dermatologist",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            $('#chFirstName:text').val(data['firstName']);
            $('#chLastName:text').val(data['lastName']);
            $('#chAddress:text').val(data['address']);
            $('#chPhone:text').val(data['phone']);
            $('#chCity:text').val(data['city']);
            $('#chCountry:text').val(data['country']);
        },
        error: function (data) {
            window.location.href="error.html";
        }
    });

});

$(document).on('click', '#btnSaveProfileDermatologist', function () {

    var data = {
         firstName : $('input[id="chFirstName"]').val(),
         lastName : $('input[id="chLastName"]').val(),
         email : $('input[id="chEmail"]').val(),
         address : $('input[id="chAddress"]').val(),
         phone : $('input[id="chPhone"]').val(),
         city : $('textarea[id="chCity"]').val(),
         country : $('textarea[id="chCountry"]').val(),
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/dermatologists/dermatologistChange",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        success: function (data) {



            console.log(data);
            alert("Izmene su uspešno sacuvane");
            window.location.href = "dermatologistProfile.html";
        },
        error: function (data) {
            window.location.href="error.html";
        }
    });
});