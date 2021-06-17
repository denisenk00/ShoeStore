$(function () {
    $(".checkselect").checkselect();
    $("#filter-button").click(function (){
        let pageLocation = "/shoestore/admin/models";
        let wishedSeasons = [];
        let wishedBrands = [];
        let wishedTypes = [];
        let wishedColors = [];
        let wishedGenders = [];
        let minPrice = $('#min-price').val();
        let maxPrice = $('#max-price').val();

        if(Number.parseFloat(maxPrice) < Number.parseFloat(minPrice) || minPrice < 0){
            alert("Некорректно задан фильтр цены");
            return;
        }
        $(".season:checked").each(function (){
            let val = $(this).val();
            wishedSeasons.push(val);
        })
        $(".brand:checked").each(function (){
            let val = $(this).val();
            wishedBrands.push(val);
        })
        $(".type:checked").each(function (){
            let val = $(this).val();
            wishedTypes.push(val);
        })
        $(".color:checked").each(function (){
            let val = $(this).val();
            wishedColors.push(val);
        })
        $(".gender:checked").each(function (){
            let val = $(this).val();
            wishedGenders.push(val);
        })

        $.when(setFilters(wishedSeasons, wishedTypes, wishedBrands, wishedColors, null, wishedGenders, minPrice, maxPrice)).then(function (){
            getModels(1, null).then(onModelsReceived);
            getPagination(1, pageLocation, null).then(onPaginationReceived);
        })
    });


    $("#new-model").click(function () {
        let button = document.getElementById("new-model");
        button.disabled = true;
        let form = document.getElementById("new-model-form");
        let str = "<h3>Введите данные</h3>" +
            "<label>Бренд:</label>\n" +
            "<input id=\"brand\" type=\"text\" placeholder=\"Введите бренд\"><br><br>\n" +
            "<label>Название:</label>\n" +
            "<input id=\"name\" type=\"text\" placeholder=\"Введите название\"><br><br>\n" +
            "<label>Цена:</label>\n" +
            "<input id=\"price\" type=\"number\" placeholder=\"Введите цену\"><br><br>\n" +
            "<label>Тип:</label>\n" +
            "<input id=\"type\" type=\"text\" placeholder=\"Введите тип\"><br><br>\n" +
            "<label>Цвет:</label>\n" +
            "<input id=\"color\" type=\"text\" placeholder=\"Введите цвет\"><br><br>\n" +
            "<label>Сезон:</label>\n" +
            "<select id='season-selector'>\n" +
            "<option>Лето</option>\n" +
            "<option>Весна/Осень</option>\n" +
            "<option>Зима</option>\n" +
            "</select><br><br>\n" +
            "<label>Стать:</label>\n" +
            "<select id='gender-selector'>\n" +
            "<option>Мужская</option>\n" +
            "<option>Женская</option>\n" +
            "<option>Унисекс</option>\n" +
            "</select><br><br>\n" +
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
            if(price >= 0){
                form.innerHTML = "";
                postModel(brand, name, price, type, color, season, gender, supplierId);
                button.disabled = false;
            }else{
                alert("Цена не может быть меньше нуля!");
            }
        })
        function onSuppliersReceived(suppliers){
            let selector = "<select id=\"supplier-selector\">\n";
            suppliers.forEach(el => {
                selector = selector.concat("<option>").concat(el.id).concat("</option>\n");
            })
            selector = selector.concat("</selector><br><br>\n")
            return selector;
        }
    })
});