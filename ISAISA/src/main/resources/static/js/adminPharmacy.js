$(document).ready(function () {
    console.log("gg");
    $.ajax({
        type: "GET",
        url: "http://localhost:8081/pharmacy",
        dataType: "json",
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS: ", data);
            $('#pharmacyName').append(data['name']);
            $('#address').append(data['address']);
            $('#description').append(data['description']);
            $('#rating').append(data['rating']);
        },
        error: function (data) {
            window.location.href = "error.html"
        }
    });
});

