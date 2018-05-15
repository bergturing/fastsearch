$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    var loadData = function(){

    };

    $("#form-car-add").validate({
        rules: {
            title: {
                required: true,
                maxlength: 16
            },
            price: {
                required: true
            },
            seats: {
                required: true
            },
            displacement: {
                required: true
            },
            mileage: {
                required: true
            },
            age: {
                required: true
            },
            gearBox: {
                required: true
            },
            color: {
                required: true
            },
            driveType: {
                required: true
            },
            emissionStandard: {
                required: true
            },
            style: {
                required: true
            },
            fuelType: {
                required: true
            },
            address: {
                required: true
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var params = $(form).serializeArray();
            var values = {};
            for( x in params ){
                if(params.hasOwnProperty(x)){
                    values[params[x].name] = params[x].value;
                }
            }
            var idata = JSON.stringify(values);

            $.ajax({
                url: "/car/" + values.id,
                type: "PUT",
                contentType: "application/json",
                data: idata,
                dataType: "json",
                success: function (data) {
                    layer.msg('添加成功!', {icon: 1, time: 1000});
                },
                error: function (XmlHttpRequest, textStatus, errorThrown) {
                    layer.msg('error!', {icon: 1, time: 1000});
                }
            });
            var index = parent.layer.getFrameIndex(window.name);
            parent.$('.btn-refresh').click();
            parent.layer.close(index);
        }
    });
});