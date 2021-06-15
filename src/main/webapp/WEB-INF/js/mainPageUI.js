$(function (){
    $('.checkselect').checkselect();
    let pageName = $('#page-name').val();
    $("#filterbutton").click(function (){
        let pageLocation;
        let wishedSeasons = [];
        let wishedBrands = [];
        let wishedTypes = [];
        let wishedSizes = [];
        let wishedColors = [];
        let wishedGenders = [];
        let minPrice = $('#minprice').val();
        let maxPrice = $('#maxprice').val();
        if(pageName == "mainPage") pageLocation = "/";
        if(pageName == "modelsPage") pageLocation = "admin/allModels";
        if(maxPrice < minPrice || minPrice < 0){
            alert("Некорректные данные");
            return;
        }
        $('.season:checked').each(function (){
            let val = $(this).val();
            wishedSeasons.push(val);
        })
        $('.brand:checked').each(function (){
            let val = $(this).val();
            wishedBrands.push(val);
        })
        $('.type:checked').each(function (){
            let val = $(this).val();
            wishedTypes.push(val);
        })
        $('.size:checked').each(function (){
            let val = $(this).val();
            wishedSizes.push(val);
        })
        $('.color:checked').each(function (){
            let val = $(this).val();
            wishedColors.push(val);
        })
        $('.gender:checked').each(function (){
            let val = $(this).val();
            wishedGenders.push(val);
        })
        if(wishedSeasons.length == 0){
            $('.season').each(function (){
                let val = $(this).val();
                wishedSeasons.push(val);
            })
        }
        if(wishedBrands.length == 0){
            $('.brand').each(function (){
                let val = $(this).val();
                wishedBrands.push(val);
            })
        }
        if(wishedColors.length == 0){
            $('.color').each(function (){
                let val = $(this).val();
                wishedColors.push(val);
            })
        }
        if(wishedGenders.length == 0){
            $('.gender').each(function (){
                let val = $(this).val();
                wishedGenders.push(val);
            })
        }
        if(wishedTypes.length == 0){
            $('.type').each(function (){
                let val = $(this).val();
                wishedTypes.push(val);
            })
        }
        if(wishedSizes.length == 0){
            $('.size').each(function (){
                let val = $(this).val();
                wishedSizes.push(val);
            })
        }
        $.when(setFilters(wishedSeasons, wishedTypes, wishedBrands, wishedColors,
            wishedSizes, wishedGenders, minPrice, maxPrice)).then(function (){
                getModels(1, pageName).then(onModelsReceived);
                getPagination(1, pageLocation, pageName).then(onPaginationReceived);
        })
    });

    function onModelsReceived(data){
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
            if(pageName == "mainPage"){
                name.innerHTML = "<a href=\"/shoestore/model?id=".concat(el.id).concat("\">").concat(el.name).concat("</a>");
            }else if(pageName == "modelsPage"){
                name.innerHTML = "<a href=\"/shoestore/admin/model?id=".concat(el.id).concat("\">").concat(el.name).concat("</a>");
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
        oldPagination.remove();
        let newPagination = document.createElement("div");
        newPagination.setAttribute("id", "pagination");
        let blockBody = document.getElementsByTagName("body").item(0);
        newPagination.innerHTML = pagination;
        blockBody.appendChild(newPagination);
    }
});


