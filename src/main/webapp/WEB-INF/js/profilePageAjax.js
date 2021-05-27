function editCustomerInfo(name, surname, phone){
    var url = "editcustomer?";
    url.concat("name=").concat(name);
    url.concat("&surname=").concat(surname);
    url.concat("&phone=").concat(phone)
    const promise = $.ajax({
        type: "POST",
        url: url
    });
    return promise;
}