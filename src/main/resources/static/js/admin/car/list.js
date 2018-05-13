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