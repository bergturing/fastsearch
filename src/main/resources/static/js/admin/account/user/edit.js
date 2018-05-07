$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });

    $("#form-admin-add").validate({
        rules: {
            adminName: {
                required: true,
                minlength: 4,
                maxlength: 16
            },
            password: {
                required: true,
            },
            password2: {
                required: true,
                equalTo: "#password"
            },
            sex: {
                required: true,
            },
            phone: {
                required: true,
                isPhone: true,
            },
            email: {
                required: true,
                email: true,
            },
            adminRole: {
                required: true,
            },
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit({
                type: 'post',
                url: "xxxxxxx",
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