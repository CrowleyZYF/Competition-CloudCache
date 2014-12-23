/**
 * Created by 奕峰 on 2014/11/29.
 */

function drawMap(){
    var allWidth=1200;
    var allHeight=800;
    var canvas=document.getElementById('mapCanvas');
    var ctx=canvas.getContext('2d');
    ctx.fillStyle='#f5f5f5';
    ctx.fillRect(0,0,1200,800);
}

var drawingApp = (function () {

    "use strict";

    var canvas,
        context,
        canvasWidth = 1200,
        canvasHeight = 800,
        totalLoadResources = 3,
        curLoadResNum = 0,
        loaderImage = new Image(),
        httpImage = new Image(),
        cacheImage = new Image(),
        positions=[
            [50,350],
            [300,120],
            [300,360],
            [300,600],
            [550,20],
            [550,230],
            [650,20],
            [650,230],
            [750,20],
            [750,230],
            [850,20],
            [850,230],
            [950,20],
            [950,230],
            [1050,20],
            [1050,230],
            [550,510],
            [550,710],
            [650,510],
            [650,710],
            [750,510],
            [750,710],
            [850,510],
            [850,710],
            [950,510],
            [950,710],
            [1050,510],
            [1050,710]
        ],
        sizes=[[100,100],[80,80],[80,80],[80,80],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50],[50,50]],
        colors=["#428bca","#5cb85c","#f0ad4e","#d9534f","#000000"],
        lines=[
            [
                [10,400,50,400],
                [150,400,220,400]
            ],
            [
                [220,160,300,160],
                [380,160,460,160]
            ],
            [
                [220,400,300,400],
                [380,400,460,400]
            ],
            [
                [220,640,300,640],
                [380,640,460,640]
            ],
            [
                [560,100,560,160],
                [590,100,590,160]
            ],
            [
                [660,100,660,160],
                [690,100,690,160]
            ],
            [
                [760,100,760,160],
                [790,100,790,160]
            ],
            [
                [860,100,860,160],
                [890,100,890,160]
            ],
            [
                [960,100,960,160],
                [990,100,990,160]
            ],
            [
                [1060,100,1060,160],
                [1090,100,1090,160]
            ],
            [
                [560,160,560,220],
                [590,160,590,220]
            ],
            [
                [660,160,660,220],
                [690,160,690,220]
            ],
            [
                [760,160,760,220],
                [790,160,790,220]
            ],
            [
                [860,160,860,220],
                [890,160,890,220]
            ],
            [
                [960,160,960,220],
                [990,160,990,220]
            ],
            [
                [1060,160,1060,220],
                [1090,160,1090,220]
            ],
            [
                [560,580,560,640],
                [590,580,590,640]
            ],
            [
                [660,580,660,640],
                [690,580,690,640]
            ],
            [
                [760,580,760,640],
                [790,580,790,640]
            ],
            [
                [860,580,860,640],
                [890,580,890,640]
            ],
            [
                [960,580,960,640],
                [990,580,990,640]
            ],
            [
                [1060,580,1060,640],
                [1090,580,1090,640]
            ],
            [
                [560,640,560,700],
                [590,640,590,700]
            ],
            [
                [660,640,660,700],
                [690,640,690,700]
            ],
            [
                [760,640,760,700],
                [790,640,790,700]
            ],
            [
                [860,640,860,700],
                [890,640,890,700]
            ],
            [
                [960,640,960,700],
                [990,640,990,700]
            ],
            [
                [1060,640,1060,700],
                [1090,640,1090,700]
            ]
        ],
        datas,

    // Clears the canvas.
        clearCanvas = function () {
            context.clearRect(0, 0, canvasWidth, canvasHeight);
        },

        drawLine = function(color,fromX,fromY,toX,toY){
            context.beginPath();
            context.moveTo(fromX,fromY);
            context.lineTo(toX,toY);
            context.closePath();
            if(color=="normal"){
                context.strokeStyle = colors[0];
                context.lineWidth = 5;
            }else if(color=="good"){
                context.strokeStyle = colors[1];
                context.lineWidth = 3;
            }else if(color=="ok"){
                context.strokeStyle = colors[2];
                context.lineWidth = 3;
            }else if(color=="bad"){
                context.strokeStyle = colors[3];
                context.lineWidth = 3;
            }else if(color=="dead"){
                context.strokeStyle = colors[4];
                context.lineWidth = 3;
            }
            context.stroke();
        },

        drawElement = function(i){
            if(datas[i][0]==true){
                if(i==0){
                    context.drawImage(loaderImage, positions[i][0], positions[i][1], sizes[i][0], sizes[i][1]);
                    context.fillText('负载均衡器',positions[i][0]+45, positions[i][1]+sizes[i][1]+10);
                    context.fillText('（未启动冗余）',positions[i][0]+45, positions[i][1]+sizes[i][1]+30);
                    drawLine(datas[i][1],lines[i][0][0],lines[i][0][1],lines[i][0][2],lines[i][0][3]);
                    drawLine(datas[i][2],lines[i][1][0],lines[i][1][1],lines[i][1][2],lines[i][1][3]);
                }else if(i<=3){
                    context.drawImage(httpImage, positions[i][0], positions[i][1], sizes[i][0], sizes[i][1]);
                    context.fillText('HTTP转发服务器',positions[i][0]+35, positions[i][1]+sizes[i][1]+15);
                    drawLine(datas[i][1],lines[i][0][0],lines[i][0][1],lines[i][0][2],lines[i][0][3]);
                    drawLine(datas[i][2],lines[i][1][0],lines[i][1][1],lines[i][1][2],lines[i][1][3]);
                }else{
                    context.drawImage(cacheImage, positions[i][0], positions[i][1], sizes[i][0], sizes[i][1]);
                    context.fillText(datas[i][3],positions[i][0]+25, positions[i][1]+sizes[i][1]+15);
                    drawLine(datas[i][1],lines[i][0][0],lines[i][0][1],lines[i][0][2],lines[i][0][3]);
                    drawLine(datas[i][2],lines[i][1][0],lines[i][1][1],lines[i][1][2],lines[i][1][3]);
                }
            }
        },

    // Redraws the canvas.
        redraw = function () {
            context.fillStyle='#f5f5f5';
            context.fillRect(0,0,1200,800);
            context.fillStyle='#797979';
            context.textAlign="center";
            context.font = "12px 微软雅黑";

            for(var i=0;i<datas.length;i++){
                drawElement(i);
            }
            drawLine("normal",220,642,220,158);
            drawLine("normal",460,642,460,158);
            drawLine("normal",460,161,1090,161);
            drawLine("normal",460,640,1090,640);

            context.beginPath();
            context.strokeStyle = "lightgray";
            context.lineWidth = 2;
            context.moveTo(500,1);
            context.lineTo(500,320);
            context.lineTo(1150,320);
            context.lineTo(1150,1);
            context.closePath();
            context.stroke();

            context.beginPath();
            context.strokeStyle = "lightgray";
            context.lineWidth = 2;
            context.moveTo(500,470);
            context.lineTo(500,790);
            context.lineTo(1150,790);
            context.lineTo(1150,470);
            context.closePath();
            context.stroke();

            context.textAlign="left";
            context.fillText('集群一',505, 15);
            context.fillText('集群二',505, 485);

            context.beginPath();
            context.strokeStyle = "lightgray";
            context.lineWidth = 2;
            context.moveTo(500,1);
            context.lineTo(500,20);
            context.lineTo(545,20);
            context.lineTo(545,1);
            context.closePath();
            context.stroke();

            context.beginPath();
            context.strokeStyle = "lightgray";
            context.lineWidth = 2;
            context.moveTo(500,470);
            context.lineTo(500,490);
            context.lineTo(545,490);
            context.lineTo(545,470);
            context.closePath();
            context.stroke();

        },

        resourceLoaded = function () {

            curLoadResNum += 1;
            if (curLoadResNum === totalLoadResources) {
                redraw();
            }
        },

    // Creates a canvas element, loads images, adds events, and draws the canvas for the first time.
        init = function (data) {
            canvas = document.createElement('canvas');
            canvas.setAttribute('width', canvasWidth);
            canvas.setAttribute('height', canvasHeight);
            canvas.setAttribute('id', 'canvas');
            document.getElementById('mapCanvas').appendChild(canvas);
            datas=data;
            curLoadResNum = 0;
            loaderImage = new Image();
            httpImage = new Image();
            cacheImage = new Image();
            if (typeof G_vmlCanvasManager !== "undefined") {
                canvas = G_vmlCanvasManager.initElement(canvas);
            }

            context = canvas.getContext("2d");

            loaderImage.onload = resourceLoaded;
            loaderImage.src = "image/loader.png";

            httpImage.onload = resourceLoaded;
            httpImage.src = "image/http.png";

            cacheImage.onload = resourceLoaded;
            cacheImage.src = "image/cache.png";
        };

    return {
        init: init
    };
}());

