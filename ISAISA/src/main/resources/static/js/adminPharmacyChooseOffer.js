$(document).ready(function () {

    var ordersShow = $(".ordersShow");
    var offersShow = $(".offersShow");

    ordersShow.show();
    offersShow.hide();

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

            var orderrs = [];
            var counts = new Array();

            for (i = 0; i < data.length; i++) {
                var row = "<tr>";
                orderrs.push(data[i]['orderr']);
                row += "<td>" + data[i]['medication']['code'] + "</td>";
                row += "<td>" + data[i]['medication']['name'] + "</td>";
                row += "<td>" + data[i]['amount'] + "</td>";
                $('#tableOrdersShow').append(row);

            }
            //orderrs = onlyUnique(orderrs);
            //orderrs = orderrs.filter((x, i, a) => a.indexOf(x) == i)

            console.log(onlyUnique(orderrs));

            //Koliko ponuda imamo za svaku narudzbenicu
            var count = 0;
            for (order in orderrs) {
                counts.push(0);
                for (i = 0; i < data.length; i++) {
                    if(data[i]['order'] === order) {
                        counts[count] += 1;
                    }
                }
                count += 1;
            }


            count=0;
            for (var i = 0, row; row === document.getElementById("tableOrdersShow").rows[i]; i+=counts[count]){
                row += "<td rowspan='counts[count]'>" + data[i]['dateDeadline'] + "</td>";
                var btnOffers = "<button class='btnOffers' id = " + data[i]['id'] + ">Pogledaj ponude</button>";
                row += "<td>" + btnOffers + "</td>";

                count++;
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

function onlyUnique(a) {
    return [...new Set(a)];
}

$(document).on('click', '.btnOffers', function () {
var ordersShow = $(".ordersShow");
var offersShow = $(".offersShow");

ordersShow.hide();
offersShow.show();

var id = this.id;

console.log(id);


});