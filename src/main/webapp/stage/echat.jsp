<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="../js/echarts.min.js"></script>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/layer.js"></script>
	<style type="text/css">
    html,body{
        margin:0;
        padding: 0;
    }
    .chartContainer{
	    margin:0 auto;
        width:40%;
        height:25rem;
        border:0px solid pink;
        box-sizing:border-box;
		float:left;
    }
</style>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main1" style="width: 600px;height:400px;float:left;"></div>
	<div class="chartContainer" id="chart1"></div>
	<div id="main" style="height:400px;width: 600px"></div>
    <script type="text/javascript">
        //从后台中获取统计数据
        var menuTypes = [];
        var menuSales = [];
        var commentAccount = [];
        var temp = [];
        var color = {color : "red"};
        var normal = {normal : color};
        var itemStyle = {itemStyle : normal};

        $.ajax({
            url: "${pageContext.request.contextPath}/shop/getShopAccount.do",
            data: {shopId : "${sessionScope.user.shopId}"},
            type: "post",
            dataType: "json",
            async:false,
            success: function (data) {
                var obj;
                if(data.length == 0){
                    layer.msg("暂无任何统计数据");
                }else{
                    obj = data[0];
//                    var menuT = obj.menuTypes;
//                    for(var i=0;i<menuT.length;i++){
//                        menuTypes.push(menuT[i]);
//                    }
                    menuTypes = obj.menuTypes;
                    menuSales = obj.menuSales;
                    commentAccount = obj.commentList;
//                    for(var i=0;i<commentAccount.length;i++){
//                        temp.push(commentAccount[i]);
//                        temp.push(itemStyle);
//                        console.log(temp);
//                    }
                }
                console.log(itemStyle);
                console.log(menuTypes);
                console.log(commentAccount);
            }
        });



        // 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('main1'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: menuTypes//所有的菜单类型
            },
            yAxis: {},
            series: [{
				color : 'yellow',
                name: '销量',
                type: 'bar',
                data: menuSales//类型--->销量
            }]
        };



        // 使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option);


//饼图------------------------------------------------------------------------
	//初始化echarts实例
    var chart2=$("#chart1").get(0);
    var myChart2=echarts.init(chart2);
    //对echarts做基础配置
    myChart2.setOption({
        //背景色是全局的，所以直接在 option 下设置 backgroundColor,一开始放在series里面没有效果'#2c343c'
        backgroundColor:'white', 
        visualMap:{
            show:false, // 不显示 visualMap 组件，只用于明暗度的映射
            min:80,// 映射的最小值为 80
            max:600,// 映射的最大值为 600
            inRange:{
                colorLightness:[0,1] // 明暗度的范围是 0 到 1
            }
        },
        series:[{
            name:"访问来源",
            type:"pie",
            radius:"55%",
            roseType: 'angle',//此字段表示南丁格尔图（通过半径表示数据大小）
            itemStyle:{
                normal:{//正常情况下的样式
                    shadowBlur:200,//阴影的大小
                    shadowOffsetx:0,//阴影水平方向上的偏移
                    shadowOffsetY:0,//阴影垂直方向上的偏移
                    shadowColor:"rgba(0,0,0,0.5)",
                    color:"red" //设置扇叶整体颜色
                },
                emphasis:{//鼠标hover的高亮时候的样式rgba(255,255,255,0.3)
                    shadowBlur:400,
                    shadowColor:"rgba(0,0,0,1)"
                }
            },
            label:{
                normal:{
                    textStyle:{
                        color:"red",
                        fontSize:"12"
                    }
                }
            },
            labelLine:{//跟itemStyle一样，label和labelLine的样式也有normal和emphasis两个状态。rgba(255,255,255,0.3)
                normal:{
                    lineStyle:{
                        color:"red"
                    }
                }
            },
            data: commentAccount
//            data:[
//                {
//                    value:400,
//                    name:"搜索引擎",
//                    /*itemStyle:{
//                        normal:{
//                            color:"red"
//                        }
//                    }*/
//                },
//                {
//                    value:340,
//                    name:"直接访问",
//                    /*itemStyle:{
//                        normal:{
//                            color:"blue"
//                        }
//                    }*/
//                },
//                {
//                    value:310,
//                    name:"邮件营销",
//                    /*itemStyle:{
//                        normal:{
//                            color:"green"
//                        }
//                    }*/
//                },
//                {
//                    value:140,
//                    name:"联盟广告",
//                    /*itemStyle:{
//                        normal:{
//                            color:"pink"
//                        }
//                    }*/
//                },
//                {
//                    value:230,
//                    name:"视频广告",
//                    /*itemStyle:{
//                        normal:{
//                            color:"yellow"
//                        }
//                    }*/
//                },
//            ]
        }]
    });

	//折线图-----------------------------------------------------------------
	    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById("main"));
    var option = {
        title : {
            text: '未来一周气温变化',
            subtext: '纯属虚构'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['最高气温','最低气温']
        },
        //右上角工具条
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['周一','周二','周三','周四','周五','周六','周日']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value} °C'
                }
            }
        ],
        series : [
            {
                name:'最高气温',
                type:'line',
                data:[11, 11, 15, 13, 12, 13, 10],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'最低气温',
                type:'line',
                data:[1, -2, 2, 5, 3, 2, 0],
                markPoint : {
                    data : [
//                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                        {type : 'min', name: '周最低'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
    </script>

</body>
</html>