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
    <title>后台管理中心</title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.min.js"></script>
</head>

<script type="text/javascript">
    //点击店铺资料
    var keyPage = function () {
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
    }
</script>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img id="headPic" src="../${sessionScope.user.headPic}" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
    </div>
    <div class="head-l"><a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a href="##" class="button button-little bg-blue"><span class="icon-wrench"></span> 清除缓存</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="login.html"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>基本设置</h2>
    <ul style="display:block">
        <li><a href="info.jsp" target="right"><span class="icon-caret-right"></span>个人资料</a></li>
        <li><a href="pass.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
        <li onclick="keyPage();"><a href="page.jsp" target="right"><span class="icon-caret-right"></span>店铺资料</a></li>
        <li><a href="adv.jsp" target="right"><span class="icon-caret-right"></span>首页轮播</a></li>
        <li><a href="book.jsp" target="right"><span class="icon-caret-right"></span>留言管理</a></li>
        <li><a href="column.jsp" target="right"><span class="icon-caret-right"></span>栏目管理</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>栏目管理</h2>
    <ul>
        <li><a href="list.jsp" target="right"><span class="icon-caret-right"></span>内容管理</a></li>
        <li><a href="add.jsp" target="right"><span class="icon-caret-right"></span>添加内容</a></li>
        <li><a href="cate.jsp" target="right"><span class="icon-caret-right"></span>分类管理</a></li>
    </ul>
</div>
<script type="text/javascript">
    $(function(){
        $(".leftnav h2").click(function(){
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        });
        $(".leftnav ul li a").click(function(){
            //$("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        });
    });


</script>
<ul class="bread">
    <li><a href="info.jsp" target="right" class="icon-home"> 首页</a></li><!--"{:U('Index/info')}"-->
    <li><a href="##" id="a_leader_txt"></a>${sessionScope.user.userName }</li><!--网站信息-->
    <li><b>当前语言：</b><span style="color:red;">中文</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="info.jsp" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>