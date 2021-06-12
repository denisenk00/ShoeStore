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