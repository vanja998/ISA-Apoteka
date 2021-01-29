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
	        success: function (data) {
				// data = ulogovani korisnik koji je vratila metoda iz kontrolera
				// mozemo tu vrednost da ispisemo u konzoli
	            console.log(data);
	
	            alert(email + " je uspešno ulogovan");

				localStorage.setItem('token', data['accessToken']);


				if (data['role']==='adminpharmacy'){

					window.location.href = "welcomeAdminPharmacy.html";

				}
				else if(data['role']==='patient'){
					window.location.href = "patientProfile.html";
				}
				else{
					window.location.href = "error.html";
				}




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