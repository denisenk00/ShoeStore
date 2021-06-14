$(function () {
    $("#new-model").click(function () {
        let button = document.getElementById("new-model");
        button.disabled = true;
        let form = document.getElementById("edit-info-form");
        let suppliers = getSuppliers();
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
            "</select>\n";
        str = str.concat(onSuppliersReceived(suppliers));
        str = str.concat("<input id=\"submit\" type=\"button\" value=\"Применить\">");

        form.innerHTML = str;
        $("#submit").click(function () {
            let brand = $("#brand").val();
            let name = $("#name").val();
            let price = $("#price").val();
            let type = $("#type").val();
            let color = $("#color").val();
            let selectedIndex = $("#season-selector").options.selectedIndex;
            let season = $("#season-selector").options[selectedIndex].value;
            selectedIndex = $("#gender-selector").options.selectedIndex;
            let gender = $("#gender-selector").options[selectedIndex].value;
            selectedIndex = $("#supplier-selector").options.selectedIndex;
            let supplierId = $("#supplier-selector").options[selectedIndex].value;
            if(price > 0){
                form.remove();
                postModel(brand, name, price, type, color, season, gender, supplierId);
            }else{
                alert("Некорректные данные");
            }
        })
        function onSuppliersReceived(suppliers){
            let selector = "<select id=\"supplier-selector\">\n";
            suppliers.forEach(el => {
                selector = selector.concat("<option>").concat(el[1]).concat("</option>");
            })
            selector = selector.concat("</selector>")
            return selector;
        }
    })
});