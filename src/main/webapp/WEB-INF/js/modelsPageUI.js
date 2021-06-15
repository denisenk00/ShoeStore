$(function () {
    $("#new-model").click(function () {
        let button = document.getElementById("new-model");
        button.disabled = true;
        let form = document.getElementById("new-model-form");
        let str = "<h3>Введите данные</h3>" +
            "<label>Бренд</label>\n" +
            "<input id=\"brand\" type=\"text\" placeholder=\"Введите бренд\">\n" +
            "<label>Название</label>\n" +
            "<input id=\"name\" type=\"text\" placeholder=\"Введите название\">\n" +
            "<label>Цена</label>\n" +
            "<input id=\"price\" type=\"number\" placeholder=\"Введите цену\">\n" +
            "<label>Тип</label>\n" +
            "<input id=\"type\" type=\"text\" placeholder=\"Введите тип\">\n" +
            "<label>Цвет</label>\n" +
            "<input id=\"color\" type=\"text\" placeholder=\"Введите цвет\">\n" +
            "<label>Сезон</label>\n" +
            "<select id='season-selector'>\n" +
            "<option>Лето</option>\n" +
            "<option>Весна/Осень</option>\n" +
            "<option>Зима</option>\n" +
            "</select>\n" +
            "<label>Стать</label>\n" +
            "<select id='gender-selector'>\n" +
            "<option>Мужская</option>\n" +
            "<option>Женская</option>\n" +
            "<option>Унисекс</option>\n" +
            "</select>\n" +
            "<label>Id Поставщика</label>";
        $.when(getSuppliers()).then(function (data){
            let s = onSuppliersReceived(data);
            str = str.concat(s);
        })
        str = str.concat("<form action=\"/shoestore/store/removeFilters\" method=\"get\" >\n" +
            "                    <button id=\"admin-panel\">Перейти в админ-панель</button>\n" +
            "                    <input name=\"goTo\" type=\"hidden\" value=\"adminPanel\">\n" +
            "                </form><input id=\"submit\" type=\"button\" value=\"Применить\">");
        form.innerHTML = str;
        $("#submit").click(function () {
            let brand = $("#brand").val();
            let name = $("#name").val();
            let price = $("#price").val();
            let type = $("#type").val();
            let color = $("#color").val();
            let season = $("#season-selector").val();
            let gender = $("#gender-selector").val();
            let supplierId = $("#supplier-selector").val();
            if(price > 0){
                form.innerHTML = "";
                postModel(brand, name, price, type, color, season, gender, supplierId);
            }else{
                alert("Некорректные данные");
            }
        })
        function onSuppliersReceived(suppliers){
            let selector = "<select id=\"supplier-selector\">\n";
            suppliers.forEach(el => {
                selector = selector.concat("<option>").concat(el.id).concat("</option>\n");
            })
            selector = selector.concat("</selector>\n")
            return selector;
        }
    })
});