$(function (){
    $("#edit-info").click(function (){
        var form = document.getElementById("edit-info-form");
        form.innerHTML = "<h3>Введите новые данные</h3>" +
            "<input id=\"new-name\" type=\"text\" placeholder=\"Введите имя\">\n" +
            "<input id=\"new-surname\" type=\"text\" placeholder=\"Введите фамилию\">\n" +
            "<input id=\"new-phone\" type=\"text\" placeholder=\"Введите телефон\">\n" +
            "<input id=\"submit\" type=\"button\" value=\"Применить\">"
            $("#submit").click(function (){
                var newName = $("#new-name").val();
                var newSurname = $("#new-surname").val();
                var newPhone = $("#new-phone").val();
                var reWord = /[A-Z][a-z]+/;
                if(newName.match(reWord) && newSurname.match(reWord) && checkNumber(newPhone)){
                    form.remove();
                    const promise = editCustomerInfo(newName, newSurname, newPhone);
                    promise.then(changeCustomerInfo(newName, newSurname, newPhone));
                }else {
                    alert("Некорректные данные");
                }
                function checkNumber(AStr) {
                    AStr = AStr.replace(/[\s\-]/g, '');
                    return AStr.match(/^((\+?3)?8)?((0\(\d{2}\)?)|(\(0\d{2}\))|(0\d{2}))\d{7}$/) != null;
                }
            })
            function changeCustomerInfo(name, surname, phone){
                var customerName = document.getElementById("customer-name");
                customerName.nodeValue = name;
                var customerSurname = document.getElementById("customer-surname");
                customerSurname.nodeValue = surname;
                var customerPhone = document.getElementById("customer-phone");
                customerPhone.nodeValue = phone;
            }
    })

});