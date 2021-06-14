$(function (){
   $("#add-shoe").click(function (){
       let size = $("#new-pair-size").val();
       let selectedIndex = $("#new-pair-size-selector").options.selectedIndex;
       let status = $("#new-pair-size-selector").options[selectedIndex].value;
       let modelId = $("#model-id").val();
       if(size > 15 && size < 55){
          postNewPair(size, modelId, status);
       }else{
          alert("Введены некорректные данные");
       }
   })
   $("#update-price").click(function (){
       let newPrice = $("#new-price").val();
       if(newPrice > 0){
           updateModel(newPrice);
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