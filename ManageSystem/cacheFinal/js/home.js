/**
 * Created by 奕峰 on 2014/11/29.
 */

//配置区域
var createMethod="POST";
var createUrl="/manage_server/manage/redis/createInstance";
var modifyMethod="GET";
var modifyUrl="testUrl/url";
var deleteMethod="POST";
var deleteUrl="/manage_server/manage/redis/deleteInstance";
var stopMethod="POST";
var stopUrl="/manage_server/manage/redis/stopInstance";
var restartMethod="POST";
var restartUrl="/manage_server/manage/redis/startInstance";

var getAllMethod="GET";
var getAllUrl="/manage_server/manage/redis/getInstances";

var getRedisMethod="GET";
var getRedisUrl="/manage_server/manage/redis/getInstances";

var getMemcachedMethod="GET";
var getMemcachedUrl="testUrl/url";

//测试数据
var fateData={"id":"61.164.36.20:1111","identify":"f7657f31553b11e4","name":"庄奕峰","status":0,"type":"redis","area":"集群二","time":"2014-10-16","used":"0","all":"1024"};

var test01=[
    {"id":"61.164.36.23:0001","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"484","all":"1024"},
    {"id":"61.164.36.23:0002","identify":"f7657f31553b11e5","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"572","all":"1024"},
    {"id":"61.164.36.23:0003","identify":"f7657f31553b11e6","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"753","all":"1024"},
    {"id":"61.164.36.23:0004","identify":"f7657f31553b11e7","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"142","all":"1024"},
    {"id":"61.164.36.23:0005","identify":"f7657f31553b11e8","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"785","all":"1024"},
    {"id":"61.164.36.23:0006","identify":"f7657f31553b11e9","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"652","all":"1024"},
    {"id":"61.164.36.23:0007","identify":"f7657f31553b11e0","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"432","all":"1024"},
    {"id":"61.164.36.23:0008","identify":"f7657f31553b11e1","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"785","all":"1024"},
    {"id":"61.164.36.23:0009","identify":"f7657f31553b11e2","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"246","all":"1024"},
    {"id":"61.164.36.23:0010","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"452","all":"1024"},
    {"id":"61.164.36.23:0003","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"753","all":"1024"},
    {"id":"61.164.36.23:0004","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"142","all":"1024"},
    {"id":"61.164.36.23:0005","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"785","all":"1024"},
    {"id":"61.164.36.23:0006","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"652","all":"1024"},
    {"id":"61.164.36.23:0007","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"432","all":"1024"},
    {"id":"61.164.36.23:0008","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"785","all":"1024"},
    {"id":"61.164.36.23:0009","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"246","all":"1024"},
    {"id":"61.164.36.23:0010","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"type":"redis","area":"集群一","time":"2014-10-16","used":"452","all":"1024"}
];

var test02=[
    {"id":"61.164.36.23:0011","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"452","all":"1024"},
    {"id":"61.164.36.23:0012","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"562","all":"1024"},
    {"id":"61.164.36.23:0013","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"296","all":"1024"},
    {"id":"61.164.36.23:0204","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"342","all":"1024"},
    {"id":"61.164.36.23:0245","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"315","all":"1024"},
    {"id":"61.164.36.23:0306","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"842","all":"1024"},
    {"id":"61.164.36.23:0047","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"237","all":"1024"},
    {"id":"61.164.36.23:0228","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"782","all":"1024"},
    {"id":"61.164.36.23:0329","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"145","all":"1024"},
    {"id":"61.164.36.23:0110","identify":"f7657f31553b11e4","name":"陈丽霞","status":0,"area":"集群一","time":"2014-10-16","used":"723","all":"1024"}
];


//全局变量
//操作的行
var myTr;

//创建单行
function createTr(data,cacheType){
    var tr=$("<tr></tr>");
    var td1=$("<td><div class='checkbox'><label><input type='checkbox'></label></div></td></td>");
    var td2=$("<td>"+data["id"]+"</td>");
    var td3=$("<td>"+data["identify"]+"</td>");
    var td5=$("<td>"+data["name"]+"</td>");
    if(data["status"]==0){
        var td6=$("<td class='using'>使用中</td>");
    }else{
        var td6=$("<td class='stopping'>已停止</td>");
    }
    var td8=$("<td>"+data["area"]+"</td>");
    var td9=$("<td>"+data["time"]+"</td>");
    var rate=parseInt(100*parseInt(data["used"])/parseInt(data["all"]));
    var cssClass;
    if(rate<30){
        cssClass="progress-bar-success";
    }else if(rate<60){
        cssClass="progress-bar-info";
    }else if(rate<90){
        cssClass="progress-bar-warning";
    }else{
        cssClass="progress-bar-danger";
    }
    var td10=$("<td class='specialTD'><div class='progress'><div class='progress-bar "+cssClass+"' role='progressbar' aria-valuenow='"+rate+"' aria-valuemin='0' aria-valuemax='100' style='width: "+rate+"%;'>"+rate+"%</div></div><p>"+data["used"]+"Byte/"+data["all"]+"MB("+rate+"%)</p></td>");
    var td11=$("<td class='specialTD'><button type='button' class='btn btn-warning stopAndRestartButton' data-id='"+data["id"]+"' data-toggle='modal' data-target='#confirmModal'>停止</button><button type='button' class='btn btn-info btn-modify' data-id='"+data["id"]+"' data-toggle='modal' data-target='#modifyModal'>调整</button><button type='button' class='btn btn-danger btn-delete' data-id='"+data["id"]+"' data-toggle='modal' data-target='#confirmModal'>删除</button></td>");
    if(cacheType){
        var td7=$("<td>"+data["type"]+"</td>");
        tr.append(td1).append(td2).append(td3).append(td5).append(td6).append(td7).append(td8).append(td9).append(td10).append(td11);
        return tr;
    }else{
        tr.append(td1).append(td2).append(td3).append(td5).append(td6).append(td8).append(td9).append(td10).append(td11);
        return tr;
    }
}

//创建表格
function createTbody(id,data,cacheType){
    $(id+">tbody").children().remove();
    for(var i=0;i<data.length;i++){
        $(id+">tbody").append(createTr(data[i],cacheType));
    }
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-grey',
        radioClass: 'iradio_square-grey',
        increaseArea: '20%' // optional
    });
    adjustView();
    //修改配置
    $(".btn-modify").click(function(){
        $(".btn-fix").attr("data-id",$($(this).parent().parent().children()[1]).html());
        $("#modifyID").val($($(this).parent().parent().children()[1]).html());
        $("#modifyNumber").val($($(this).parent().parent().children()[2]).html());
        $("#modifyName").val($($(this).parent().parent().children()[3]).html());
        $(".btn-fix").click(function(){
            cacheAjaxWithData(modifyMethod,modifyUrl,"json",
                {
                    //page代表传给我什么，all代表所有，redis代表redis，memcached代表memcached
                    "page":$(".tab-content").children(".active").attr("id"),
                    "id":$("#modifyID").val(),
                    "type":$("#modifyEngine").val(),
                    "area":$("#modifyGroup").val(),
                    "all":$("#modifyAmount").val()
                },
                function(json){
                    createTbody("#"+$(".tab-content").children(".active").find("table").attr("id"),json,true);
                },
                function(){
                    createTbody("#"+$(".tab-content").children(".active").find("table").attr("id"),test01,true);
                });
        });
    });
    //停止和重启
    $(".stopAndRestartButton").click(function(){
        myTr=$(this).parents("tr");
        if($(this).hasClass("btn-warning")){
            $("#confirm").attr("data-type","stop-single").attr("data-id",$(this).attr("data-id"));
        }else{
            $("#confirm").attr("data-type","restart-single").attr("data-id",$(this).attr("data-id"));
        }
    });
    //删除
    $(".btn-delete").click(function(){
        myTr=$(this).parents("tr");
        $("#confirm").attr("data-type","delete-single").attr("data-id",$(this).attr("data-id"));
    });
}

//创建新实例
function createNewItem(data){
    if($(".tab-content>.active").attr("id")=="all"){
        $(".tab-content>.active tbody").prepend(createTr(data,true));
    }else{
        $(".tab-content>.active tbody").prepend(createTr(data,false));
    }
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-grey',
        radioClass: 'iradio_square-grey',
        increaseArea: '20%' // optional
    });
    adjustView();
}

//停止所有实例
function stopAll(){
    $(".tab-pane").find(".using").each(function(){
        $(this).removeClass("using").addClass("stopping").html("已停止");
    });
    $(".tab-pane").find(".btn-warning").each(function(){
        $(this).removeClass("btn-warning").addClass("btn-success");
        $(this).html("重启");
    });
    $("#stopAll").removeClass("btn-warning").addClass("btn-success").html("重 启 所 有 实 例").attr("id","restartAll");
}

//停止选中实例
function stopSelect(){
    $(".tab-pane.active input[type=checkbox]:checked").parents("tr").find(".using").each(function(){
        $(this).removeClass("using").addClass("stopping").html("已停止");
    });
    $(".tab-pane.active input[type=checkbox]:checked").parents("tr").find(".btn-warning").each(function(){
        $(this).removeClass("btn-warning").addClass("btn-success");
        $(this).html("重启");
    });
}

//停止单个实例
function stopSingle(){
    myTr.find(".using").each(function(){
        $(this).removeClass("using").addClass("stopping").html("已停止");
    });
    myTr.find(".btn-warning").each(function(){
        $(this).removeClass("btn-warning").addClass("btn-success");
        $(this).html("重启");
    });
}

//删除所有实例
function deleteAll(){
    $("tbody").find("tr").each(function(){
        $(this).remove();
    });
    adjustView();
}

//删除选中实例
function deleteSelect(){
    $(".tab-pane.active input[type=checkbox]:checked").parents("tr").each(function(){
        $(this).remove();
    });
    adjustView();
}

//重启所有实例
function restartAll(){
    $(".tab-pane").find(".stopping").each(function(){
        $(this).removeClass("stopping").addClass("using").html("使用中");
    });
    $(".tab-pane").find(".btn-success").each(function(){
        $(this).removeClass("btn-success").addClass("btn-warning");
        $(this).html("停止");
    });
    $("#restartAll").removeClass("btn-success").addClass("btn-warning").html("停 止 所 有 实 例").attr("id","stopAll");
}

//重启选中实例
function restartSelect(){
    $(".tab-pane.active input[type=checkbox]:checked").parents("tr").find(".stopping").each(function(){
        $(this).removeClass("stopping").addClass("using").html("使用中");
    });
    $(".tab-pane.active input[type=checkbox]:checked").parents("tr").find(".btn-success").each(function(){
        $(this).removeClass("btn-success").addClass("btn-warning");
        $(this).html("停止");
    });
}

//重启单个实例
function restartSingle(){
    myTr.find(".stopping").each(function(){
        $(this).removeClass("stopping").addClass("using").html("使用中");
    });
    myTr.find(".btn-success").each(function(){
        $(this).removeClass("btn-success").addClass("btn-warning");
        $(this).html("停止");
    });
}





$(document).ready(function(){
    //all
    cacheAjaxWithOutData(getAllMethod,getAllUrl,"json",function(json){createTbody("#allTable",json,true);},function(){createTbody("#allTable",test01,true);});
    //redis
    cacheAjaxWithOutData(getRedisMethod,getRedisUrl,"json",function(json){createTbody("#redisTable",json,false);},function(){createTbody("#redisTable",test02,false);});
    //memcached
    cacheAjaxWithOutData(getMemcachedMethod,getMemcachedUrl,"json",function(json){createTbody("#memcachedTable",json,false);},function(){createTbody("#memcachedTable",test02,false);});

    //兼容导航长度
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        adjustView();
    });

    //创建新的元素
    $("#createNewItem").click(function(){
        $('#createModal').modal('toggle');
        cacheAjaxWithData(getAllMethod,getAllUrl,"json",
            {
                "name":$("#createName").val(),
                "type":$("#createType").val(),
                "area":$("#createArea").val(),
                "all":$("#createAll").val()
            },
            function(json){
                createNewItem(json);
            },
            function(){
                createNewItem(fateData);
            }
        );
    });

    //确认按钮
    $(".needConfirm").click(function(){
        $("#confirm").attr("data-type",$(this).attr("id"));
    });

    //确认操作后 根据data-id进行操作
    //停止实例，传id，如果id为0，则代表停止所有实例
    $("#confirm").click(function(){
        switch ($(this).attr("data-type")){
            case "stopAll":
                var data=[];
                $("tbody>tr").each(function(){
                    data[data.length]=$($(this).children()[1]).html();
                });
                cacheAjaxWithData(stopMethod,stopUrl,"json",{"id":data},function(){stopAll();},function(){stopAll();});
                break;
            case "deleteAll":
                var data=[];
                $("tbody>tr").each(function(){
                    data[data.length]=$($(this).children()[1]).html();
                });
                cacheAjaxWithData(deleteMethod,deleteUrl,"json",{"id":data},function(){deleteAll();},function(){deleteAll();});
                break;
            case "restartAll":
                var data=[];
                $("tbody>tr").each(function(){
                    data[data.length]=$($(this).children()[1]).html();
                });
                cacheAjaxWithData(restartMethod,restartUrl,"json",{"id":data},function(){restartAll();},function(){restartAll();});
                break;
            case "stopSelect":
                var data=[];
                $(".tab-pane.active input[type=checkbox]:checked").parents("td").next().each(function(){
                    data[data.length]=$(this).html();
                });
                cacheAjaxWithData(stopMethod,stopUrl,"json",{"id":data},function(){stopSelect();},function(){stopSelect();});
                break;
            case "deleteSelect":
                var data=[];
                $(".tab-pane.active input[type=checkbox]:checked").parents("td").next().each(function(){
                    data[data.length]=$(this).html();
                });
                cacheAjaxWithData(deleteMethod,deleteUrl,"json",{"id":data},function(){deleteSelect();},function(){deleteSelect();});
                break;
            case "restartSelect":
                var data=[];
                $(".tab-pane.active input[type=checkbox]:checked").parents("td").next().each(function(){
                    data[data.length]=$(this).html();
                });
                cacheAjaxWithData(restartMethod,restartUrl,"json",{"id":data},function(){restartSelect();},function(){restartSelect();});
                break;
            case "stop-single":
                cacheAjaxWithData(stopMethod,stopUrl,"json",{"id":[$(this).attr("data-id")]},function(){stopSingle();},function(){stopSingle();});
                break;
            case "restart-single":
                cacheAjaxWithData(restartMethod,restartUrl,"json",{"id":[$(this).attr("data-id")]},function(){restartSingle();},function(){restartSingle();});
                break;
            case "delete-single":
                cacheAjaxWithData(deleteMethod,deleteUrl,"json",{"id":[$(this).attr("data-id")]},function(){myTr.remove();},function(){myTr.remove();});
                break;
        }
        $('#confirmModal').modal('toggle');
    });
});
