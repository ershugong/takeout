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
        var index = 1;
        var addPage = function(event){
            index = index + 1;
            getComments(index,event);
        }

        var removePage = function(event){
            index = index - 1;
            getComments(index,event);
        }

        var getComments = function(page,cc){

            $.ajax({
                url: "${pageContext.request.contextPath}/comment/getCommentListForPC.do",
                method: "post",
                async:true,
                dataType: "json",
                data: {
                    shopId: shopId,
                    page : page
                },
                success:function(data){
                    var container = $("#container");
                    container.html("");
                    container.append("            <tr>\n" +
                        "                <th width=\"120\">菜图</th>\n" +
                        "                <th>菜名</th>\n" +
                        "                <th>用户名</th>\n" +
                        "                <th width=\"25%\">评价</th>\n" +
                        "                <th>态度</th>\n" +
                        "                <th width=\"120\">评价时间</th>\n" +
                        "            </tr>");
                    $.each(data,function(i){
                        var srcUrl = '../'+data[i].menuHeadPic;
                        container.append("<tr><td><img style='height: 50px;width: 80px' src = '"+srcUrl+"'/></td><td>"+data[i].menuName+"</td><td>"+data[i].userName+"</td><td>"+data[i].content+"</td><td>"+data[i].commentType+"</td><td>"+data[i].createTime+"</td></tr>");
                    })

                    var index = data[0].num / 5;//页数，一页5条记录
                    container.append("<tr><td colspan='8'><div id='pageNum' class='pagelist'>  <a onclick='removePage(this)'>上一页</a><a onclick='addPage(this)'>下一页</a><a onclick='getComments(index,this)'>尾页</a> <span id='one' class='' onclick='getComments(1,this)'>1</span> </div></td></tr>");
                    for(var i=1;i<index;i++){
                        var j = i+1;

                        $("#pageNum").append("<span onclick='getComments("+ j +",this)' >"+ j +"</span>");

                    }


                },
                error: function(data){
                    alert("error:" + data.responseText);
                }
            })
            if(page != 1){//选定转换
                $(cc).addClass('current');
                /*$("#one").css("class","");
                cc.className = "current";
                cc.setAttribute("class","current");*/
            }else{
                $(cc).addClass('current');
            }
                console.log(cc)

        }



        getComments(1,this);

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
            <%--</tr> <a href="">2</a><a href="">3</a>--%>
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
