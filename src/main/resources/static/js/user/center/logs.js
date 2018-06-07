var fsUserCenterLogs;

$(function(){

    fsUserCenterLogs = new FSUserCenterLogs().init();

});

var FSUserCenterLogs = (function(){
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

    var _makeList = function(data){
        if(data && (data instanceof Array)){
            var target = $("#wait-record-table>tbody");
            var _template = $("#wait-record-template>tbody").html();

            var _html = "";
            $.each(data, function(index, item){
                _html += _template
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

    };

    var _loadData = function(){
        $("#wait-record-table tbody tr").remove();

        $.ajax({
            url: '/car/subscribe/me?status=SAW',
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

    var fsUserCenterLogs = function(){};

    fsUserCenterLogs.prototype.init = function(){
        _init();
        _loadData();
    };

    return fsUserCenterLogs;

})();