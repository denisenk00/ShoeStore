function postNewPair(size, modelId, status){
    let url = "/shoestore/shoe/add"
    const promise = $.ajax({
        url: url,
        data:{"size":size, "modelId":modelId, "status":status},
        method: "POST",
        dataType: "JSON"
    });
    return promise;
}
function updateModel(modelId, newPrice){
    let url = "/shoestore/model/updateModel";
    const promice = $.ajax({
        url: url,
        data:{"id":modelId, "price":newPrice},
        method: "POST",
        dataType: "JSON"
    });
    return promice;
}
function updateShoe(shoeId, status){
    let url = "/shoestore/shoe/update";
    url = url.concat("id=").concat(shoeId);
    url = url.concat("&status=").concat(status);
    const promice = $.ajax({
        url: url,
        data:{"id":shoeId, "status":status},
        method: "POST",
        dataType: "JSON"
    });
    return promice;
}

function postModel(brand, name, price, type, color, season, gender, supplierId){
    let url = "/shoestore/model/addModel";
    $.ajax({
        url: url,
        data:{"name":name, "brand":brand, "price":price, "type":type, "season":season,
            "supplierId":supplierId, "color":color, "gender":gender},
        method: "POST"
    });
}
function getSuppliers(){
    let url = "getAllSuppliers";
    let suppliers = $.ajax(url);
    return suppliers;
}

function postUserInfo(name, surname, phone, email){
    let url = "/shoestore/user/edit";
    const promise = $.ajax({
        method: "POST",
        url: url,
        dataType: "JSON",
        data: {"name":name, "surname":surname, "phone":phone, "email":email}
    });
    return promise;
}
function logout(){
    $.ajax("logout");
}

function addToProductCart(modelId, size){
    let url = "/shoestore/cart/add";
    const promise = $.ajax({
        url:url,
        method:"POST",
        dataType: "JSON",
        data: {"modelId":modelId, "size":size}
    });
    return promise;
}

function setFilters(seasons, types, brands, colors,  sizes, genders, minPrice, maxPrice){
    let url = "/shoestore/store/updateFilters?seasons=";
    function urlBuilder(currentValue, index, arr){
        url = url.concat(encodeURIComponent(currentValue));
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
    const promise = $.ajax({
        url:url,
        method: "POST",
        dataType: "JSON"
    });
    return promise;
}
function getModels(currentPage){
    let url = "/shoestore/store/getModelsByFilter";
    const promise = $.ajax({
        url:url,
        data:{"page":currentPage},
        method: "GET",
        dataType: "JSON"
    });
    return promise;
}

function getPagination(currentPage, pageLocation){
    var url = "/shoestore/store/getPaginationByFilter";
    const promise = $.ajax({
        url:url,
        method: "GET",
        dataType: "JSON",
        data:{"page":currentPage, "pageLocation":pageLocation}
    });
    return promise;
}

function postNewSupplier(company, city, country, address, phone, postalCode){
    var url = "/shoestore/addSupplier";
    const promise = $.ajax({
        url:url,
        data: {"company":company, "city":city, "country":country,
            "address":address, "phone":phone, "postalCode":postalCode},
        method:"POST"
    });
    return promise;
}