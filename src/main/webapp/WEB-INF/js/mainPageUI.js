$(function (){
    $('.checkselect').checkselect();
    $("#filterbutton").click(function (){
        var wishedSeasons = [];
        var wishedBrands = [];
        var wishedTypes = [];
        var wishedSizes = [];
        var wishedColors = [];
        var wishedGenders = [];
        var minPrice = $('#minprice').val();
        var maxPrice = $('#maxprice').val();
        var pageLocation = $('#page-location').val();

        if(pageLocation == "mainPage") pageLocation = "store/";
        if(pageLocation == "modelsPage") pageLocation = "admin/allModels";
        if(maxPrice < minPrice || minPrice < 0){
            alert("Некоректні дані");
            return;
        }
        $('.season:checked').each(function (){
            var val = $(this).val();
            wishedSeasons.push(val);
        })
        $('.brand:checked').each(function (){
            var val = $(this).val();
            wishedBrands.push(val);
        })
        $('.type:checked').each(function (){
            var val = $(this).val();
            wishedTypes.push(val);
        })
        $('.size:checked').each(function (){
            var val = $(this).val();
            wishedSizes.push(val);
        })
        $('.color:checked').each(function (){
            var val = $(this).val();
            wishedColors.push(val);
        })
        $('.gender:checked').each(function (){
            var val = $(this).val();
            wishedGenders.push(val);
        })
        if(wishedSeasons.length == 0){
            $('.season').each(function (){
                var val = $(this).val();
                wishedSeasons.push(val);
            })
        }
        if(wishedBrands.length == 0){
            $('.brand').each(function (){
                var val = $(this).val();
                wishedBrands.push(val);
            })
        }
        if(wishedColors.length == 0){
            $('.color').each(function (){
                var val = $(this).val();
                wishedColors.push(val);
            })
        }
        if(wishedGenders.length == 0){
            $('.gender').each(function (){
                var val = $(this).val();
                wishedGenders.push(val);
            })
        }
        if(wishedTypes.length == 0){
            $('.type').each(function (){
                var val = $(this).val();
                wishedTypes.push(val);
            })
        }
        if(wishedSizes.length == 0){
            $('.size').each(function (){
                var val = $(this).val();
                wishedSizes.push(val);
            })
        }

        const promise = setFilters(wishedSeasons, wishedTypes, wishedBrands, wishedColors,
            wishedSizes, wishedGenders, minPrice, maxPrice);
        promise.then(getModels(1).then(onModelsReceived)).then(getPagination(1, pageLocation).then(onPaginationReceived));
    });

    function onModelsReceived(models){
        var thead = document.getElementById("models");
        thead.remove();
        var tbody = document.createElement("tbody");
        tbody.setAttribute("id", "models");
        models.forEach(el => {
            var tr = tbody.insertRow();
            var brand = tr.insertCell(0);
            var name = tr.insertCell(1);
            var gender = tr.insertCell(2);
            var season = tr.insertCell(3);
            var type = tr.insertCell(4);
            var color = tr.insertCell(5);
            var price = tr.insertCell(6);
            brand.innerHTML = el[2];
            name.innerHTML = el[1];
            gender.innerHTML = el[7];
            season.innerHTML = el[5];
            type.innerHTML = el[4];
            color.innerHTML = el[6];
            price.innerHTML = el[3];
        })
        var table = document.getElementsByTagName('table').item(0);
        table.appendChild(tbody);
    }
    function onPaginationReceived(pagination){
        var oldPagination = document.getElementById("pagination");
        oldPagination.remove();
        var newPagination = document.createElement("div");
        newPagination.setAttribute("id", "pagination");
        var blockBody = document.getElementsByTagName("body").item(0);
        newPagination.innerHTML = pagination;
        blockBody.appendChild(newPagination);
    }
});


