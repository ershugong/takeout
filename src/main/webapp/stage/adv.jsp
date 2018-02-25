<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/4
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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

        function del(data){
            if(confirm("您确定要删除吗?")){
                $.ajax({
                    url:"${pageContext.request.contextPath}/menu/delMenu.do",
                    type : "post",
                    data : {id : data.id},
                    dataType : "text",
                    success : function (data) {
                        window.location.reload();
                    },
                    error : function (data) {
                        alert(data);
                    }
                });
            }
        }

        //点击修改的按钮------>把此行的信息添加到待提交的信息当中
        function updateSubmit(event) {
            $("#name").val(event.name);//设置菜名
            $("#price").val(event.price);
            $("#ext").val(event.ext);
            $("#id").val(event.id);
            $("#type").val(event.type);
            /*if(event.type == 1){
                $("#dessert").attr("checked","checked");
            }else if(event.type == "2"){
                $("#rice").attr("checked","checked");
            }else if(event.type == 3){
                $("#congee").attr("checked","checked");
            }else{
                $("#noodle").attr("checked","checked");
            }*/
            if(event.status == 1){
                $("#isTrue").attr("checked","checked");
            }else {
                $("#isFlase").attr("checked", "checked");
            }
        //菜色照片
        $("#img").attr("src","../"+event.headPic);
    }

    $(function () {
        var shopId = "${sessionScope.user.shopId}";
            $.ajax({
                url : "${pageContext.request.contextPath}/menu/getAllMenusByShop.do",
                type : "post",
                data : {shopId : shopId},
                dataType : "json",
                success : function(data) {
                    $.each(data,function (index) {
                        var xxxData = JSON.stringify(data[index]);
                        var text = "<tr>" +
                            "<td>"+(index+1)+"</td>" +
                            "<td><img src='"+"../"+data[index].headPic+"' alt='' width='120' height=\"50\" /></td>" +
                            "<td>"+data[index].name+"</td>" +
                            "<td>"+data[index].ext+"</td>" +
                            "<td>"+data[index].type+"</td>" +
                            "<td>"+data[index].price+"</td>" +
                            "<td><div class='button-group'>" +
                            "<a class='button border-main' href='javascript:void(0)' onclick='updateSubmit("+xxxData+");'><span class='icon-edit'></span> 修改</a>" +
                            "<a class='button border-red' href='javascript:void(0)' onclick='return del("+xxxData+")'><span class='icon-trash-o'></span> 删除</a>" +
                            "</div></td>" +
                            "</tr>";

                        $("#tableData").append(text);
                    });
                },
                error : function(data) {
                    alert("error:" + data.responseText);
                }
            });


            //连带点击
            $("#image1").click(function () {
               $("#file").click();
            });


            //点击提交按钮
            $("#submit").click(function () {
                var file = $("#file").val();
                if(file == ""){//当头像没有修改，file为空
                    $("#formData").ajaxSubmit({
                        url : "${pageContext.request.contextPath}/menu/updateMenuNotFile.do",
                        type : "post",
                        dataType : "json",
                        success : function(data){
                            window.location.reload();
                        },
                        error : function(data){
                            alert("error:" + data.responseText)
                        }
                    });
                }else{
                    $("#formData").ajaxSubmit({
                        url : "${pageContext.request.contextPath}/menu/updateMenu.do",
                        type : "post",
                        dataType : "json",
                        success : function(data) {
                            window.location.reload();//刷新页面
                        },
                        error : function(data) {
                            alert("error:" + data.responseText);
                        }
                    });
                }
            });
        });

        //改变图片
        var fileChange = function(event) {
            var files = event.target.files, file;
            if (files && files.length > 0) {
                // 获取目前上传的文件
                file = files[0];// 文件大小校验的动作
                if (file.size > 1024 * 1024 * 2) {
                    alert('图片大小不能超过 2MB!');
                    return false;
                }
                // 获取 window 的 URL 工具
                var URL = window.URL || window.webkitURL;
                // 通过 file 生成目标 url
                var imgURL = URL.createObjectURL(file);
                //用attr将img的src属性改成获得的url
                $("#img").attr("src", imgURL);
                // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
                // URL.revokeObjectURL(imgURL);
            }
        }

    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow" onclick="window.location.href='#add'"><span class="icon-plus-square-o"></span> 添加内容</button>
    </div>
    <table class="table table-hover text-center" id="tableData">
        <tr>
            <th width="10%">ID</th>
            <th width="15%">图片</th>
            <th width="10%">名称</th>
            <th width="20%">描述</th>
            <th width="10%">类型</th>
            <th width="10%">价格</th>
            <th width="15%">操作</th>
        </tr>

        <!--<tr>
            <td>1</td>
            <td><img src="../images/platform/11.jpg" alt="" width="120" height="50" /></td>
            <td>首页焦点图</td>
            <td>描述文字....</td>
            <td>1</td>
            <td>1</td>
            <td><div class="button-group">
                <a class="button border-main" href="#add"><span class="icon-edit"></span> 修改</a>
                <a class="button border-red" href="javascript:void(0)" onclick="return del(1,1)"><span class="icon-trash-o"></span> 删除</a>
            </div></td>
        </tr>
        <tr>
            <td>2</td>
            <td><img src="../images/platform/11.jpg" alt="" width="120" height="50" /></td>
            <td>首页焦点图</td>
            <td>描述文字....</td>
            <td>1</td>
            <td>1</td>
            <td><div class="button-group">
                <a class="button border-main" href="#add"><span class="icon-edit"></span> 修改</a>
                <a class="button border-red" href="javascript:void(0)" onclick="return del(1,1)"><span class="icon-trash-o"></span> 删除</a>
            </div></td>
        </tr>
        <tr>
            <td>3</td>
            <td><img src="../images/platform/11.jpg" alt="" width="120" height="50" /></td>
            <td>首页焦点图</td>
            <td>描述文字....</td>
            <td>1</td>
            <td>1</td>
            <td><div class="button-group">
                <a class="button border-main" href="#add"><span class="icon-edit"></span> 修改</a>
                <a class="button border-red" href="javascript:void(0)" onclick="return del(1,1)"><span class="icon-trash-o"></span> 删除</a>
            </div></td>
        </tr>-->

    </table>
