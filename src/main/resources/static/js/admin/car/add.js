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


    var _initController = function(){
        // 二级联动 地区 以及 地铁线路 动态变动
        $city.change(function () {
            var selectedVal = $(this).val();
            if (typeof(selectedVal) == 'undefined' || selectedVal == "") {
                layer.msg('请选择所在城市！', {icon: 5, time: 2000});
                return;
            }

            _changeRegion($region, selectedVal);
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


// $(function () {
//
//
//     changeCity($city);
//
//
//
//     // 地铁站三级联动
//     $subwayLine.change(function () {
//         var selectedVal = $(this).val();
//         if (typeof(selectedVal) == 'undefined' || selectedVal == "") {
//             layer.msg('请选择地铁线路！', {icon: 5, time: 2000});
//             return;
//         }
//
//         changeSubwayStation($subwayStation, selectedVal);
//     });
//
//     var tags = new Set();
//     $('#tags span').on('click', function () {
//         var tag = $(this).text();
//         if (tags.has(tag)) {
//             $(this).removeClass('label-success').addClass('label-default').css('border', 'none');
//             tags.delete(tag);
//         } else {
//             $(this).removeClass('label-default').addClass('label-success').css('border', 'solid black 1px');
//             tags.add(tag);
//         }
//     });
//
//
//     //表单验证
//     $('.skin-minimal input').iCheck({
//         checkboxClass: 'icheckbox-blue',
//         radioClass: 'iradio-blue',
//         increaseArea: '20%'
//     });
//
//     $("#form-car-add").validate({
//         rules: {
//             title: {
//                 required: true,
//                 maxlength: 16
//             },
//             price: {
//                 required: true
//             },
//             seats: {
//                 required: true
//             },
//             displacement: {
//                 required: true
//             },
//             mileage: {
//                 required: true
//             },
//             age: {
//                 required: true
//             },
//             gearBox: {
//                 required: true
//             },
//             color: {
//                 required: true
//             },
//             driveType: {
//                 required: true
//             },
//             emissionStandard: {
//                 required: true
//             },
//             style: {
//                 required: true
//             },
//             fuelType: {
//                 required: true
//             },
//             address: {
//                 required: true
//             }
//         },
//         onkeyup: false,
//         focusCleanup: true,
//         success: "valid",
//         submitHandler: function (form) {
//             var params = $(form).serializeArray();
//             var values = {};
//             for( x in params ){
//                 if(params.hasOwnProperty(x)){
//                     values[params[x].name] = params[x].value;
//                 }
//             }
//             var idata = JSON.stringify(values);
//
//             $.ajax({
//                 url: "/car",
//                 type: "POST",
//                 contentType: "application/json",
//                 data: idata,
//                 dataType: "json",
//                 success: function (data) {
//                     layer.msg('添加成功!', {icon: 1, time: 1000});
//                 },
//                 error: function (XmlHttpRequest, textStatus, errorThrown) {
//                     layer.msg('error!', {icon: 1, time: 1000});
//                 }
//             });
//             var index = parent.layer.getFrameIndex(window.name);
//             parent.$('.btn-refresh').click();
//             parent.layer.close(index);
//         }
//     });
// });
