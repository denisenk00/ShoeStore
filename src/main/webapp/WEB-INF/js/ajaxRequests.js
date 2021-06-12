function postNewPair(size, modelId, status){
    var url = "/shoe/add?size=".concat(size);
    url = url.concat("&modelId=").concat(modelId);
    url = url.concat("&status=").concat(status);
    const promise = $.ajax(url);
    return promise;
}
function updateModel(modelId, newPrice){
    var url = "model/updateModel?";
    url = url.concat("id=").concat(modelId);
    url = url.concat("&price=").concat(newPrice);
    const promice = $.ajax(url);
    return promice;
}
function updateShoe(shoeId, status){
    var url = "shoe/update?";
    url = url.concat("id=").concat(shoeId);
    url = url.concat("&status=").concat(status);
    const promice = $.ajax(url);
    return promice;
}

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
    var url = "getAllSuppliers";
    var suppliers = $.ajax(url);
    return suppliers;
}

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
    $.ajax("store/removeFilters?goTo=adminPanel");
}

function addToBasket(modelId, size){
    let url = "cart/add?modelId=";
    url = url.concat(modelId);
    url = url.concat("&size=").concat(size);
    const promise = $.ajax(url);
    return promise;
}

function setFilters(seasons, types, brands, colors,  sizes, genders, minPrice, maxPrice){
    var url = "updateFilters?seasons=";
    function urlBuilder(currentValue, index, arr){
        url = url.concat(currentValue);
        if(index != arr.length-1) url = url.concat("+");
    }
    seasons.forEach(urlBuilder);
    url = url.concat("&types=");
    types.forEach(urlBuilder);
    url = url.concat("&brands=");
    brands.forEach(urlBuilder);
    url = url.concat("&colors=");
    colors.forEach(urlBuilder);
    url = url.concat("&sizes=");
    sizes.forEach(urlBuilder);
    url = url.concat("&genders=");
    genders.forEach(urlBuilder);
    url = url.concat("&minPrice=").concat(minPrice);
    url = url.concat("&maxPrice=").concat(maxPrice);
    const promise = $.ajax(url);
    return promise;
}
function getModels(currentPage){
    var url = "getModelsByFilter?page=".concat(currentPage);
    const promise = $.ajax(url);
    return promise;
}

function getPagination(currentPage, pageLocation){
    var url = "getPaginationByFilter?page=".concat(currentPage);
    url = url.concat("&pageLocation=").concat(pageLocation);
    const promise = $.ajax(url);
    return promise;
}

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