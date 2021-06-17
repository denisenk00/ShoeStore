function postNewShoe(size, modelId, status){
    let url = "/shoestore/rest/shoes/add"
    return $.ajax({
        url: url,
        data:{"size":size, "modelId":modelId, "status":status},
        method: "POST",
        dataType: "JSON",
        success:function (){
            let url = "/shoestore/admin/models/model?id=".concat(modelId);
            window.location.href = url;
        }
    });
}
function updateModel(modelId, newPrice){
    let url = "/shoestore/rest/models/update";
    return  $.ajax({
        url: url,
        data:{"id":modelId, "price":newPrice},
        method: "POST",
        dataType: "JSON"
    });
}
function updateShoe(shoeId, status){
    let url = "/shoestore/rest/shoes/update";
    return $.ajax({
        url: url,
        data:{"id":shoeId, "status":status},
        method: "POST",
        dataType: "JSON"
    });
}

function postModel(brand, name, price, type, color, season, gender, supplierId){
    let url = "/shoestore/rest/models/add";
    return $.ajax({
        url: url,
        data:{"name":name, "brand":brand, "price":price, "type":type, "season":season,
            "supplierId":supplierId, "color":color, "gender":gender},
        method: "POST",
        success:function (){
            window.location.href = "/shoestore/admin/models"
        }
    });
}
function getSuppliers(){
    let url = "/shoestore/rest/suppliers/getAll";
     return $.ajax({
         url:url,
         method:"get",
         async:false,
         contentType: 'application/json'
     });
}

function postUserInfo(name, surname, phone, email){
    let url = "/shoestore/store/users/update";
    return  $.ajax({
        method: "POST",
        url: url,
        dataType: "JSON",
        data: {"name":name, "surname":surname, "phone":phone, "email":email}
    });
}


function addToProductCart(modelId, size){
    let url = "/shoestore/store/users/addToPCart";
    return  $.ajax({
        url:url,
        method:"POST",
        dataType: "JSON",
        data: {"modelId":modelId, "size":size}
    });
}

function setFilters(seasons, types, brands, colors,  sizes, genders, minPrice, maxPrice){
    let url = "/shoestore/store/users/updateFilters?seasons=";
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
    if(sizes != null) {
        sizes.forEach(urlBuilder);
    }
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
function getModels(currentPage, status){
    let url = "/shoestore/store/models/getModelsByFilter";
    let data = {"page":currentPage}
    if(status != null){
        data = {"page":currentPage, "status":status}
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

function getPagination(currentPage, pageLocation, status){
    let url = "/shoestore/store/models/getPaginationByFilter";
    let data = {"page":currentPage, "pageLocation":pageLocation};
    if(status != null){
        data = {"page":currentPage,"pageLocation":pageLocation, "status":status};
    }
    return $.ajax({
        url:url,
        method: "GET",
        dataType: "text",
        data:data
    });
}

function postNewSupplier(company, city, country, address, phone, postalCode){
    let url = "/shoestore/rest/suppliers/add";
    return  $.ajax({
        url:url,
        data: {"company":company, "city":city, "country":country,
            "address":address, "phone":phone, "postalCode":postalCode},
        method:"POST"
    });
}

function updateUser(userId, role){
    let url = "/shoestore/admin/users/changeRole";
    return $.ajax({
        url:url,
        data: {"id":userId, "role":role},
        method:"POST",
    })
}

function updateSupplier(id, company, city, country, address, phone, postalCode){
    let url = "/shoestore/rest/suppliers/update";
    return $.ajax({
        url:url,
        data: {"id":id, "company":company, "city":city, "country":country, "address":address, "phone":phone, "postalCode":postalCode},
        method:"POST",
        success: function (){
            window.location.href = "/shoestore/admin/suppliers"
        }
    })
}

