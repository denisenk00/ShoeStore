$(function (){
    $("#addToBasket").onclick(function (){
        const modelId = $("#model-id").val();
        const selectedIndex = $("#size-selector").options.selectedIndex;
        const size = $("#size-selector").options[selectedIndex].value;
        const promise = addToBasket(modelId, size);
        promise.then(alert("Товар успешно добавлен в корзину"));
    })
    function addToBasket(modelId, size){
        let url = "cart/add?modelId=";
        url = url.concat(modelId);
        url = url.concat("&size=").concat(size);
        const promise = $.ajax(url);
        return promise;
    }
})