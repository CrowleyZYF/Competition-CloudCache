/**
 * Created by 奕峰 on 2014/11/29.
 */
//向后台发送相关消息
function cacheAjaxWithData(type,url,dataType,data,successFunc,errorFunc){
    $.ajax({
        type:type,
        url:url,
        data:data,
        dataType:dataType,
        success:successFunc,
        error:errorFunc
    });
}

//向后台发送 无相关消息
function cacheAjaxWithOutData(type,url,dataType,successFunc,errorFunc){
    $.ajax({
        type:type,
        url:url,
        dataType:dataType,
        success:successFunc,
        error:errorFunc
    });
}

function adjustView(){
    $("#system >nav").css("height",parseInt($("#partSystem").css("height"))+50>parseInt($(window).height())-130?parseInt($("#partSystem").css("height"))+50:parseInt($(window).height())-130);
    $("#partSystem").css("width",parseInt($("#system").css("width"))-300);
}
$(document).ready(function(){

    adjustView();

    $(window).resize(function(){adjustView();});


    $("button").mousedown(function(){
        $(this).css("outline","none");
    });

    $(".btn-href").click(function(){
        location.href=$(this).attr("data-href");
    })
});
