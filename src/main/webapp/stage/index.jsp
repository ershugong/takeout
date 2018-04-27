<%@ page import="cn.web.takeout.model.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/4
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>便捷外卖商家管理中心</title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/pintuer.js"></script>
    <script src="../js/layer.js"></script>
    <script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
    <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script>
    <script src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
</head>

<script type="text/javascript">
    var websocket;
    //点击店铺资料
    var keyPage = function (res) {
        $("#myFrame").attr("src",$(res).attr("href"));
        $.ajax({
            url : "${pageContext.request.contextPath}/shop/selectShop.do",
            type : "post",
            data : {id : "${sessionScope.user.shopId}"},
            dataType : "json",
            success : function (data) {

            },
            error : function (data) {
                alert(data);
            }
        });
    };

    var openSocket = function openWs(){
        websocket = new WebSocket("ws://localhost:8080/takeout/websocket");
        websocket.onmessage = function (e) {
            if(e.data == "yes"){
                var a=document.getElementById("audio");
                if(a.paused){
                    a.play();
                }else{
                    a.pause();
                }

                console.log($("#myFrame").attr("src"));
                //如果当前页是订单页面,刷新
                if($("#myFrame").attr("src") == "column.jsp"){
                    layer.msg("你有新的订单");
                    $("#order").click();
                }else{
                    layer.msg("你有新的订单，请在订单页面上查看!");
                }

            }

        };

        //添加状态判断，当为OPEN时，发送消息
        //添加事件监听
        websocket.addEventListener('open', function () {
            websocket.send("${sessionScope.user.shopId}");
        });

    };
    //关闭连接
    function wsClose() {
        websocket.close();
    };

    //打开webscket连接
    openSocket();

    var changeSrc = function(res){
        $("#myFrame").attr("src",$(res).attr("href"));
    }


</script>
<body style="background-color:#f2f9fd;">
<audio id="audio" src="../music/lingsheng.mp3"></audio>
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img id="headPic" src="../${sessionScope.user.headPic}" class="radius-circle rotate-hover" height="50" alt="" />便捷外卖商家管理中心</h1>
    </div>
    <%--&nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a>--%>
    <div class="head-l"><a class="button button-little bg-green" href="echat.jsp" target="right"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="../login.jsp"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>基本设置</h2>
    <ul style="display:block">
        <li><a href="info.jsp" onclick="changeSrc(this);" target="right"><span class="icon-caret-right"></span>个人资料</a></li>
        <li><a href="pass.jsp" onclick="changeSrc(this);" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
        <li onclick="keyPage(this);"><a href="page.jsp" target="right"><span class="icon-caret-right"></span>店铺资料</a></li>
        <li id="menu"><a href="adv.jsp" onclick="changeSrc(this);" target="right"><span class="icon-caret-right"></span>菜单管理</a></li>
        <li><a href="book.jsp" onclick="changeSrc(this);" target="right"><span class="icon-caret-right"></span>评价管理</a></li>
        <li><a href="column.jsp" onclick="changeSrc(this);" target="right" id="order"><span class="icon-caret-right"></span>订单管理</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>统计管理</h2>
    <ul>
        <li><a href="echat.jsp" target="right"><span class="icon-caret-right"></span>数据统计</a></li>
    </ul>
</div>
<script type="text/javascript">
    $(function(){
        $(".leftnav h2").click(function(){
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        });
        $(".leftnav ul li a").click(function(){
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        });

        if("${sessionScope.user.userName}" == ""){//页面刷新，session为空则直接跳转到登录页面
            console.log("${sessionScope.user.userName}");
            self.location = "../login.jsp";
        }
        //页面关闭资源关闭
        window.onbeforeunload = function(){
            wsClose();
        };
    });


</script>
<ul class="bread">
    <li><a href="info.jsp" target="right" class="icon-home"> 首页</a></li><!--"{:U('Index/info')}"-->
    <li><a href="##" id="a_leader_txt"></a>${sessionScope.user.userName }</li><!--网站信息-->
    <li><b>当前语言：</b><span style="color:red;">中文</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="echat.jsp" name="right" width="100%" height="100%" id = 'myFrame'></iframe>
</div>
</body>
</html>
