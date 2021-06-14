$(function (){
    $('.checkselect').checkselect();
    $("#filterbutton").click(function (){
        let wishedSeasons = [];
        let wishedBrands = [];
        let wishedTypes = [];
        let wishedSizes = [];
        let wishedColors = [];
        let wishedGenders = [];
        let minPrice = $('#minprice').val();
        let maxPrice = $('#maxprice').val();
        let pageLocation = $('#page-location').val();

        if(pageLocation == "mainPage") pageLocation = "/";
        if(pageLocation == "modelsPage") pageLocation = "admin/allModels";
        if(maxPrice < minPrice || minPrice < 0){
            alert("Некоректні дані");
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

        const promise = setFilters(wishedSeasons, wishedTypes, wishedBrands, wishedColors,
            wishedSizes, wishedGenders, minPrice, maxPrice);
        promise.then(getModels(1).then(onModelsReceived)).then(getPagination(1, pageLocation).then(onPaginationReceived));
    });

    function onModelsReceived(models){
        alert(models);
        let thead = document.getElementById("models");
        let tbody = document.createElement("tbody");
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
            brand.innerText = el[2];
            name.innerText = el[1];
            gender.innerText = el[7];
            season.innerText = el[5];
            type.innerText = el[4];
            color.innerText = el[6];
            price.innerText = el[3];
        })
        let table = document.getElementsByTagName('table').item(0);
        table.appendChild(tbody);
    }
    function onPaginationReceived(pagination){
        alert(pagination)
        let oldPagination = document.getElementById("pagination");
        oldPagination.remove();
        let newPagination = document.createElement("div");
        newPagination.setAttribute("id", "pagination");
        let blockBody = document.getElementsByTagName("body").item(0);
        newPagination.innerHTML = pagination;
        blockBody.appendChild(newPagination);
    }
});


