$(function (){
    $("#register").click(function (){
        let name = document.getElementById("firstname").value;
        let surname = document.getElementById("surname").value;
        let phone = document.getElementById("phone").value;
        let email = document.getElementById("email").value;
        let password = document.getElementById("password").value;

        if(!checkNameWord(name) || !checkNameWord(surname) || !checkPhoneNumber(phone) ||
            !checkEmail(email)){
            event.preventDefault();
            alert("Введены некорректные данные");
        }
        if(password.toString().length < 6){
            event.preventDefault();
            alert("Пароль слишком короткий");
        }
        if(emailIsPresent(email) == true){
            event.preventDefault();
            alert("Пользователь с таким email уже существует");
        }
        if(phoneNumberIsPresent(phone) == true){
            event.preventDefault();
            alert("Пользователь с таким номером телефона уже существует");
        }
    })

});