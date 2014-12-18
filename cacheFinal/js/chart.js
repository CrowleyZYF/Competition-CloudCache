/**
 * Created by 奕峰 on 2014/12/15.
 */
function createLineChart(id,title,data,min,max,line,categories) {
    $(id).kendoChart({
        theme:"metro",
        title: {
            text: title
        },
        legend: {
            position: "bottom"
        },
        chartArea: {
            background: "#f5f5f5"
        },
        seriesDefaults: {
            type: "line",
            style: "smooth"
        },
        series: data,
        valueAxis: {
            min:min,
            max:max,
            labels: {
                format: "{0}"
            },
            line: {
                visible: false
            },
            plotBands: [{
                from: line,
                to: max,
                color: "#D8534F",
                opacity: 0.3
            }, {
                from: min,
                to: line,
                color: "#5CB75C",
                opacity: 0.3
            }
            ]
        },
        categoryAxis: {
            categories: categories,
            majorGridLines: {
                visible: false
            }
        },
        tooltip: {
            visible: true,
            format: "{0}%",
            template: "#= series.name #: #= value #"
        }
    });
}

function createLine2Chart(id,title,data,min,max,line1,line2,categories) {
    $(id).kendoChart({
        theme:"metro",
        title: {
            text: title
        },
        legend: {
            position: "bottom"
        },
        chartArea: {
            background: "#f5f5f5"
        },
        seriesDefaults: {
            type: "line",
            style: "smooth"
        },
        series: data,
        valueAxis: {
            min:min,
            max:max,
            labels: {
                format: "{0}"
            },
            line: {
                visible: false
            },
            plotBands: [{
                from: line1,
                to: max,
                color: "#D8534F",
                opacity: 0.3
            },{
                from: line2,
                to: line1,
                color: "#5CB75C",
                opacity: 0.3
            }, {
                from: min,
                to: line2,
                color: "#D8534F",
                opacity: 0.3
            }
            ]
        },
        categoryAxis: {
            categories: categories,
            majorGridLines: {
                visible: false
            }
        },
        tooltip: {
            visible: true,
            format: "{0}%",
            template: "#= series.name #: #= value #"
        }
    });
}

function createPieChart(id,title,data) {
    $(id).kendoChart({
        theme:"metro",
        title: {
            position: "top",
            text: title
        },
        legend: {
            position: "bottom"
        },
        chartArea: {
            background: "#f5f5f5"
        },
        series: [{
            type: "pie",
            startAngle: 150,
            data: data
        }],
        tooltip: {
            visible: true,
            format: "{0}",
            template: "#= category #: #= value #"
        }
    });
}

function createColChart(id,title,data,categories){
    $(id).kendoChart({
        theme:"metro",
        title: {
            text: title
        },
        chartArea: {
            background: "#f5f5f5"
        },
        legend: {
            position: "bottom"
        },
        seriesDefaults: {
            type: "column"
        },
        series: data,
        valueAxis: {
            labels: {
                format: "{0}"
            },
            line: {
                visible: false
            }
        },
        categoryAxis: {
            categories: categories,
            line: {
                visible: false
            }
        },
        tooltip: {
            visible: true,
            format: "{0}%",
            template: "#= series.name #: #= value #"
        }
    });
}

function createAreaChart(id,title,data,categories,min,max,line){
    $(id).kendoChart({
        theme:"metro",
        title: {
            text: title
        },
        chartArea: {
            background: "#f5f5f5"
        },
        legend: {
            position: "bottom"
        },
        seriesDefaults: {
            type: "area",
            area: {
                line: {
                    style: "smooth"
                }
            }
        },
        series: data,
        valueAxis: {
            min:min,
            max:max,
            labels: {
                format: "{0}%"
            },
            line: {
                visible: false
            },
            axisCrossingValue: -10,
            plotBands: [{
                from: line,
                to: max,
                color: "#D8534F",
                opacity: 0.3
            }, {
                from: min,
                to: line,
                color: "#5CB75C",
                opacity: 0.3
            }
            ]
        },
        categoryAxis: {
            categories: categories,
            majorGridLines: {
                visible: false
            }
        },
        tooltip: {
            visible: true,
            format: "{0}%",
            template: "#= series.name #: #= value #"
        }
    });
}

function createDoubleChart(id,title,data,min,max,line,min2,max2,cat) {
    $(id).kendoChart({
        theme:"metro",
        title: {
            text: title
        },
        legend: {
            position: "bottom"
        },
        seriesDefaults: {
            style: "smooth"
        },
        chartArea: {
            background: "#f5f5f5"
        },
        series: data,
        valueAxes: [{
            name: "rate",
            labels: {
                format: "{0}%"
            },
            color: "#FD6901",
            min: min,
            max: max,
            plotBands: [{
                from: line,
                to: max,
                color: "#5CB75C",
                opacity: 0.3
            }, {
                from: min,
                to: line,
                color: "#D8534F",
                opacity: 0.3
            }]
        }, {
            name: "time",
            color: "#259FD9",
            min: min2,
            max: max2
        }],
        categoryAxis: {
            categories: cat
        },
        tooltip: {
            visible: true,
            format: "{0}",
            template: "#= category #:00: #= value #"
        }
    });
}

function createBarChart(id,title,time,key,max) {
    $(id).kendoChart({
        theme:"metro",
        title: {
            text: title
        },
        chartArea: {
            background: "#f5f5f5"
        },
        legend: {
            visible: false
        },
        seriesDefaults: {
            type: "bar"
        },
        series: [{
            name: "Total Visits",
            data: time,
            color:"#259ED8",
            labels: {
                visible: true,
                background: "transparent",
                position: "outsideEnd",
                template: "#= category #:  #= value#"
            }
        }],
        valueAxis: {
            max: max,
            minorGridLines: {
                visible: true
            }
        },
        categoryAxis: {
            categories: key,
            majorGridLines: {
                visible: false
            }
        },
        tooltip: {
            visible: true,
            template: "#= category #: #= value #"
        }
    });
}

function createDouble2Chart(id,title,data,min,max,line1,line2,min2,max2,cat) {
    $(id).kendoChart({
        theme:"metro",
        title: {
            text: title
        },
        legend: {
            position: "bottom"
        },
        seriesDefaults: {
            style: "smooth"
        },
        chartArea: {
            background: "#f5f5f5"
        },
        series: data,
        valueAxes: [{
            name: "time",
            labels: {
                format: "{0} mins"
            },
            color: "#FD6901",
            min: min,
            max: max,
            plotBands: [{
                from: min,
                to: line1,
                color: "#D8534F",
                opacity: 0.3
            },{
                from: line1,
                to: line2,
                color: "#5CB75C",
                opacity: 0.3
            },{
                from: line2,
                to: max,
                color: "#D8534F",
                opacity: 0.3
            }]
        }, {
            name: "size",
            labels: {
                format: "{0} MB"
            },
            color: "#259FD9",
            min: min2,
            max: max2
        }],
        categoryAxis: {
            categories: cat
        },
        tooltip: {
            visible: true,
            format: "{0}",
            template: "#= category # #= value #"
        }
    });
}
