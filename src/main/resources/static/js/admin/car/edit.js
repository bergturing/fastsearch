/**
 *
 */
var fsCarEdit;

$(function(){
    fsCarEdit = new FSCarEdit().init();
});

var FSCarEdit = (function(){

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $city = $("#car_city");

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $region = $("#car_region");

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $brand = $("#car_brand");

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $series = $("#car_series");

    /**
     *
     * @type {string}
     */
    var tipStr = '<option value="">请选择</option>';

    /**
     * 标签
     * @type {Set}
     */
    var _tags = new Set();

    /**
     *
     * @param message
     * @private
     */
    var _showError = function(message) {
        layer.msg("Error: " + message, {icon: 5, time: 2000});
    };

    /**
     *
     * @param city
     * @private
     */
    var _changeCity = function(city) {
        $.get('/address?level=city', function (data, status) {
            if (status !== 'success' || data.code !== 200) {
                _showError(data.message);
                return;
            }

            var selectedVal = city.val();
            city.html(tipStr);

            var str = '';
            $.each(data.data, function (i, item) {
                if (item.id == selectedVal) {
                    str += "<option value=" + item.id + " selected='selected'>" + item.cnName + "</option>";
                } else {
                    str += "<option value=" + item.id + ">" + item.cnName + "</option>";
                }
            });
            city.append(str);
        });
    };

    /**
     *
     * @param region
     * @param belongTo
     * @private
     */
    var _changeRegion = function (region, belongTo) {
        $.get('/address?level=region&belongTo=' + belongTo, function (data, status) {
            if (status !== 'success' || data.code !== 200) {
                _showError(data.message);
                return;
            }

            var selectedVal = region.val();
            region.html(tipStr);

            var str = "";
            $.each(data.data, function (i, item) {
                if (item.id == selectedVal) {
                    str += "<option value=" + item.id + " selected='selected'>" + item.cnName + "</option>";
                } else {
                    str += "<option value=" + item.id + ">" + item.cnName + "</option>";
                }
            });
            region.append(str);
        });
    };

    /**
     *
     * @param brand
     * @private
     */
    var _changeBrand = function(brand) {
        $.get('/car/brand', function (data, status) {
            if (status !== 'success' || data.code !== 200) {
                _showError(data.message);
                return;
            }

            var selectedVal = brand.val();
            brand.html(tipStr);

            var str = '';
            $.each(data.data, function (i, item) {
                if (item.id == selectedVal) {
                    str += "<option value=" + item.id + " selected='selected'>" + item.name + "</option>";
                } else {
                    str += "<option value=" + item.id + ">" + item.name + "</option>";
                }
            });
            brand.append(str);
        });
    };

    /**
     *
     * @param series
     * @param brandId
     * @private
     */
    var _changeSeries = function (series, brandId) {
        $.get('/car/series?brandId=' + brandId, function (data, status) {
            if (status !== 'success' || data.code !== 200) {
                _showError(data.message);
                return;
            }
            var selectedVal = series.val();
            series.html(tipStr);

            var str = "";
            $.each(data.data, function (i, item) {
                if (item.id == selectedVal) {
                    str += "<option value=" + item.id + " selected='selected'>" + item.name + "</option>";
                } else {
                    str += "<option value=" + item.id + ">" + item.name + "</option>";
                }
            });
            series.append(str);
        });
    };


    var _initController = function(){
        // 二级联动 车辆品牌与系列联动
        $brand.change(function () {
            var selectedVal = $(this).val();
            if (typeof(selectedVal) === 'undefined' || selectedVal === "") {
                layer.msg('请选择车辆品牌！', {icon: 5, time: 2000});
                return;
            }

            _changeSeries($series, selectedVal);
        });

        // 二级联动 地区 以及 区域联动
        $city.change(function () {
            var selectedVal = $(this).val();
            if (typeof(selectedVal) === 'undefined' || selectedVal === "") {
                layer.msg('请选择所在城市！', {icon: 5, time: 2000});
                return;
            }

            _changeRegion($region, selectedVal);
        });

        /**
         * 绑定标签的点击事件
         */
        $('#tags span').on('click', function () {
            var tag = $(this).text();
            if (_tags.has(tag)) {
                $(this).removeClass('label-success').addClass('label-default').css('border', 'none');
                _tags.delete(tag);
            } else {
                $(this).removeClass('label-default').addClass('label-success').css('border', 'solid black 1px');
                _tags.add(tag);
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

                values["tags"] = _tags;

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

    };

    /**
     *
     * @private
     */
    var _init = function(){
        _initController();
    };

    /**
     *
     * @private
     */
    var _loadData = function(){
        _changeBrand($brand);
        _changeCity($city);
    };

    /**
     *
     * @private
     */
    var _fsCarEdit = function(){};

    /**
     *
     * @returns {FSCarEdit}
     */
    _fsCarEdit.prototype.init = function(){

        _init();

        _loadData();

        return this;
    };

    return _fsCarEdit;
})();
