function postNewSupplier(company, city, country, address, phone, postalCode){
    var url = "addSupplier?";
    url = url.concat("company=").concat(company);
    url = url.concat("&city=").concat(city);
    url = url.concat("&country=").concat(country);
    url = url.concat("&address=").concat(address);
    url = url.concat("&phone=").concat(phone);
    url = url.concat("&postalCode").concat(postalCode);
    const promise = $.ajax(url);
    return promise;
}