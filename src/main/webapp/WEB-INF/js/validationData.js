function checkNameWord(str){
    return str.match(/[A-Z][a-z]+/);
}
function checkPhoneNumber(number) {
    number = number.replace(/[\s\-]/g, '');
    return number.match(/^((\+?3)?8)?((0\(\d{2}\)?)|(\(0\d{2}\))|(0\d{2}))\d{7}$/) != null;
}
function checkEmail(email){
    return email.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/) != null;
}
function phoneNumberIsPresent(number) {
    var url = "checkPhoneNumber?number=".concat(number);
    const promise = $.ajax(url);
    return promise;
}
function emailIsPresent(email){
    var url = "checkEmail?email=".concat(email);
    const promise = $.ajax(url);
    return promise;
}

