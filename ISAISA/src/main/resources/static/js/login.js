$(document).ready(function () {

    $('#btnLogin').click(function () {

    	event.preventDefault();

        console.log('proba')
        
	    var email = $("#email").val();
	    var password = $("#password").val();
	
	    var myJSON = formToJSON(email, password);
	
	    $.ajax({
	        type: "POST",
	        url: "http://localhost:8081/auth/login",
	        dataType: "json",
	        contentType: "application/json",
	        data: myJSON,
			//headers: {"Authorization": localStorage.getItem('token')},
	        success: function (data) {
				// data = ulogovani korisnik koji je vratila metoda iz kontrolera
				// mozemo tu vrednost da ispisemo u konzoli
	            console.log(data);
	
	            alert(email + " je uspešno ulogovan");

	            localStorage.setItem('email', email);

	            window.location.href = "welcome.html";
	        },
	        error: function (data) {
	            console.log(data);
	            alert("Greska");
	        }
	    });
        
    })
})

function formToJSON(email, password) {
    return JSON.stringify(
        {
            "email": email,
            "password": password
        }
    );
}