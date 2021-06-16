$(function (){
    $("#new-supplier").click(function (){
        let button = document.getElementById("new-supplier");
        button.disabled = true;
        let form = document.getElementById("new-supplier-form");
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
            let company = $("#company").val();
            let city = $("#city").val();
            let country = $("#country").val();
            let address = $("#address").val();
            let phone = $("#phone").val();
            let postalCode = $("#postal-code").val();
            if(checkNameWord(city) && checkNameWord(country) && checkPhoneNumber(phone)){
                form.remove();
                const promise = postNewSupplier(company, city, country, address, phone, postalCode);
                promise.then(addSupplierToList(company, city, country, address, phone, postalCode));
                button.disabled = false;
            }else{
                alert("Проверьте правильность данных")
            }
        })
    })
    function  addSupplierToList(company, city, country, address, phone, postalCode){
        let tbody = document.getElementsByTagName("tbody").item(0);
        let tr = tbody.insertRow();
        tr.insertCell(0).innerHTML = company;
        tr.insertCell(1).innerHTML = city;
        tr.insertCell(2).innerHTML = country;
        tr.insertCell(3).innerHTML = address;
        tr.insertCell(4).innerHTML = phone;
        tr.insertCell(5).innerHTML = postalCode;
    }
})