</div>

<div class="panel admin-panel margin-top" id="add">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 增加菜色</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="" id="formData">
            <div class="form-group">
                <div class="label">
                    <label>菜名：</label>
                </div>
                <div class="field">
                    <input type="text" id="id" name="id" value="" style="display: none">
                    <input type="text" class="input w50" id="name" value="" name="name" data-validate="required:请输入菜名" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>价格：</label>
                </div>
                <div class="field">
                    <input type="number" class="input w50" id="price" name="price" value=""  style="width: 160px;height: 10px;margin-top: 5px;"/>
                    <div class="label">
                        <label style="margin-left: 5px">.00元</label>
                    </div>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>参照图：</label>
                </div>
                <div class="field">
                    <input type="file" id="file" name="file" style="display: none" onchange="fileChange(event);">
                    <img src="../upload/x.png" id="img" style="width: 80px;height: 60px">
                    <%--<input type="text" id="url1" name="img" class="input tips" style="width:25%; float:left;"  value="" data-toggle="hover" data-place="right" data-image="" />--%>
                    <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传"  style="float:left;">
                    <div class="tipss">图片尺寸：1920*500</div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>描述：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" id="ext" name="ext" style="height:120px;" value=""></textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <!--<div class="form-group">
                <div class="label">
                    <label>类型：</label>
                </div>
                <div class="ffield">
                    <%--<input type="text" class="input w50" name="sort" value="0"  data-validate="required:,number:排序必须为数字" />--%>
                    小吃:<input type="radio" id="dessert" name="type" value="1">
                    饭类:<input type="radio" id="rice" name="type" value="2">
                    粥类:<input type="radio" id="congee" name="type" value="3">
                    面/粉类:<input type="radio" id="noodle" name="type" value="4">
                    <div class="tips"></div>
                </div>
            </div>-->
            <div class="form-group">
                <div class="label">
                    <label>上架：</label>
                </div>
                <div class="ffield">
                    <%--<input type="text" class="input w50" name="sort" value="0"  data-validate="required:,number:排序必须为数字" />--%>
                    是:<input type="radio" id="isTrue" name="status" value="1">
                    否:<input type="radio" id="isFlase" name="status" value="0">
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>类型：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="type" value="" name="type" data-validate="required:请输入菜色类型" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="button" id="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body></html>
