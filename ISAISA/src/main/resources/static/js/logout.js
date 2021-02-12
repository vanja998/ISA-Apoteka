$(document).on('click', '#logout', function (){
    localStorage.setItem('token', null);
    window.location.href = "login.html";
})
