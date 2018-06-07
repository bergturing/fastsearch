var fsUserCenterCollect;

$(function(){

    fsUserCenterCollect = new FSUserCenterCollect().init();

});

var FSUserCenterCollect = (function(){

    var _deleteSubscribe = function(){
        var id = $(this).attr("primaryKey");

        layer.confirm('确认要取消收藏吗', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.ajax({
                url: '/car/subscribe/' + id,
                type: 'DELETE',
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg('已取消收藏!', {icon: 6, time: 2000});
                        _loadData();
                    } else if (data.code === 403) {
                        layer.msg('请先登录,再执行操作', {icon: 5, time: 2000});
                    } else {
                        layer.msg(data.message, {icon: 5, time: 2000});
                    }
                },
                error: function (xhr, response, error) {
                    if (xhr.status === 403) {
                        layer.msg('请先登录,再执行收藏操作', {icon: 5, time: 2000});
                    } else {
                        layer.msg('Server Error: ' + response, {icon: 5, time: 3000});
                    }
                }
            });
        });
    };

    var _bind = function(){
        $("#wait-record-table")
            .on("click", ".delete_subscribe", _deleteSubscribe);             //取消收藏

        $("#submitSubscribe").bind("click", function(){
            var telephone = $("#telephone").val();
            var orderTime = $("#orderTime").val();

            if(!telephone){
                layer.msg('电话号码必须', {icon: 5, time: 2000});
                return false;
            }

            if(!orderTime){
                layer.msg('预约时间必须', {icon: 5, time: 2000});
                return false;
            }

            //处理的值对象
            var _valus = [];

            //所有选中的对象
            var _objs = $("#wait-record-table tbody input:checkbox:checked");

            $.each(_objs, function (index, item) {
                _valus.push($(item).val());
            });

            if(_valus.length<=0){
                layer.msg('请选择预约的车辆', {icon: 5, time: 2000});
                return false;
            }

            var _data = JSON.stringify({
                ids: _valus,
                telephone: telephone,
                orderTime: orderTime
            });

            $.ajax({
                url: '/car/subscribe/add',
                type: 'POST',
                contentType: "application/json",
                data: _data,
                dataType: 'json',
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg('已取消收藏!', {icon: 6, time: 2000});
                        _loadData();
                    } else if (data.code === 403) {
                        layer.msg('请先登录,再执行操作', {icon: 5, time: 2000});
                    } else {
                        layer.msg(data.message, {icon: 5, time: 2000});
                    }
                },
                error: function (xhr, response, error) {
                    if (xhr.status === 403) {
                        layer.msg('请先登录,再执行收藏操作', {icon: 5, time: 2000});
                    } else {
                        layer.msg('Server Error: ' + response, {icon: 5, time: 3000});
                    }
                }
            });
        });
    };

    var _makeList = function(data){
        if(data && (data instanceof Array)){
            var target = $("#wait-record-table>tbody");
            var _template = $("#wait-record-template>tbody").html();

            var _html = "";
            $.each(data, function(index, item){
                _html += _template
                    .replace("{{id}}", item.id)
                    .replace("{{id}}", item.id)
                    .replace("{{carId}}", item.carId)
                    .replace("{{title}}", item.title)
                    .replace("{{brand}}", item.brand)
                    .replace("{{series}}", item.series)
                    .replace("{{city}}", item.city)
                    .replace("{{region}}", item.region);
            });

            target.html(_html);
        }
    };

    var _init = function(){
        _bind();
    };

    var _loadData = function(){
        $("#wait-record-table tbody tr").remove();

        $.ajax({
            url: '/car/subscribe/me?status=ADDED',
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

    var fsUserCenterCollect = function(){};

    fsUserCenterCollect.prototype.init = function(){
        _init();
        _loadData();
    };

    return fsUserCenterCollect;

})();