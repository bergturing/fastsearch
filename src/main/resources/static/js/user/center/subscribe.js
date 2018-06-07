var fsUserCenterSubscribe;

$(function(){

    fsUserCenterSubscribe = new FSUserCenterSubscribe().init();

});

var FSUserCenterSubscribe = (function(){

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

    var _deleteSubscribe = function(){
        var id = $(this).attr("primaryKey");

        layer.confirm('确认要取消预约吗', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.ajax({
                url: '/car/subscribe/del?id=' + id,
                type: 'GET',
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg('已取消预约!', {icon: 6, time: 2000});
                        _loadData();
                    } else if (data.code === 403) {
                        layer.msg('请先登录,再执行操作', {icon: 5, time: 2000});
                    } else {
                        layer.msg(data.message, {icon: 5, time: 2000});
                    }
                },
                error: function (xhr, response, error) {
                    if (xhr.status === 403) {
                        layer.msg('请先登录,再执行预约操作', {icon: 5, time: 2000});
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
    };

    var _makeList = function(data){
        if(data && (data instanceof Array)){
            var target = $("#wait-record-table>tbody");
            var _template = $("#wait-record-template>tbody").html();

            var _html = "";
            $.each(data, function(index, item){
                _html += _template
                    .replace("{{id}}", item.id)
                    .replace("{{carId}}", item.carId)
                    .replace("{{title}}", item.title)
                    .replace("{{city}}", item.city)
                    .replace("{{region}}", item.region)
                    .replace("{{phoneNumber}}", item.phoneNumber)
                    .replace("{{orderTime}}", new Date(item.orderTime).format("yyyy-MM-dd"));
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
            url: '/car/subscribe/me?status=SUBSCRIBED',
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

    var fsUserCenterSubscribe = function(){};

    fsUserCenterSubscribe.prototype.init = function(){
        _init();
        _loadData();
    };

    return fsUserCenterSubscribe;

})();