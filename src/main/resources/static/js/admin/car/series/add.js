/**
 *
 */
var fsCarSeriesAdd;

$(function(){
    fsCarSeriesAdd = new FSCarSeriesAdd().init();
});

var FSCarSeriesAdd = (function(){

    var _initController = function(){
        //表单验证
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-add").validate({
            rules: {
                brandId: {
                    required: true
                },
                code: {
                    required: true,
                    maxlength: 16
                },
                name: {
                    required: true,
                    maxlength: 16
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: '/car/series/form', // 提交地址
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
    var _fsCarSeriesAdd = function(){};

    /**
     *
     * @returns {FSCarSeriesAdd}
     */
    _fsCarSeriesAdd.prototype.init = function(){

        _init();

        return this;
    };

    return _fsCarSeriesAdd;
})();
