function postUserInfo(name, surname, phone, email){
    var url = "user/edit?";
    url = url.concat("name=").concat(name);
    url = url.concat("&surname=").concat(surname);
    url = url.concat("&phone=").concat(phone)
    url = url.concat("&email=").concat(email)
    const promise = $.ajax({
        type: "POST",
        url: url
    });
    return promise;
}
function logout(){
    $.ajax("logout");
}
function goToAdminPanel(){
    $.ajax("store/adminPanel");
}