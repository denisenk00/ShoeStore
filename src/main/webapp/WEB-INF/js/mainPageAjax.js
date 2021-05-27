function setFilters(seasons, types, brands, colors,  sizes, genders, minPrice, maxPrice){
    var url = "setfilters?seasons=";
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
    url = url.concat("&minprice=").concat(minPrice);
    url = url.concat("&maxprice=").concat(maxPrice);
    const promise = $.ajax(url);
    return promise;
}
function getModels(currentPage){
    var url = "?page=".concat(currentPage);
    const promise = $.ajax(url);
    return promise;
}

function getPagination(currentPage){
    var url = "?page=".concat(currentPage);
    const promise = $.ajax(url);
    return promise;
}
