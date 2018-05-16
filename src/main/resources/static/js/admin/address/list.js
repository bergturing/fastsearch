var fastSerachAddress;

$(function(){
    fastSerachAddress = new FastSearchAddress().init();
});

var FastSearchAddress = (function(){

    /**
     * 内部方法: 添加一个汽车
     */
    var _add = function(){
        var title = '添加地点';
        var url = '/admin/address/add';
        var width = '900';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 批量删除数据
     * @private
     */
    var _batchDelete = function(){
        //处理的值对象
        var _valus = [];

        //所有选中的对象
        var _objs = $("#list tbody input:checkbox:checked");

        $.each(_objs, function (index, item) {
            _valus.push($(item).val());
        });

        //将对象字符串化
        var _data = JSON.stringify(_valus);

        //提交请求
        layer.confirm('地点信息删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                url: '/address/batch',
                type: 'POST',
                contentType: "application/json",
                data: _data,
                dataType: 'json',
                success: function (data) {
                    $(_objs).parents("tr").remove();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (data) {
                    console.log(data.msg);
                }
            });
        });
    };

    /**
     * 内部方法: 索引所有数据
     * @private
     */
    var _indexAll = function(){
        $.ajax({
            url: '/address/indexAll',
            type: 'GET',
            dataType: 'json',
            success: function () {
                layer.msg('索引成功!', {icon: 1, time: 1000}, function () {
                    //重新加载
                    _loadData();
                });
            },
            error: function () {
                layer.msg('未知错误!', {icon: 2, time: 1000});
            }
        });
    };

    /**
     * 内部方法: 编辑指定的信息
     */
    var _edit = function(){
        var title = '编辑地点';
        var url = '/admin/address/edit?id=' + $(this).attr("primaryKey");
        var width = '900';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 删除指定的信息
     */
    var _delete = function(){
        var obj = this;

        layer.confirm('地点信息删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                url: '/address/' + $(obj).attr("primaryKey"),
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
        //添加信息
        $("#add").bind("click", _add);

        //批量删除
        $("#batch_delete").bind("click", _batchDelete);

        //索引所有数据
        $("#index_all").bind("click", _indexAll);


        $("#list")
            //编辑信息
            .on("click", ".edit", _edit)
            //删除信息
            .on("click", ".delete", _delete);
    };

    /**
     * 内部方法: 渲染列表数据
     */
    var _makeList = function(data){
        if(data && (data instanceof Array)){
            $("#count").html('共有数据：<strong>' + data.length +'</strong> 条');

            var target = $("#list>tbody");
            var _template = $("#template>tbody").html().replace(" style=\"display: none\"", "");

            var _html = "";

            $.each(data, function(index, item){
                _html += _template.replace("{{id}}", item.id)
                    .replace("{{id}}", item.id)
                    .replace("{{id}}", item.id)
                    .replace("{{enName}}", item.enName)
                    .replace("{{cnName}}", item.cnName)
                    .replace("{{level}}", item.level)
                    .replace("{{belongToCnName}}", item.belongToCnName)
                    .replace("{{baiduMapLng}}", item.belongTo)
                    .replace("{{baiduMapLat}}", item.belongTo);
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
        //移除所有数据
        $("#list tbody tr").remove();

        $.ajax({
            url: '/address',
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
    var _fastSerachAddress = function () {};

    /**
     * 初始化代码
     */
    _fastSerachAddress.prototype.init = function(){
        _init();
        _loadData();
    };

    return _fastSerachAddress;
})();