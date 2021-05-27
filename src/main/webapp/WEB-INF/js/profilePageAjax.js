function editUserInfo(name, surname, phone, email){
    var url = "edituser?";
    url.concat("name=").concat(name);
    url.concat("&surname=").concat(surname);
    url.concat("&phone=").concat(phone)
    url.concat("&email=").concat(email)
    const promise = $.ajax({
        type: "POST",
        url: url
    });
    return promise;
}