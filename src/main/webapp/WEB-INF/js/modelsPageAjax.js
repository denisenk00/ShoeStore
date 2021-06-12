function postModel(brand, name, price, type, color, season, gender, supplierId){
    var url = "model/addModel?";
    url = url.concat("name=").concat(name);
    url = url.concat("&brand=").concat(brand);
    url = url.concat("&price=").concat(price);
    url = url.concat("&type=").concat(type);
    url = url.concat("&season=").concat(season);
    url = url.concat("&supplierId=").concat(supplierId);
    url = url.concat("&color=").concat(color);
    url = url.concat("&gender=").concat(gender);
    $.ajax(url);
}
function getSuppliers(){
    var url = "rest/supplier/getAll";
    var suppliers = $.ajax(url);
    return suppliers;
}