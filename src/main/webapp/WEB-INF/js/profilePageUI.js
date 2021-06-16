$(function (){
    $("#edit-info").click(function (){
        let button = document.getElementById("edit-info");
        button.disabled = true;
        let oldName = $("#user-name").val();
        let oldSurname = $("#user-surname").val();
        let oldPhone = $("#user-phone").val();
        let oldEmail = $("#user-email").val();
        let form = document.getElementById("edit-info-form");
        form.innerHTML = "<h3>Введите новые данные</h3>" +
            "<input id=\"new-name\" type=\"text\" placeholder=\"Введите имя\" value=\"" + oldName + "\">\n" +
            "<input id=\"new-surname\" type=\"text\" placeholder=\"Введите фамилию\" value=\"" + oldSurname + "\">\n" +
            "<input id=\"new-phone\" type=\"text\" placeholder=\"Введите телефон\" value=\"" + oldPhone + "\">\n" +
            "<input id=\"new-email\" type=\"text\" placeholder=\"Введите почту\" value=\"" + oldEmail + "\">\n" +
            "<input id=\"submit\" type=\"button\" value=\"Применить\">"
            $("#submit").click(function (){
                let newName = $("#new-name").val();
                let newSurname = $("#new-surname").val();
                let newPhone = $("#new-phone").val();
                let newEmail = $("#new-email").val();
                if(checkNameWord(newName) && checkNameWord(newSurname) && checkPhoneNumber(newPhone) && checkEmail(newEmail)){
                    form.innerHTML = '';
                    const promise = postUserInfo(newName, newSurname, newPhone, newEmail);
                    promise.then(changeUserInfo(newName, newSurname, newPhone, newEmail));
                    button.disabled = false;
                }else {
                    alert("Некорректные данные");
                }
            })
            function changeUserInfo(name, surname, phone, email){
                let userName = document.getElementById("user-name");
                userName.innerText = name;
                let userSurname = document.getElementById("user-surname");
                userSurname.innerText = surname;
                let userPhone = document.getElementById("user-phone");
                userPhone.innerText = phone;
                let userEmail = document.getElementById("user-email");
                userEmail.innerText = email;
            }
    })
    $("#logout").click(function (){
        logout();
    })
});
