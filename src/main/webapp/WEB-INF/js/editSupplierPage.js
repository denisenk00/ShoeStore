$(function (){
    $("#edit").click(function (){
       let id = $("#supplier-id").val();
       let company = $("#company").val();
       let city = $("#city").val();
       let country = $("#country").val();
       let address = $("#address").val();
       let phone = $("#phone").val();
       let postalCode = $("#postalCode").val();

       if(!checkPhoneNumber(phone) || !checkNameWord(city) || !checkNameWord(country)){
           alert("Введены некорректные данные!");
       }else {
            updateSupplier(id, company, city, country, address, phone, postalCode);
       }
    });
})