$(document).ready(function () {

    var ordersShow = $(".ordersShow");
    var offersShow = $(".offersShow");
    var acceptOffer = $(".acceptOffer");
    var deleteOrder = $(".deleteOrder");
    var changeOrder = $(".changeOrder");

    ordersShow.show();
    offersShow.hide();
    acceptOffer.hide();
    deleteOrder.hide();
    changeOrder.hide();

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
                row += "<td>" + data[i]['id'] + "</td>";
                row += "<td>" + data[i]['dateDeadline'] + "</td>";

                var btnOffers = "<button class='btnOffers' id = " + data[i]['id'] + ">Pogledaj ponude</button>";
                row += "<td>" + btnOffers + "</td>";

                var btnChangeOrder = "<button class='btnChangeOrder' id = " + data[i]['id'] + ">Izmeni narudzbenicu</button>";
                row += "<td>" + btnChangeOrder + "</td>";

                var btnDeleteOrder = "<button class='btnDeleteOrder' id = " + data[i]['id'] + ">Obrisi narudzbenicu</button>";
                row += "<td>" + btnDeleteOrder + "</td>";

                $('#tableOrdersShow').append(row);
            }
        },
        error: function (jqXHR) {
            if(jqXHR.status === 403) {
                window.location.href="error.html";
            }
            if(jqXHR.status === 401) {
                window.location.href="error.html";
            }
        }
    });
});

$(document).on('click', '.btnOffers', function () {
    var ordersShow = $(".ordersShow");
    var offersShow = $(".offersShow");
    var acceptOffer = $(".acceptOffer");
    var deleteOrder = $(".deleteOrder");
    var changeOrder = $(".changeOrder");

    ordersShow.hide();
    offersShow.show();
    acceptOffer.hide();
    deleteOrder.hide();
    changeOrder.hide();

    var id = this.id;

    console.log(id);

    id = JSON.stringify({"id" : id})

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/offers/offersByOrder",
        dataType: "json",
        contentType: "application/json",
        data: id,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS", data);

            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                row += "<td>" + data[i]['supplier']['id'] + "</td>";
                row += "<td>" + data[i]['supplier']['email'] + "</td>";
                row += "<td>" + data[i]['offerPrice'] + "</td>";
                row += "<td>" + data[i]['deliveryDate'] + "</td>";
                var btnCreateOffer = "<button class='btnAcceptOffer' id = " + data[i]['id'] + ">Prihvati ponudu</button>";
                row += "<td>" + btnCreateOffer + "</td>";

                $('#tableOffersShow').append(row);
            }
        },
        error: function (jqXHR) {
            if(jqXHR.status === 403) {
                window.location.href="error.html";
            }
            if(jqXHR.status === 401) {
                window.location.href="error.html";
            }
        }
    });
});

$(document).on('click', '.btnAcceptOffer', function () {

    var offersShow = $(".offersShow");
    offersShow.hide();

    var acceptOffer = $(".acceptOffer");
    acceptOffer.show();


    var id = this.id;

    console.log(id);

    id = JSON.stringify({"id" : id})

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/offers/acceptOffer",
        dataType: "json",
        contentType: "application/json",
        data: id,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS", data);

            var successOrder = $(".successOrder");
            successOrder.show();
            var errorOrder = $(".errorOrder");
            errorOrder.hide();
        },
        error: function (jqXHR) {
            if(jqXHR.status === 500) {
                var errorOrder = $(".errorOrder");
                errorOrder.show();
                var successOrder = $(".successOrder");
                successOrder.hide();

                var response = JSON.parse(jqXHR.responseText);
                $('#errorOrder').append(response['message']);
            }
            else {
                window.location.href="error.html";
            }
        }
    });
});

$(document).on('click', '.btnReturnOffer', function () {
    var offersShow = $(".offersShow");
    var acceptOffer = $(".acceptOffer");

    offersShow.show();
    acceptOffer.hide();
});

$(document).on('click', '.btnDeleteOrder', function () {

    var ordersShow = $(".ordersShow");
    ordersShow.hide();

    var deleteOrder = $(".deleteOrder");
    deleteOrder.show();

    var id = this.id;
    id = JSON.stringify({"id" : id});

    $.ajax({
        type: "POST",
        url: "http://localhost:8081/orders/deleteOrder",
        contentType: "application/json",
        data: id,
        beforeSend: function (xhr) {
            if (localStorage.token) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.token);
            }
        },
        success: function (data) {
            console.log("SUCCESS", data);

            var errorDeleteOrder = $(".errorDeleteOrder");
            errorDeleteOrder.hide();

            var successDeleteOrder = $(".successDeleteOrder");
            successDeleteOrder.show();
        },
        error: function (jqXHR, data) {
            if(jqXHR.status === 500) {
                console.log(data)

                var errorDeleteOrder = $(".errorDeleteOrder");
                errorDeleteOrder.show();

                var successDeleteOrder = $(".successDeleteOrder");
                successDeleteOrder.hide();

                var response = JSON.parse(jqXHR.responseText);
                $('#errorDeleteOrder').append(response['message']);
            }
            else {
                window.location.href="error.html";
            }
        }
    });

});

$(document).on('click', '.btnChangeOrder', function () {

    var ordersShow = $(".ordersShow");
    ordersShow.hide();

    var changeOrder = $(".changeOrder");
    changeOrder.show();

    var errorDeleteOrder = $(".errorDeleteOrder");
    errorDeleteOrder.hide();

    var successDeleteOrder = $(".successDeleteOrder");
    successDeleteOrder.hide();

});



$(document).on('click', '.btnReturnOrder', function () {
    window.location.href="adminPharmacyChooseOffer.html";
});