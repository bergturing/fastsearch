/**
 *
 */
var fsCarAdd;

$(function(){
    fsCarAdd = new FSCarAdd().init();
});

var FSCarAdd = (function(){

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
            city.html(tipStr);
            var str = '';
            $.each(data.data, function (i, item) {
                str += "<option value=" + item.id + ">" + item.cnName + "</option>";
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
                if (item.id === selectedVal) {
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
            brand.html(tipStr);
            var str = '';
            $.each(data.data, function (i, item) {
                str += "<option value=" + item.id + ">" + item.name + "</option>";
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
                if (item.id === selectedVal) {
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
            var tag = $(this).attr("primaryKey");
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

        $("#form-add").validate({
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
                var cover = $(form).find("input:radio[name='cover']:checked").val();

                if (cover == null || typeof(cover) == "undefined" || cover == "" || cover.length < 1) {
                    layer.msg('至少要上传一个封面！', {icon: 5, time: 2000});
                    return false;
                }

                $(form).find('input.house-tag').remove();
                var index = 0;
                _tags.forEach(function (tag) {
                    $(form).append('<input class="house-tag" name="tags[' + index++ + '].id" type="hidden" value="'+ tag + '"/>');
                });

                $(form).ajaxSubmit({
                    type: 'post',
                    url: '/car/form', // 提交地址
                    success: function (data) {
                        if (data.code === 200) {
                            alert('提交成功！');
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.$('.btn-refresh').click();
                            parent.layer.close(index);
                            removeIframe();
                        } else {
                            layer.msg(data.message, {icon: 5, time: 2000});
                        }
                    },
                    error: function (request, message, e) {
                        layer.msg(request.responseText, {icon: 5, time: 2000});
                    }
                });
                return false; //此处必须返回false，阻止常规的form提交
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
    var _fsCarAdd = function(){};

    /**
     *
     * @returns {FSCarAdd}
     */
    _fsCarAdd.prototype.init = function(){

        _init();

        _loadData();

        return this;
    };

    return _fsCarAdd;
})();
