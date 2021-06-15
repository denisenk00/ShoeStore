$(function (){
   $("#add-shoe").click(function (){
       let size = $("#new-pair-size").val();
       let status = $("#new-pair-size-selector").val();
       let modelId = $("#model-id").val();
       if(size > 15 && size < 55){
          postNewPair(size, modelId, status);
       }else{
          alert("Введены некорректные данные");
       }
   })
   $("#update-price").click(function (){
       let modelId = $("#model-id").val();
       let newPrice = $("#new-price").val();
       if(newPrice > 0){
           updateModel(modelId, newPrice);
           document.getElementById("price").innerText = newPrice;
       }else{
           alert("Введены некорректные данные");
       }
   })
    $("#status-selector").change(function (){
        let status = this.value;
        let shoeId = $("shoe-id").val();
        updateShoe(shoeId, status);
    })
});