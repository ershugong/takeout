<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/4
  Time: 9:19
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
    <script src="../js/jquery-form.js"></script>
    <script src="../js/pintuer.js"></script>
    <script src="../js/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            //初始化八种类型
            var isFood = ${sessionScope.shop.isFood};
            if(isFood == 1){//是
                $("#Food").attr("checked","checked");
            }else{//否
                $("#notFood").attr("checked","checked");
            }
            var isMarket = ${sessionScope.shop.isMarket};
            if(isMarket == 1){//是
                $("#Market").attr("checked","checked");
            }else{//否
                $("#notMarket").attr("checked","checked");
            }
            var isFruit = ${sessionScope.shop.isFruit};
            if(isFruit == 1){//是
                $("#Fruit").attr("checked","checked");
            }else{//否
                $("#notFruit").attr("checked","checked");
            }
            var isDessert = ${sessionScope.shop.isDessert};
            if(isDessert == 1){//是
                $("#Dessert").attr("checked","checked");
            }else{//否
                $("#notDessert").attr("checked","checked");
            }
            var isMajorSend = ${sessionScope.shop.isMajorSend};
            if(isMajorSend == 1){//是
                $("#MajorSend").attr("checked","checked");
            }else{//否
                $("#notMajorSend").attr("checked","checked");
            }
            var isSupper = ${sessionScope.shop.isSupper};
            if(isSupper == 1){//是
                $("#Supper").attr("checked","checked");
            }else{//否
                $("#notSupper").attr("checked","checked");
            }
            var isSnack = ${sessionScope.shop.isSnack};
            if(isSnack == 1){//是
                $("#Snack").attr("checked","checked");
            }else{//否
                $("#notSnack").attr("checked","checked");
            }

            $("#image1").click(function () {
                $("#file").click();
            })

            //点击提交
            $("#submit").click(function () {
                var file = $("#file").val();
                if(file == ""){//当logo没有修改，file为空
                    $("#formData").ajaxSubmit({
                        url : "${pageContext.request.contextPath}/shop/updateShopNotFile.do",
                        type : "post",
                        dataType : "json",
                        success : function(data){
                            alert("设置成功！");
                        }
//                        error : function(data){
//                            alert("error:" + data.responseText);
//                        }
                    });
                }else{
                    $("#formData").ajaxSubmit({
                        url : "${pageContext.request.contextPath}/shop/updateShop.do",
                        type : "post",
                        dataType : "json",
                        success : function(data) {
                            alert("设置成功！");
                        },
                        error : function(data) {
                            alert("error:" + data.responseText);
                        }
                    });
                }
            });

            //点击活动的按钮
            $("#activity").click(function(){
                //页面层
                layer.open({
                    title:"活动设置",
                    type: 2,
                    skin: 'layui-layer-molv', //加上边框
                    area: ['420px', '240px'], //宽高
                    content: 'activity.html'
                });
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
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 店铺资料</strong></div>
    <div class="body-content">
        <form method="post" id="formData" class="form-x" name="shop" enctype="multipart/form-data">
            <div class="form-group">
                <div class="label">
                    <label>店名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="shopName" value="${sessionScope.shop.shopName}" />
                    <input type="text" class="input" name="id" style="display: none" value="${sessionScope.shop.id}" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>Logo：</label>
                </div>
                <div class="field">
                    <%--<input type="text" id="url1" name="img" class="input tips" style="width:25%; float:left;"  value="${sessionScope.shop.logo}"  data-toggle="hover" data-place="right" data-image="" />--%>
                    <!--img便签不能带中文-->
                    <img id="img" src="../${sessionScope.shop.logo}" style="width: 260px;height: 200px;">
                    <input type="text" style="display: none" name="logo" value="${sessionScope.shop.logo}">
                    <input type="file" id="file" name = "file" style="display: none" onchange="fileChange(event);">
                    <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传"  style="float:left;">
                    <div class="tipss">图片尺寸：260*200</div>
                </div>
            </div>
            <!---->
            <div class="form-group">
                <div class="label">
                    <label>美食类：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    是&nbsp;&nbsp;&nbsp;<input type="radio" name="isFood" id="Food"  value="1"/>
                    否&nbsp;&nbsp;&nbsp;<input type="radio" name="isFood" id="notFood" value="0"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>超市类：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    是&nbsp;&nbsp;&nbsp;<input type="radio" name="isMarket" id="Market"  value="1"/>
                    否&nbsp;&nbsp;&nbsp;<input type="radio" name="isMarket" id="notMarket" value="0"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>果蔬类：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    是&nbsp;&nbsp;&nbsp;<input type="radio" name="isFruit" id="Fruit"  value="1"/>
                    否&nbsp;&nbsp;&nbsp;<input type="radio" name="isFruit" id="notFruit" value="0"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>甜品：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    是&nbsp;&nbsp;&nbsp;<input type="radio" name="isDessert" id="Dessert"  value="1"/>
                    否&nbsp;&nbsp;&nbsp;<input type="radio" name="isDessert" id="notDessert" value="0"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>平台专送类：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    是&nbsp;&nbsp;&nbsp;<input type="radio" name="isMajorSend" id="MajorSend"  value="1"/>
                    否&nbsp;&nbsp;&nbsp;<input type="radio" name="isMajorSend" id="notMajorSend" value="0"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>正餐类：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    是&nbsp;&nbsp;&nbsp;<input type="radio" name="isSupper" id="Supper"  value="1"/>
                    否&nbsp;&nbsp;&nbsp;<input type="radio" name="isSupper" id="notSupper" value="0"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>小吃类：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    是&nbsp;&nbsp;&nbsp;<input type="radio" name="isSnack" id="Snack"  value="1"/>
                    否&nbsp;&nbsp;&nbsp;<input type="radio" name="isSnack" id="notSnack" value="0"/>
                </div>
            </div>
            <!---->
            <div class="form-group">
                <div class="label">
                    <label>起送价：</label>
                </div>
                <div class="field">
                    <input type="number" style="height: 40px;" class="input" name="lowSend" value="${sessionScope.shop.lowSend}" />
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>配送价：</label>
                </div>
                <div class="field">
                    <input type="number" style="height: 40px;" class="input" name="sendPrice" value="${sessionScope.shop.sendPrice}" />
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>商店地址：</label>
                </div>
                <div class="field">
                    <%--<textarea name="content"></textarea>--%>
                    <input type="text" style="height: 40px;" class="input" name="address" value="${sessionScope.shop.address}" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button id="submit" class="button bg-main icon-check-square-o" type="button"> 提交</button>
                    <button id="activity" class="button bg-main icon-check-square-o" style="margin-left: 30px;background-color:#F7CC4F;border-color:#F7CC4F;" type="button"> 活动</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body></html>