$(function(){

    $("#selectList li").bind("click", function(){
        $(this).parent().children("li").removeClass("hover");
        $(this).addClass("hover");

        $("#iframe").attr("src", $(this).attr("url"));
    });

});