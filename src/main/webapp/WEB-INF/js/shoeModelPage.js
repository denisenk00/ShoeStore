$(function (){
    $("#addToBasket").click(function (){
        let modelId = $("#model-id").val();
        let size = $("#size-selector").val();
        const promise = addToProductCart(modelId, size)
        promise.then(alert("Товар успешно добавлен в корзину"));
    })
})