$(function (){
   $("#add-shoe").click(function (){
       var size = $("#new-pair-size").val();
       var selectedIndex = $("#new-pair-size-selector").options.selectedIndex;
       var status = $("#new-pair-size-selector").options[selectedIndex].value;
       var modelId = $("#model-id").val();
       if(size > 15 && size < 55){
          postNewPair(size, modelId, status);
       }else{
          alert("Введены некорректные данные");
       }
   })
   $("#update-price").click(function (){
       var newPrice = $("#new-price").val();
       if(newPrice > 0){
           updateModel(newPrice);
       }else{
           alert("Введены некорректные данные");
       }
   })
    $("#status-selector").change(function (){
        var status = this.value;
        var shoeId = $("shoe-id").val();
        updateShoe(shoeId, status);
    })
});