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

function getPagination(currentPage){
    var url = "getPaginationByFilter?page=".concat(currentPage);
    const promise = $.ajax(url);
    return promise;
}
