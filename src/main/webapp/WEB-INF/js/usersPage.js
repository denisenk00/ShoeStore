$(function (){
    $("#roles-selector").change(function (){
        let role = this.value;
        let userId = $("#user-id").val();
        updateUser(userId, role);
    })
})