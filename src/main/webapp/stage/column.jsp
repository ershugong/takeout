<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/4
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery-form.js"></script>
    <script src="../js/pintuer.js"></script>
    <script src="../js/layer.js"></script>
    <script type="text/javascript">
        var index = 1;
        var maxIndex;

        var addPage = function(){
            if(index == maxIndex){
                layer.msg('已经最后一页了');
                return;
            }

            index = index + 1;
            getOrders(index);
        }

        var removePage = function(){
            if(index == 1){
                layer.msg("没有上一页了");
                return;
            }
            index = index - 1;
            getOrders(index);
        }

        var reLoadWindow = function(){
            window.location.reload();//刷新页面
        }
        //setTimeout(reLoadWindow,10000);

        var getOrders = function(page){
            index = page;
            $.ajax({
                url : "${pageContext.request.contextPath}/order/getOrderByShopId.do",
                data : {
                    shopId : "${sessionScope.user.shopId}",
                    page : page
                },
                dataType : "json",
                method : "POST",
                success : function(data){
                    var container = $("#tableData");
                    container.html("");
                    container.append('        <tr>' +
                        '            <th width="15%">订单ID</th>' +
                        '            <th width="10%">菜名</th>' +
                        '            <th width="10%">样图</th>' +
                        '            <th width="10%">数量</th>' +
                        '            <th width="10%">备注</th>' +
                        '            <th width="10%">用户名</th>' +
                        '            <th>地址</th>' +
                        '            <th width="5%">状态</th>' +
                        '            <th width="250">操作</th>' +
                        '        </tr>');
                    if(data.length == 0){
                        layer.msg("暂无任何订单数据！");
                        return;
                    }
                    $.each(data,function(index){
                        var xxxData = JSON.stringify(data[index]);
                        var srcUrl = '../'+data[index].menuHeadPic;
                        container.append("<tr><td>"+data[index].id+"</td>" +
                            "<td>"+data[index].menuName+"</td><td><img style='height: 30px;width: 80px' src = '"+srcUrl+"'/></td>" +
                            "<td>"+data[index].numb+"</td><td>"+data[index].ext+"</td><td>"+data[index].userName+"</td><td>"+data[index].detailPlace+"</td><td>"+data[index].status+"</td>" +
                            "<td> <div class='button-group'><a type='button' class='button border-main' href='javascript:void(0)' onclick='send(1,"+xxxData+")'><span class='icon-edit'></span>派送</a>" +
                            "<a class='button border-red' href='javascript:void(0)' onclick='send(2,"+xxxData+")'><span class='icon-trash-o'></span> 取消</a>" +
                            "</div></td></tr>");
                    });

                    var index = Math.ceil(data[0].pages / 5);//页数，一页5条记录(取整)
                    maxIndex = index;
                    container.append("<tr><td colspan='8'><div id='pageNum' class='pagelist'>  <a onclick='removePage(this)'>上一页</a><a onclick='addPage(this)'>下一页</a><a onclick='getOrders(maxIndex)'>尾页</a> </div></td></tr>");
                    for(var i=0;i<index;i++){
                        var j = i+1;
                        $("#pageNum").append("<span onclick='getOrders("+ j +")' class='page"+j+"'>"+ j +"</span>");
                    }
                    $("#pageNum").find('.page'+page).addClass('current');

//                    if(data[0].isRemind == 1){//是否提示外卖接单铃声
//                        var a=document.getElementById("audio");
//                        if(a.paused){
//                            a.play();
//                        }else{
//                            a.pause();
//                        }
//                    }

                },
                error: function(data){
                    alert("error:" + data.responseText);
                }
            });
        }

        $(function () {
            getOrders(1);
        });

        //接单
        function send(num,e){
            var status = e.status;
            if(status == "已派送"){
                layer.msg("此订单已派送中...");
                return;
            }
            if(status == "已取消"){
                layer.msg("此订单已取消中...");
                return;
            }
            var type;
            if(num == 1){
                type = "1";
            }else{
                type = "2";
            }
            //更新订单的状态为---已派送
            $.ajax({
                url : "${pageContext.request.contextPath}/order/updateOrderStatusByShop.do",
                data : {
                    orderId : e.id,
                    type : type
                },
                dataType : "json",
                method : "POST",
                success : function(data){
                    window.location.reload();
                },
                error : function(data){
                    alert("error:" + data.responseText);
                }
            });
        }
    </script>
