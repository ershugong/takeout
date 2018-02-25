<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/4
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/pintuer.js"></script>
    <script src="../js/jquery-form.js"></script>
    <script type="text/javascript">
        var shopId = "${sessionScope.user.shopId}";
        $.ajax({
            url: "http://localhost:8080/takeout/comment/getCommentListForPC.do",
            method: "post",
            dataType: "json",
            data: {
                shopId: shopId
            },
            success:function(data){
                var container = $("#container");
                $.each(data,function(i){
                    var srcUrl = '../'+data[i].menuHeadPic;
                    container.append("<tr><td><img style='height: 30px;width: 80px' src = '"+srcUrl+"'/></td><td>"+data[i].menuName+"</td><td>"+data[i].userName+"</td><td>"+data[i].content+"</td><td>"+data[i].commentType+"</td><td>"+data[i].createTime+"</td></tr>");
                })

//            <tr>
//            <td><input type="checkbox" name="id[]" value="1" />
//            1</td>
//            <td>神夜</td>
//            <td>13420925611</td>
//            <td>564379992@qq.com</td>
//            <td>深圳市民治街道</td>
//            <td>这是一套后台UI，喜欢的朋友请多多支持谢谢。</td>
//            <td>2016-07-01</td>
//            </tr>
            },
            error: function(data){
                alert("error:" + data.responseText);
            }
        })
    </script>
</head>
<body>
<form method="post" action="">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 留言管理</strong></div>
        <div class="padding border-bottom">
            <ul class="search">
                <li>
                    <button type="button"  class="button border-green" id="checkall"><span class="icon-check"></span> 全选</button>
                    <button type="submit" class="button border-red"><span class="icon-trash-o"></span> 批量删除</button>
                </li>
            </ul>
        </div>
        <table class="table table-hover text-center" id="container">
            <tr>
                <th width="120">菜图</th>
                <th>菜名</th>
                <th>用户名</th>
                <th width="25%">评价</th>
                <th>态度</th>
                <th width="120">评价时间</th>
            </tr>
            <%--<tr>--%>
                <%--<td><input type="checkbox" name="id[]" value="1" />--%>
                    <%--1</td>--%>
                <%--<td>神夜</td>--%>
                <%--<td>13420925611</td>--%>
                <%--<td>564379992@qq.com</td>--%>
                <%--<td>深圳市民治街道</td>--%>
                <%--<td>这是一套后台UI，喜欢的朋友请多多支持谢谢。</td>--%>
                <%--<td>2016-07-01</td>--%>
                <%--<td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a> </div></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="checkbox" name="id[]" value="1" />--%>
                    <%--1</td>--%>
                <%--<td>神夜</td>--%>
                <%--<td>13420925611</td>--%>
                <%--<td>564379992@qq.com</td>--%>
                <%--<td>深圳市民治街道</td>--%>
                <%--<td>这是一套后台UI，喜欢的朋友请多多支持谢谢。</td>--%>
                <%--<td>2016-07-01</td>--%>
                <%--<td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a> </div></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="checkbox" name="id[]" value="1" />--%>
                    <%--1</td>--%>
                <%--<td>神夜</td>--%>
                <%--<td>13420925611</td>--%>
                <%--<td>564379992@qq.com</td>--%>
                <%--<td>深圳市民治街道</td>--%>
                <%--<td>这是一套后台UI，喜欢的朋友请多多支持谢谢。</td>--%>
                <%--<td>2016-07-01</td>--%>
                <%--<td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a> </div></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="checkbox" name="id[]" value="1" />--%>
                    <%--1</td>--%>
                <%--<td>神夜</td>--%>
                <%--<td>13420925611</td>--%>
                <%--<td>564379992@qq.com</td>--%>
                <%--<td>深圳市民治街道</td>--%>
                <%--<td>这是一套后台UI，喜欢的朋友请多多支持谢谢。</td>--%>
                <%--<td>2016-07-01</td>--%>
                <%--<td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a> </div></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="checkbox" name="id[]" value="1" />--%>
                    <%--1</td>--%>
                <%--<td>神夜</td>--%>
                <%--<td>13420925611</td>--%>
                <%--<td>564379992@qq.com</td>--%>
                <%--<td>深圳市民治街道</td>--%>
                <%--<td>这是一套后台UI，喜欢的朋友请多多支持谢谢。</td>--%>
                <%--<td>2016-07-01</td>--%>
                <%--<td><div class="button-group"> <a class="button border-red" href="javascript:void(0)" onclick="return del(1)"><span class="icon-trash-o"></span> 删除</a> </div></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td colspan="8"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>--%>
            <%--</tr>--%>
        </table>
    </div>
</form>
<script type="text/javascript">


    function del(id){
        if(confirm("您确定要删除吗?")){

        }
    }

    $("#checkall").click(function(){
        $("input[name='id[]']").each(function(){
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    function DelSelect(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var t=confirm("您确认要删除选中的内容吗？");
            if (t==false) return false;
        }
        else{
            alert("请选择您要删除的内容!");
            return false;
        }
    }

</script>
</body></html>
