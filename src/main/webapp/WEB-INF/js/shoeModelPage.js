$(function (){
    $("#addToBasket").click(function (){
        const modelId = $("#model-id").val();
        const selectedIndex = $("#size-selector").options.selectedIndex;
        const size = $("#size-selector").options[selectedIndex].value;
        const promise = addToBasket(modelId, size);
        promise.then(alert("Товар успешно добавлен в корзину"));
    })
})