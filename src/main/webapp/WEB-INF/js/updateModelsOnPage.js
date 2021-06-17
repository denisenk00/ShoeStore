function onModelsReceived(data){
    let pageName = $("#page-name").val();
    let tbody = document.getElementsByTagName("tbody").item(0);
    tbody.innerHTML = "";
    data.forEach(el => {
        var tr = tbody.insertRow();
        var brand = tr.insertCell(0);
        var name = tr.insertCell(1);
        var gender = tr.insertCell(2);
        var season = tr.insertCell(3);
        var type = tr.insertCell(4);
        var color = tr.insertCell(5);
        var price = tr.insertCell(6);
        brand.innerText = el.brand;
        if(pageName == "modelsPage"){
            name.innerHTML = "<a href=\"/shoestore/store/models/model?id=".concat(el.id).concat("\">").concat(el.name).concat("</a>");
        }else if(pageName == "adminModelsPage"){
            name.innerHTML = "<a href=\"/shoestore/admin/models/model?id=".concat(el.id).concat("\">").concat(el.name).concat("</a>");
        }
        gender.innerText = el.gender;
        season.innerText = el.season;
        type.innerText = el.type;
        color.innerText = el.color;
        price.innerText = el.price;
        tbody.appendChild(tr);
    })
}
function onPaginationReceived(pagination){
    let oldPagination = document.getElementById("pagination");
    oldPagination.innerHTML = pagination;
}