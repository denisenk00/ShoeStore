$(function (){
    (function($) {
        function setChecked(target) {
            var checked = $(target).find("input[type='checkbox']:checked").length;
            if (checked) {
                $(target).find('select option:first').html('Выбрано: ' + checked);
            } else {
                $(target).find('select option:first').html('Выберите из списка');
            }
        }

        $.fn.checkselect = function() {
            this.wrapInner('<div class="checkselect-popup"></div>');
            this.prepend(
                '<div class="checkselect-control">' +
                '<select class="form-control"><option></option></select>' +
                '<div class="checkselect-over"></div>' +
                '</div>'
            );

            this.each(function(){
                setChecked(this);
            });
            this.find('input[type="checkbox"]').click(function(){
                setChecked($(this).parents('.checkselect'));
            });

            this.parent().find('.checkselect-control').on('click', function(){
                $popup = $(this).next();
                $('.checkselect-popup').not($popup).css('display', 'none');
                if ($popup.is(':hidden')) {
                    $popup.css('display', 'block');
                    $(this).find('select').focus();
                } else {
                    $popup.css('display', 'none');
                }
            });

            $('html, body').on('click', function(e){
                if ($(e.target).closest('.checkselect').length == 0){
                    $('.checkselect-popup').css('display', 'none');
                }
            });
        };
    })(jQuery);

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

        const promise = getModels(wishedSeasons, wishedTypes, wishedBrands, wishedColors,
            wishedSizes, wishedGenders, minPrice, maxPrice);
        promise.then(onModelsReceived)
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
            var seasone = tr.insertCell(3);
            var type = tr.insertCell(4);
            var color = tr.insertCell(5);
            var price = tr.insertCell(6);
            brand.innerHTML = el[2];
            name.innerHTML = el[1];
            gender.innerHTML = el[8];
            seasone.innerHTML = el[6];
            type.innerHTML = el[4];
            color.innerHTML = el[7];
            price.innerHTML = el[3];
        })
        var table = document.getElementsByTagName('table').item(0);
        table.innerHTML = "";
        table.appendChild(tbody);
    }
});


