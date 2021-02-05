$(document).ready(function () {
    var createPromotion = $(".createPromotion");
    createPromotion.show();

    var successfulPromotion = $(".successfulPromotion");
    successfulPromotion.hide();

    var unsuccessfulPromotion = $(".unsuccessfulPromotion");
    unsuccessfulPromotion.hide();
});

$(document).on('click', '#btnSubmitCreatePromotion', function () {

    var description = $("#descriptionOfPromotion").val();
    var validFrom = $("#validFrom").val();
    var validUntil = $("#validUntil").val();

    var myJSON = formToJSON(description, validFrom, validUntil);

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/promotions/createPromotion",
        dataType: "json",
        contentType: "application/json",
        data: myJSON,
        beforeSend: function(xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS: ", data);

            var createPromotion = $(".createPromotion");
            createPromotion.hide();

            var successfulPromotion = $(".successfulPromotion");
            successfulPromotion.show();
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

function formToJSON(description, validFrom, validUntil) {
    return JSON.stringify(
        {
            "description" : description,
            "validFrom" : validFrom,
            "validUntil" : validUntil
        }
    );
}


$(document).on('click', '#btnSuccessfulCreatePromotion', function () {

    var createPromotion = $(".createPromotion");
    createPromotion.show();

    var successfulPromotion = $(".successfulPromotion");
    successfulPromotion.hide();

    var unsuccessfulPromotion = $(".unsuccessfulPromotion");
    unsuccessfulPromotion.hide();

    $("#descriptionOfPromotion").val("");
    $("#validFrom").val("");
    $("#validUntil").val("");

});


$(document).on('click', '#btnUnsuccessfulCreatePromotion', function () {
    var createPromotion = $(".createPromotion");
    createPromotion.show();

    var successfulPromotion = $(".successfulPromotion");
    successfulPromotion.hide();

    var unsuccessfulPromotion = $(".unsuccessfulPromotion");
    unsuccessfulPromotion.hide();

    $("#descriptionOfPromotion").val("");
    $("#validFrom").val("");
    $("#validUntil").val("");
});