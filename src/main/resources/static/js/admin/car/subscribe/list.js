var fastSerachCarSubscribe;

$(function(){
    fastSerachCarSubscribe = new FastSearchCarSubscribe().init();
});

var FastSearchCarSubscribe = (function(){

    Date.prototype.format = function(format) {
        var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
        };
        if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
        }
        for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1
                    ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
        }
        return format;
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
        layer.confirm('预约信息删除须谨慎，确认要删除吗？', function (index) {
            $.ajax({
                url: '/car/subscribe/batch',
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
            url: '/car/subscribe/indexAll',
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
     * 内部方法: 完成看车
     */
    var _finishSaw = function(){
        var id = $(this).attr("primaryKey");

        layer.confirm('确定完成看车?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.ajax({
                url: '/car/subscribe/finishSaw?id=' + id,
                type: 'POST',
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg('完成看车!', {icon: 6, time: 2000});
                        _loadData();
                    }
                },
                error: function (xhr, response, error) {
                }
            });
        });
    };

    /**
     * 内部方法: 完成交易
     */
    var _finishTrade = function(){
        var id = $(this).attr("primaryKey");

        layer.confirm('确认完成交易?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.ajax({
                url: '/car/subscribe/finishTrade?id=' + id,
                type: 'POST',
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg('完成交易!', {icon: 6, time: 2000});
                        _loadData();
                    }
                },
                error: function (xhr, response, error) {
                }
            });
        });
    };

    /**
     * 内部方法: 绑定事件
     */
    var _bind = function(){
        //批量删除
        $("#batch_delete").bind("click", _batchDelete);

        //索引所有数据
        $("#index_all").bind("click", _indexAll);


        $("#list")
        //编辑信息
            .on("click", ".finish_saw", _finishSaw)
            //删除信息
            .on("click", ".finish_trade", _finishTrade);

        $("#subscribe_search").bind("click", function () {
            _loadData();
        });
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
                _html += _template.replace("{{id}}", item.id || "")
                    .replace("{{id}}", item.id || "")
                    .replace("{{id}}", item.id || "")
                    .replace("{{title}}", item.title || "")
                    .replace("{{userName}}", item.userName || "")
                    .replace("{{orderTime}}", new Date(item.orderTime).format("yyyy-MM-dd") || "")
                    .replace("{{phoneNumber}}", item.phoneNumber || "")
                    .replace("{{description}}", item.description || "")
                    .replace("{{status}}", item.statusMeaning || "")
                    .replace("{{createTime}}", new Date(item.createTime).format("yyyy-MM-dd") || "")
                    .replace("{{lastUpdateTime}}", new Date(item.lastUpdateTime).format("yyyy-MM-dd") || "");
            });

            target.html(_html);
        }
    };

    /**
     * 获取查询条件
     * @private
     */
    var _getCondition = function(condition){
        condition = condition || "1=1";

        //其他条件
        var status = $("#subscribe_status").val();
        if(!!status){
            condition += "&status=" + status;
        }

        return condition;
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
    var _loadData = function(condition){
        //移除所有数据
        $("#list tbody tr").remove();

        $.ajax({
            url: '/car/subscribe?' + _getCondition(condition),
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
    var _fastSerachCarSubscribe = function () {};

    /**
     * 初始化代码
     */
    _fastSerachCarSubscribe.prototype.init = function(){
        _init();
        _loadData();
    };

    return _fastSerachCarSubscribe;
})();