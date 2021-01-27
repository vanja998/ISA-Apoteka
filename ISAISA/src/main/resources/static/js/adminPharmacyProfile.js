$(document).ready(function () {

    var changeProfileAdminPharmacy = $(".changeProfileAdminPharmacy")
    changeProfileAdminPharmacy.hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/pharmacyadmins/admin",
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

//Promena profila
$(document).on('click', '#btnChangeProfileAdminPharmacy', function () {

    var profileAdminPharmacy = $(".profileAdminPharmacy")
    profileAdminPharmacy.hide();

    var changeProfileAdminPharmacy = $(".changeProfileAdminPharmacy")
    changeProfileAdminPharmacy.show();
    
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/pharmacyadmins/adminChange",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log(data);
            /*
            $('#chFirstName').append(data['firstName']);
            $('#chLastName').append(data['lastName']);
            $('#chEmail').append(data['email']);
            $('#chAddress').append(data['address']);
            $('#chPhone').append(data['phone']);
            $('#chCity').append(data['city']);
            $('#chCountry').append(data['country']);
            $('#chPharmacy').append(data['pharmacy']);
            */
        },
        error: function (data) {
            window.location.href="error.html";
        }
    });
});