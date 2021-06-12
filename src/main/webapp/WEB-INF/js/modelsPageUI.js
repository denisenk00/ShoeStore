$(function () {
    $("#new-model").click(function () {
        var button = document.getElementById("new-model");
        button.disabled = true;
        var form = document.getElementById("edit-info-form");
        var suppliers = getSuppliers();
        var str = "<h3>Введите данные</h3>" +
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
            "</select>\n";
        str = str.concat(onSuppliersReceived(suppliers));
        str = str.concat("<input id=\"submit\" type=\"button\" value=\"Применить\">");

        form.innerHTML = str;
        $("#submit").click(function () {
            var brand = $("#brand").val();
            var name = $("#name").val();
            var price = $("#price").val();
            var type = $("#type").val();
            var color = $("#color").val();
            var selectedIndex = $("#season-selector").options.selectedIndex;
            var season = $("#season-selector").options[selectedIndex].value;
            selectedIndex = $("#gender-selector").options.selectedIndex;
            var gender = $("#gender-selector").options[selectedIndex].value;
            selectedIndex = $("#supplier-selector").options.selectedIndex;
            var supplierId = $("#supplier-selector").options[selectedIndex].value;
            if(price > 0){
                form.remove();
                postModel(brand, name, price, type, color, season, gender, supplierId);
            }else{
                alert("Некорректные данные");
            }
        })
        function onSuppliersReceived(suppliers){
            var selector = "<select id=\"supplier-selector\">\n";
            suppliers.forEach(el => {
                selector = selector.concat("<option>").concat(el[8]).concat("</option>");
            })
            selector = selector.concat("</selector>")
            return selector;
        }
    })
});