$(function (){
    $("#new-supplier").click(function (){
        var button = document.getElementById("new-supplier");
        button.disabled = true;
        var form = document.getElementById("new-supplier-form");
        form.innerHTML = "<label>Компания</label>\n" +
            "<input id=\"company\" type=\"text\" placeholder=\"Введите компанию\">\n" +
            "<label>Город</label>\n" +
            "<input id=\"city\" type=\"text\" placeholder=\"Введите город\">\n" +
            "<label>Страна</label>\n" +
            "<input id=\"country\" type=\"text\" placeholder=\"Введите страну\">\n" +
            "<label>Адрес</label>\n" +
            "<input id=\"address\" type=\"text\" placeholder=\"Введите адрес\">\n" +
            "<label>Телефон</label>\n" +
            "<input id=\"phone\" type=\"text\" placeholder=\"Введите телефон\">\n" +
            "<label>Почтовый индекс</label>\n" +
            "<input id=\"postal-code\" type=\"text\" placeholder=\"Введите почтовый индекс\">\n" +
            "<input id=\"submit\" type=\"button\" value=\"Применить\">";
        $("#submit").click(function (){
            var company = $("#company").val();
            var city = $("#city").val();
            var country = $("#country").val();
            var address = $("#address").val();
            var phone = $("#phone").val();
            var postalCode = $("#postal-code").val();
            if(checkNameWord(city) && checkNameWord(country) && checkPhoneNumber(phone)){
                form.remove();
                postNewSupplier(company, city, country, address, phone, postalCode);
                button.disabled = false;
            }else{
                alert("Проверьте правильность данных")
            }
        })
    })
})