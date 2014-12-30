/**
 * Created by 奕峰 on 2014/12/17.
 */
/**
 * Created by 奕峰 on 2014/11/29.
 */

var getAllMethod="GET";
var getAllUrl="testUrl/url";

var getRedisMethod="GET";
var getRedisUrl="testUrl/url";

var getMemcachedMethod="GET";
var getMemcachedUrl="testUrl/url";

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

//创建单行
function createTr(data,cacheType){
    var tr=$("<tr data-href='adjustRecord.html'></tr>");
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
    var td11=$("<td>算法一</td>");
    if(cacheType){
        var td7=$("<td>"+data["type"]+"</td>");
        tr.append(td1).append(td2).append(td3).append(td5).append(td6).append(td7).append(td8).append(td9).append(td11).append(td10);
        return tr;
    }else{
        tr.append(td1).append(td2).append(td3).append(td5).append(td6).append(td8).append(td9).append(td11).append(td10);
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
    $('tbody tr').click(function(){
        location.href=$(this).attr("data-href");
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


});
