function checkNameWord(str){
    return str.match(/[А-я][а-я]+/) != null || str.match(/[A-z][a-z]+/) != null;
}
function checkPhoneNumber(number) {
    number = number.replace(/[\s\-]/g, '');
    return number.match(/^((\+?3)?8)?((0\(\d{2}\)?)|(\(0\d{2}\))|(0\d{2}))\d{7}$/) != null;
}
function checkEmail(email){
    return email.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) != null;
}
function phoneNumberIsPresent(phone) {
    let url = "/shoestore/service/checkPhoneForExistence?phone=".concat(phone);
    let bool = false;
    $.ajax({
        url:url,
        type:"get",
        dataType: "text",
        async: false,
        success: function (data){
            if(data == "true") bool = true;
            else bool = false;
        }
    });
    return bool;
}
function emailIsPresent(email){
    let url = "/shoestore/service/checkEmailForExistence?email=".concat(email);
    let bool = false;
    $.ajax({
        url:url,
        type:"get",
        dataType: "text",
        async: false,
        success: function (data){
            if(data == "true") bool = true;
            else bool = false;
        }
    });
    return bool;
}

