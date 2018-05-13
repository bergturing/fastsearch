var fastSerachCode;

$(function(){
    fastSerachCode = new FastSearchCode().init();
});

var FastSearchCode = (function(){

    /**
     * 内部方法: 添加一条代码记录
     */
    var _codeAdd = function(){
        var title = '添加代码';
        var url = 'code-add.html';
        var width = '800';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 编辑车辆信息
     */
    var _codeEdit = function(){
        var title = '编辑代码';
        var url = 'code-add.html?id=' + $(this).attr("code_id");
        var width = '800';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 删除指定的车辆信息
     */
    var _codeDelete = function(){
        var obj = this;

        layer.confirm('代码信息删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                url: '/system/code/' + $(obj).attr("code_id"),
                type: 'DELETE',
                dataType: 'json',
                success: function (data) {
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                }
            });
        });
    };

    /**
     * 内部方法: 绑定事件
     */
    var _bind = function(){
        //添加车辆
        $("#code_add").bind("click", _codeAdd);

        //编辑车辆
        $("#code_list").on("click", ".code_edit", _codeEdit);

        //删除车辆
        $("#code_list").on("click", ".code_delete", _codeDelete);
    };

    /**
     * 内部方法: 渲染列表数据
     */
    var _makeList = function(data){
        if(data && (data instanceof Array)){
            $("#code_count").html('共有数据：<strong>' + data.length +'</strong> 条');

            var target = $("#code_list>tbody");
            var _template = $("#code_template>tbody").html().replace(" style=\"display: none\"", "");

            var _html = "";

            $.each(data, function(index, item){
                _html += _template.replace("{{id}}", item.id)
                    .replace("{{id}}", item.id)
                    .replace("{{code}}", item.code)
                    .replace("{{description}}", item.description)
                    .replace("{{enabledFlag}}", item.enabledFlag);
            });

            target.html(_html);
        }
    };

    /**
     * 内部方法: 初始化方法
     */
    var _init = function(){
        //绑定
        _bind();
    };

    /**
     * 内部方法: 加载数据
     */
    var _loadData = function(){
        $.ajax({
            url: '/system/code',
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                if(response.code === 200){
                    _makeList(response.data);
                }else{
                    console.log(response.msg);
                }
            },
            error: function (response) {
                console.log(response.msg);
            }
        });
    };


    /**
     * 内部对象
     */
    var _fastSerachCode = function () {};

    /**
     * 初始化代码
     */
    _fastSerachCode.prototype.init = function(){
        _init();
        _loadData();
    };

    return _fastSerachCode;
})();