$(document).ready(function(){
    var data=[
        [true,"good","good",""],
        [true,"good","bad",""],
        [true,"good","ok",""],
        [true,"good","good",""],
        [true,"good","good","Redis：0001"],
        [true,"good","good","Redis：0002"],
        [true,"good","good","Redis：0003"],
        [true,"good","good","Redis：0004"],
        [true,"good","good","Redis：0005"],
        [true,"good","good","Redis：0006"],
        [true,"good","good","Redis：0007"],
        [true,"good","good","Redis：0008"],
        [true,"good","good","Redis：0009"],
        [true,"good","good","Redis：0010"],
        [true,"good","good","Redis：0011"],
        [true,"good","good","Redis：0012"],
        [true,"good","good","Redis：0013"],
        [true,"good","good","Redis：0014"],
        [true,"good","good","Redis：0015"],
        [true,"good","good","Redis：0016"],
        [true,"good","good","Redis：0017"],
        [true,"good","good","Redis：0018"],
        [true,"good","good","Redis：0019"],
        [true,"good","good","Redis：0020"],
        [true,"good","good","Redis：0021"],
        [true,"good","good","Redis：0022"],
        [true,"good","good","Redis：0023"],
        [true,"good","good","Redis：0024"]
    ];

    var testArray=["good","ok","bad","dead"];

    drawingApp.init(data);
    var test=setInterval(function(){
        for(var i=0;i<data.length;i++){
            data[i][1]=testArray[parseInt(Math.random()*4)];
            data[i][2]=testArray[parseInt(Math.random()*4)];
        }
        $("canvas").remove();
        drawingApp.init(data);
    },200000)
});
