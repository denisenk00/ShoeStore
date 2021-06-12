$(function (){
    $("#edit-info").click(function (){
        var button = document.getElementById("edit-info");
        button.disabled = true;
        var oldName = $("#user-name").val();
        var oldSurname = $("#user-surname").val();
        var oldPhone = $("#user-phone").val();
        var oldEmail = $("#user-email").val();
        var form = document.getElementById("edit-info-form");
        form.innerHTML = "<h3>Введите новые данные</h3>" +
            "<input id=\"new-name\" type=\"text\" placeholder=\"Введите имя\" value=\"" + oldName + "\">\n" +
            "<input id=\"new-surname\" type=\"text\" placeholder=\"Введите фамилию\" value=\"" + oldSurname + "\">\n" +
            "<input id=\"new-phone\" type=\"text\" placeholder=\"Введите телефон\" value=\"" + oldPhone + "\">\n" +
            "<input id=\"new-email\" type=\"text\" placeholder=\"Введите почту\" value=\"" + oldEmail + "\">\n" +
            "<input id=\"submit\" type=\"button\" value=\"Применить\">"
            $("#submit").click(function (){
                var newName = $("#new-name").val();
                var newSurname = $("#new-surname").val();
                var newPhone = $("#new-phone").val();
                var newEmail = $("#new-email").val();
                if(checkNameWord(newName) && checkNameWord(newSurname) && checkPhoneNumber(newPhone) && checkEmail(newEmail)){
                    form.remove();
                    const promise = postUserInfo(newName, newSurname, newPhone, newEmail);
                    promise.then(changeUserInfo(newName, newSurname, newPhone, newEmail));
                    button.disabled = false;
                }else {
                    alert("Некорректные данные");
                }
            })
            function changeUserInfo(name, surname, phone, email){
                var userName = document.getElementById("user-name");
                userName.nodeValue = name;
                var userSurname = document.getElementById("user-surname");
                userSurname.nodeValue = surname;
                var userPhone = document.getElementById("user-phone");
                userPhone.nodeValue = phone;
                var userEmail = document.getElementById("user-email");
                userEmail.nodeValue = email;
            }
    })
    $("#logout").click(function (){
        logout();
    })
    $("#admin-panel").click(function (){
        goToAdminPanel();
    })
});
