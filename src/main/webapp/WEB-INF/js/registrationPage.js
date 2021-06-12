$(function (){
    $("#register").click(function (){
        var name = document.getElementById("firstname");
        var surname = document.getElementById("surname");
        var phone = document.getElementById("phone");
        var email = document.getElementById("email");
        if(!checkNameWord(name) || !checkNameWord(surname) || !checkPhoneNumber(phone) ||
            !checkEmail(email)){
            event.preventDefault();
            alert("Введены некорректные данные");
        }
        if(emailIsPresent(email)){
            event.preventDefault();
            alert("Пользователь с таким email уже существует");
        }
        if(phoneNumberIsPresent(phone)){
            event.preventDefault();
            alert("Пользователь с таким номером телефона уже существует");
        }
    })

});