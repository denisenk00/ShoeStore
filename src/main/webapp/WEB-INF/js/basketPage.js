$(function (){
    $("#book").click(function (){
        const promise = $.ajax("order/create");
        promise.then("Заказ успешно оформлен");
    })
})