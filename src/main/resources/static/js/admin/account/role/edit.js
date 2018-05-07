$(function () {
    $(".permission-list dt input:checkbox").click(function () {
        $(this).closest("dl").find("dd input:checkbox").prop("checked", $(this).prop("checked"));
    });
    $(".permission-list2 dd input:checkbox").click(function () {
        var l = $(this).parent().parent().find("input:checked").length;
        var l2 = $(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
        if ($(this).prop("checked")) {
            $(this).closest("dl").find("dt input:checkbox").prop("checked", true);
            $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", true);
        }
        else {
            if (l == 0) {
                $(this).closest("dl").find("dt input:checkbox").prop("checked", false);
            }
            if (l2 == 0) {
                $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", false);
            }
        }
    });

    $("#form-admin-role-add").validate({
        rules: {
            roleName: {
                required: true,
            },
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            $(form).ajaxSubmit();
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
});