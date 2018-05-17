/**
 *
 */
var fsCarAddressAdd;

$(function(){
    fsCarAddressAdd = new FSCarAddressAdd().init();
});

var FSCarAddressAdd = (function(){

    var _initController = function(){
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
    var _fsCarAddressAdd = function(){};

    /**
     *
     * @returns {FSCarAddressAdd}
     */
    _fsCarAddressAdd.prototype.init = function(){

        _init();

        return this;
    };

    return _fsCarAddressAdd;
})();