</head>
<body>
<audio id="audio" src="../music/lingsheng.mp3"></audio>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 订单列表</strong></div>
    <div class="padding border-bottom">
        <%--<a class="button border-yellow" href=""><span class="icon-plus-square-o"></span> 添加内容</a>--%>
    </div>
    <table class="table table-hover text-center" id="tableData">
        <tr>
            <th width="15%">订单ID</th>
            <th width="10%">菜名</th>
            <th width="10%">样图</th>
            <th width="10%">数量</th>
            <th width="10%">备注</th>
            <th width="10%">用户名</th>
            <th>地址</th>
            <th width="250">操作</th>
        </tr>

        <%--<tr>--%>
            <%--<td>17</td>--%>
            <%--<td>公司简介</td>--%>
            <%--<td>1</td>--%>
            <%--<td>--%>
                <%--<div class="button-group">--%>
                    <%--<a type="button" class="button border-main" href="#"><span class="icon-edit"></span>修改</a>--%>
                    <%--<a class="button border-red" href="javascript:void(0)" onclick="return del(17)"><span class="icon-trash-o"></span> 删除</a>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td>17</td>--%>
            <%--<td>产品中心</td>--%>
            <%--<td>1</td>--%>
            <%--<td>--%>
                <%--<div class="button-group">--%>
                    <%--<a type="button" class="button border-main" href="#"><span class="icon-edit"></span>修改</a>--%>
                    <%--<a class="button border-red" href="javascript:void(0)" onclick="return del(17)"><span class="icon-trash-o"></span> 删除</a>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td>17</td>--%>
            <%--<td>新闻资讯</td>--%>
            <%--<td>1</td>--%>
            <%--<td>--%>
                <%--<div class="button-group">--%>
                    <%--<a type="button" class="button border-main" href="#"><span class="icon-edit"></span>修改</a>--%>
                    <%--<a class="button border-red" href="javascript:void(0)" onclick="return del(17)"><span class="icon-trash-o"></span> 删除</a>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td>17</td>--%>
            <%--<td>人才招聘</td>--%>
            <%--<td>1</td>--%>
            <%--<td>--%>
                <%--<div class="button-group">--%>
                    <%--<a type="button" class="button border-main" href="#"><span class="icon-edit"></span>修改</a>--%>
                    <%--<a class="button border-red" href="javascript:void(0)" onclick="return del(17)"><span class="icon-trash-o"></span> 删除</a>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>

        <%--<tr>--%>
            <%--<td>17</td>--%>
            <%--<td>联系我们</td>--%>
            <%--<td>1</td>--%>
            <%--<td>--%>
                <%--<div class="button-group">--%>
                    <%--<a type="button" class="button border-main" href="#"><span class="icon-edit"></span>修改</a>--%>
                    <%--<a class="button border-red" href="javascript:void(0)" onclick="return del(17)"><span class="icon-trash-o"></span> 删除</a>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>

    </table>
</div>
<%--<script>--%>
    <%--function del(id){--%>
        <%--if(confirm("您确定要删除吗?")){--%>

        <%--}--%>
    <%--}--%>
<%--</script>--%>
<%--<div class="panel admin-panel margin-top">--%>
    <%--<div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加内容</strong></div>--%>
    <%--<div class="body-content">--%>
        <%--<form method="post" class="form-x" action="">--%>
            <%--<input type="hidden" name="id"  value="" />--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>栏目名称：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<input type="text" class="input w50" name="title" value="" data-validate="required:请输入标题" />--%>
                    <%--<div class="tips"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>栏目图片：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<input type="text" id="url1" name="banner" class="input tips" style="width:25%; float:left;"  value="" data-toggle="hover" data-place="right" data-image="" />--%>
                    <%--<input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传"  style="float:left;">--%>
                    <%--<div class="tipss">图片尺寸：1920*200</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>英文标题：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<input type="text" class="input w50" name="entitle" value="" />--%>
                    <%--<div class="tips"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>关键字标题：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<input type="text" class="input" name="s_title" value="" />--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>栏目关键字：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<input type="text" class="input" name="s_keywords" value=""/>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>关键字描述：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<textarea type="text" class="input" name="s_desc" style="height:100px;" ></textarea>--%>
                <%--</div>--%>
            <%--</div>--%>

            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>显示：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<div class="button-group radio">--%>

                        <%--<label class="button active">--%>
                            <%--<span class="icon icon-check"></span>--%>
                            <%--<input name="isshow" value="1" type="radio" checked="checked">是--%>
                        <%--</label>--%>

                        <%--<label class="button active"><span class="icon icon-times"></span>--%>
                            <%--<input name="isshow" value="0"  type="radio" checked="checked">否--%>
                        <%--</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>排序：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<input type="text" class="input w50" name="sort" value="0"  data-validate="required:,number:排序必须为数字" />--%>
                    <%--<div class="tips"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label></label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <%--<button class="button bg-main icon-check-square-o" type="submit"> 提交</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</div>--%>
<%--</div>--%>
</body></html>
