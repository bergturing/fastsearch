var fastSerachCar;

$(function(){
    fastSerachCar = new FastSearchCar().init();
});

var FastSearchCar = (function(){

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $city = $("#car_city");

    /**
     *
     * @type {*|jQuery|HTMLElement}
     */
    var $region = $("#car_region");

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
     * 内部方法: 发布
     * @private
     */
    var _carUp = function(){
        var obj = this;

        $.ajax({
            url: '/car/pass/' + $(obj).attr("car_id"),
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                if(response.code === 200){
                    //重新加载
                    _loadData();
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
     * 内部方法: 下架
     * @private
     */
    var _carDown = function(){
        var obj = this;

        $.ajax({
            url: '/car/stop/' + $(obj).attr("car_id"),
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                if(response.code === 200){
                    //重新加载
                    _loadData();
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
     * 获取查询条件
     * @private
     */
    var _getCondition = function(condition){
        condition = condition || "1=1";

        //其他条件
        var cityEnName = $("#car_city").val().split("-")[1];
        if(!!cityEnName){
            condition += "&cityEnName=" + cityEnName;
        }

        var regionEnName = $("#car_region").val().split("-")[1];
        if(!!regionEnName){
            condition += "&regionEnName=" + regionEnName;
        }

        var status = $("#car_status").val();
        if(!!status){
            condition += "&status=" + status;
        }

        var title = $("#car_title").val();
        if(!!title){
            condition += "&title=" + title;
        }

        return condition;
    };

    /**
     *
     * @param city
     * @private
     */
    var _changeCity = function(city) {
        $.get('/address?level=city', function (data, status) {
            if (status !== 'success' || data.code !== 200) {
                _showError(data.message);
                return;
            }
            city.html('<option value="">所有城市</option>');
            var str = '';
            $.each(data.data, function (i, item) {
                str += "<option value=" + item.id + "-" + item.enName + ">" + item.cnName + "</option>";
            });
            city.append(str);
        });
    };

    /**
     *
     * @param region
     * @param belongTo
     * @private
     */
    var _changeRegion = function (region, belongTo) {
        $.get('/address?level=region&belongTo=' + belongTo, function (data, status) {
            if (status !== 'success' || data.code !== 200) {
                _showError(data.message);
                return;
            }
            var selectedVal = region.val();
            region.html('<option value="">所有区域</option>');

            var str = "";
            $.each(data.data, function (i, item) {
                str += "<option value=" + item.id + "-" + item.enName + ">" + item.cnName + "</option>";
            });
            region.append(str);
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

        $("#car_list")
            .on("click", ".car_up", _carUp)             //发布操作
            .on("click", ".car_down", _carDown)         //下架操作
            .on("click", ".car_edit", _carEdit)         //编辑车辆
            .on("click", ".car_delete", _carDelete);    //删除车辆

        $("#car_search").bind("click", function () {
            _loadData();
        });

        // 二级联动 地区 以及 区域联动
        $city.change(function () {
            var selectedVal = $(this).val().split("-")[0];

            _changeRegion($region, selectedVal);
        });
    };

    /**
     * 内部方法: 渲染列表数据
     */
    var _makeList = function(data){
        if(data && (data instanceof Array)){
            var target = $("#car_list>tbody");
            var _template = $("#car_template>tbody").html();

            //发布
            var operate_template_up = $("#operate_template_up").html();
            //下架
            var operate_template_down = $("#operate_template_down").html();

            var _html = "";

            $.each(data, function(index, item){

                //处理操作的模板
                var operate = "";
                switch(item.status){
                    //未审核
                    case "UNAUDITED":
                        operate = operate_template_up;
                        break;
                    //审核通过
                    case "PASSED":
                        operate = operate_template_down;
                        break;
                    default:
                        operate = operate_template_down;
                        break;
                }

                operate = operate
                    .replace("{{id}}", item.id)
                    .replace("{{id}}", item.id)
                    .replace("{{id}}", item.id);

                _html += _template.replace("{{id}}", item.id)
                    .replace("{{title}}", item.title)
                    .replace("{{price}}", item.price)
                    .replace("{{seats}}", item.seats)
                    .replace("{{displacement}}", item.displacement)
                    .replace("{{mileage}}", item.mileage)
                    .replace("{{age}}", item.age)
                    .replace("{{color}}", item.colorMeaning)
                    .replace("{{style}}", item.styleMeaning)
                    .replace("{{fuelType}}", item.fuelTypeMeaning)
                    .replace("{{watchTimes}}", item.watchTimes)
                    .replace("{{address}}", item.address)
                    .replace("{{statusMeaning}}", item.statusMeaning)
                    .replace("{{description}}", item.description)
                    .replace("{{operate}}", operate);
            });

            target.html(_html);
        }
    };

    var _makePage = function(response){
        var $page = $("#page_nl");

        // $page.html("<li>\n" +
        //     "                <a href=\"#\" aria-label=\"Previous\">\n" +
        //     "                    <span aria-hidden=\"true\">&laquo;</span>\n" +
        //     "                </a>\n" +
        //     "            </li>\n" +
        //     "            <li><a href=\"#\">1</a></li>\n" +
        //     "            <li><a href=\"#\">2</a></li>\n" +
        //     "            <li><a href=\"#\">3</a></li>\n" +
        //     "            <li><a href=\"#\">4</a></li>\n" +
        //     "            <li><a href=\"#\">5</a></li>\n" +
        //     "            <li>\n" +
        //     "                <a href=\"#\" aria-label=\"Next\">\n" +
        //     "                    <span aria-hidden=\"true\">&raquo;</span>\n" +
        //     "                </a>\n" +
        //     "            </li>");

    };

    /**
     * 内部方法: 初始化方法
     */
    var _init = function(){
        //绑定
        _bind();
        //初始化城市数据
        _changeCity($city);
    };

    /**
     * 内部方法: 加载数据
     */
    var _loadData = function(condition){
        //移除所有数据
        $("#car_list tbody tr").remove();

        $.ajax({
            url: '/car?' + _getCondition(condition),
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                if(response.code === 200){
                    $("#car_count").html('共有数据：<strong>' + response.total +'</strong> 条');
                    _makeList(response.data);
                    _makePage(response);
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