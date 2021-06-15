function postNewPair(size, modelId, status){
    let url = "/shoestore/shoe/add"
    return $.ajax({
        url: url,
        data:{"size":size, "modelId":modelId, "status":status},
        method: "POST",
        dataType: "JSON",
        success:function (){
            let url = "/shoestore/admin/model?id=".concat(modelId);
            window.location.href = url;
        }
    });
}
function updateModel(modelId, newPrice){
    let url = "/shoestore/updateModel";
    return  $.ajax({
        url: url,
        data:{"id":modelId, "price":newPrice},
        method: "POST",
        dataType: "JSON"
    });
}
function updateShoe(shoeId, status){
    let url = "/shoestore/shoe/update";
    url = url.concat("id=").concat(shoeId);
    url = url.concat("&status=").concat(status);
    return $.ajax({
        url: url,
        data:{"id":shoeId, "status":status},
        method: "POST",
        dataType: "JSON"
    });
}

function postModel(brand, name, price, type, color, season, gender, supplierId){
    let url = "/shoestore/addModel";
    return $.ajax({
        url: url,
        data:{"name":name, "brand":brand, "price":price, "type":type, "season":season,
            "supplierId":supplierId, "color":color, "gender":gender},
        method: "POST",
        success:function (){
            window.location.href = "/shoestore/admin/allModels"
        }
    });
}
function getSuppliers(){
    let url = "/shoestore/getAllSuppliers";
     return $.ajax({
         url:url,
         method:"get",
         async:false,
         contentType: 'application/json'
     });
}

function postUserInfo(name, surname, phone, email){
    let url = "/shoestore/user/edit";
    return  $.ajax({
        method: "POST",
        url: url,
        dataType: "JSON",
        data: {"name":name, "surname":surname, "phone":phone, "email":email}
    });
}
function logout(){
    return $.ajax("logout");
}

function addToProductCart(modelId, size){
    let url = "/shoestore/cart/add";
    return  $.ajax({
        url:url,
        method:"POST",
        dataType: "JSON",
        data: {"modelId":modelId, "size":size}
    });
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
    return $.ajax({
        url:url,
        method: "POST",
        dataType: "JSON"
    });
}
function getModels(currentPage, pageName){
    let url = "/shoestore/store/getModelsByFilter";
    let data = {"page":currentPage}
    if(pageName == "mainPage"){
        data = {"page":currentPage, "status":"IN_STOCK"}
    }
    return $.ajax({
        url:url,
        data:data,
        method: "GET",
        dataType: "JSON",
        contentType: 'application/json',
        mimeType: 'application/json',
    });
}

function getPagination(currentPage, pageLocation, pageName){
    let url = "/shoestore/store/getPaginationByFilter";
    let data = {"page":currentPage, "pageLocation":pageLocation};
    if(pageName == "mainPage"){
        data = {"page":currentPage,"pageLocation":pageLocation, "status":"IN_STOCK"};
    }
    return $.ajax({
        url:url,
        method: "GET",
        dataType: "text",
        data:data
    });
}

function postNewSupplier(company, city, country, address, phone, postalCode){
    var url = "/shoestore/addSupplier";
    return  $.ajax({
        url:url,
        data: {"company":company, "city":city, "country":country,
            "address":address, "phone":phone, "postalCode":postalCode},
        method:"POST"
    });
}

function updateUser(userId, role){
    var url = "/shoestore/user/changeRole";
    return $.ajax({
        url:url,
        data: {"id":userId, "role":role},
        method:"POST"
    })
}
