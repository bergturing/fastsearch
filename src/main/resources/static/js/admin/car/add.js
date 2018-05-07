$(function () {
    // 自定义地址选择器
    var $city = $("#city"),
        $region = $("#region"),
        $subwayLine = $("#subwayLine"),
        $subwayStation = $("#subwayStation");

    changeCity($city);

    // 二级联动 地区 以及 地铁线路 动态变动
    $city.change(function () {
        var selectedVal = $(this).val();
        if (typeof(selectedVal) == 'undefined' || selectedVal == "") {
            layer.msg('请选择所在城市！', {icon: 5, time: 2000});
            return;
        }

        changeRegion($region, selectedVal);
        changeSubwayLine($subwayLine, selectedVal);
    });

    // 地铁站三级联动
    $subwayLine.change(function () {
        var selectedVal = $(this).val();
        if (typeof(selectedVal) == 'undefined' || selectedVal == "") {
            layer.msg('请选择地铁线路！', {icon: 5, time: 2000});
            return;
        }

        changeSubwayStation($subwayStation, selectedVal);
    });

    var tags = new Set();
    $('#tags span').on('click', function () {
        var tag = $(this).text();
        if (tags.has(tag)) {
            $(this).removeClass('label-success').addClass('label-default').css('border', 'none');
            tags.delete(tag);
        } else {
            $(this).removeClass('label-default').addClass('label-success').css('border', 'solid black 1px');
            tags.add(tag);
        }
    });


    //表单验证
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

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
                url: "/car",
                type: "POST",
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
