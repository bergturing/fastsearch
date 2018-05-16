var fastSerachCar;

$(function(){
    fastSerachCar = new FastSearchCar().init();
});

var FastSearchCar = (function(){

    /**
     * 内部方法: 添加一个汽车
     */
    var _carAdd = function(){
        var title = '添加车辆';
        var url = '/admin/car/add';
        var width = '900';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 批量删除数据
     * @private
     */
    var _carBatchDelete = function(){
        //处理的值对象
        var _valus = [];

        //所有选中的对象
        var _objs = $("#car_list tbody input:checkbox:checked");

        $.each(_objs, function (index, item) {
            _valus.push($(item).val());
        });

        //将对象字符串化
        var _data = JSON.stringify(_valus);

        //提交请求
        layer.confirm('车辆信息删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                url: '/car/batch',
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
            url: '/car/indexAll',
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
     * 内部方法: 编辑车辆信息
     */
    var _carEdit = function(){
        var title = '编辑车辆';
        var url = '/admin/car/edit?id=' + $(this).attr("car_id");
        var width = '900';
        var height;

        layer_show(title, url, width, height);
    };

    /**
     * 内部方法: 删除指定的车辆信息
     */
    var _carDelete = function(){
        var obj = this;

        layer.confirm('车辆信息删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                url: '/car/' + $(obj).attr("car_id"),
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
        $("#car_add").bind("click", _carAdd);

        //批量删除
        $("#car_batch_delete").bind("click", _carBatchDelete);

        //索引所有数据
        $("#index_all").bind("click", _indexAll);

        //编辑车辆
        $("#car_list").on("click", ".car_edit", _carEdit);

        //删除车辆
        $("#car_list").on("click", ".car_delete", _carDelete);
    };

    /**
     * 内部方法: 渲染列表数据
     */
    var _makeList = function(data){
        if(data && (data instanceof Array)){
            $("#car_count").html('共有数据：<strong>' + data.length +'</strong> 条');

            var target = $("#car_list>tbody");
            var _template = $("#car_template>tbody").html().replace(" style=\"display: none\"", "");

            var _html = "";

            $.each(data, function(index, item){
                _html += _template.replace("{{id}}", item.id)
                    .replace("{{id}}", item.id)
                    .replace("{{id}}", item.id)
                    .replace("{{title}}", item.title)
                    .replace("{{price}}", item.price)
                    .replace("{{seats}}", item.seats)
                    .replace("{{displacement}}", item.displacement)
                    .replace("{{mileage}}", item.mileage)
                    .replace("{{age}}", item.age)
                    .replace("{{gearBox}}", item.gearBoxMeaning)
                    .replace("{{color}}", item.colorMeaning)
                    .replace("{{driveType}}", item.driveTypeMeaning)
                    .replace("{{emissionStandard}}", item.emissionStandardMeaning)
                    .replace("{{style}}", item.styleMeaning)
                    .replace("{{fuelType}}", item.fuelTypeMeaning)
                    .replace("{{watchTimes}}", item.watchTimes)
                    .replace("{{address}}", item.address)
                    .replace("{{status}}", item.status)
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
        //移除所有数据
        $("#car_list tbody tr").remove();

        $.ajax({
            url: '/car',
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
    var _fastSerachCar = function () {};

    /**
     * 初始化代码
     */
    _fastSerachCar.prototype.init = function(){
        _init();
        _loadData();
    };

    return _fastSerachCar;
})();