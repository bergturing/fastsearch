/**
 *
 */
var fsCarAddressEdit;

$(function(){
    fsCarAddressEdit = new FSCarAddressEdit().init();
});

var FSCarAddressEdit = (function(){

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $level = $("#level");

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $city = $("#city");

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
     * @param level
     * @private
     */
    var _changeCity = function(city, level) {

        if(level === "city"){
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
        }else{
            city.html("");
        }
    };

    var _initController = function(){

        // 二级联动 行政级别与城市联动
        $level.change(function () {
            var selectedVal = $(this).val();debugger;
            if (typeof(selectedVal) === 'undefined' || selectedVal === "") {
                layer.msg('请选择行政级别！', {icon: 5, time: 2000});
                return;
            }

            _changeCity($city, selectedVal);
        });

        //表单验证
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-add").validate({
            rules: {
                level: {
                    required: true,
                    maxlength: 16
                },
                cnName: {
                    required: true,
                    maxlength: 16
                },
                enName: {
                    required: true,
                    maxlength: 16
                },
                baiduMapLng: {
                    required: true
                },
                baiduMapLat: {
                    required: true
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: '/address/form', // 提交地址
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
    var _fsCarAddressEdit = function(){};

    /**
     *
     * @returns {FSCarAddressEdit}
     */
    _fsCarAddressEdit.prototype.init = function(){

        _init();

        return this;
    };

    return _fsCarAddressEdit;
})();
