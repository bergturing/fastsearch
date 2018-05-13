var fastSerachRole;

$(function(){
    fastSerachRole = new FastSearchRole().init();
});

var FastSearchRole = (function(){

    /**
     * 内部方法: 添加一个角色
     */
    var _roleAdd = function(){
        var title = '添加角色';
        var url = 'role-add.html';
        var width = '800';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 编辑角色信息
     */
    var _roleEdit = function(id){
        var title = '编辑角色';
        var url = 'role-add.html';
        var width = '800';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 删除指定的角色
     */
    var _roleDelete = function(obj, id){
        layer.confirm('角色删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '',
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
        //添加角色
        $("#role_add").bind("click", _roleAdd);
    };

    /**
     * 内部方法: 渲染列表数据
     */
    var _makeList = function(data){
        if(data && (data instanceof Array)){
            $("#role_count").html('共有数据：<strong>' + data.length +'</strong> 条');

            var target = $("#role_list>tbody");
            var _template = $("#role_template>tbody").html().replace(" style=\"display: none\"", "");

            var _html = "";

            $.each(data, function(index, item){
                _html += _template.replace("{{id}}", item.id)
                    .replace("{{code}}", item.code)
                    .replace("{{name}}", item.name)
                    .replace("{{description}}", item.description);
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
            url: '/account/role',
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
    var _fastSerachRole = function () {};

    /**
     * 初始化代码
     */
    _fastSerachRole.prototype.init = function(){
        _init();
        _loadData();
    };

    return _fastSerachRole;
})();