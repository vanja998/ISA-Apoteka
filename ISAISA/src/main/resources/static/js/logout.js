$(document).on('click', '#logout', function (){
    localStorage.setItem('token', null);
    localStorage.setItem('role', null);
    window.location.href = "login.html";
})
