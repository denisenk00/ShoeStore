$(function (){
    $(".roles-selector").change(function (){
        let role = this.value;
        let userId = $(this).attr("name");
        updateUser(userId, role);
    })
})