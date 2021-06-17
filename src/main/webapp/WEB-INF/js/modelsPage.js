$(function (){
    $('.checkselect').checkselect();
    $("#filter-button").click(function (){
        let pageLocation = "/shoestore/store/models";
        let wishedSeasons = [];
        let wishedBrands = [];
        let wishedTypes = [];
        let wishedSizes = [];
        let wishedColors = [];
        let wishedGenders = [];
        let minPrice = $('#min-price').val();
        let maxPrice = $('#max-price').val();
        if(Number.parseFloat(maxPrice) < Number.parseFloat(minPrice) || minPrice < 0){
            alert("Некорректно задан фильтр цены");
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
        $.when(setFilters(wishedSeasons, wishedTypes, wishedBrands, wishedColors,
            wishedSizes, wishedGenders, minPrice, maxPrice)).then(function (){
                getModels(1, "IN_STOCK").then(onModelsReceived);
                getPagination(1, pageLocation, "IN_STOCK").then(onPaginationReceived);
        })
    });
});


