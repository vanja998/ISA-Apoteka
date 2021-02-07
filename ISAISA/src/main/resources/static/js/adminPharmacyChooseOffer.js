$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "http://localhost:8081/orders/ordersByPharmacy",
        dataType: "json",
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS", data);
            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                for (j in data[i]['order_medications']) {
                    row += "<td>" + data[i]['medication'] + "</td>";
                    row += "<td>" + data[i]['amount'] + "</td>";
                    row += "<td>" + data[i]['dateDeadline'] + "</td>";

                    var btnOffers = "<button class='btnOffers' id = " + data[i]['id'] + ">Pogledaj ponude</button>";
                    row += "<td>" + btnOffers + "</td>";

                    $('#tableOrdersShow').append(row);
                }
            }
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
